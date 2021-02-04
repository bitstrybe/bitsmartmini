package bt.bitsmartmini.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import bt.bitsmartmini.bl.ItemsBL;
import bt.bitsmartmini.bl.ItemspriceBL;
import bt.bitsmartmini.bl.ReceiptBL;
import bt.bitsmartmini.bl.SalesBL;
import bt.bitsmartmini.bl.StockinBL;
import bt.bitsmartmini.entity.Items;
import bt.bitsmartmini.entity.ItemsPrice;
//import bt.bitsmartmini.entity.UomDef;
import bt.bitsmartmini.tablemodel.SalesDetailsTableModel;
import bt.bitsmartmini.tablemodel.SalesTableModel;
import bt.bitsmartmini.tablemodel.SelectItemSaleTableModel;
import static bt.bitsmartmini.ui.MainAppController.cart;
import static bt.bitsmartmini.ui.MainAppController.static_label;
import lxe.utility.math.DecimalUtil;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class CatalogController extends MainAppController implements Initializable {

    private final ObservableList<SelectItemSaleTableModel> data = FXCollections.observableArrayList();
    ObservableList<String> search;
    ObservableList<SalesTableModel> salesdata;
    ObservableList<SalesDetailsTableModel> salesdetailsdata;
//    private final ObservableList<Person> data
//            = FXCollections.observableArrayList();

    @FXML
    private JFXTextField itemsearch;
    private TableView<SelectItemSaleTableModel> seleteditemtableview;
    private TableColumn<SelectItemSaleTableModel, String> batchcode;
    private TableColumn<SelectItemSaleTableModel, String> itemname;
    private TableColumn<SelectItemSaleTableModel, Number> qnt;
    private TableColumn<SelectItemSaleTableModel, Number> price;
    private TableColumn<SelectItemSaleTableModel, Number> total;

    private TableColumn<SelectItemSaleTableModel, Number> nhisvalprice;
    private TableColumn<SelectItemSaleTableModel, String> nhis;
    private TableColumn<SelectItemSaleTableModel, Boolean> action;
    private TableColumn<SelectItemSaleTableModel, Boolean> discount;
    private TableColumn<SelectItemSaleTableModel, Number> discountval;
    private JFXButton sellbtn;
    private Label totalprice;
    //Sales Table
    private TableView<SalesTableModel> salestable;
    private TableColumn<SalesTableModel, Number> salecode;
    private TableColumn<SalesTableModel, Number> saleprice;
    private TableColumn<SalesTableModel, String> date;
    private TableColumn<SalesTableModel, Number> paidvalue;
    private TableColumn<SalesTableModel, String> salesbalance;
    private TableColumn<SalesTableModel, Boolean> action1;
    private TableColumn<SalesTableModel, Boolean> action2;
    private TableColumn<SalesTableModel, Boolean> actionsales;
    //Sales Details Table
    private TableView<SalesDetailsTableModel> salesdetailstable;
    private TableColumn<SalesDetailsTableModel, String> batchno;
    private TableColumn<SalesDetailsTableModel, Number> scode;
    private TableColumn<SalesDetailsTableModel, Number> qty;
    private TableColumn<SalesDetailsTableModel, Number> sp;
    private TableColumn<SalesDetailsTableModel, Number> np;
    private TableColumn<SalesDetailsTableModel, String> ns;
    private TableColumn<SalesDetailsTableModel, Number> discountsalesdetails;
    private DatePicker startdate;
    private DatePicker enddate;
    private Label totalsalesprice;

    DecimalFormat df = new DecimalFormat("0.00");
    SalesBL sb = new SalesBL();
    ReceiptBL rec = new ReceiptBL();
    double totalp;

    static int bal = 0;
    static int qntval1 = 0;
    static int stockbal = 0;
    String lsv;
    @FXML
    private FlowPane displaypane;

    public void getStockingItemList(String p) {
        //displaypane.getChildren().clear();
        Task<Void> longRunningTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Platform.runLater(() -> {
                    displaypane.getChildren().clear();
                    ItemsBL i = new ItemsBL();
                    List<Items> list;
                    if (p.length() > 0) {
                        list = new ItemsBL().searchAllItems(p);
                    } else {
                        list = i.getItemsPerPage(12);
                    }
                    ItemspriceBL itb = new ItemspriceBL();
                    ObservableList<Items> result = FXCollections.observableArrayList(list);
                    result.forEach((items) -> {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ItemsDisplay.fxml"));
                            Parent parent = (Parent) fxmlLoader.load();
                            ItemsDisplayController childController = fxmlLoader.getController();
                            Long balance = new StockinBL().getStockBalance(items.getItemDesc());
                            if ("Supervisor".equals(LoginController.u.getRoles()) || "Administrator".equals(LoginController.u.getRoles())) {
                                // childController.catalogstaus.getChildren().remove(childController.adminstockout);
                                childController.catalogstaus.getChildren().add(childController.adminstockin);
                                if (balance > 0) {
                                    childController.outofstockshape.setVisible(false);
                                    //childController.catalogstaus.getChildren().remove(childController.addtocart);
                                    childController.catalogstaus.getChildren().add(childController.adminstockout);
                                    childController.catalogstaus.getChildren().add(childController.addtocart);
                                    //childController.adminstockout.setDisable(true);
                                }
                            }
                            if ("Sales".equals(LoginController.u.getRoles())) {
                                //childController.outofstockshape.setVisible(true);
                                //childController.catalogstaus.getChildren().add(childController.addtocart);
                                //childController.adminstockout.setDisable(true);
                                if (balance > 0) {
                                    childController.outofstockshape.setVisible(false);
                                    //childController.catalogstaus.getChildren().remove(childController.addtocart);
                                    childController.catalogstaus.getChildren().add(childController.addtocart);
                                    //childController.adminstockout.setDisable(true);
                                }

                            }
                            childController.medsname.setText(items.getItemName());
                            // UomDef domf = new UomBL().getUombyItemId(items.getItemDesc());
                            //int uomitem = domf.getUomItem();
                            String uom_val = String.valueOf(items.getUomDef().getUomDesc());
                            childController.uom.setText(balance + " " + uom_val + "(s) In Stock");
                            //childController.cat.setText(stock.getItems().getForm().getFormName());
                            childController.man.setText(items.getManufacturer().getManufacturer());
                            ItemsPrice itp = itb.getItemspricebyItemName(items.getItemDesc());
                            //System.out.println("Item Price :" + itp.getSalesPrice());
                            double prices = itp.getSalesPrice();
                            //childController.curr.setText("GHC");
                            childController.exp.setText(DecimalUtil.format2(prices));
                            childController.curr.setText(MainAppController.B.getBCurrency());
                            // childController.exp.setText(String.valueOf(stock.getExpiryDate()));
                            FileInputStream ifile = new FileInputStream(items.getItemImg());
                            Image image = new Image(ifile);
                            childController.itemsimage.setImage(image);
                            childController.itemsimage.setFitHeight(150);
                            childController.itemsimage.setFitWidth(150);
                            displaypane.getChildren().add(parent);
                            //Catalog Administrator Stockin starts
                            childController.adminstockin.setOnAction(v -> {
                                try {
                                    Stage stage = new Stage();
                                    FXMLLoader fxmlLoadersk = new FXMLLoader(getClass().getResource("AdminStockin.fxml"));
                                    Parent parentsk = (Parent) fxmlLoadersk.load();
                                    AdminStockinController childControllersk = fxmlLoadersk.getController();
                                    childControllersk.itemname.setText(items.getItemDesc());
                                    //UomDef ud = new UomBL().getUombyItemId(itemlist.getSelectionModel().getSelectedItem());
                                    Items its = new ItemsBL().getImageItembyCode(items.getItemDesc());
                                    //uomitem.setText(ud.getUomCode().getUomDesc() + " " + ud.getUomNm() + " X " + ud.getUomDm());
                                    FileInputStream input;
                                    input = new FileInputStream(its.getItemImg());
                                    Image imagesk = new Image(input);
                                    childControllersk.itemimage.setImage(image);
                                    childControllersk.save.setDisable(false);
                                    childControllersk.save.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                                        childControllersk.save.setDisable(true);
                                        Task<Void> task = new Task<Void>() {
                                            @Override
                                            protected Void call() throws Exception {
                                                childControllersk.spinner.setVisible(true);
                                                childControllersk.check.setVisible(false);
                                                updateMessage("PROCESSING PLS WAIT.....");
                                                Thread.sleep(1000);
                                                return null;
                                            }
                                        };
                                        childControllersk.displayinfo.textProperty().bind(task.messageProperty());
                                        task.setOnSucceeded(s -> {
                                            childControllersk.saveTemplate();
                                            getStockingItemList(itemsearch.getText());
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
                            });

                            //Catalog Administrator Stockout starts
                            childController.adminstockout.setOnAction(v -> {
                                try {
                                    Stage stage = new Stage();
                                    FXMLLoader fxmlLoadersk = new FXMLLoader(getClass().getResource("AdminStockout.fxml"));
                                    Parent parentsk = (Parent) fxmlLoadersk.load();
                                    AdminStockoutController childControllersk = fxmlLoadersk.getController();
                                    childControllersk.itemname.setText(items.getItemDesc());
                                    //UomDef ud = new UomBL().getUombyItemId(itemlist.getSelectionModel().getSelectedItem());
                                    Items its = new ItemsBL().getImageItembyCode(items.getItemDesc());
                                    //uomitem.setText(ud.getUomCode().getUomDesc() + " " + ud.getUomNm() + " X " + ud.getUomDm());
                                    FileInputStream input;
                                    input = new FileInputStream(its.getItemImg());
                                    Image imagesk = new Image(input);
                                    childControllersk.itemimage.setImage(image);
                                    childControllersk.save.setDisable(false);

                                    childControllersk.save.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                                        childControllersk.save.setDisable(true);
                                        Task<Void> task = new Task<Void>() {
                                            @Override
                                            protected Void call() throws Exception {
                                                childControllersk.spinner.setVisible(true);
                                                childControllersk.check.setVisible(false);
                                                updateMessage("PROCESSING PLS WAIT.....");
                                                Thread.sleep(1000);
                                                return null;
                                            }
                                        };
                                        childControllersk.displayinfo.textProperty().bind(task.messageProperty());
                                        task.setOnSucceeded(s -> {
                                            childControllersk.saveTemplate();
                                            getStockingItemList(itemsearch.getText());
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
                            });

                            //Sales Add to cart starts
                            childController.addtocart.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                                try {
                                    Stage stage = new Stage();
                                    FXMLLoader fxmlLoaderQnt = new FXMLLoader(getClass().getResource("AddToCart.fxml"));
                                    Parent parentQtn = (Parent) fxmlLoaderQnt.load();
                                    AddToCartController childControllerQnt = fxmlLoaderQnt.getController();
                                    ItemsPrice itprice = new ItemsBL().getItemsPriceByItemDesc(items.getItemDesc());
                                    childControllerQnt.itemnamelabel.setText(items.getItemDesc());

                                    childControllerQnt.itemqty.setText(balance.toString());
                                    childControllerQnt.addtocartbtn.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                                        if (balance <= 0) {
                                            childControllerQnt.addtocartinfo.setText("Out of Stocks");
                                        } else {
                                            try {
                                                long stockinqty = new StockinBL().getStockInTotal(items.getItemDesc());
                                                long qntfield = Long.valueOf(childControllerQnt.qnttextfield.getText());
                                                if (qntfield <= balance) {
                                                    double totalqnt = qntfield * itprice.getSalesPrice();
                                                    //UomDef uom = new UomBL().getUombyItemId(items.getItemDesc());
                                                    SelectItemSaleTableModel item = new SelectItemSaleTableModel(items.getItemDesc(), Integer.valueOf(childControllerQnt.qnttextfield.getText()), itprice.getCostPrice(), itprice.getSalesPrice(), totalqnt, 0, items.getUomDef().getUomDesc());
                                                    cart.put(item.getItemName(), item);
                                                    static_label.setText(String.valueOf(cart.size()));
                                                    childControllerQnt.addtocartinfo.setText("added to cart");
                                                } else {
                                                    childControllerQnt.addtocartinfo.setText("Not enough Quantity");
                                                }
                                            } catch (Exception ex) {
                                                childControllerQnt.addtocartinfo.setText("Invalid Format");
                                                Logger.getLogger(CatalogController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                    });
                                    Scene scene = new Scene(parentQtn);
                                    scene.setFill(Color.TRANSPARENT);
                                    stage.setScene(scene);
                                    stage.initStyle(StageStyle.TRANSPARENT);
                                    stage.setMaximized(true);
                                    stage.show();
                                } catch (IOException ex) {
                                    Logger.getLogger(CatalogController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });
                            //Return Items starts
                        } catch (IOException ex) {
                            Logger.getLogger(CatalogController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                });
                return null;
            }
        };
        new Thread(longRunningTask).start();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getStockingItemList("");
        itemsearch.textProperty().addListener(e -> {
            //if (itemsearch.getText().length() > 4) {
            getStockingItemList(itemsearch.getText());
//            } else {
//                getStockingItemList("");
//            }
        });

    }

}
