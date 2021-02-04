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
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author scarface
 */
public class MainAppController implements Initializable {

//    ObservableList<SelectItemSaleTableModel> data;
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
    private VBox sidemenu;
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
    public MenuItem addtocartbtn;
    @FXML
    private VBox catalog;
    @FXML
    private FontAwesomeIcon salesawesome;
    @FXML
    private Text salestext;

    public static HashMap<String, SelectItemSaleTableModel> cart;

    public static Business B;

    @FXML
    public Label cartnum;

    public static Label static_label;
    @FXML
    private MenuItem clearcart;
    @FXML
    private BorderPane boarderpane;
    @FXML
    private MenuButton cartmainbtn;
    @FXML
    private Menu pharmabits;

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
        mainmenu.setText(LoginController.u.getFname() + " " + LoginController.u.getLname());
        user.setText(LoginController.u.getUsername());
        user_role.setText(LoginController.u.getRoles());
        if (LoginController.u.getRoles().equals("Administrator") || LoginController.u.getRoles().equals("Supervisor")) {
            admintopmenu.setVisible(true);
            cartmainbtn.setVisible(true);
            stocks.setDisable(false);
            cartnum.setVisible(true);
            pharmabits.setVisible(true);
        } else {
            admintopmenu.setVisible(false);
            cartmainbtn.setVisible(true);
            sidemenu.getChildren().remove(stocks);
            cartnum.setVisible(true);
            pharmabits.setVisible(false);
        }
//        if (LoginController.u.getRoles().equals("Administrator") || LoginController.u.getRoles().equals("Sales Supervisor")) {
//           
//        } else {
//           
//        }

        try {
            setScene("Dashboard.fxml");
            dashboard.setStyle("-fx-background-color:#cbd0d4");
            dashawesome.setFill(Color.BLACK);
            dashtext.setFill(Color.BLACK);
            stocks.setStyle("-fx-background-color: transparent");
            sales.setStyle("-fx-background-color: transparent");
        } catch (IOException ex) {
            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
        }

        dashboard.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            try {
                setScene("Dashboard.fxml");
                dashboard.setStyle("-fx-background-color:#cbd0d4");
                dashawesome.setFill(Color.BLACK);
                dashtext.setFill(Color.BLACK);
                stockawesome.setFill(Color.valueOf("#dddee0"));
                stocktext.setFill(Color.valueOf("#dddee0"));
                catalogawesome.setFill(Color.valueOf("#dddee0"));
                catalogtext.setFill(Color.valueOf("#dddee0"));
                salesawesome.setFill(Color.valueOf("#dddee0"));
                salestext.setFill(Color.valueOf("#dddee0"));
                stocks.setStyle("-fx-background-color: transparent");
                sales.setStyle("-fx-background-color: transparent");
                catalog.setStyle("-fx-background-color: transparent");
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        stocks.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            try {
                setScene("Stock.fxml");
                stocks.setStyle("-fx-background-color:#cbd0d4");
                stockawesome.setFill(Color.BLACK);
                stocktext.setFill(Color.BLACK);
                dashawesome.setFill(Color.valueOf("#dddee0"));
                dashtext.setFill(Color.valueOf("#dddee0"));
                catalogawesome.setFill(Color.valueOf("#dddee0"));
                catalogtext.setFill(Color.valueOf("#dddee0"));
                salesawesome.setFill(Color.valueOf("#dddee0"));
                salestext.setFill(Color.valueOf("#dddee0"));
                sales.setStyle("-fx-background-color:  transparent");
                dashboard.setStyle("-fx-background-color: transparent");
                catalog.setStyle("-fx-background-color: transparent");
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        catalog.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            try {
                setScene("Catalog.fxml");
                catalog.setStyle("-fx-background-color:#cbd0d4");
                catalogawesome.setFill(Color.BLACK);
                catalogtext.setFill(Color.BLACK);
                dashawesome.setFill(Color.valueOf("#dddee0"));
                dashtext.setFill(Color.valueOf("#dddee0"));
                stockawesome.setFill(Color.valueOf("#dddee0"));
                stocktext.setFill(Color.valueOf("#dddee0"));
                salesawesome.setFill(Color.valueOf("#dddee0"));
                salestext.setFill(Color.valueOf("#dddee0"));
                stocks.setStyle("-fx-background-color:  transparent");
                dashboard.setStyle("-fx-background-color: transparent");
                sales.setStyle("-fx-background-color: transparent");
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        sales.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            try {
                setScene("Sales.fxml");
                sales.setStyle("-fx-background-color:#cbd0d4");
                salesawesome.setFill(Color.BLACK);
                salestext.setFill(Color.BLACK);
                dashawesome.setFill(Color.valueOf("#dddee0"));
                dashtext.setFill(Color.valueOf("#dddee0"));
                stockawesome.setFill(Color.valueOf("#dddee0"));
                stocktext.setFill(Color.valueOf("#dddee0"));
                catalogawesome.setFill(Color.valueOf("#dddee0"));
                catalogtext.setFill(Color.valueOf("#dddee0"));
                stocks.setStyle("-fx-background-color:  transparent");
                dashboard.setStyle("-fx-background-color: transparent");
                catalog.setStyle("-fx-background-color: transparent");
            } catch (IOException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        backup.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            try {
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Backup.fxml"));
                Parent parent = (Parent) fxmlLoader.load();
                Scene scene = new Scene(parent);
                // stage.initModality(Modality.APPLICATION_MODAL);
                stage.initOwner(parent.getScene().getWindow());
                stage.setScene(scene);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.resizableProperty().setValue(false);
                stage.showAndWait();
            } catch (IOException ex) {
                Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void setLabelText(String text) {
        //System.out.println("MainAppController.setLabelText(): Called");
        cartnum.setText(text);
    }

//    public static void Connecting(MainAppController controller) {
//        System.out.println("Connector.Connecting(): Called");
//        controller.setLabelText(String.valueOf(cart.size()));
//    }
//    public static void setCartNum(String num) {
//        cartnum.setText(num);
//    }
    @FXML
    public void signOut() {
        LoginBL mtd = new LoginBL();
        Userlogs l = mtd.getLogById(LoginController.log.getLogsid());
        l.setLogoutDatetime(new Date(System.currentTimeMillis()));
        //System.out.println(new Date(System.currentTimeMillis()));
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
//        double width = screenSize.getWidth();
////        double height = screenSize.getHeight();
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setMaximized(true);
        //stage.initModality(Modality.APPLICATION_MODAL);
//        stage.initOwner(parent.getScene().getWindow());
//        stage.setScene(scene);
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.resizableProperty().setValue(false);
        stage.show();
    }

    @FXML
    private void addmanufacturer(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddManufacturer.fxml"));
        Parent parent = (Parent) fxmlLoader.load();
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setMaximized(true);
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(parent.getScene().getWindow());
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        //stage.resizableProperty().setValue(false);
        //stage.initModality(Modality.APPLICATION_MODAL);
//        stage.initStyle(StageStyle.UNDECORATED);

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
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(parent.getScene().getWindow());
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        //stage.initModality(Modality.APPLICATION_MODAL);
//        stage.initStyle(StageStyle.UNDECORATED);
        //stage.resizableProperty().setValue(false);
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
//        double width = screenSize.getWidth();
////        double height = screenSize.getHeight();
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setMaximized(true);
        //stage.initModality(Modality.APPLICATION_MODAL);
//        stage.initOwner(parent.getScene().getWindow());
//        stage.setScene(scene);
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.resizableProperty().setValue(false);
        stage.show();
    }

    @FXML
    public void addtoCartAction() {
        try {
            setScene("ItemCart.fxml");
        } catch (IOException ex) {
            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void clearAllCartItem() {
        cart.clear();
        static_label.setText(String.valueOf(cart.size()));
    }

    @FXML
    private void addUsers(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Users.fxml"));
        Parent parent = (Parent) fxmlLoader.load();
//        double width = screenSize.getWidth();
////        double height = screenSize.getHeight();
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setMaximized(true);
        //stage.initModality(Modality.APPLICATION_MODAL);
//        stage.initOwner(parent.getScene().getWindow());
//        stage.setScene(scene);
//        stage.initStyle(StageStyle.UNDECORATED);
//        stage.resizableProperty().setValue(false);
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
        // }
//        try {
//            new PrintReport().showSalesReceipteport(new Date(), new Date());
//        } catch (JRException ex) {
//            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(MainAppController.class.getName()).log(Level.SEVERE, null, ex);
//        }
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
        //stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(parent.getScene().getWindow());
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        //stage.initModality(Modality.APPLICATION_MODAL);
//        stage.initStyle(StageStyle.UNDECORATED);
        //stage.resizableProperty().setValue(false);
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

}
