package bt.bitsmartmini.ui;

import com.jfoenix.controls.JFXRadioButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import bt.bitsmartmini.bl.CustomerBL;
import bt.bitsmartmini.bl.InsertUpdateBL;
import bt.bitsmartmini.bl.ItemsBL;
import bt.bitsmartmini.bl.ReceiptBL;
import bt.bitsmartmini.bl.SalesBL;
import bt.bitsmartmini.entity.Customers;
import bt.bitsmartmini.entity.Items;
import bt.bitsmartmini.entity.Receipt;
import bt.bitsmartmini.entity.Sales;
import bt.bitsmartmini.entity.SalesDetails;
import bt.bitsmartmini.entity.Uom;
import bt.bitsmartmini.entity.Users;
import bt.bitsmartmini.tablemodel.SelectItemSaleTableModel;
import static bt.bitsmartmini.ui.MainAppController.cart;
import bt.bitsmartmini.utils.PrintReport;
import bt.bitsmartmini.utils.Utilities;
import lxe.utility.math.DecimalUtil;
import net.sf.jasperreports.engine.JRException;
import org.apache.commons.text.WordUtils;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class ItemCartController extends MainAppController implements Initializable {

    ObservableList<SelectItemSaleTableModel> data;
    SalesBL sb = new SalesBL();
    ReceiptBL rb = new ReceiptBL();
    ItemsBL ib = new ItemsBL();

    @FXML
    private TableView<SelectItemSaleTableModel> carttable;
    @FXML
    private TableColumn<SelectItemSaleTableModel, String> itemname;
    @FXML
    private TableColumn<SelectItemSaleTableModel, String> quantity;
    @FXML
    private TableColumn<SelectItemSaleTableModel, String> itemprice;
    @FXML
    private TableColumn<SelectItemSaleTableModel, Boolean> action;
    @FXML
    public ChoiceBox<String> customerdroplist;
    @FXML
    private TableColumn<SelectItemSaleTableModel, String> total;
    @FXML
    private TableColumn<SelectItemSaleTableModel, Boolean> Discount;
    @FXML
    private TableColumn<SelectItemSaleTableModel, String> Discountcent;

    double totalp;
    DecimalFormat df = new DecimalFormat("0.00");

    @FXML
    private Label totalprice;

    InsertUpdateBL insert = new InsertUpdateBL();
    @FXML
    private TableColumn<SelectItemSaleTableModel, ImageView> itemimage;
    @FXML
    private Label curr;
    @FXML
    private TableColumn<SelectItemSaleTableModel, String> itemcode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AllCartToTable();
        getCustomer();
        getTotalprice();
        customerdroplist.getSelectionModel().selectFirst();
    }

    public void getCustomer() {
        List<Customers> list = new CustomerBL().getAllCustomers();
        ObservableList<Customers> result = FXCollections.observableArrayList(list);
        result.forEach((man) -> {
            customerdroplist.getItems().add(String.valueOf(man.getCustomerId()) + " -> " + WordUtils.capitalizeFully(man.getFullname()));
        });
    }

    public void AllCartToTable() {
        data = FXCollections.observableArrayList();
        for (SelectItemSaleTableModel c : cart.values()) {
            System.out.println("i: " + c.getItemCode());
            Items item = ib.getImageItembyCode(c.getItemCode());
            ImageView imageitems = new ImageView();
            File file = new File(item.getItemImg());
            Image image = new Image(file.toURI().toString());
            imageitems.setImage(image);
            imageitems.setFitWidth(70);
            imageitems.setFitHeight(70);
            imageitems.setPreserveRatio(true);
            imageitems.scaleXProperty();
            imageitems.scaleYProperty();
            imageitems.setSmooth(true);
            imageitems.setCache(true);
            //System.out.println("i: " + c.getItemCode());
            //System.out.println("i: " + c.getItemCode());
            data.add(new SelectItemSaleTableModel(item.getUpc(), item.getItemDesc(), c.getQuantity(), c.getCost(), c.getPrice(), c.getTotal(), c.getDiscountValue(), imageitems));
            itemcode.setCellValueFactory(cell -> cell.getValue().getItemCodeProperty());
            itemname.setCellValueFactory(cell -> cell.getValue().getItemNameProperty());
            quantity.setCellValueFactory(cell -> cell.getValue().getQuantityProperty());
            itemprice.setCellValueFactory(cell -> cell.getValue().getPriceProperty());
            Discountcent.setCellValueFactory(cell -> cell.getValue().getDiscountValueProperty());
            total.setCellValueFactory(cell -> cell.getValue().getTotalProperty());
            itemimage.setCellValueFactory(new PropertyValueFactory<>("image"));
            itemimage.setPrefWidth(80);

            Discount.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SelectItemSaleTableModel, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SelectItemSaleTableModel, Boolean> features) {
                    return new SimpleBooleanProperty(features.getValue() != null);
                }
            });

            Discount.setCellFactory(new Callback<TableColumn<SelectItemSaleTableModel, Boolean>, TableCell<SelectItemSaleTableModel, Boolean>>() {
                @Override
                public TableCell<SelectItemSaleTableModel, Boolean> call(TableColumn<SelectItemSaleTableModel, Boolean> personBooleanTableColumn) {
                    return new AddPersonDiscountCell();
                }
            });
            action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<SelectItemSaleTableModel, Boolean>, ObservableValue<Boolean>>() {
                @Override
                public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<SelectItemSaleTableModel, Boolean> features) {
                    return new SimpleBooleanProperty(features.getValue() != null);
                }
            });

            action.setCellFactory(new Callback<TableColumn<SelectItemSaleTableModel, Boolean>, TableCell<SelectItemSaleTableModel, Boolean>>() {
                @Override
                public TableCell<SelectItemSaleTableModel, Boolean> call(TableColumn<SelectItemSaleTableModel, Boolean> personBooleanTableColumn) {
                    return new AddPersonRemoveCell();
                }
            });

            carttable.setItems(data);

        }

    }

    @FXML
    private void clearAllCart(ActionEvent event) {
        cart.clear();
        static_label.setText(String.valueOf(cart.size()));
        carttable.getItems().clear();
        totalprice.setText(" 0");
        curr.setText(MainAppController.B.getBCurrency());
    }

    public class AddPersonDiscountCell extends TableCell<SelectItemSaleTableModel, Boolean> {

        //Image img = new Image(getClass().getResourceAsStream("edit.png"));
        Image img2 = new Image(getClass().getResourceAsStream("/bt/resources/discount.png"));
        // a button for adding a new person.
        Button discountButton = new Button();
        // pads and centers the add button in the cell.
        HBox paddedButton = new HBox();

        // records the y pos of the last button press so that the add person dialog can be shown next to the cell.
        final DoubleProperty buttonY = new SimpleDoubleProperty();

        /**
         * AddPersonCell constructor
         *
         * @param stage the stage in which the table is placed.
         * @param table the table to which a new person can be added.
         */
        AddPersonDiscountCell() {
            paddedButton.setStyle("-fx-alignment: CENTER;");
            paddedButton.getChildren().add(discountButton);
            discountButton.setGraphic(new ImageView(img2));

//            delButton.setRipplerFill(Paint.valueOf("#D8E1DC"));
//            nhisButton.setCheckedColor(Paint.valueOf("#6699ff"));
            discountButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
//
                    try {
                        Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddDiscount.fxml"));
                        Parent parent = (Parent) fxmlLoader.load();
                        AddDiscountController childController = fxmlLoader.getController();

                        childController.discountbtn.addEventHandler(MouseEvent.MOUSE_CLICKED, a -> {
                            int selectdIndex = getTableRow().getIndex();
                            //Create a new table show details of the selected item
                            SelectItemSaleTableModel selectedRecord = (SelectItemSaleTableModel) carttable.getItems().get(selectdIndex);
                            selectedRecord.setDiscountValueProperty(childController.discounttextfield.getText());
                            totalp = Float.parseFloat(df.format(Utilities.sumList(getPrice())));
                            totalprice.setText(DecimalUtil.format2(totalp));
                            curr.setText(MainAppController.B.getBCurrency());
                            if (Integer.parseInt(selectedRecord.getDiscountValue()) >= 0) {
                                getTotalprice();
                                stage.close();
                            }
                        });
                        Scene scene = new Scene(parent);
                        scene.setFill(Color.TRANSPARENT);
                        stage.setScene(scene);
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.setMaximized(true);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(ItemCartController.class.getName()).log(Level.SEVERE, null, ex);
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
//                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(paddedButton);
            } else {
                setGraphic(null);
            }
        }
    }

    public class AddPersonRemoveCell extends TableCell<SelectItemSaleTableModel, Boolean> {

        //Image img = new Image(getClass().getResourceAsStream("edit.png"));
        Image img2 = new Image(getClass().getResourceAsStream("delete.png"));
        // a button for adding a new person.
        Button discountButton = new Button();
        // pads and centers the add button in the cell.
        HBox paddedButton = new HBox();
        // records the y pos of the last button press so that the add person dialog can be shown next to the cell.
        final DoubleProperty buttonY = new SimpleDoubleProperty();

        /**
         * AddPersonCell constructor
         *
         * @param stage the stage in which the table is placed.
         * @param table the table to which a new person can be added.
         */
        AddPersonRemoveCell() {
            paddedButton.setStyle("-fx-alignment: CENTER;");
            paddedButton.getChildren().add(discountButton);
            discountButton.setGraphic(new ImageView(img2));

//            delButton.setRipplerFill(Paint.valueOf("#D8E1DC"));
//            nhisButton.setCheckedColor(Paint.valueOf("#6699ff"));
            discountButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int selectdIndex = getTableRow().getIndex();
                    SelectItemSaleTableModel selectedRecord = (SelectItemSaleTableModel) carttable.getItems().get(selectdIndex);
                    cart.remove(selectedRecord.getItemCode());
                    static_label.setText(String.valueOf(cart.size()));
                    carttable.getItems().remove(selectedRecord);
                    AllCartToTable();
                    //selection.refresh();
                    getTotalprice();

                }

            });
//            checkbox.setSelected(true);

        }

        /**
         * places an add button in the row only if the row is not empty.
         */
        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
//                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(paddedButton);
            } else {
                setGraphic(null);
            }
        }
    }

    @FXML
    private void checkout(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddCheckoutPayment.fxml"));
        Parent parent = (Parent) fxmlLoader.load();
        AddCheckoutPaymentController childController = fxmlLoader.getController();
        String customerid = customerdroplist.getSelectionModel().getSelectedItem();
        String cus[] = customerdroplist.getSelectionModel().getSelectedItem().split("[->]");
        List<String> spiltedval = Arrays.asList(cus);
        String cusid = spiltedval.get(0).trim();
        if ("1".equals(cusid)) {
            childController.checkoutpaytextfield.setText(DecimalUtil.format2(totalp));
            childController.checkoutpaytextfield.setDisable(true);
        } else {
            childController.checkoutpaytextfield.setDisable(false);
        }
        childController.chekoutpaybtn.setOnAction(eventcheckout -> {
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
            task.setOnSucceeded(e -> {
                childController.displayinfo.textProperty().unbind();
                double amount = Double.parseDouble(childController.checkoutpaytextfield.getText());
                if (amount > 0 && cart.size() > 0) {
                    Sales sale = new Sales();
                    List<Integer> salescount = sb.getSalesCount();
                    if (salescount.isEmpty()) {
                        int slc = 1;
                        sale.setSalesId(1);
                    } else {
                        int slc = salescount.get(0);
                        sale.setSalesId(++slc);
                    }
                    sale.setSalesDate(new Date(System.currentTimeMillis()));
                    sale.setUsers(new Users(LoginController.u.getUserid()));
                    sale.setCustomers(new Customers(Integer.valueOf(cusid)));
                    sale.setEntryDate(new Date(System.currentTimeMillis()));
                    List<SalesDetails> sds = new ArrayList<>();
                    ObservableList<SelectItemSaleTableModel> list = carttable.getItems();
                    list.forEach(v -> {
                        SalesDetails sd = new SalesDetails();
                        sd.setUpc(new Items(v.getItemCode()));
                        sd.setSaleId(sale);
                        sd.setSalesPrice(Double.valueOf(v.getPrice()));
                        sd.setQuantity(Integer.valueOf(v.getQuantity()));
                        sd.setCostPrice(Double.valueOf(v.getCost()));
                        sd.setDiscount(Double.valueOf(v.getDiscountValue()));
                        sd.setEntryDate(new Date());
                        sd.setModifiedDate(new Date());
                        sds.add(sd);
                    });
                    sale.setSalesDetailsCollection(sds);
                    JFXRadioButton jrb = (JFXRadioButton) childController.tg.getSelectedToggle();
                    List<Receipt> rds = new ArrayList<>();
                    Receipt receipt = new Receipt();
                    List<Integer> receiptcount = rb.getReceiptCount();
                    if (receiptcount.isEmpty()) {
                        int slc = 1;
                        receipt.setReceiptId(1);
                    } else {
                        int slc = receiptcount.get(0);
                        receipt.setReceiptId(++slc);
                    }
                    receipt.setSalesId(sale);
                    receipt.setAmountPaid(amount);
                    receipt.setEntryLog(new Date());
                    receipt.setPayMode(jrb.getText());
                    receipt.setModifiedDate(new Date());
                    receipt.setUsers(new Users(LoginController.u.getUserid()));
                    receipt.setReceiptDate(new Date(System.currentTimeMillis()));
                    receipt.setReceiptTime(new Date(System.currentTimeMillis()));
                    rds.add(receipt);
                    sale.setReceiptCollection(rds);
                    int checkresult = insert.insertData(sale);
                    if (checkresult == 1) {
                        childController.displayinfo.setText("SUCCESSFULLY SAVED");
                        childController.spinner.setVisible(false);
                        childController.check.setVisible(true);
                        cart.clear();
                        static_label.setText(String.valueOf(cart.size()));
                        carttable.getItems().clear();
                        totalprice.setText("");
                        try {
                            stage.close();
                            Stage receiptStage = new Stage();
                            FXMLLoader fxmlLoaderReciptPopup = new FXMLLoader(getClass().getResource("ReceiptPopup.fxml"));
                            Parent parentReciptPopup = (Parent) fxmlLoaderReciptPopup.load();
                            ReceiptPopupController childControllerReciptPopup = fxmlLoaderReciptPopup.getController();
                            childControllerReciptPopup.paidvalue.setText(childController.checkoutpaytextfield.getText());
                            childControllerReciptPopup.preview.setOnAction(p -> {
                                receiptStage.close();
                                try {
                                    new PrintReport().showReceiptReport(sale.getSalesId().toString());
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
                    //System.out.println("Saved");

                } else {
                    System.out.println("Unable to save");
                }
            });
            Thread th = new Thread(task);
            th.start();

        });

        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setMaximized(true);
        stage.show();

    }

    @FXML
    private void addcustomer(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Customer.fxml"));
        Parent parent = (Parent) fxmlLoader.load();
        CustomerController childController = fxmlLoader.getController();
        childController.save.setOnAction(e -> {

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
                getCustomer();
            });
            Thread d = new Thread(task);
            d.setDaemon(true);
            d.start();

        });

        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setMaximized(true);
        stage.show();
    }

    public List getPrice() {
        List<Number> columnData = new ArrayList<>();
        for (SelectItemSaleTableModel item : carttable.getItems()) {
            String qunt = quantity.getCellObservableValue(item).getValue();
//            Number nhisps = nhisvalprice.getCellObservableValue(item).getValue();
            double actualprice = Integer.valueOf(quantity.getCellObservableValue(item).getValue()) * Double.valueOf(itemprice.getCellObservableValue(item).getValue());
            double discount = Double.valueOf(Discountcent.getCellObservableValue(item).getValue());
//            double nhistopup = actualprice - qunt.intValue();

            if (discount > 0) {
                double discountval2 = discount / 100;
                double disactval = actualprice * discountval2;
                columnData.add(actualprice - disactval);
            } else {
                columnData.add(actualprice);
            }

        }

        return columnData;
    }

    public void getTotalprice() {
        totalp = Float.parseFloat(DecimalUtil.format2(Utilities.sumList(getPrice())));
        totalprice.setText(DecimalUtil.format2(totalp));
        curr.setText(MainAppController.B.getBCurrency());
    }

}
