package bt.bitsmartmini.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
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
    private TableColumn<StockTableModel, String> barcode;
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
        stocksearch.selectAll();
        Utilities.repeatFocus(stocksearch);
        itembl = new ItemsBL();
        salesbl = new SalesBL();
        stkinbl = new StockinBL();
        stkobl = new StockoutBL();
        retbl = new ReturnBL();
        AllStockTableData("");
        stock.addEventHandler(MouseEvent.MOUSE_CLICKED, v -> {
            try {
                list = stock.getSelectionModel().getSelectedItem();
                StockinTableData(list.getBarcode());
                StockoutTableData(list.getBarcode());
                ReturnsTableData(list.getBarcode());
                SalesTableData(list.getBarcode());
            } catch (Exception ex) {
                StockinTableData(null);
                StockoutTableData(null);
                ReturnsTableData(null);
                SalesTableData(null);
            }
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
        if (p != null && p.length() > 0) {
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
                salesqty = salesbl.getSalesTotal(e.getUpc());
                System.out.println("saletotal: " + salesqty);
            } catch (Exception ex) {
                salesqty = 0;
            }
            try {
                stockinqty = stkinbl.getStockInTotal(e.getUpc());
            } catch (Exception ex) {
                stockinqty = 0;
            }
            try {
                stockoutqty = stkobl.getTotalStockoutbyItemDesc(e.getUpc());
            } catch (NullPointerException ex) {
                stockoutqty = 0;
            }
            try {
                returnqty = retbl.getTotalReturnsbyItemDesc(e.getUpc());
            } catch (NullPointerException ex) {
                returnqty = 0;
            }
            long balance = new StockinBL().getStockBalance(e.getUpc());
            double profit = e.getSp() - e.getCp();
            double expprofit = profit * balance;

            data.add(new StockTableModel(e.getUpc(), e.getItemDesc(), e.getBrand().getBrandName(), stockinqty, stockoutqty, returnqty, salesqty, balance, DecimalUtil.format2(e.getCp()), DecimalUtil.format2(e.getSp()), DecimalUtil.format2(expprofit)));
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
                                Long balance = sk.getStockBalance(person.getBarcode());
                                if (balance == 0) {
                                    childController.outofstockbackground.setVisible(true);
                                } else {
                                    childController.outofstockbackground.setVisible(false);
                                }
                                Items i = ib.getImageItembyCode(person.getBarcode());
                                childController.itembcode.setText(person.getBarcode());
                                childController.itemsdesc.setText(person.getItems());
                                childController.brand.setText(person.getBrand());
                                //childController.brand.setText(person.getItems());
                                childController.qty.setText(balance.toString());
                                childController.exp.setText(DecimalUtil.format2(i.getSp()));
                                childController.curr.setText(MainAppController.B.getBCurrency());
                                //Items its = new ItemsBL().getImageItembyCode(person.getItems());
                                FileInputStream input;
                                input = new FileInputStream(i.getItemImg());
                                Image image = new Image(input);
                                childController.itemsimage.setImage(image);
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
        barcode.setCellValueFactory(cell -> cell.getValue().getBarcodeProperty());
        stkitem.setCellFactory(cellFactory);
        stkinqty.setCellValueFactory(cell -> cell.getValue().getStockinQtyProperty());
        stkoutqty.setCellValueFactory(cell -> cell.getValue().getStockoutQtyProperty());
        returnsqty.setCellValueFactory(cell -> cell.getValue().getReturnQtyProperty());
        salesqty.setCellValueFactory(cell -> cell.getValue().getSalesQtyProperty());
        stkbal.setCellValueFactory(cell -> cell.getValue().getStockbalProperty());
        cstprice.setCellValueFactory(cell -> cell.getValue().getStockCostPriceProperty());
        salesprice.setCellValueFactory(cell -> cell.getValue().getStockSalesPriceProperty());
        exp.setCellValueFactory(cell -> cell.getValue().getExprofitProperty());

        stock.setItems(data);
//        clientTable.getColumns().add(action);
        stock.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    public void StockinTableData(String upc) {
        List<Stockin> v;
        if (upc != null && upc.length() > 0) {
            v = new StockinBL().searchAllStockin(upc);
        } else {
            v = new StockinBL().getAllStockinBarcode(upc, 10);
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
                return new StockinDeleteCell();
            }
        });

    }

    public void StockoutTableData(String itemsDesc) {
        List<Stockout> v = stkobl.searchAllStockout(itemsDesc);
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
                return new StockoutDeleteCell();
            }
        });

    }

    public void ReturnsTableData(String itemsDesc) {
        List<RtdItem> v = retbl.searchAllReturnItems(itemsDesc);
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
                return new ReturnDeleteCell();
            }
        });
    }

    public void SalesTableData(String itemsDesc) {
        List<SalesDetails> s;
        s = salesbl.getAllSalesDetailsbyBarcode(itemsDesc, 10);
        salesdata = FXCollections.observableArrayList();
        s.forEach((sales) -> {
            try {
                salesdata.add(new SalesDetailsTableModel(sales.getUpc().getUpc(), sales.getUpc().getItemDesc(), sales.getQuantity(), DecimalUtil.format2(sales.getSalesPrice()), DecimalUtil.format2(sales.getDiscount()), Utilities.convertDateToString(sales.getEntryDate())));
            } catch (Exception ex) {
                Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            Task<Integer> task = new Task<Integer>() {
                @Override
                protected Integer call() throws Exception {
                    childController.spinner.setVisible(true);
                    childController.check.setVisible(false);
                    updateMessage(MainAppController.PROCESS_MESSAGE);
                    Thread.sleep(500);
                    return childController.saveTemplate();
                }
            };
            childController.displayinfo.textProperty().bind(task.messageProperty());
            task.setOnSucceeded(s -> {
                if (task.getValue() == 1) {
                    childController.saveTrans();
                    AllStockTableData(childController.itembarcode.getText());
                    StockinTableData(childController.itembarcode.getText());
                    stocksearch.setText(childController.itemname.getText());
                } else {
                    childController.errorTrans();
                }

            });
            Thread d = new Thread(task);
            d.setDaemon(true);
            d.start();
        });
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setMaximized(true);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(parent.getScene().getWindow());
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
            Task<Integer> task = new Task<Integer>() {
                @Override
                protected Integer call() throws Exception {
                    childController.spinner.setVisible(true);
                    updateMessage("PROCESSING PLS WAIT.....");
                    Thread.sleep(500);
                    return childController.saveTemplate();
                }
            };
            childController.displayinfo.textProperty().bind(task.messageProperty());
            task.setOnSucceeded(s -> {
                if (task.getValue() == 1) {
                    childController.saveTrans();
                    AllStockTableData(childController.itembarcode.getText());
                    StockinTableData(childController.itembarcode.getText());
                    stocksearch.setText(childController.itemname.getText());
                } else {
                    childController.errorTrans();
                }
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

    @FXML
    private void stocksearchAction(ActionEvent event) {
        stocksearch.selectAll();
        Utilities.repeatFocus(stocksearch);
        AllStockTableData(stocksearch.getText());

    }

    public class StockinDeleteCell extends TableCell<StockinTableModel, Boolean> {

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
        StockinDeleteCell() {
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
                            Task<Integer> task = new Task<Integer>() {
                                @Override
                                protected Integer call() throws Exception {
                                    childController.spinner.setVisible(true);
                                    updateMessage(MainAppController.PROCESS_MESSAGE);
                                    Thread.sleep(500);
                                    List st = new SalesDetailsBL().getStockinFromSalesDetails(selectedRecord.getItem());
                                    if (st.isEmpty()) {
                                        return stockinDeleteTemplate(selectedRecord.getStockinCode());
                                    } else {
                                        return 0;
                                    }
                                }
                                
                            };
                            childController.displayinfo.textProperty().bind(task.messageProperty());
                            task.setOnSucceeded(f -> {
                            childController.displayinfo.textProperty().unbind();
                                if (task.getValue() == 1) {
                                    AllStockTableData(stocksearch.getText());
                                    StockinTableData(stocksearch.getText());
                                    StockoutTableData(stocksearch.getText());
                                    ReturnsTableData(stocksearch.getText());
                                    SalesTableData(stocksearch.getText());
                                    childController.deleteTrans();
                                } else {
                                    childController.errorTrans();
                                }
                            });
                            Thread d = new Thread(task);
                            d.setDaemon(true);
                            d.start();

                        });
                        Scene scene = new Scene(parent);
                        scene.setFill(Color.TRANSPARENT);
                        stage.setMaximized(true);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.initOwner(parent.getScene().getWindow());
                        stage.setScene(scene);
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.show();

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

    public class StockoutDeleteCell extends TableCell<StockoutTableModel, Boolean> {

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
        StockoutDeleteCell() {
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
                            Task<Integer> task = new Task<Integer>() {
                                @Override
                                protected Integer call() throws Exception {
                                    childController.spinner.setVisible(true);
                                    updateMessage("Processing...");
                                    Thread.sleep(500);
                                    return stockoutDeleteTemplate(selectedRecord.getStockoutId());
                                }
                            };
                            childController.displayinfo.textProperty().bind(task.messageProperty());
                            task.setOnSucceeded(f -> {
                                childController.displayinfo.textProperty().unbind();
                                if (task.getValue() == 1) {
                                    childController.deleteTrans();
                                    AllStockTableData(stocksearch.getText());
                                    StockinTableData(stocksearch.getText());
                                    StockoutTableData(stocksearch.getText());
                                    ReturnsTableData(stocksearch.getText());
                                    SalesTableData(stocksearch.getText());
                                } else {
                                    childController.errorTrans();
                                }
                            });
                            Thread d = new Thread(task);
                            d.setDaemon(true);
                            d.start();

                        });
                        Scene scene = new Scene(parent);
                        scene.setFill(Color.TRANSPARENT);
                        stage.setMaximized(true);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.initOwner(parent.getScene().getWindow());
                        stage.setScene(scene);
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.show();

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

    public class ReturnDeleteCell extends TableCell<ReturnTableModel, Boolean> {

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
        ReturnDeleteCell() {
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
                            Task<Integer> task = new Task<Integer>() {
                                @Override
                                protected Integer call() throws Exception {
                                    childController.spinner.setVisible(true);
                                    updateMessage(MainAppController.PROCESS_MESSAGE);
                                    Thread.sleep(500);
                                    return returnDeleteTemplate(selectedRecord.getReturnsId());
                                }
                            };
                            childController.displayinfo.textProperty().bind(task.messageProperty());
                            task.setOnSucceeded(f -> {
                                childController.displayinfo.textProperty().unbind();
                                if (task.getValue() == 1) {
                                    childController.deleteTrans();
                                } else {
                                    childController.errorTrans();
                                }

                            });
                            Thread d = new Thread(task);
                            d.setDaemon(true);
                            d.start();

                        });
                        Scene scene = new Scene(parent);
                        scene.setFill(Color.TRANSPARENT);
                        stage.setMaximized(true);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.initOwner(parent.getScene().getWindow());
                        stage.setScene(scene);
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.show();

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

    public int stockinDeleteTemplate(Integer value) {
        int result = new StockinBL().removeData(value);
        System.out.println("Result:" + result);
        return result;
    }

    public int stockoutDeleteTemplate(Integer value) {
        int result = new StockoutBL().removeData(value);
        return result;
    }

    public int returnDeleteTemplate(Integer value) {
        int result = new ReturnBL().removeData(value);
        return result;
    }
}
