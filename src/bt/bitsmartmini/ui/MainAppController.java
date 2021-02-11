package bt.bitsmartmini.ui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import bt.bitsmartmini.bl.BusinessBL;
import bt.bitsmartmini.bl.LoginBL;
import bt.bitsmartmini.entity.Business;
import bt.bitsmartmini.entity.Userlogs;
import bt.bitsmartmini.tablemodel.SelectItemSaleTableModel;
import bt.bitsmartmini.utils.PrintReport;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author scarface
 */
public class MainAppController implements Initializable {

    @FXML
    private MenuButton mainmenu;
    @FXML
    private Label user;

    @FXML
    private VBox sales;
    @FXML
    private VBox stocks;
    @FXML
    private VBox dashboard;
    @FXML
    private Menu admintopmenu;
    @FXML
    private Label user_role;
    @FXML
    private VBox backup;
    @FXML
    private MenuItem reorderlevel;
    @FXML
    private FontAwesomeIcon dashawesome;
    @FXML
    private Text dashtext;
    @FXML
    private FontAwesomeIcon stockawesome;
    @FXML
    private Text stocktext;
    @FXML
    private FontAwesomeIcon catalogawesome;
    @FXML
    private Text catalogtext;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    @FXML
    private VBox catalog;
    @FXML
    private FontAwesomeIcon salesawesome;
    @FXML
    private Text salestext;

    public static HashMap<String, SelectItemSaleTableModel> cart;

    public static Business B;

    public Label cartnum;

    public static Label static_label;
    @FXML
    private BorderPane boarderpane;
    @FXML
    private Menu pharmabits;
    @FXML
    private FontAwesomeIcon backupawesome;
    @FXML
    private Text backuptext;
    @FXML
    private HBox topmenu;
    @FXML
    private VBox cartbox;

    public static final String SUCCESS_MESSAGE = "Saved";
    public static final String ERROR_MESSAGE = "There is an error please check and try again";
    //public static final String INFO_MESSAGE = "";
    //public static final String WARNING_MESSAGE;

    public void setScene(String scenechange) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource(scenechange));
        root.getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());
        boarderpane.setCenter(root);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        static_label = cartnum;
        cart = new HashMap<>();
        BusinessBL bl = new BusinessBL();
        B = bl.findBusiness();
        if (B != null) {

        }
//         cartnum.setText(String.valueOf(cart.size()));
        // TODO
        //init image directory
        File directory = new File("./img/");
        if (!directory.exists()) {
            directory.mkdir();
        }
        mainmenu.setText(LoginController.u.getUsername());
        user.setText(LoginController.u.getFname() + " " + LoginController.u.getLname());
        user_role.setText(LoginController.u.getRoles());
        if (LoginController.u.getRoles().equals("Administrator") || LoginController.u.getRoles().equals("Supervisor")) {
            admintopmenu.setVisible(true);
//            cartmainbtn.setVisible(true);
            stocks.setDisable(false);
            cartnum.setVisible(true);
            pharmabits.setVisible(true);
        } else {
            admintopmenu.setVisible(false);
//            cartmainbtn.setVisible(true);
            topmenu.getChildren().remove(stocks);
            cartnum.setVisible(true);
            pharmabits.setVisible(false);
        }

        try {
            setScene("Dashboard.fxml");
            //Dashboard is Active
            dashboard.getStyleClass().add("hoverVboxActive");
            dashawesome.getStyleClass().clear();
            dashawesome.getStyleClass().add("ActiveSideTextAndFont");
            dashtext.getStyleClass().clear();
            dashtext.getStyleClass().add("ActiveSideTextAndFont");
            //Catalog
            catalog.getStyleClass().clear();
            catalog.getStyleClass().add("hoverVbox");
            catalogawesome.getStyleClass().add("hovericons");
            catalogawesome.getStyleClass().add("InactiveSideTextAndFont");
            catalogtext.getStyleClass().add("hovericons");
            catalogtext.getStyleClass().add("InactiveSideTextAndFont");
            //Sales
            sales.getStyleClass().clear();
            sales.getStyleClass().add("hoverVbox");
            salesawesome.getStyleClass().add("hovericons");
            salesawesome.getStyleClass().add("InactiveSideTextAndFont");
            salestext.getStyleClass().add("hovericons");
            salestext.getStyleClass().add("InactiveSideTextAndFont");
            //Stocks
            stocks.getStyleClass().clear();
            stocks.getStyleClass().add("hoverVbox");
            stockawesome.getStyleClass().add("hovericons");
            stockawesome.getStyleClass().add("InactiveSideTextAndFont");
            stocktext.getStyleClass().add("hovericons");
            stocktext.getStyleClass().add("InactiveSideTextAndFont");
            //Backup
            backup.getStyleClass().add("hoverVbox");
            backupawesome.getStyleClass().add("hovericons");
            backupawesome.getStyleClass().add("InactiveSideTextAndFont");
            backuptext.getStyleClass().add("hovericons");
            backuptext.getStyleClass().add("InactiveSideTextAndFont");
            //Cart
            cartbox.getStyleClass().clear();
            cartbox.getStyleClass().add("hoverVbox");

        } catch (IOException ex) {
            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
        }

        dashboard.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            try {
                setScene("Dashboard.fxml");
                //Dashboard is Active
                dashboard.getStyleClass().add("hoverVboxActive");
                dashawesome.getStyleClass().clear();
                dashawesome.getStyleClass().add("ActiveSideTextAndFont");
                dashtext.getStyleClass().clear();
                dashtext.getStyleClass().add("ActiveSideTextAndFont");
                //Catalog
                catalog.getStyleClass().clear();
                catalog.getStyleClass().add("hoverVbox");
                catalogawesome.getStyleClass().add("hovericons");
                catalogawesome.getStyleClass().add("InactiveSideTextAndFont");
                catalogtext.getStyleClass().add("hovericons");
                catalogtext.getStyleClass().add("InactiveSideTextAndFont");
                //Sales
                sales.getStyleClass().clear();
                sales.getStyleClass().add("hoverVbox");
                salesawesome.getStyleClass().add("hovericons");
                salesawesome.getStyleClass().add("InactiveSideTextAndFont");
                salestext.getStyleClass().add("hovericons");
                salestext.getStyleClass().add("InactiveSideTextAndFont");
                //Stocks
                stocks.getStyleClass().clear();
                stocks.getStyleClass().add("hoverVbox");
                stockawesome.getStyleClass().add("hovericons");
                stockawesome.getStyleClass().add("InactiveSideTextAndFont");
                stocktext.getStyleClass().add("hovericons");
                stocktext.getStyleClass().add("InactiveSideTextAndFont");
                //Backup
                backup.getStyleClass().add("hoverVbox");
                backupawesome.getStyleClass().add("hovericons");
                backupawesome.getStyleClass().add("InactiveSideTextAndFont");
                backuptext.getStyleClass().add("hovericons");
                backuptext.getStyleClass().add("InactiveSideTextAndFont");
                //Cart
                cartbox.getStyleClass().clear();
                cartbox.getStyleClass().add("hoverVbox");

            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        catalog.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            try {
                setScene("Catalog.fxml");
                //Dashboard
                dashboard.getStyleClass().clear();
                dashboard.getStyleClass().add("hoverVbox");
                dashawesome.getStyleClass().add("hovericons");
                dashawesome.getStyleClass().add("InactiveSideTextAndFont");
                dashtext.getStyleClass().add("hovericons");
                dashtext.getStyleClass().add("InactiveSideTextAndFont");
                //Catalog is Active
                catalog.getStyleClass().add("hoverVboxActive");
                catalogawesome.getStyleClass().clear();
                catalogawesome.getStyleClass().add("ActiveSideTextAndFont");
                catalogtext.getStyleClass().clear();
                catalogtext.getStyleClass().add("ActiveSideTextAndFont");
                //Sales
                sales.getStyleClass().clear();
                sales.getStyleClass().add("hoverVbox");
                salesawesome.getStyleClass().add("hovericons");
                salesawesome.getStyleClass().add("InactiveSideTextAndFont");
                salestext.getStyleClass().add("hovericons");
                salestext.getStyleClass().add("InactiveSideTextAndFont");
                //Stocks
                stocks.getStyleClass().clear();
                stocks.getStyleClass().add("hoverVbox");
                stockawesome.getStyleClass().add("hovericons");
                stockawesome.getStyleClass().add("InactiveSideTextAndFont");
                stocktext.getStyleClass().add("hovericons");
                stocktext.getStyleClass().add("InactiveSideTextAndFont");
                //Cart
                cartbox.getStyleClass().clear();
                cartbox.getStyleClass().add("hoverVbox");
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        sales.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            try {
                setScene("Sales.fxml");
                //Dashboard
                dashboard.getStyleClass().clear();
                dashboard.getStyleClass().add("hoverVbox");
                dashawesome.getStyleClass().add("hovericons");
                dashawesome.getStyleClass().add("InactiveSideTextAndFont");
                dashtext.getStyleClass().add("hovericons");
                dashtext.getStyleClass().add("InactiveSideTextAndFont");
                //Catalog
                catalog.getStyleClass().clear();
                catalog.getStyleClass().add("hoverVbox");
                catalogawesome.getStyleClass().add("hovericons");
                catalogawesome.getStyleClass().add("InactiveSideTextAndFont");
                catalogtext.getStyleClass().add("hovericons");
                catalogtext.getStyleClass().add("InactiveSideTextAndFont");
                //Sales is Active
                sales.getStyleClass().add("hoverVboxActive");
                salesawesome.getStyleClass().clear();
                salesawesome.getStyleClass().add("ActiveSideTextAndFont");
                salestext.getStyleClass().clear();
                salestext.getStyleClass().add("ActiveSideTextAndFont");
                //Stocks
                stocks.getStyleClass().clear();
                stocks.getStyleClass().add("hoverVbox");
                stockawesome.getStyleClass().add("hovericons");
                stockawesome.getStyleClass().add("InactiveSideTextAndFont");
                stocktext.getStyleClass().add("hovericons");
                stocktext.getStyleClass().add("InactiveSideTextAndFont");
                //Cart
                cartbox.getStyleClass().clear();
                cartbox.getStyleClass().add("hoverVbox");
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        stocks.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            try {
                setScene("Stock.fxml");
                //Dashboard
                dashboard.getStyleClass().clear();
                dashboard.getStyleClass().add("hoverVbox");
                dashawesome.getStyleClass().add("hovericons");
                dashawesome.getStyleClass().add("InactiveSideTextAndFont");
                dashtext.getStyleClass().add("hovericons");
                dashtext.getStyleClass().add("InactiveSideTextAndFont");
                //Catalog
                catalog.getStyleClass().clear();
                catalog.getStyleClass().add("hoverVbox");
                catalogawesome.getStyleClass().add("hovericons");
                catalogawesome.getStyleClass().add("InactiveSideTextAndFont");
                catalogtext.getStyleClass().add("hovericons");
                catalogtext.getStyleClass().add("InactiveSideTextAndFont");
                //Sales
                sales.getStyleClass().clear();
                sales.getStyleClass().add("hoverVbox");
                salesawesome.getStyleClass().add("hovericons");
                salesawesome.getStyleClass().add("InactiveSideTextAndFont");
                salestext.getStyleClass().add("hovericons");
                salestext.getStyleClass().add("InactiveSideTextAndFont");
                //Stocks is Active
                stocks.getStyleClass().add("hoverVboxActive");
                stockawesome.getStyleClass().clear();
                stockawesome.getStyleClass().add("ActiveSideTextAndFont");
                stocktext.getStyleClass().clear();
                stocktext.getStyleClass().add("ActiveSideTextAndFont");
                //Backup
                backup.getStyleClass().add("hoverVbox");
                backupawesome.getStyleClass().add("hovericons");
                backupawesome.getStyleClass().add("InactiveSideTextAndFont");
                backuptext.getStyleClass().add("hovericons");
                backuptext.getStyleClass().add("InactiveSideTextAndFont");
                //Cart
                cartbox.getStyleClass().clear();
                cartbox.getStyleClass().add("hoverVbox");

            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        backup.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            try {
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DataMgnt.fxml"));
                Parent parent = (Parent) fxmlLoader.load();
                Scene scene = new Scene(parent);
                scene.setFill(Color.TRANSPARENT);
                stage.setScene(scene);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setMaximized(true);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        cartbox.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            try {
                setScene("ItemCart.fxml");
                //Dashboard
                dashboard.getStyleClass().clear();
                dashboard.getStyleClass().add("hoverVbox");
                dashawesome.getStyleClass().add("hovericons");
                dashawesome.getStyleClass().add("InactiveSideTextAndFont");
                dashtext.getStyleClass().add("hovericons");
                dashtext.getStyleClass().add("InactiveSideTextAndFont");
                //Catalog
                catalog.getStyleClass().clear();
                catalog.getStyleClass().add("hoverVbox");
                catalogawesome.getStyleClass().add("hovericons");
                catalogawesome.getStyleClass().add("InactiveSideTextAndFont");
                catalogtext.getStyleClass().add("hovericons");
                catalogtext.getStyleClass().add("InactiveSideTextAndFont");
                //Sales
                sales.getStyleClass().clear();
                sales.getStyleClass().add("hoverVbox");
                salesawesome.getStyleClass().add("hovericons");
                salesawesome.getStyleClass().add("InactiveSideTextAndFont");
                salestext.getStyleClass().add("hovericons");
                salestext.getStyleClass().add("InactiveSideTextAndFont");
                //Stocks
                stocks.getStyleClass().clear();
                stocks.getStyleClass().add("hoverVbox");
                stockawesome.getStyleClass().add("hovericons");
                stockawesome.getStyleClass().add("InactiveSideTextAndFont");
                stocktext.getStyleClass().add("hovericons");
                stocktext.getStyleClass().add("InactiveSideTextAndFont");
                //Cart is Active
                cartbox.getStyleClass().add("hoverVboxActive");
//                stockawesome.getStyleClass().add("InactiveSideTextAndFont");
//                stocktext.getStyleClass().add("hovericons");
//                stocktext.getStyleClass().add("InactiveSideTextAndFont");

            } catch (IOException ex) {
                Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void setLabelText(String text) {
        cartnum.setText(text);
    }

    @FXML
    public void signOut() {
        LoginBL mtd = new LoginBL();
        Userlogs l = mtd.getLogById(LoginController.log.getLogsid());
        l.setLogoutDatetime(new Date(System.currentTimeMillis()));
        int result = mtd.updateData(l);
        if (result == 1) {
            try {
                Stage st = (Stage) mainmenu.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                Image icon = new Image(getClass().getResourceAsStream("/bt/resources/meds_logo.png"));
                stage.getIcons().add(icon);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
                st.hide();
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Unable to log out");
        }

    }

    @FXML
    private void addcategory(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddCategory.fxml"));
        Parent parent = (Parent) fxmlLoader.load();
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setMaximized(true);
        stage.show();
    }

    @FXML
    private void additems(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddItems.fxml"));
        Parent parent = (Parent) fxmlLoader.load();
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
    private void stockreorderlevel(ActionEvent event) {
        try {
            new PrintReport().showStockReorderReport();
        } catch (JRException ex) {
            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void minimize(ActionEvent event) {
        Stage stage = (Stage) boarderpane.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void stocksReport(ActionEvent event) {
        try {
            new PrintReport().showStocksReport();
        } catch (JRException ex) {
            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void stocksActivityReport(ActionEvent event) {
        try {
            new PrintReport().showStocksDetailsReport();
        } catch (JRException ex) {
            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void itemlistreport(ActionEvent event) {
        try {
            new PrintReport().showItemListReport();
        } catch (JRException ex) {
            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void addUom(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddUom.fxml"));
        Parent parent = (Parent) fxmlLoader.load();
        Scene scene = new Scene(parent);
        stage.setMaximized(true);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        //stage.initModality(Modality.APPLICATION_MODAL);
//        stage.initOwner(parent.getScene().getWindow());
//        stage.setScene(scene);
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.resizableProperty().setValue(false);
        stage.show();
    }

    @FXML
    private void addcustomer(ActionEvent event) throws IOException {

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Customer.fxml"));
        Parent parent = (Parent) fxmlLoader.load();
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setMaximized(true);
        stage.show();
    }

    public void clearAllCartItem() {
        cart.clear();
        static_label.setText(String.valueOf(cart.size()));
    }

    @FXML
    private void addUsers(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Users.fxml"));
        Parent parent = (Parent) fxmlLoader.load();
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setMaximized(true);
        stage.show();
    }

    @FXML
    private void salesReceiptReport(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SalesReceiptReport.fxml"));
        Parent parent = (Parent) fxmlLoader.load();
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setMaximized(true);
        stage.show();
    }

    private void debtorsByCustomerReport(ActionEvent event) {
        try {
            new PrintReport().showDebtorsByCustomerReport("");

        } catch (JRException ex) {
            Logger.getLogger(MainAppController.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainAppController.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(MainAppController.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(MainAppController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void debtorsBySalesReport(ActionEvent event) {
        try {
            new PrintReport().showDebtorsByCustomerReport("");

        } catch (JRException ex) {
            Logger.getLogger(MainAppController.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MainAppController.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(MainAppController.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(MainAppController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void debtorsReport(ActionEvent event) {
    }

    @FXML
    private void getbusiness(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BusinessDisplay.fxml"));
        Parent parent = (Parent) fxmlLoader.load();
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
    private void refundPolicyView(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RefundPolicy.fxml"));
        Parent parent = (Parent) fxmlLoader.load();
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setMaximized(true);
        stage.show();
    }

    @FXML
    private void addbrand(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddBrand.fxml"));
        Parent parent = (Parent) fxmlLoader.load();
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setMaximized(true);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(parent.getScene().getWindow());
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

}
