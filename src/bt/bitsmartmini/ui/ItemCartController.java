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
import bt.bitsmartmini.bl.ReturnBL;
import bt.bitsmartmini.bl.SalesBL;
import bt.bitsmartmini.bl.StockinBL;
import bt.bitsmartmini.bl.UomBL;
import bt.bitsmartmini.entity.Customers;
import bt.bitsmartmini.entity.Items;
import bt.bitsmartmini.entity.Receipt;
import bt.bitsmartmini.entity.RefundPolicy;
import bt.bitsmartmini.entity.Sales;
import bt.bitsmartmini.entity.SalesDetails;
import bt.bitsmartmini.entity.UomSet;
import bt.bitsmartmini.entity.Users;
import bt.bitsmartmini.tablemodel.SelectItemSaleTableModel;
import static bt.bitsmartmini.ui.MainAppController.cart;
import static bt.bitsmartmini.ui.MainAppController.static_label;
import bt.bitsmartmini.utils.PrintReport;
import bt.bitsmartmini.utils.Utilities;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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

    AtomicInteger rowCounter = new AtomicInteger(0);

    double totalp;
    DecimalFormat df = new DecimalFormat("0.00");

    @FXML
    private Label totalprice;

    InsertUpdateBL insert = new InsertUpdateBL();
    @FXML
    private TableColumn<?, ?> itemimage;
    @FXML
    private Label curr;
    @FXML
    private TableColumn<SelectItemSaleTableModel, String> itemcode;
    @FXML
    private TextField itembarcode;
    @FXML
    private Text itemcartname;
    @FXML
    private Text itembrand;
    @FXML
    private Text itemqty;
    @FXML
    private Text itemsp;
    @FXML
    private JFXTextField qnttextfield;
    @FXML
    private ImageView itemimage1;
    @FXML
    private Text curry;
    @FXML
    private Text qtyrem;
    @FXML
    private HBox qtyHbox;
    @FXML
    private JFXButton addtocartbtn;
    @FXML
    private Label addtocartinfo;
    @FXML
    private Button checkoutbtn;

    Double CP;
    @FXML
    private ComboBox<String> uomcombo;
    @FXML
    private Text m1;
    @FXML
    private Text u1;
    @FXML
    private TableColumn<SelectItemSaleTableModel, String> measure;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        qnttextfield.setText("1");
        AllCartToTable();
        getCustomer();
        getTotalprice();
        customerdroplist.getSelectionModel().selectFirst();
        repeatFocus(itembarcode);
        //System.out.println("cart size: " + cart);
        controlbtn();
        addtocartbtn.setDisable(true);
    }

    public void controlbtn() {
        if (cart.size() > 0) {
            checkoutbtn.setDisable(false);
        } else {
            checkoutbtn.setDisable(true);
        }
    }

    public void getUomsets(String s) {
        //System.out.println("sosket: "+s);
        uomcombo.getItems().clear();
        UomSet uoms = new UomBL().getUomSets(s);
        //System.out.println("UOM"+uoms. );
        List uomsets = new ArrayList<>();
        uomsets.add(uoms.getMeasure1().getUomDesc() + " - " + uoms.getUnit1());
        uomsets.add(uoms.getMeasure2().getUomDesc() + " - " + uoms.getUnit2());
        ObservableList<String> result = FXCollections.observableArrayList(uomsets);
        result.forEach((man) -> {
            //System.out.println("man:" + man);
            uomcombo.getItems().add(WordUtils.capitalize(man));
        });
        uomcombo.getSelectionModel().selectFirst();
    }

    @FXML
    private void addItemToCart() {
        Task<Void> longRunningTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Platform.runLater(() -> {
                    try {
                        long stockinqty = new StockinBL().getStockInTotal(itembarcode.getText());
                        long qntfield = Long.valueOf(itemqty.getText());
                        if (Long.valueOf(qnttextfield.getText()) <= qntfield) {
                            double totalqnt = Long.valueOf(qnttextfield.getText()) * Double.valueOf(itemsp.getText()) * Long.valueOf(uomcombo.getValue().split("-")[1].trim());
                            double price = Double.valueOf(itemsp.getText());
                            SelectItemSaleTableModel item = new SelectItemSaleTableModel(itembarcode.getText(), itemname.getText(), qnttextfield.getText(), CP.toString(), DecimalUtil.format2(price), DecimalUtil.format2(totalqnt), "0");
                            cart.put(itembarcode.getText(), item);
                            static_label.setText(String.valueOf(cart.size()));
                            addtocartinfo.setText("Added to cart");
                            AllCartToTable();
                            resetItemDisplay();
                            controlbtn();
                        } else {
                            addtocartinfo.setText("Not enough Quantity");
                        }
                    } catch (Exception ex) {
                        addtocartinfo.setText("Invalid Format");
                        Logger.getLogger(CatalogController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                return null;
            }
        };
        new Thread(longRunningTask).start();
    }

    public void clearAllCartItem() {
        cart.clear();
        static_label.setText(String.valueOf(cart.size()));
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
            //System.out.println("i: " + c.getItemCode());
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
            data.add(new SelectItemSaleTableModel(item.getUpc(), item.getItemDesc(), uomcombo.getValue(), c.getQuantity(), c.getCost(), c.getPrice(), c.getTotal(), c.getDiscountValue(), imageitems));
            itemcode.setCellValueFactory(cell -> cell.getValue().getItemCodeProperty());
            itemname.setCellValueFactory(cell -> cell.getValue().getItemNameProperty());
            measure.setCellValueFactory(cell -> cell.getValue().getMeasureProperty());
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
            getTotalprice();
        }
    }

    @FXML
    private void clearAllCart(ActionEvent event) {
        cart.clear();
        static_label.setText(String.valueOf(cart.size()));
        carttable.getItems().clear();
        totalprice.setText("0");
        curr.setText(MainAppController.B.getBCurrency());
    }

    @FXML
    private void addDiscountbtn(ActionEvent event) {
    }

    @FXML
    private void minusqnty(ActionEvent event) {
        if (rowCounter.get() > 1) {
            qnttextfield.setText(Integer.toString(rowCounter.decrementAndGet()));
        } else {
            rowCounter.set(1);
        }
    }

    @FXML
    private void plusqnty(ActionEvent event) {
        if (rowCounter.get() < Integer.valueOf(itemqty.getText())) {
            qnttextfield.setText(Integer.toString(rowCounter.incrementAndGet()));
        }
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
            discountButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
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
            discountButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int selectdIndex = getTableRow().getIndex();
                    SelectItemSaleTableModel selectedRecord = (SelectItemSaleTableModel) carttable.getItems().get(selectdIndex);
                    cart.remove(selectedRecord.getItemCode());
                    static_label.setText(String.valueOf(cart.size()));
                    carttable.getItems().remove(selectedRecord);
                    AllCartToTable();
                    getTotalprice();
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
                setGraphic(paddedButton);
            } else {
                setGraphic(null);
            }
        }
    }

    @FXML
    private void checkout(ActionEvent event) throws IOException {
        ReturnBL r = new ReturnBL();
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddCheckoutPayment.fxml"));
        Parent parent = (Parent) fxmlLoader.load();
        AddCheckoutPaymentController childController = fxmlLoader.getController();
        // String customerid = customerdroplist.getSelectionModel().getSelectedItem();
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
                        //int slc = 1;
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
                    RefundPolicy y = r.findRefundPolicy();
                    receipt.setReturnPolicy(y.getRefundCustomMsg().replace("?", y.getRefundPeriodVal() + " " + y.getRefundPeriod()));
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
            if (measure.getCellObservableValue(item).getValue() != null) {
                String unit = measure.getCellObservableValue(item).getValue().split("-")[1].trim();
                double actualprice = Integer.valueOf(quantity.getCellObservableValue(item).getValue()) * Double.valueOf(itemprice.getCellObservableValue(item).getValue()) * Integer.valueOf(measure.getCellObservableValue(item).getValue());
                double discount = Double.valueOf(Discountcent.getCellObservableValue(item).getValue());
                if (discount > 0) {
                    double discountval2 = discount / 100;
                    double disactval = actualprice * discountval2;
                    columnData.add(actualprice - disactval);
                } else {
                    columnData.add(actualprice);
                }
            }

        }

        return columnData;
    }

    public void getTotalprice() {
        totalp = Float.parseFloat(DecimalUtil.format2(Utilities.sumList(getPrice())));
        totalprice.setText(DecimalUtil.format2(totalp));
        curr.setText(MainAppController.B.getBCurrency());
    }

    private void repeatFocus(Node node) {
        Platform.runLater(() -> {
            if (!node.isFocused()) {
                node.requestFocus();
                repeatFocus(node);
            }
        });
    }

    private void resetItemDisplay() {
        itembarcode.setText(null);
        itemcartname.setText(null);
        itembrand.setText(null);
        itemqty.setText(null);
        itemsp.setText(null);
        curry.setText(null);
        itemimage1.setImage(null);
        qtyrem.setText(null);
        uomcombo.getItems().clear();
        qtyHbox.setStyle("-fx-background-color:#fff");
        qnttextfield.setText("1");
        repeatFocus(itembarcode);

    }

    @FXML
    private void searchitemsaction(ActionEvent event) throws IOException {
        StockinBL sbl = new StockinBL();
        itembarcode.selectAll();
        //System.out.println("cod: " + itembarcode.getText());
        Items item = new ItemsBL().getImageItembyCode(itembarcode.getText());
        if (item != null) {
            CP = item.getCp();
            itembarcode.setText(item.getUpc());
            itemcartname.setText(item.getItemDesc());
            itembrand.setText(item.getBrand().getBrandName());
            long qty = sbl.getStockBalance(itembarcode.getText());
            itemqty.setText(Long.toString(qty));
            if (qty > 0) {
                qtyrem.setText("Remaining");
                qtyHbox.setStyle("-fx-background-color:#1faa00");
                addtocartbtn.setDisable(false);
            } else {
                qtyrem.setText("Out of Stock");
                qtyHbox.setStyle("-fx-background-color:#ba000d");
                addtocartbtn.setDisable(true);
            }
            itemsp.setText(DecimalUtil.format2(item.getSp()));
            curry.setText(MainAppController.B.getBCurrency());
            FileInputStream input;
            try {
                input = new FileInputStream(item.getItemImg());
                Image image = new Image(input);
                itemimage1.setImage(image);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AddStockInController.class.getName()).log(Level.SEVERE, null, ex);
            }

            getUomsets(item.getUomset().getUomSetCode());
        } else {
            resetItemDisplay();
        }
        itembarcode.selectAll();
    }

}
