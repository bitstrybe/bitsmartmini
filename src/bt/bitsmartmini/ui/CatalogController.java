package bt.bitsmartmini.ui;

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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import bt.bitsmartmini.bl.ItemsBL;
import bt.bitsmartmini.bl.ReceiptBL;
import bt.bitsmartmini.bl.SalesBL;
import bt.bitsmartmini.bl.StockinBL;
import bt.bitsmartmini.entity.Items;
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

    @FXML
    private JFXTextField itemsearch;

    DecimalFormat df = new DecimalFormat("0.00");
    SalesBL sb = new SalesBL();
    ReceiptBL rec = new ReceiptBL();
    StockinBL sk = new StockinBL();
    double totalp;
    StockinBL st = new StockinBL();

    static int bal = 0;
    static int qntval1 = 0;
    static int stockbal = 0;
    String lsv;
    @FXML
    private FlowPane displaypane;

    public void getStockingItemList(String p) {
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
                        list = i.getItemsPerPage(8);
                    }
                    ObservableList<Items> result = FXCollections.observableArrayList(list);
                    result.forEach((items) -> {
                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ItemsDisplay.fxml"));
                            Parent parent = (Parent) fxmlLoader.load();
                            ItemsDisplayController childController = fxmlLoader.getController();
                            Long balance = new StockinBL().getStockBalance(items.getUpc());
                            if ("Supervisor".equals(LoginController.u.getRoles()) || "Administrator".equals(LoginController.u.getRoles())) {
                                childController.catalogstaus.getChildren().add(childController.adminstockin);
                                if (balance > 0) {
                                    childController.outofstocklabel.setVisible(false);
                                    childController.outofstockbackground.setVisible(false);
                                    childController.catalogstaus.getChildren().add(childController.adminstockout);
                                    childController.catalogstaus.getChildren().add(childController.addtocart);
                                }
                            }
                            if ("Sales".equals(LoginController.u.getRoles())) {
                                if (balance > 0) {
                                    childController.outofstocklabel.setVisible(false);
                                    childController.outofstockbackground.setVisible(false);
                                    childController.catalogstaus.getChildren().add(childController.addtocart);
                                }
                            }
                            childController.itembcode.setText(items.getUpc());
                            childController.medsname.setText(items.getItemDesc());
                            childController.brand.setText(items.getBrand().getBrandName());
                            Long bal = sk.getStockBalance(items.getUpc());
                            childController.qty.setText(bal.toString());
                            double prices = items.getSp();
                            childController.exp.setText(DecimalUtil.format2(prices));
                            childController.curr.setText(MainAppController.B.getBCurrency());
                            FileInputStream ifile = new FileInputStream(items.getItemImg());
                            Image image = new Image(ifile);
                            childController.itemsimage.setImage(image);
                            childController.itemsimage.setFitHeight(150);
                            childController.itemsimage.setFitWidth(150);
                            if (bal > 0) {
                                childController.outofstockbackground.setVisible(false);
                            } else {
                                childController.outofstockbackground.setVisible(true);
                            }
                            displaypane.getChildren().add(parent);
                            childController.adminstockin.setOnAction(v -> {
                                try {
                                    Stage stage = new Stage();
                                    FXMLLoader fxmlLoadersk = new FXMLLoader(getClass().getResource("AdminStockin.fxml"));
                                    Parent parentsk = (Parent) fxmlLoadersk.load();
                                    AdminStockinController childControllersk = fxmlLoadersk.getController();
                                    childControllersk.itemname.setText(items.getItemDesc());
                                    childControllersk.itembarcode.setText(childController.itembcode.getText());
                                    childControllersk.itemname.setText(childController.medsname.getText());
                                    childControllersk.itembrand.setText(childController.brand.getText());
                                    long qty = st.getStockBalance(childController.itembcode.getText());
                                    childControllersk.itemqty.setText(Long.toString(qty) + " Remaining");
                                    childControllersk.itemsp.setText(MainAppController.B.getBCurrency() + " " + childController.exp.getText());
                                    Items its = new ItemsBL().getImageItembyCode(childController.itembcode.getText());
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
                            childController.adminstockout.setOnAction(v -> {
                                try {
                                    Stage stage = new Stage();
                                    FXMLLoader fxmlLoadersk = new FXMLLoader(getClass().getResource("AdminStockout.fxml"));
                                    Parent parentsk = (Parent) fxmlLoadersk.load();
                                    AdminStockoutController childControllersk = fxmlLoadersk.getController();
                                    childControllersk.itemname.setText(items.getItemDesc());
                                    childControllersk.itembarcode.setText(childController.itembcode.getText());
                                    childControllersk.itemname.setText(childController.medsname.getText());
                                    childControllersk.itembrand.setText(childController.brand.getText());
                                    long qty = st.getStockBalance(childController.itembcode.getText());
                                    childControllersk.itemqty.setText(Long.toString(qty) + " Remaining");
                                    childControllersk.itemsp.setText(MainAppController.B.getBCurrency() + " " + childController.exp.getText());
                                    Items its = new ItemsBL().getImageItembyCode(childController.itembcode.getText());
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
                                    childControllerQnt.itemnamelabel.setText(items.getItemDesc());
                                    childControllerQnt.itemqty.setText(balance.toString());
                                    childControllerQnt.addtocartbtn.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                                        if (balance <= 0) {
                                            childControllerQnt.addtocartinfo.setText("Out of Stocks");
                                        } else {
                                            try {
                                                long stockinqty = new StockinBL().getStockInTotal(items.getUpc());
                                                long qntfield = Long.valueOf(childControllerQnt.qnttextfield.getText());
                                                if (qntfield <= balance) {
                                                    double totalqnt = qntfield * items.getSp();
                                                    SelectItemSaleTableModel item = new SelectItemSaleTableModel(items.getUpc(), items.getItemDesc(), childControllerQnt.qnttextfield.getText(), DecimalUtil.format2(items.getCp()), DecimalUtil.format2(items.getSp()), DecimalUtil.format2(totalqnt), "0");
                                                    System.out.println("item: "+items.getUpc());
                                                    System.out.println("item1: "+item.getItemCode());
                                                    System.out.println("qty: "+totalqnt);
                                                    cart.put(items.getUpc(), item);
                                                    static_label.setText(String.valueOf(cart.size()));
                                                    childControllerQnt.addtocartinfo.setText("item has been added to cart");
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
