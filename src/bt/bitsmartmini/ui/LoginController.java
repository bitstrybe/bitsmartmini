package bt.bitsmartmini.ui;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import bt.bitsmartmini.bl.BusinessBL;
import bt.bitsmartmini.bl.LoginBL;
import bt.bitsmartmini.entity.Business;
import bt.bitsmartmini.entity.Userlogs;
import bt.bitsmartmini.entity.Users;
import lxe.utility.date.DateUtil;
import lxe.utility.encryptor.UrlEncryptor;
import org.apache.commons.codec.digest.DigestUtils;
import org.joda.time.DateTime;

/**
 *
 * @author jexshizzle
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    public static Users u;
    static Userlogs log;
    LoginBL loginbl = new LoginBL();

    @FXML
    private Button login;

    Image icon = new Image(getClass().getResourceAsStream("/bt/resources/bitsmart-logo-black.jpg"));

    @FXML
    private Hyperlink hyperlink;

    final WebView browser = new WebView();
    final WebEngine webEngine = browser.getEngine();

    @FXML
    private Label statusL;

    int i = 0;

    //Stage st;
    //Stage st = (Stage) login.getScene().getWindow();
    @FXML
    public void dolog() throws InterruptedException {

        //statusL.setText("Validating...");
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                updateMessage("Validating...");
                login.setDisable(true);
                Thread.sleep(1000);
                return null;
            }
        };
        statusL.textProperty().bind(task.messageProperty());
        task.setOnSucceeded(s -> {
            statusL.textProperty().unbind();
            if (i > 0) {
                statusL.getStyleClass().remove("error-label");
                statusL.setText("Login Successful");
                if (u != null && u.getPwdStatus() == 0) {
                    getChangePassword();
//                    st.show();
                } else {
                    if (u.getRoles().equals("Administrator")) {
                        validateLicense();
                    } else {
                        statusL.getStyleClass().add("error-label");//setTextFill(Paint.valueOf("#ff3333"));
                        statusL.setText("Your account is not allowed to perform this operation, Contact Admin!!!");
                    }
                }
            } else {
                statusL.getStyleClass().add("error-label");//setTextFill(Paint.valueOf("#ff3333"));
                statusL.setText("Login failed, please check your username or password and try again");
            }
        });

        task.setOnFailed(f -> {
            statusL.textProperty().unbind();
            statusL.getStyleClass().add("error-label");//setTextFill(Paint.valueOf("#ff3333"));
            statusL.setText("Could not validate, please try again");
        });

        task.setOnRunning(r -> {
            statusL.textProperty().bind(task.messageProperty());
            Users user = new Users();
            user.setUsername(username.getText());
            user.setPwd(password.getText());
            u = new LoginBL().validateUser(user);
            i = userLog();
        });

        Thread d = new Thread(task, "task1");
        //d.setDaemon(true);
        d.start();
    }

    @FXML

    public void exit() {
        System.exit(0);
    }

    @FXML
    public void resetElem() {
        statusL.textProperty().unbind();
        login.setDisable(false);
        statusL.setText("");
        statusL.getStyleClass().remove("error-label");
        //statusL.textProperty().bind(task.messageProperty());
    }

    public int userLog() {
        log = new Userlogs();
        log.setUsername(new Users(u.getUserid()));
        log.setLoginDatetime(new Date(System.currentTimeMillis()));
        int result = loginbl.insertData(log);
        return result;
    }

    public void visitUrl() {
        hyperlink.setOnAction(e -> {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                try {
                    Desktop.getDesktop().browse(new URI("http://www.google.com"));
                } catch (URISyntaxException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        statusL.setText("");
        LoginBL l = new LoginBL();
        visitUrl();
    }

    public void getMainApp() {
        try {
            Stage st = (Stage) login.getScene().getWindow();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("MainApp.fxml"));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.resizableProperty().setValue(false);
            stage.getIcons().add(icon);
            stage.setTitle("Pharmabits");
            stage.setMaximized(true);
            stage.setScene(new Scene(root));
            stage.show();
            st.hide();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getChangePassword() {
        try {
            Stage st = (Stage) login.getScene().getWindow();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangePassword.fxml"));
            Parent parent = (Parent) fxmlLoader.load();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.resizableProperty().setValue(false);
            stage.setScene(new Scene(parent));
            stage.show();
            st.hide();
            ChangePasswordController childController = fxmlLoader.getController();
//            System.out.println("Reached");
            childController.login.addEventHandler(MouseEvent.MOUSE_CLICKED, v -> {
                Task<Void> task = new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
//                        childController.spinner.setVisible(true);
                        updateMessage("Data Processing...");
                        Thread.sleep(1000);
                        return null;
                    }
                };
                childController.login.textProperty().bind(task.messageProperty());
                task.setOnSucceeded(s -> {
                    childController.login.textProperty().unbind();
                    if (!childController.repassword.getText().equals(childController.password.getText())) {
                        //System.out.println("Password does match");
                    } else {
                        Users user = new Users();
                        user.setUserid(u.getUserid());
                        user.setTitle(u.getTitle());
                        user.setFname(u.getFname());
                        user.setLname(u.getLname());
                        user.setMobile(u.getMobile());
                        user.setEmail(childController.emailaddress.getText());
                        user.setUsername(u.getUsername());
                        user.setPwd(DigestUtils.md5Hex(new StringBuilder().append(childController.password.getText()).append(LoginBL.DKEY).toString()));
                        user.setRoles(u.getRoles());
                        user.setDateCreated(u.getDateCreated());
                        user.setModifiedDate(u.getModifiedDate());
                        user.setPwdStatus(1);
                        user.setActive(1);
                        int result = loginbl.updateData(user);
//                        System.out.println("match");
                        childController.login.setText("Password Changed");
                    }
                    stage.close();
                    validateLicense();

                    //dolog();
                });
                Thread d = new Thread(task);
                d.setDaemon(true);
                d.start();
            });

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void validateLicense() {
        try {
            Business b = new BusinessBL().findBusiness();
            if (u.getRoles().equals("Administrator")) {
                if (b != null) {
                    int v = checkLicenseExpiry(b.getLicenseKey().getLicenseKey());
                    if (v == 0) {
                        getMainApp();
                    } else {
                        Stage st = (Stage) login.getScene().getWindow();
                        Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("VerifyLicense.fxml"));
                        Parent parent = (Parent) fxmlLoader.load();
                        stage.setScene(new Scene(parent));
                        stage.initStyle(StageStyle.TRANSPARENT);
                        stage.resizableProperty().setValue(false);
                        stage.show();
                        st.hide();
                    }
                } else {
                    Stage st = (Stage) login.getScene().getWindow();
                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("VerifyLicense.fxml"));
                    Parent parent = (Parent) fxmlLoader.load();
                    stage.setScene(new Scene(parent));
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.resizableProperty().setValue(false);
                    stage.show();
                    st.hide();
                }
            } else {
                getMainApp();
            }
            //VerifyLicenseController childController = fxmlLoader.getController();
            //Licensing l = new LicenseBL().findLicenses();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int checkLicenseExpiry(String Key) {
        try {
            UrlEncryptor enc = new UrlEncryptor();
            String etext = enc.doDecrypt(Key);
            //System.out.println("d: " + etext);
            String[] pt = etext.split(":");
            //bnametextfield.setText(pt[0]);
            if (new DateTime().toDate().after(new Date(DateUtil.parserTime(pt[3], "yyyy-MM-dd")))) {
                return 1;
            } else {
                return 0;

            }
        } catch (ParseException ex) {
            Logger.getLogger(VerifyLicenseController.class
                    .getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

}
