package bt.bitsmartmini.ui;

//
//package lxe.pharmabitretail.ui;
//
//import com.jfoenix.controls.JFXButton;
//import com.jfoenix.controls.JFXTextField;
//import java.io.IOException;
//import java.net.URL;
//import java.text.DecimalFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.beans.property.DoubleProperty;
//import javafx.beans.property.SimpleBooleanProperty;
//import javafx.beans.property.SimpleDoubleProperty;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.concurrent.Task;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.ContentDisplay;
//import javafx.scene.control.Label;
//import javafx.scene.control.TableCell;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.HBox;
//import javafx.scene.paint.Paint;
//import javafx.stage.Modality;
//import javafx.stage.Stage;
//import javafx.stage.StageStyle;
//import javafx.util.Callback;
//import lxe.pharmabitretail.bl.ReceiptBL;
//import lxe.pharmabitretail.bl.SalesBL;
//import lxe.pharmabitretail.entity.Receipt;
//import lxe.pharmabitretail.tablemodel.ReceiptTableModel;
//import lxe.pharmabitretail.utils.Utilities;
//
///**
// * FXML Controller class
// *
// * @author JScare
// */
//public class AddPaymentController implements Initializable {
//
//    ObservableList<ReceiptTableModel> data;
//
//    @FXML
//    public JFXTextField amountpaid;
//    @FXML
//    public JFXButton save;
//    @FXML
//    public Label customername;
//    @FXML
//    private Button closeButton;
//    @FXML
//    public TableView<ReceiptTableModel> paymenttable;
//    @FXML
//    private TableColumn<ReceiptTableModel, Number> paymentid;
//    @FXML
//    private TableColumn<ReceiptTableModel, Number> paymentsaleid;
//    @FXML
//    private TableColumn<ReceiptTableModel, String> date;
//    @FXML
//    private TableColumn<ReceiptTableModel, Number> amountpaidcell;
//    @FXML
//    private TableColumn<ReceiptTableModel, Boolean> action;
//    @FXML
//    public Label pendingbill;
//    @FXML
//    public Label totalbill;
//
//    DecimalFormat df = new DecimalFormat("0.00");
//    SalesBL sb = new SalesBL();
//    @FXML
//    public Button deletereceipt;
//    
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//        //  Restrict Text to Number only..............................................................
//        amountpaid.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                if (!newValue.matches("\\d*")) {
//                    amountpaid.setText(newValue.replaceAll("[^\\d\\.]", ""));
//                }
//            }
//        });
//    }
//  
//
//    @FXML
//    private void saveAction(ActionEvent event) {
//    }
//
//
//    @FXML
//    public void closemtd(ActionEvent event) {
//        Stage stage = (Stage) closeButton.getScene().getWindow();
//        stage.close();
//    }
//
//    public void paymentTableData(Integer saleid) {
////        List<Receipt> c = new ReceiptBL().getReciptbySales(saleid);
//        data = FXCollections.observableArrayList();
//
//        c.forEach((receipt) -> {
//            data.add(new ReceiptTableModel(receipt.getReceiptId(), receipt.getSalesId().getSalesId(), receipt.getAmountPaid(), Utilities.convertDateToString(receipt.getDateR())));
//        });
//        paymentid.setCellValueFactory(cell -> cell.getValue().getReceiptIdProperty());
//        paymentsaleid.setCellValueFactory(cell -> cell.getValue().getSalesIdProperty());
//        amountpaidcell.setCellValueFactory(cell -> cell.getValue().getAmountPaidProperty());
//        date.setCellValueFactory(cell -> cell.getValue().getDateProperty());
////        clientTable.getColumns().add(action);
////        action.setSortable(false);
////        action.setMaxWidth(480);
////
////        action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ReceiptTableModel, Boolean>, ObservableValue<Boolean>>() {
////            @Override
////            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<ReceiptTableModel, Boolean> features) {
////                return new SimpleBooleanProperty(features.getValue() != null);
////            }
////        });
////        action.setCellFactory(new Callback<TableColumn<ReceiptTableModel, Boolean>, TableCell<ReceiptTableModel, Boolean>>() {
////            @Override
////            public TableCell<ReceiptTableModel, Boolean> call(TableColumn<ReceiptTableModel, Boolean> personBooleanTableColumn) {
////                return new AddPersonCell();
////            }
////        });
//        paymenttable.setItems(data);
//        paymenttable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//
//    }
//
////    public class AddPersonCell extends TableCell<ReceiptTableModel, Boolean> {
////
////        //Image img = new Image(getClass().getResourceAsStream("edit.png"));
////        Image img2 = new Image(getClass().getResourceAsStream("delete.png"));
////
////        // a button for adding a new person.
////        JFXButton addButton = new JFXButton();
////
////        // pads and centers the add button in the cell.
////        HBox paddedButton = new HBox();
////
////        JFXButton delButton = new JFXButton();
////        // records the y pos of the last button press so that the add person dialog can be shown next to the cell.
////        final DoubleProperty buttonY = new SimpleDoubleProperty();
////
////        /**
////         * AddPersonCell constructor
////         *
////         * @param stage the stage in which the table is placed.
////         * @param table the table to which a new person can be added.
////         */
////        AddPersonCell() {
////            paddedButton.setStyle("-fx-alignment: CENTER;");
////            paddedButton.getChildren().add(delButton);
////            delButton.setGraphic(new ImageView(img2));
////            delButton.setRipplerFill(Paint.valueOf("#D8E1DC"));
////            delButton.setOnAction(new EventHandler<ActionEvent>() {
////                @Override
////
////                public void handle(ActionEvent event) {
////
////                    try {
////
////                        int selectdIndex = getTableRow().getIndex();
////                        //Create a new table show details of the selected item
////                        ReceiptTableModel selectedRecord = (ReceiptTableModel) paymenttable.getItems().get(selectdIndex);
////                        Stage stage = new Stage();
////                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Delete.fxml"));
////                        Parent parent = (Parent) fxmlLoader.load();
////                        DeleteController childController = fxmlLoader.getController();
////                        childController.delete.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
////                            childController.displayinfo.setText("PROCESSING PLS WAIT.....");
////                            Task<Void> task = new Task<Void>() {
////                                @Override
////                                protected Void call() throws Exception {
////                                    childController.spinner.setVisible(true);
////                                    updateMessage("Processing......");
////                                    Thread.sleep(1000);
////                                    return null;
////                                }
////                            };
////                            task.setOnSucceeded(f -> {
////                                int result = new ReceiptBL().removeData(selectedRecord.getReceiptId());
////                                switch (result) {
////                                    case 1:
////                                        childController.displayinfo.setText("SUCCESSFULLY DELETED");
////                                        childController.spinner.setVisible(false);
////                                        childController.check.setVisible(true);
////                                        paymentTableData(selectedRecord.getSalesId());
////                                        double totoalsalesnonhis;
////                                        try {
////                                            totoalsalesnonhis = Double.parseDouble(df.format(sb.getTotalSalesNoNHIS(selectedRecord.getSalesId())));
////                                        } catch (Exception ex) {
////                                            totoalsalesnonhis = 0.0;
////                                        }
////                                        double totoalsalesnhis;
////                                        try {
////                                            totoalsalesnhis = Double.parseDouble(df.format(sb.getTotalSalesNHIS(selectedRecord.getSalesId())));
////                                        } catch (Exception ex) {
////                                            totoalsalesnhis = 0.0;
////                                        }
////                                        double totalsales = totoalsalesnonhis + totoalsalesnhis;
////                                        double bal = totalsales - selectedRecord.getAmountPaid();
////                                        totalbill.setText(String.valueOf(bal));
//////                                         double pend = selectedRecord.getAmountPaid() - bal;
//////                                          pendingbill.setText(String.valueOf(pend));
////
////                                        stage.close();
////                                        break;
////                                    default:
////                                        childController.displayinfo.setText("NOTICE! AN ERROR OCCURED");
////                                        childController.spinner.setVisible(false);
////                                        childController.check.setVisible(false);
////                                        break;
////
////                                }
////                            });
////                            Thread d = new Thread(task);
////                            d.setDaemon(true);
////                            d.start();
////
////                        });
////                        Scene scene = new Scene(parent);
////                        stage.initModality(Modality.APPLICATION_MODAL);
////                        stage.initOwner(parent.getScene().getWindow());
////                        stage.setScene(scene);
////                        stage.initStyle(StageStyle.UNDECORATED);
////                        stage.resizableProperty().setValue(false);
////                        stage.showAndWait();
////
////                    } catch (IOException ex) {
////                        Logger.getLogger(AddPaymentController.class.getName()).log(Level.SEVERE, null, ex);
////                    }
////                }
////
////            });
////
////        }
////
////        /**
////         * places an add button in the row only if the row is not empty.
////         */
////        @Override
////        protected void updateItem(Boolean item, boolean empty) {
////            super.updateItem(item, empty);
////            if (!empty) {
////                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
////                setGraphic(paddedButton);
////            } else {
////                setGraphic(null);
////            }
////        }
////    }
//
//}
