package bt.bitsmartmini.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import bt.bitsmartmini.bl.InsertUpdateBL;
import bt.bitsmartmini.bl.ItemsBL;
import bt.bitsmartmini.bl.ReturnBL;
import bt.bitsmartmini.bl.SalesBL;
import bt.bitsmartmini.bl.SalesDetailsBL;
import bt.bitsmartmini.bl.StockinBL;
import bt.bitsmartmini.bl.StockoutBL;
import bt.bitsmartmini.entity.Items;
import bt.bitsmartmini.entity.RtdItem;
import bt.bitsmartmini.entity.SalesDetails;
import bt.bitsmartmini.entity.Stockin;
import bt.bitsmartmini.entity.Stockout;
//import bt.bitsmartmini.entity.UomDef;
import bt.bitsmartmini.entity.Users;
import bt.bitsmartmini.tablemodel.ReturnTableModel;
import bt.bitsmartmini.tablemodel.SalesDetailsTableModel;
import bt.bitsmartmini.tablemodel.StockTableModel;
import bt.bitsmartmini.tablemodel.StockinTableModel;
import bt.bitsmartmini.tablemodel.StockoutTableModel;
import bt.bitsmartmini.utils.Utilities;
import lxe.utility.math.DecimalUtil;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class StockController implements Initializable {

    ObservableList<StockTableModel> data;
    ObservableList<StockinTableModel> stkindata;
    ObservableList<StockoutTableModel> stkoutdata;
    ObservableList<ReturnTableModel> returndata;
    ObservableList<SalesDetailsTableModel> salesdata;

    @FXML
    private TableView<StockTableModel> stock;
    @FXML
    private TableColumn<StockTableModel, String> stkitem;
    @FXML
    private TableColumn<StockTableModel, Number> stkinqty;
    @FXML
    private TableColumn<StockTableModel, Number> stkoutqty;
    @FXML
    private TableColumn<StockTableModel, Number> returnsqty;
    @FXML
    private TableColumn<StockTableModel, Number> salesqty;
    @FXML
    private TableColumn<StockTableModel, Number> stkbal;
    @FXML
    private TableColumn<StockTableModel, String> cstprice;
    @FXML
    private TableColumn<StockTableModel, String> salesprice;
    @FXML
    private TableView<StockinTableModel> stockin;
//    @FXML
//    private TableColumn<StockinTableModel, String> stkinbatchno;
    @FXML
    private TableColumn<StockinTableModel, String> stkinitems;
    @FXML
    private TableColumn<StockinTableModel, Number> stkqty;
    @FXML
    private TableColumn<StockinTableModel, String> stkindate;
    @FXML
    private TableColumn<StockinTableModel, String> expirydate;
    @FXML
    private TableColumn<StockinTableModel, Boolean> stkaction;
    @FXML
    private TableView<StockoutTableModel> stockout;
//    @FXML
//    private TableColumn<StockoutTableModel, String> stkoutbatchno;
    @FXML
    private TableColumn<StockoutTableModel, String> stkoutitems;
    @FXML
    private TableColumn<StockoutTableModel, Number> stkoutqtytb;
    @FXML
    private TableColumn<StockoutTableModel, String> stkoutdate;
    @FXML
    private TableColumn<StockoutTableModel, Boolean> stkoutaction;
    @FXML
    private JFXButton stockinbtn;
    @FXML
    private JFXButton stockoutbtn;
    @FXML
    private JFXTextField stocksearch;
    private JFXTextField stockinsearch;

    static StockTableModel list;

    ItemsBL itembl;
    SalesBL salesbl;
    StockinBL stkinbl;
    StockoutBL stkobl;
    ReturnBL retbl;
    @FXML
    private TableColumn<StockTableModel, String> exp;
    @FXML
    private TableView<ReturnTableModel> returnstable;
    @FXML
    private TableColumn<ReturnTableModel, String> returnitemstb;
    @FXML
    private TableColumn<ReturnTableModel, Number> returnqtytb;
    @FXML
    private TableColumn<ReturnTableModel, String> returndatetb;
    @FXML
    private TableColumn<ReturnTableModel, Boolean> returnaction;
//    private JFXTextField stockoutsearch;
//    private JFXTextField returnsearch;
    @FXML
    private TableView<SalesDetailsTableModel> salestable;
    @FXML
    private TableColumn<SalesDetailsTableModel, String> salesitemstb;
    @FXML
    private TableColumn<SalesDetailsTableModel, Number> salesqyttb;
    @FXML
    private TableColumn<SalesDetailsTableModel, String> salesdatetb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        itembl = new ItemsBL();
        salesbl = new SalesBL();
        stkinbl = new StockinBL();
        stkobl = new StockoutBL();
        retbl = new ReturnBL();

        AllStockTableData("");
        stocksearch.textProperty().addListener(e -> {
            AllStockTableData(stocksearch.getText());
        });

        stock.addEventHandler(MouseEvent.MOUSE_CLICKED, v -> {
            list = stock.getSelectionModel().getSelectedItem();
            StockinTableData(list.getItemCode());
            StockoutTableData(list.getItemCode());
            ReturnsTableData(list.getItemCode());
            SalesTableData(list.getItemCode());
        });

        if (LoginController.u.getRoles().equals("Administrator") || LoginController.u.getRoles().equals("Supervisor")) {
            stockinbtn.setDisable(false);
            stock.addEventHandler(MouseEvent.MOUSE_CLICKED, v -> {
                stockoutbtn.setDisable(false);
            });

        } else {
            stockinbtn.setDisable(true);
            stockoutbtn.setDisable(true);
        }
    }

    public void AllStockTableData(String p) {
        List<Items> stk;
        if (p!= null && p.length() > 0) {
            stk = itembl.searchAllItems(p);
        } else {
            stk = new ItemsBL().getItemsPerPage(10);
        }

        data = FXCollections.observableArrayList();
        stk.forEach(e -> {
            long salesqty;
            long stockinqty;
            long stockoutqty;
            long returnqty;
            try {
                salesqty = salesbl.getSalesTotal(e.getItemDesc());
            } catch (Exception ex) {
                salesqty = 0;
            }
            try {
                stockinqty = stkinbl.getStockInTotal(e.getItemDesc());
            } catch (Exception ex) {
                stockinqty = 0;
            }
            try {
                stockoutqty = stkobl.getTotalStockoutbyItemDesc(e.getItemDesc());
            } catch (NullPointerException ex) {
                stockoutqty = 0;
            }
            try {
                returnqty = retbl.getTotalReturnsbyItemDesc(e.getItemDesc());
            } catch (NullPointerException ex) {
                returnqty = 0;
            }
            long balance = new StockinBL().getStockBalance(e.getItemDesc());
            double profit = e.getSp() - e.getCp();
            double expprofit = profit * balance;

            data.add(new StockTableModel(e.getUpc(), e.getItemDesc(), stockinqty, stockoutqty, returnqty, salesqty, balance, DecimalUtil.format2(e.getCp()), DecimalUtil.format2(e.getSp()), DecimalUtil.format2(expprofit)));
        });
        Callback<TableColumn<StockTableModel, String>, TableCell<StockTableModel, String>> cellFactory = (final TableColumn<StockTableModel, String> param) -> {
            final TableCell<StockTableModel, String> cell = new TableCell<StockTableModel, String>() {

                @Override
                public void updateItem(String item, boolean empty) {
                    StockinBL sk = new StockinBL();
                    ItemsBL ib = new ItemsBL();
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        HBox hb = new HBox();
                        Button btn = new Button();
                        FontAwesomeIcon fa = new FontAwesomeIcon();
                        Label lab = new Label();
                        StockTableModel person = getTableView().getItems().get(getIndex());
                        this.getStyleClass().add("table-row-cell");
                        hb.setAlignment(Pos.CENTER);
                        fa.setGlyphName("INFO_CIRCLE");
                        btn.getStyleClass().add("closeform");
                        btn.setGraphic(fa);
                        lab.setText(person.getItems());
                        hb.setAlignment(Pos.CENTER_LEFT);
                        hb.getChildren().addAll(lab, btn);
                        btn.setOnAction(event -> {
                            try {
                                Stage stage = new Stage();
                                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ItemInfo.fxml"));
                                Parent parent = (Parent) fxmlLoader.load();
                                ItemInfoController childController = fxmlLoader.getController();
                                long balance = sk.getStockBalance(person.getItems());
                                if (balance == 0) {
                                    childController.outofstockshape.setVisible(true);
                                } else {
                                    childController.outofstockshape.setVisible(false);
                                }
                                Items i = ib.getImageItembyCode(person.getItems());
                                childController.iteminfoname.setText(person.getItems());
                                childController.itemqty.setText(balance + " " + " In Stock");
                                childController.itemccost.setText(MainAppController.B.getBCurrency() + " " + DecimalUtil.format2(i.getSp()));
                                Items its = new ItemsBL().getImageItembyCode(person.getItems());
                                FileInputStream input;
                                input = new FileInputStream(its.getItemImg());
                                Image image = new Image(input);
                                childController.itemimagename.setImage(image);
                                Scene scene = new Scene(parent);
                                scene.setFill(Color.TRANSPARENT);
                                stage.setMaximized(true);
                                stage.initOwner(parent.getScene().getWindow());
                                stage.setScene(scene);
                                stage.initStyle(StageStyle.TRANSPARENT);
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        });
                        setGraphic(hb);
                    }
                }
            };

            return cell;
        };

        stkitem.setCellFactory(cellFactory);
        stkinqty.setCellValueFactory(cell -> cell.getValue().getStockinQtyProperty());
        stkinqty.getStyleClass().add("align_table_center");
        stkoutqty.setCellValueFactory(cell -> cell.getValue().getStockoutQtyProperty());
        stkoutqty.getStyleClass().add("align_table_center");
        returnsqty.setCellValueFactory(cell -> cell.getValue().getReturnQtyProperty());
        returnsqty.getStyleClass().add("align_table_center");
        salesqty.setCellValueFactory(cell -> cell.getValue().getSalesQtyProperty());
        salesqty.getStyleClass().add("align_table_center");
        stkbal.setCellValueFactory(cell -> cell.getValue().getStockbalProperty());
        stkbal.getStyleClass().add("align_table_center");
        cstprice.setCellValueFactory(cell -> cell.getValue().getStockCostPriceProperty());
        cstprice.getStyleClass().add("align_table_right");
        salesprice.setCellValueFactory(cell -> cell.getValue().getStockSalesPriceProperty());
        salesprice.getStyleClass().add("align_table_right");
        exp.setCellValueFactory(cell -> cell.getValue().getExprofitProperty());
        exp.getStyleClass().add("align_table_right");

        stock.setItems(data);
//        clientTable.getColumns().add(action);
        stock.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    public void StockinTableData(String itemDesc) {
        List<Stockin> v;
        if (itemDesc.length() > 0) {
            v = new StockinBL().searchAllStockin(itemDesc);
        } else {
            v = new StockinBL().getAllStockinBarcode(itemDesc, 10);
        }

        stkindata = FXCollections.observableArrayList();
        v.forEach((stockin) -> {
            stkindata.add(new StockinTableModel(stockin.getStockinId(), stockin.getUpc().getUpc(), stockin.getUpc().getItemDesc(), stockin.getQuantity(), Utilities.convertDateToString(stockin.getStockinDate()), Utilities.convertDateToString(stockin.getExpiryDate()), Utilities.roundToTwoDecimalPlace(stockin.getUpc().getCp(), 2), Utilities.roundToTwoDecimalPlace(stockin.getUpc().getSp(), 2)));
        });
        stkinitems.setCellValueFactory(cell -> cell.getValue().getItemProperty());
        stkqty.setCellValueFactory(cell -> cell.getValue().getQuantityProperty());
        stkindate.setCellValueFactory(cell -> cell.getValue().getStockinDateProperty());
        expirydate.setCellValueFactory(cell -> cell.getValue().getExpiryDateProperty());
        stkaction.setSortable(false);
        stockin.setItems(stkindata);
        stockin.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        stkaction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StockinTableModel, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<StockinTableModel, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });
        stkaction.setCellFactory(new Callback<TableColumn<StockinTableModel, Boolean>, TableCell<StockinTableModel, Boolean>>() {
            @Override
            public TableCell<StockinTableModel, Boolean> call(TableColumn<StockinTableModel, Boolean> personBooleanTableColumn) {
                return new AddPersonCell();
            }
        });

    }

    public void StockoutTableData(String itemsDesc) {
        List<Stockout> v;
        if (itemsDesc.length() > 0) {
            v = stkobl.searchAllStockout(itemsDesc);
        } else {
            v = stkobl.getAllStockoutbyBarcode(itemsDesc, 10);
        }
        stkoutdata = FXCollections.observableArrayList();
        v.forEach((out) -> {
            List<Stockin> batchno = new StockinBL().getItemStockinByBarcode(out.getUpc().getUpc());
            stkoutdata.add(new StockoutTableModel(out.getStockoutId(), out.getUpc().getItemDesc(), out.getQuantity(), out.getRemarks(), Utilities.convertDateToString(out.getStkDate())));
        });
        stkoutitems.setCellValueFactory(cell -> cell.getValue().getItemProperty());
        stkoutqtytb.setCellValueFactory(cell -> cell.getValue().getQuantityProperty());
        stkoutdate.setCellValueFactory(cell -> cell.getValue().getDateProperty());
        stkoutaction.setSortable(false);
        stockout.setItems(stkoutdata);
        stockout.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        stkoutaction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StockoutTableModel, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<StockoutTableModel, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });
        stkoutaction.setCellFactory(new Callback<TableColumn<StockoutTableModel, Boolean>, TableCell<StockoutTableModel, Boolean>>() {
            @Override
            public TableCell<StockoutTableModel, Boolean> call(TableColumn<StockoutTableModel, Boolean> personBooleanTableColumn) {
                return new AddPersonCellStockout();
            }
        });

    }

    public void ReturnsTableData(String itemsDesc) {
        List<RtdItem> v;
        if (itemsDesc.length() > 0) {
            v = retbl.searchAllReturnItems(itemsDesc);
        } else {
            v = retbl.getAllRtdItembyBarcode(itemsDesc, 10);
        }
        returndata = FXCollections.observableArrayList();
        v.forEach((out) -> {
            double totalA = (out.getRtdQty() * out.getSalesDetails().getSalesPrice());
            returndata.add(new ReturnTableModel(out.getSalesDetails().getSalesDetailsId(), out.getSalesDetails().getUpc().getItemDesc(), out.getRtdQty(), DecimalUtil.format2(out.getSalesDetails().getSalesPrice()), DecimalUtil.format2(totalA), out.getRemarks(), Utilities.convertDateToString(out.getRtdDate())));
        });
        returnitemstb.setCellValueFactory(cell -> cell.getValue().getItemProperty());
        returnqtytb.setCellValueFactory(cell -> cell.getValue().getQuantityProperty());
        returndatetb.setCellValueFactory(cell -> cell.getValue().getDateProperty());
        returnaction.setSortable(false);
        returnstable.setItems(returndata);
        returnstable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        returnaction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ReturnTableModel, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<ReturnTableModel, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });
        returnaction.setCellFactory(new Callback<TableColumn<ReturnTableModel, Boolean>, TableCell<ReturnTableModel, Boolean>>() {
            @Override
            public TableCell<ReturnTableModel, Boolean> call(TableColumn<ReturnTableModel, Boolean> personBooleanTableColumn) {
                return new AddPersonCellReturns();
            }
        });
    }

    public void SalesTableData(String itemsDesc) {
        List<SalesDetails> s = salesbl.getAllSalesDetailsbyBarcode(itemsDesc, 10);
        salesdata = FXCollections.observableArrayList();
        s.forEach((sales) -> {
            salesdata.add(new SalesDetailsTableModel(sales.getUpc().getUpc(), sales.getUpc().getItemDesc(), sales.getQuantity(), DecimalUtil.format2(sales.getSalesPrice()), sales.getRtdItem().getRtdQty(), DecimalUtil.format2(sales.getDiscount()),"0","0", Utilities.convertDateToString(sales.getEntryDate())));
        });
        salesitemstb.setCellValueFactory(cell -> cell.getValue().getItemsnameProperty());
        salesqyttb.setCellValueFactory(cell -> cell.getValue().getQuantityProperty());
        salesdatetb.setCellValueFactory(cell -> cell.getValue().getDateProperty());
        salestable.setItems(salesdata);
        salestable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    private void addstockinpopup() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddStockIn.fxml"));
        Parent parent = (Parent) fxmlLoader.load();
        AddStockInController childController = fxmlLoader.getController();
        childController.save.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            childController.save.setDisable(true);
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    childController.spinner.setVisible(true);
                    childController.check.setVisible(false);
                    updateMessage("PROCESSING PLS WAIT.....");
                    Thread.sleep(1000);
                    return null;
                }
            };
            childController.displayinfo.textProperty().bind(task.messageProperty());
            task.setOnSucceeded(s -> {
                childController.saveTemplate();
                //list = stock.getSelectionModel().getSelectedItem();
                AllStockTableData(childController.itembarcode.getText());
                StockinTableData(childController.itembarcode.getText());
                stocksearch.setText(childController.itemname.getText());
            });
            Thread d = new Thread(task);
            d.setDaemon(true);
            d.start();
        });
        Scene scene = new Scene(parent);
        stage.setMaximized(true);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    private void addstockoutpopup() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddStockOut.fxml"));
        Parent parent = (Parent) fxmlLoader.load();
        AddStockOutController childController = fxmlLoader.getController();
        childController.save.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    childController.spinner.setVisible(true);
                    updateMessage("PROCESSING PLS WAIT.....");
                    Thread.sleep(1000);
                    return null;
                }
            };
            childController.displayinfo.textProperty().bind(task.messageProperty());
            task.setOnSucceeded(s -> {
                childController.saveTemplate();
                //list = stock.getSelectionModel().getSelectedItem();
                AllStockTableData(childController.itembarcode.getText());
                StockoutTableData(childController.itembarcode.getText());
                stocksearch.setText(childController.itemname.getText());
            });
            Thread d = new Thread(task);
            d.setDaemon(true);
            d.start();

        });
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setMaximized(true);
        stage.initOwner(parent.getScene().getWindow());
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);

        stage.show();

    }

    public class AddPersonCell extends TableCell<StockinTableModel, Boolean> {
//        Image img = new Image(getClass().getResourceAsStream("edit.png"));

        Image img2 = new Image(getClass().getResourceAsStream("delete.png"));
        HBox paddedButton = new HBox();
        JFXButton delButton = new JFXButton();
        JFXButton editButton = new JFXButton();
        // records the y pos of the last button press so that the add person dialog can be shown next to the cell.
        final DoubleProperty buttonY = new SimpleDoubleProperty();

        /**
         * AddPersonCell constructor
         *
         * @param stage the stage in which the table is placed.
         * @param table the table to which a new person can be added.
         */
        AddPersonCell() {
            paddedButton.setStyle("-fx-alignment: CENTER;");
//            paddedButton.getChildren().add(editButton);
            paddedButton.getChildren().add(delButton);
            delButton.setGraphic(new ImageView(img2));
//            editButton.setGraphic(new ImageView(img));
            delButton.setRipplerFill(Paint.valueOf("#D8E1DC"));
//            editButton.setRipplerFill(Paint.valueOf("#D8E1DC"));
//            editButton.setOnAction(new EventHandlerImpl());

            delButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override

                public void handle(ActionEvent event) {
                    try {
                        int selectdIndex = getTableRow().getIndex();
                        //Create a new table show details of the selected item
                        StockinTableModel selectedRecord = (StockinTableModel) stockin.getItems().get(selectdIndex);
                        Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Delete.fxml"));
                        Parent parent = (Parent) fxmlLoader.load();
                        DeleteController childController = fxmlLoader.getController();
                        childController.delete.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                            childController.displayinfo.setText("PROCESSING PLS WAIT.....");
                            Task<Void> task = new Task<Void>() {
                                @Override
                                protected Void call() throws Exception {
                                    childController.spinner.setVisible(true);
                                    updateMessage("Processing...");
                                    Thread.sleep(1000);
                                    return null;
                                }
                            };
                            task.setOnSucceeded(f -> {
                                List st = new SalesDetailsBL().getStockinFromSalesDetails(selectedRecord.getItem());
                                if (st.isEmpty()) {
                                    int result = new StockinBL().removeData(selectedRecord.getStockinCode());
                                    switch (result) {
                                        case 1:
                                            childController.displayinfo.setText("SUCCESSFULLY DELETED");
                                            childController.spinner.setVisible(false);
                                            childController.check.setVisible(true);
                                            AllStockTableData(stocksearch.getText());
                                            StockinTableData(selectedRecord.getItem());
                                            stage.close();
                                            break;
                                        default:
                                            childController.displayinfo.setText("NOTICE! AN ERROR OCCURED");
                                            childController.spinner.setVisible(false);
                                            childController.check.setVisible(false);
                                            break;

                                    }
                                } else {
                                    childController.displayinfo.setText("UNABLE TO DELETE RECORD");
                                    childController.spinner.setVisible(false);
                                    childController.check.setVisible(false);
                                }
                            });
                            Thread d = new Thread(task);
                            d.setDaemon(true);
                            d.start();

                        });
                        Scene scene = new Scene(parent);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.initOwner(parent.getScene().getWindow());
                        stage.setScene(scene);
                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.resizableProperty().setValue(false);
                        stage.showAndWait();

                    } catch (IOException ex) {
                        Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }

        /**
         * places an add button in the row only if the row is not empty.
         */
        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(paddedButton);
            } else {
                setGraphic(null);
            }
        }

        private class EventHandlerImpl implements EventHandler<ActionEvent> {

            public EventHandlerImpl() {
            }

            @Override
            public void handle(ActionEvent event) {
                try {
                    int selectedIndex = getTableRow().getIndex();
                    StockinTableModel selectedRecord = (StockinTableModel) stockin.getItems().get(selectedIndex);
                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditSkockIn.fxml"));
                    Parent parent = (Parent) fxmlLoader.load();
                    EditSkockInController childController = fxmlLoader.getController();
                    childController.itemname.setText(selectedRecord.getItem());
                    childController.batchtextfield.setText(selectedRecord.getBatchNo());
                    LocalDate expdate = Utilities.LOCAL_DATE(selectedRecord.getExpiryDate());
                    childController.expirydate.setValue(expdate);
                    childController.costtextfield.setText(String.valueOf(selectedRecord.getCostPrice()));
                    double costtotal = selectedRecord.getCostPrice() * selectedRecord.getQuantity();
                    childController.costpiecestextfield.setText(String.valueOf(costtotal));
                    childController.salestextfield.setText(String.valueOf(selectedRecord.getSalesPrice()));
                    double salestotal = selectedRecord.getSalesPrice() * selectedRecord.getQuantity();
                    childController.salespiecetextfield.setText(String.valueOf(salestotal));
                    childController.qnttextfield.setText(String.valueOf(selectedRecord.getQuantity()));
                    childController.save.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                        Task<Void> task = new Task<Void>() {
                            @Override
                            protected Void call() throws Exception {
                                childController.spinner.setVisible(true);
                                updateMessage("Processing...");
                                Thread.sleep(1000);
                                return null;
                            }
                        };
                        childController.displayinfo.textProperty().bind(task.messageProperty());
                        task.setOnSucceeded(s -> {
                            try {
                                childController.displayinfo.textProperty().unbind();
                                Stockin st = new Stockin();
                                st.setStockinId(selectedRecord.getStockinCode());
                                st.setUpc(new Items(childController.itemname.getText()));
                                st.setExpiryDate(Utilities.convertToDateViaSqlDate(childController.expirydate.getValue()));
                                st.setQuantity(Integer.parseInt(childController.qnttextfield.getText()));
                                Date date = Utilities.convertStringToDate(selectedRecord.getStockinDate());
                                st.setStockinDate(date);
                                st.setUsers(new Users(LoginController.u.getUserid()));
                                st.setEntryLog(new Date());
                                st.setLastModified(new Date());
                                int result = new InsertUpdateBL().updateData(st);
                                switch (result) {
                                    case 1:
                                        childController.displayinfo.setText("SUCCESSFULLY SAVED");
//                                        Utilities.clearAllField(childController.stockpane);
                                        childController.spinner.setVisible(false);
                                        childController.check.setVisible(true);
                                        list = stock.getSelectionModel().getSelectedItem();
                                        AllStockTableData(stocksearch.getText());
                                        stockin.getItems().clear();
                                        stockout.getItems().clear();
                                        stage.close();
                                        break;
                                    default:
                                        childController.displayinfo.setText("NOTICE! AN ERROR OCCURED");
                                        childController.spinner.setVisible(false);
                                        childController.check.setVisible(false);
                                        break;

                                }
                            } catch (ParseException ex) {
                                Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        Thread d = new Thread(task);
                        d.setDaemon(true);
                        d.start();
                    });
                    Scene scene = new Scene(parent);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initOwner(parent.getScene().getWindow());
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.resizableProperty().setValue(false);
                    stage.showAndWait();
                } catch (IOException ex) {
                    Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public class AddPersonCellStockout extends TableCell<StockoutTableModel, Boolean> {

        Image img2 = new Image(getClass().getResourceAsStream("delete.png"));

        // a button for adding a new person.
        JFXButton addButton = new JFXButton();

        // pads and centers the add button in the cell.
        HBox paddedButton = new HBox();
        JFXButton delButton = new JFXButton();
        // records the y pos of the last button press so that the add person dialog can be shown next to the cell.
        final DoubleProperty buttonY = new SimpleDoubleProperty();

        /**
         * AddPersonCell constructor
         *
         * @param stage the stage in which the table is placed.
         * @param table the table to which a new person can be added.
         */
        AddPersonCellStockout() {
            paddedButton.setStyle("-fx-alignment: CENTER;");
            paddedButton.getChildren().add(delButton);
            delButton.setGraphic(new ImageView(img2));
            delButton.setRipplerFill(Paint.valueOf("#D8E1DC"));
            delButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override

                public void handle(ActionEvent event) {
                    try {
                        int selectdIndex = getTableRow().getIndex();
                        //Create a new table show details of the selected item
                        StockoutTableModel selectedRecord = (StockoutTableModel) stockout.getItems().get(selectdIndex);
                        Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Delete.fxml"));
                        Parent parent = (Parent) fxmlLoader.load();
                        DeleteController childController = fxmlLoader.getController();
                        childController.delete.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                            childController.displayinfo.setText("PROCESSING PLS WAIT.....");
                            Task<Void> task = new Task<Void>() {
                                @Override
                                protected Void call() throws Exception {
                                    childController.spinner.setVisible(true);
                                    updateMessage("Processing...");
                                    Thread.sleep(1000);
                                    return null;
                                }
                            };
                            task.setOnSucceeded(f -> {
                                int result = new StockoutBL().removeData(selectedRecord.getStockoutId());
                                switch (result) {
                                    case 1:
                                        childController.displayinfo.setText("SUCCESSFULLY DELETED");
                                        childController.spinner.setVisible(false);
                                        childController.check.setVisible(true);
                                        AllStockTableData(stocksearch.getText());
                                        StockoutTableData(selectedRecord.getItem());
                                        stage.close();
                                        break;
                                    default:
                                        childController.displayinfo.setText("NOTICE! AN ERROR OCCURED");
                                        childController.spinner.setVisible(false);
                                        childController.check.setVisible(false);
                                        break;
                                }
                            });
                            Thread d = new Thread(task);
                            d.setDaemon(true);
                            d.start();

                        });
                        Scene scene = new Scene(parent);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.initOwner(parent.getScene().getWindow());
                        stage.setScene(scene);
                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.resizableProperty().setValue(false);
                        stage.showAndWait();

                    } catch (IOException ex) {
                        Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });

        }

        /**
         * places an add button in the row only if the row is not empty.
         */
        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(paddedButton);
            } else {
                setGraphic(null);
            }
        }
    }

    public class AddPersonCellReturns extends TableCell<ReturnTableModel, Boolean> {

//        Image img = new Image(getClass().getResourceAsStream("edit.png"));
        Image img2 = new Image(getClass().getResourceAsStream("delete.png"));

        // a button for adding a new person.
        JFXButton addButton = new JFXButton();

        // pads and centers the add button in the cell.
        HBox paddedButton = new HBox();
        JFXButton delButton = new JFXButton();
        // records the y pos of the last button press so that the add person dialog can be shown next to the cell.
        final DoubleProperty buttonY = new SimpleDoubleProperty();

        /**
         * AddPersonCell constructor
         *
         * @param stage the stage in which the table is placed.
         * @param table the table to which a new person can be added.
         */
        AddPersonCellReturns() {
            paddedButton.setStyle("-fx-alignment: CENTER;");
            paddedButton.getChildren().add(delButton);
            delButton.setGraphic(new ImageView(img2));
            delButton.setRipplerFill(Paint.valueOf("#D8E1DC"));
            delButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override

                public void handle(ActionEvent event) {
                    try {
                        int selectdIndex = getTableRow().getIndex();
                        //Create a new table show details of the selected item
                        ReturnTableModel selectedRecord = (ReturnTableModel) returnstable.getItems().get(selectdIndex);
                        Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Delete.fxml"));
                        Parent parent = (Parent) fxmlLoader.load();
                        DeleteController childController = fxmlLoader.getController();
                        childController.delete.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                            childController.displayinfo.setText("PROCESSING PLS WAIT.....");
                            Task<Void> task = new Task<Void>() {
                                @Override
                                protected Void call() throws Exception {
                                    childController.spinner.setVisible(true);
                                    updateMessage("Processing...");
                                    Thread.sleep(1000);
                                    return null;
                                }
                            };
                            task.setOnSucceeded(f -> {
                                int result = new ReturnBL().removeData(selectedRecord.getReturnsId());
                                switch (result) {
                                    case 1:
                                        childController.displayinfo.setText("SUCCESSFULLY DELETED");
                                        childController.spinner.setVisible(false);
                                        childController.check.setVisible(true);
                                        AllStockTableData(stocksearch.getText());
                                        ReturnsTableData(selectedRecord.getItem());
                                        stage.close();
                                        break;
                                    default:
                                        childController.displayinfo.setText("NOTICE! AN ERROR OCCURED");
                                        childController.spinner.setVisible(false);
                                        childController.check.setVisible(false);
                                        break;
                                }
                            });
                            Thread d = new Thread(task);
                            d.setDaemon(true);
                            d.start();

                        });
                        Scene scene = new Scene(parent);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.initOwner(parent.getScene().getWindow());
                        stage.setScene(scene);
                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.resizableProperty().setValue(false);
                        stage.showAndWait();

                    } catch (IOException ex) {
                        Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });

        }

        /**
         * places an add button in the row only if the row is not empty.
         */
        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(paddedButton);
            } else {
                setGraphic(null);
            }
        }
    }
}
