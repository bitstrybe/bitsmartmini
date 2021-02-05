package bt.bitsmartmini.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
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
import bt.bitsmartmini.bl.ReceiptBL;
import bt.bitsmartmini.bl.ReturnBL;
import bt.bitsmartmini.bl.SalesBL;
import bt.bitsmartmini.entity.Receipt;
import bt.bitsmartmini.entity.RtdItem;
import bt.bitsmartmini.entity.Sales;
import bt.bitsmartmini.entity.SalesDetails;
import bt.bitsmartmini.entity.Users;
import bt.bitsmartmini.tablemodel.ReceiptTableModel;
import bt.bitsmartmini.tablemodel.ReturnTableModel;
import bt.bitsmartmini.tablemodel.SalesDetailsTableModel;
import bt.bitsmartmini.tablemodel.SalesTableModel;
import static bt.bitsmartmini.ui.MainAppController.cart;
import static bt.bitsmartmini.ui.MainAppController.static_label;
import bt.bitsmartmini.utils.PrintReport;
import bt.bitsmartmini.utils.Utilities;
import lxe.utility.date.DateUtil;
import lxe.utility.math.DecimalUtil;
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class SalesController implements Initializable {

    //private final ObservableList<SelectItemSaleTableModel> data = FXCollections.observableArrayList();
    ObservableList<String> search;
    ObservableList<SalesTableModel> salesdata;
    ObservableList<SalesDetailsTableModel> salesdetailsdata;
    ObservableList<ReceiptTableModel> receiptdata;
    ObservableList<ReturnTableModel> rtddata;
//    private final ObservableList<Person> data
//            = FXCollections.observableArrayList();

    DecimalFormat df = new DecimalFormat("0.00");
    SalesBL sb = new SalesBL();
    ReceiptBL rec = new ReceiptBL();
    ReturnBL rd = new ReturnBL();
    double totalp;

    @FXML
    private Button salespdfbtn;

    static int bal = 0;
    static int qntval1 = 0;
    static int stockbal = 0;
    String lsv;
    @FXML
    private DatePicker startdate;
    @FXML
    private DatePicker enddate;
    @FXML
    private TableView<SalesTableModel> salestable;
    @FXML
    private TableColumn<SalesTableModel, Number> salescodetb;
    @FXML
    private TableColumn<SalesTableModel, String> customertb;
    private TableColumn<SalesTableModel, Boolean> makepaymenttb;
    @FXML
    private TableColumn<SalesTableModel, String> salespricetb;
    @FXML
    private TableColumn<SalesTableModel, String> saleamountpaidtb;
    @FXML
    private TableColumn<SalesTableModel, String> balancetb;
    @FXML
    private TableColumn<SalesTableModel, String> saledatetb;
    @FXML
    private TableColumn<SalesTableModel, Boolean> preview;
    @FXML
    private TableColumn<SalesTableModel, Boolean> action;
    @FXML
    private TableView<SalesDetailsTableModel> salesdetailstable;
    @FXML
    private TableColumn<SalesDetailsTableModel, String> itemnametb;
    @FXML
    private TableColumn<SalesDetailsTableModel, Number> salesdetailsquantitytb;
    @FXML
    private TableColumn<SalesDetailsTableModel, String> itemcosttb;
    @FXML
    private TableColumn<SalesDetailsTableModel, String> itemspricetb;
    @FXML
    private TableColumn<SalesDetailsTableModel, Number> salesdetailsdiscount;
    @FXML
    private TableColumn<SalesDetailsTableModel, String> salesdetailsdate;
    @FXML
    private TableView<ReceiptTableModel> paymenttable;
    @FXML
    private TableColumn<ReceiptTableModel, Number> paymentcodetb;
    @FXML
    private TableColumn<ReceiptTableModel, String> paymentdatetb;
    @FXML
    private TableColumn<ReceiptTableModel, String> amountpaidtb;
    @FXML
    private TableColumn<ReceiptTableModel, String> mode;
    @FXML
    private TableColumn<?, ?> action1;

    ReceiptBL rb = new ReceiptBL();
    ReturnBL rn = new ReturnBL();

    @FXML
    private TableColumn<SalesDetailsTableModel, Boolean> raction;
    @FXML
    private TableColumn<SalesDetailsTableModel, Number> returned;
    @FXML
    private TableColumn<ReturnTableModel, String> rdate;
    @FXML
    private TableColumn<ReturnTableModel, Number> rqty;
    @FXML
    private TableColumn<ReturnTableModel, String> ramnt;
    @FXML
    private TableView<ReturnTableModel> returnstable;
    @FXML
    private TableColumn<ReturnTableModel, String> refunded;
    @FXML
    private TableColumn<SalesTableModel, String> actuals;
    @FXML
    private TableColumn<SalesTableModel, String> refunds;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getTodaysDate();

    }

    public void getTodaysDate() {
        Date dd = new Date(System.currentTimeMillis());
        SalesTableData(dd, dd);
        startdate.setValue(Utilities.convertDateToLocalDate(new Date(System.currentTimeMillis())));
        enddate.setValue(Utilities.convertDateToLocalDate(new Date(System.currentTimeMillis())));
    }

    @FXML
    private void salesPDF(ActionEvent event) {
        try {
            //if (LoginController.u.getRoles().equals("Administrator")) {
            new PrintReport().showSalesDateRangeReport(Utilities.convertToDateViaSqlDate(startdate.getValue()), Utilities.convertToDateViaSqlDate(enddate.getValue()));

        } catch (JRException ex) {
            Logger.getLogger(CatalogController.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CatalogController.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(CatalogController.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(CatalogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void clearall(ActionEvent event) {
//        seleteditemtableview.getItems().clear();
//        sellbtn.setText("SALES");
        salesdetailstable.getItems().clear();
//        getStockingItemList();
        Date dd = new Date(System.currentTimeMillis());
        SalesTableData(dd, dd);
//        totalprice.setText(null);
    }

    public void SalesTableData(Date startdate, Date enddate) {
        List<Sales> c = sb.getSalesDateRange(startdate, enddate);
        salesdata = FXCollections.observableArrayList();
        c.forEach((s) -> {
            double totalpaid;
            try {
                totalpaid = Double.parseDouble(df.format(rec.getTotalPaidbySalesCode(s.getSalesId())));
                //totalpaid = Double.parseDouble(df.format(s.getAmountPaid()));
            } catch (IllegalArgumentException ex) {
                totalpaid = 0;
            }
            double balance = totalpaid;
            String bals;
            bals = String.valueOf(balance);
            String userkey = s.getUsers().getFname() + " " + s.getUsers().getLname();
            Date entrylog = s.getEntryDate();
            double amountpaid;
            try {
                amountpaid = rec.getTotalPaidbySalesCode(s.getSalesId());//s.getAmountPaid();//
            } catch (Exception ex) {
                amountpaid = 0;
            }
            double totalsales = sb.getActualTotalDiscountedSales(s.getSalesId());
            //System.out.println("Total sales|: "+totalsales);
            double totalRtd = rd.getTotalRtdBySalesCode(s.getSalesId());
            double newtotalsales = totalsales - totalRtd;
            double balanceval = totalsales - amountpaid;
            Double ac = totalsales - (balanceval + totalRtd);//sb.getActualTotalSales(s.getSalesId());
            salesdata.add(new SalesTableModel(s.getSalesId(), s.getCustomers().getFullname(), DecimalUtil.format2(totalsales), DecimalUtil.format2(amountpaid), DecimalUtil.format2(totalRtd), DecimalUtil.format2(balanceval), Utilities.convertDateToString(s.getSalesDate()), DecimalUtil.FormatWithSign(ac), s.getUsers().getFname() + " " + s.getUsers().getLname(), DateUtil.formatDate(s.getEntryDate(), "yyyy-MM-dd HH:mm:ss")));
        });
        salescodetb.setCellValueFactory(cell -> cell.getValue().getSalescodeProperty());
        customertb.setCellValueFactory(cell -> cell.getValue().getCustomerProperty());
        salespricetb.setCellValueFactory(cell -> cell.getValue().getSalespriceProperty());
        saleamountpaidtb.setCellValueFactory(cell -> cell.getValue().getAmountpaidProperty());
        balancetb.setCellValueFactory(cell -> cell.getValue().getBalanceProperty());
        refunds.setCellValueFactory(cell -> cell.getValue().getReturnItemProperty());
        saledatetb.setCellValueFactory(cell -> cell.getValue().getDateProperty());
        actuals.setCellValueFactory(cell -> cell.getValue().getActualsProperty());
        action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SalesTableModel, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SalesTableModel, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });
        action.setCellFactory(new Callback<TableColumn<SalesTableModel, Boolean>, TableCell<SalesTableModel, Boolean>>() {
            @Override
            public TableCell<SalesTableModel, Boolean> call(TableColumn<SalesTableModel, Boolean> personBooleanTableColumn) {
                return new AddPersonCellSales();
            }
        });
        preview.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SalesTableModel, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SalesTableModel, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });
        preview.setCellFactory(new Callback<TableColumn<SalesTableModel, Boolean>, TableCell<SalesTableModel, Boolean>>() {
            @Override
            public TableCell<SalesTableModel, Boolean> call(TableColumn<SalesTableModel, Boolean> personBooleanTableColumn) {
                return new AddPersonCellPreview();
            }
        });
        //if (LoginController.u.getRoles().equals("Administrator") || LoginController.u.getRoles().equals("Administrator")) {
        salestable.setRowFactory(tv -> new TableRow<SalesTableModel>() {
            private Tooltip tooltip = new Tooltip();

            @Override
            public void updateItem(SalesTableModel person, boolean empty) {
                super.updateItem(person, empty);
                if (person == null) {
                    setTooltip(null);
                } else {
                    tooltip.setText(person.getUser() + "\n " + person.getDate());
                    setTooltip(tooltip);
                }
            }
        });
        //}
        salestable.setItems(salesdata);
//        clientTable.getColumns().add(action);
        salestable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        salestable.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            try {
                SalesTableModel t = salestable.getSelectionModel().getSelectedItem();
                List<SalesDetails> sd = sb.getAllSalesDetailsbySalesCode(t.getSalescode());
                salesdetailsdata = FXCollections.observableArrayList();
                sd.forEach((sales) -> {
                    RtdItem rt = new ReturnBL().getRtdItemByID(sales.getSalesDetailsId(), startdate, enddate);
                    if (rt != null) {
                        salesdetailsdata.add(new SalesDetailsTableModel(sales.getSalesDetailsId(), sales.getUpc().getItemDesc(), sales.getQuantity(), DecimalUtil.format2(sales.getCostPrice()), DecimalUtil.format2(sales.getSalesPrice()), rt.getRtdQty(), sales.getDiscount(), Utilities.convertDateToString(sales.getEntryDate())));
                    } else {
                        salesdetailsdata.add(new SalesDetailsTableModel(sales.getSalesDetailsId(), sales.getUpc().getItemDesc(), sales.getQuantity(), DecimalUtil.format2(sales.getCostPrice()), DecimalUtil.format2(sales.getSalesPrice()), 0, sales.getDiscount(), Utilities.convertDateToString(sales.getEntryDate())));
                    }
                });
                itemnametb.setCellValueFactory(cell -> cell.getValue().getItemsnameProperty());
                salesdetailsquantitytb.setCellValueFactory(cell -> cell.getValue().getQuantityProperty());
                itemcosttb.setCellValueFactory(cell -> cell.getValue().getItemscostProperty());
                itemspricetb.setCellValueFactory(cell -> cell.getValue().getItemsPriceProperty());
                salesdetailsdiscount.setCellValueFactory(cell -> cell.getValue().getDiscountProperty());
                salesdetailsdate.setCellValueFactory(cell -> cell.getValue().getDateProperty());
                returned.setCellValueFactory(cell -> cell.getValue().getItemsRtdProperty());
                salesdetailstable.setItems(salesdetailsdata);
                salesdetailstable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                //if()
                RtdItem rt = rn.getRtdItemByID(t.getSalescode(), startdate, enddate);
                raction.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SalesDetailsTableModel, Boolean>, ObservableValue<Boolean>>() {
                    @Override
                    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SalesDetailsTableModel, Boolean> features) {
                        return new SimpleBooleanProperty(features.getValue() != null);
                    }
                });
                raction.setCellFactory(new Callback<TableColumn<SalesDetailsTableModel, Boolean>, TableCell<SalesDetailsTableModel, Boolean>>() {
                    //
                    @Override
                    public TableCell<SalesDetailsTableModel, Boolean> call(TableColumn<SalesDetailsTableModel, Boolean> personBooleanTableColumn) {
                        if (rt != null) {
                            return new AddReturnPolicy(true);
                        } else {
                            return new AddReturnPolicy(false);
                        }

                    }
                });

                List<Receipt> receipt = rec.getAllReciptbySalescode(t.getSalescode());
                receiptdata = FXCollections.observableArrayList();
                receipt.forEach(r -> {
                    receiptdata.add(new ReceiptTableModel(r.getReceiptId(), r.getSalesId().getSalesId(), r.getPayMode(), DecimalUtil.format2(r.getAmountPaid()), Utilities.convertDateToString(r.getReceiptDate())));
                });
                paymentcodetb.setCellValueFactory(cell -> cell.getValue().getReceiptIdProperty());
                amountpaidtb.setCellValueFactory(cell -> cell.getValue().getAmountPaidProperty());
                paymentdatetb.setCellValueFactory(cell -> cell.getValue().getDateProperty());
                paymenttable.setItems(receiptdata);
                paymenttable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                mode.setCellValueFactory(cell -> cell.getValue().getPmodeProperty());

                //returns table
                List<RtdItem> returns = rd.getRtdBySalesCode(t.getSalescode());
                rtddata = FXCollections.observableArrayList();
                returns.forEach(r -> {
                    System.out.println("r: " + r.getSalescode());
                    double totalA = (r.getRtdQty() * r.getSalesDetails().getSalesPrice());
                    rtddata.add(new ReturnTableModel(r.getSalescode(), r.getSalesDetails().getRtdItem().getSalesDetails().getUpc().getItemDesc(), r.getRtdQty(), DecimalUtil.format2(r.getSalesDetails().getSalesPrice()), DecimalUtil.format2(totalA), r.getRemarks(), DateUtil.formatDate(r.getRtdDate(), "yyyy-MM-dd")));
                });
                rqty.setCellValueFactory(cell -> cell.getValue().getQuantityProperty());
                ramnt.setCellValueFactory(cell -> cell.getValue().getUnitPriceProperty());
                refunded.setCellValueFactory(cell -> cell.getValue().getAmountProperty());
                rdate.setCellValueFactory(cell -> cell.getValue().getDateProperty());
                returnstable.setItems(rtddata);
                returnstable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
                //mode.setCellValueFactory(cell -> cell.getValue().getPmodeProperty());
            } catch (Exception ex) {
                System.out.println("null");
            }
        });

    }

    public class AddPersonCellPayment extends TableCell<SalesTableModel, Boolean> {

        Image img = new Image(getClass().getResourceAsStream("/resources/pay.png"));
        HBox paddedButton = new HBox();
        JFXButton payButton = new JFXButton();
        // records the y pos of the last button press so that the add person dialog can be shown next to the cell.
        final DoubleProperty buttonY = new SimpleDoubleProperty();

        /**
         * AddPersonCell constructor
         *
         * @param stage the stage in which the table is placed.
         * @param table the table to which a new person can be added.
         */
        AddPersonCellPayment() {
            paddedButton.setStyle("-fx-alignment: CENTER;");
            paddedButton.getChildren().add(payButton);
            payButton.setGraphic(new ImageView(img));
            payButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddCheckoutPayment.fxml"));
                        Parent parent = (Parent) fxmlLoader.load();
                        AddCheckoutPaymentController childController = fxmlLoader.getController();
                        int selectdIndex = getTableRow().getIndex();
                        //Create a new table show details of the selected item
                        SalesTableModel selectedRecord = (SalesTableModel) salestable.getItems().get(selectdIndex);
                        childController.chekoutpaybtn.setOnAction(eventcheckout -> {
                            double amount = Double.parseDouble(childController.checkoutpaytextfield.getText());
                            JFXRadioButton jrb = (JFXRadioButton) childController.tg.getSelectedToggle();
                            Receipt receipt = new Receipt();
                            List<Integer> receiptcount = rb.getReceiptCount();
                            if (receiptcount.isEmpty()) {
                                int slc = 1;
                                receipt.setReceiptId(1);
                            } else {
                                int slc = receiptcount.get(0);
                                receipt.setReceiptId(++slc);
                            }
                            receipt.setSalesId(new Sales(selectedRecord.getSalescode()));
                            receipt.setAmountPaid(amount);
                            receipt.setEntryLog(new Date());
                            receipt.setPayMode(jrb.getText());
                            receipt.setModifiedDate(new Date());
                            receipt.setUsers(new Users(LoginController.u.getUserid()));
                            receipt.setReceiptDate(new Date());
                            int checkresult = new InsertUpdateBL().insertData(receipt);
                            if (checkresult == 1) {
                                cart.clear();
                                static_label.setText(String.valueOf(cart.size()));
                                try {
                                    stage.close();
                                    Stage receiptStage = new Stage();
                                    FXMLLoader fxmlLoaderReciptPopup = new FXMLLoader(getClass().getResource("ReceiptPopup.fxml"));
                                    Parent parentReciptPopup = (Parent) fxmlLoaderReciptPopup.load();
                                    ReceiptPopupController childControllerReciptPopup = fxmlLoaderReciptPopup.getController();
                                    childControllerReciptPopup.paidvalue.setText(childController.checkoutpaytextfield.getText());
                                    childControllerReciptPopup.preview.setOnAction(e -> {
                                        receiptStage.close();

                                        try {
                                            new PrintReport().showReceiptReport(selectedRecord.getSalescode());
                                        } catch (JRException ex) {
                                            Logger.getLogger(CatalogController.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (ClassNotFoundException ex) {
                                            Logger.getLogger(CatalogController.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (SQLException ex) {
                                            Logger.getLogger(CatalogController.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (IOException ex) {
                                            Logger.getLogger(CatalogController.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    });
                                    Scene scene = new Scene(parentReciptPopup);
                                    scene.setFill(Color.TRANSPARENT);
                                    receiptStage.setScene(scene);
                                    receiptStage.initStyle(StageStyle.TRANSPARENT);
                                    receiptStage.setMaximized(true);
                                    receiptStage.show();
                                } catch (IOException ex) {
                                    Logger.getLogger(ItemCartController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        Scene scene = new Scene(parent);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.initOwner(parent.getScene().getWindow());
                        stage.setScene(scene);
                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.resizableProperty().setValue(false);
                        stage.show();
//                    } catch (IOException ex) {
//                        Logger.getLogger(CatalogController.class.getName()).log(Level.SEVERE, null, ex);
//                    }
                    } catch (IOException ex) {
                        Logger.getLogger(SalesController.class.getName()).log(Level.SEVERE, null, ex);
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

    public class AddPersonCellPreview extends TableCell<SalesTableModel, Boolean> {

        //Image img = new Image(getClass().getResourceAsStream("edit.png"));
        Image preview = new Image(getClass().getResourceAsStream("/resources/eye.png"));
        // a button for adding a new person.
//        JFXButton addButton = new JFXButton();
        // pads and centers the add button in the cell.
        HBox paddedButton = new HBox();
        JFXButton btn = new JFXButton();
        // records the y pos of the last button press so that the add person dialog can be shown next to the cell.
        final DoubleProperty buttonY = new SimpleDoubleProperty();

        /**
         * AddPersonCell constructor
         *
         * @param stage the stage in which the table is placed.
         * @param table the table to which a new person can be added.
         */
        AddPersonCellPreview() {
            paddedButton.setStyle("-fx-alignment: CENTER;");
            paddedButton.getChildren().add(btn);
//            delButton.setGraphic(new ImageView(img2));
//            delButton.setRipplerFill(Paint.valueOf("#D8E1DC"));
            btn.setRipplerFill(Paint.valueOf("#6699ff"));
            btn.setGraphic(new ImageView(preview));
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override

                public void handle(ActionEvent event) {
                    int selectdIndex = getTableRow().getIndex();
                    //Create a new table show details of the selected item
                    ReceiptTableModel selectedRecord = (ReceiptTableModel) paymenttable.getItems().get(selectdIndex);
                    try {
                        new PrintReport().showReceiptReport(selectedRecord.getReceiptId());
                    } catch (JRException ex) {
                        Logger.getLogger(CatalogController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(CatalogController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(CatalogController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(CatalogController.class.getName()).log(Level.SEVERE, null, ex);
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

    public class AddPersonCellSales extends TableCell<SalesTableModel, Boolean> {

        Image img = new Image(getClass().getResourceAsStream("edit.png"));
        Image preview = new Image(getClass().getResourceAsStream("delete.png"));

        // a button for adding a new person.
        // JFXButton addButton = new JFXButton();
        // pads and centers the add button in the cell.
        HBox paddedButton = new HBox();

        JFXButton btn = new JFXButton();
        JFXButton btn1 = new JFXButton();
        // records the y pos of the last button press so that the add person dialog can be shown next to the cell.
        final DoubleProperty buttonY = new SimpleDoubleProperty();

        /**
         * AddPersonCell constructor
         *
         * @param stage the stage in which the table is placed.
         * @param table the table to which a new person can be added.
         */
        AddPersonCellSales() {
            paddedButton.setStyle("-fx-alignment: CENTER;");
            paddedButton.getChildren().add(btn1);
            paddedButton.getChildren().add(btn);
//            delButton.setGraphic(new ImageView(img2));
//            delButton.setRipplerFill(Paint.valueOf("#D8E1DC"));
            btn.setRipplerFill(Paint.valueOf("#6699ff"));
            btn.setGraphic(new ImageView(preview));
            btn1.setRipplerFill(Paint.valueOf("#6699ff"));
            btn1.setGraphic(new ImageView(img));
            //EDIT SALES
            btn1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int selectdIndex = getTableRow().getIndex();
                    //Create a new table show details of the selected item
                    SalesTableModel selectedRecord = (SalesTableModel) salestable.getItems().get(selectdIndex);
                    List<SalesDetails> sl = new SalesBL().getSalesDetailsbySalesId(selectedRecord.getSalescode());
                }
            });
            //DELETE SALES
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override

                public void handle(ActionEvent event) {
                    try {

                        int selectdIndex = getTableRow().getIndex();
                        //Create a new table show details of the selected item
                        SalesTableModel selectedRecord = (SalesTableModel) salestable.getItems().get(selectdIndex);
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
                                    updateMessage("PROCESSING PLS WAIT.....");
                                    Thread.sleep(1000);
                                    return null;
                                }
                            };
                            task.setOnSucceeded(f -> {
                                List salescode = new ReceiptBL().getSalesbyReceipt(selectedRecord.getSalescode());
                                if (!salescode.isEmpty()) {
                                    childController.displayinfo.setText("UNABLE TO DELETE RECORD");
                                    childController.spinner.setVisible(false);
                                    childController.check.setVisible(false);
                                } else {
                                    int result = sb.removeData(selectedRecord.getSalescode());
                                    switch (result) {
                                        case 1:
                                            childController.displayinfo.setText("SUCCESSFULLY DELETED");
                                            childController.spinner.setVisible(false);
                                            childController.check.setVisible(true);
                                            salesdetailstable.getItems().clear();
//                                            totalsalesprice.setText(null);
                                            getTodaysDate();
                                            stage.close();
                                            break;
                                        default:
                                            childController.displayinfo.setText("NOTICE! AN ERROR OCCURED");
                                            childController.spinner.setVisible(false);
                                            childController.check.setVisible(false);
                                            break;
                                    }
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
                        Logger.getLogger(AddCategoryController.class.getName()).log(Level.SEVERE, null, ex);
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

    public class AddReturnPolicy extends TableCell<SalesDetailsTableModel, Boolean> {

        //Image img = new Image(getClass().getResourceAsStream("edit.png"));
        Image preview = new Image(getClass().getResourceAsStream("/resources/returns.png"));
        // a button for adding a new person.
//        JFXButton addButton = new JFXButton();
        // pads and centers the add button in the cell.
        HBox paddedButton = new HBox();
        JFXButton btn = new JFXButton();
        // records the y pos of the last button press so that the add person dialog can be shown next to the cell.
        final DoubleProperty buttonY = new SimpleDoubleProperty();

        AddReturnPolicy(boolean b) {
            paddedButton.setStyle("-fx-alignment: CENTER;");
            paddedButton.getChildren().add(btn);
            btn.setRipplerFill(Paint.valueOf("#6699ff"));
            btn.setGraphic(new ImageView(preview));
            //if (!b) {
            System.out.println("b: "+b);
            paddedButton.setDisable(b);
            //}
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int selectdIndex = getTableRow().getIndex();
                    //Create a new table show details of the selected item
                    SalesDetailsTableModel selectedRecord = (SalesDetailsTableModel) salesdetailstable.getItems().get(selectdIndex);
                    try {
                        callReturnPolicyForm(selectedRecord.getSalesDetailscode());
                    } catch (Exception ex) {
                        Logger.getLogger(CatalogController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    public void filterdate() {
        SalesTableData(Utilities.convertToDateViaSqlDate(startdate.getValue()), Utilities.convertToDateViaSqlDate(enddate.getValue()));
    }

    public void callReturnPolicyForm(Integer i) {
        try {
            SalesBL sl = new SalesBL();
            ReturnBL rb = new ReturnBL();
            Stage stage = new Stage();
            FXMLLoader fxmlLoadersk = new FXMLLoader(getClass().getResource("ReturnItems.fxml"));
            Parent parentsk = (Parent) fxmlLoadersk.load();
            ReturnItemsController childControllersk = fxmlLoadersk.getController();
            SalesDetails sd = sl.getSalesDetailsById(i);
            RtdItem rd = rb.getRtdItemByID(i);
            double actualsales = sl.getActualTotalDiscountedSales(i);
            System.out.println(actualsales);
            childControllersk.itemname.setText(sd.getUpc().getItemDesc());
            FileInputStream input;
            input = new FileInputStream(sd.getUpc().getItemImg());
            Image imagesk = new Image(input);
            childControllersk.itemimage.setImage(imagesk);
            int qty = 0;
            if (rd != null) {
                qty = sd.getQuantity() - rd.getRtdQty();
            } else {
                qty = sd.getQuantity();
            }
            childControllersk.itemcode.setText(String.valueOf(sd.getSalesDetailsId()));
            childControllersk.itemcost.setText(String.valueOf(DecimalUtil.format2(sd.getSalesPrice())));
            childControllersk.returnqnty.setText(String.valueOf(qty));
            childControllersk.pay.setText(DecimalUtil.format2(qty * sd.getSalesPrice()));
            childControllersk.curr.setText(MainAppController.B.getBCurrency());
            childControllersk.save.setDisable(false);
            childControllersk.save.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                childControllersk.save.setDisable(true);
                Task<Void> task = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        childControllersk.spinner.setVisible(true);
                        childControllersk.check.setVisible(false);
                        updateMessage("PROCESSING PLS WAIT.....");
                        Thread.sleep(500);
                        return null;
                    }
                };
                childControllersk.displayinfo.textProperty().bind(task.messageProperty());
                task.setOnSucceeded(s -> {
                    childControllersk.saveTemplate(sd.getSalesDetailsId());
                    filterdate();
                });
                Thread d = new Thread(task);
                d.setDaemon(true);
                d.start();

            });

            Scene scene = new Scene(parentsk);
            stage.setMaximized(true);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CatalogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
