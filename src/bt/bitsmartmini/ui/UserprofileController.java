/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.bitsmartmini.ui;

import bt.bitsmartmini.bl.InsertUpdateBL;
import bt.bitsmartmini.bl.LoginBL;
import bt.bitsmartmini.entity.Users;
import static bt.bitsmartmini.ui.LoginController.u;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class UserprofileController implements Initializable {

    @FXML
    private Text fname;
    @FXML
    private Text lname;
    @FXML
    private Text email;
    @FXML
    private Text mobile1;
    @FXML
    private Text username;
    @FXML
    private Text passwd;
    @FXML
    private Text roles;
    @FXML
    private Label changepassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fname.setText(LoginController.u.getFname());
        lname.setText(LoginController.u.getLname());
        email.setText(LoginController.u.getEmail());
        mobile1.setText(LoginController.u.getMobile());
        username.setText(LoginController.u.getUsername());
        passwd.setText("****************");
        roles.setText(LoginController.u.getRoles());

        changepassword.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            try {
                getChangePasswordTemplate();
            } catch (IOException ex) {
                Logger.getLogger(UserprofileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    public void getChangePasswordTemplate() throws IOException {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangePassword.fxml"));
            Parent parent = (Parent) fxmlLoader.load();
            Scene scene = new Scene(parent);
            scene.setFill(Color.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.show();
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
                        user.setUserid(LoginController.u.getUserid());
                        user.setTitle(LoginController.u.getTitle());
                        user.setFname(LoginController.u.getFname());
                        user.setLname(LoginController.u.getLname());
                        user.setMobile(LoginController.u.getMobile());
                        user.setEmail(childController.emailaddress.getText());
                        user.setUsername(LoginController.u.getUsername());
                        user.setPwd(DigestUtils.md5Hex(new StringBuilder().append(childController.password.getText()).append(LoginBL.DKEY).toString()));
                        user.setRoles(u.getRoles());
                        user.setDateCreated(u.getDateCreated());
                        user.setModifiedDate(u.getModifiedDate());
                        user.setPwdStatus(1);
                        user.setActive(1);
                        int result = new InsertUpdateBL().updateData(user);
//                        System.out.println("match");
                        childController.login.setText("Password Changed");
                    }
                    stage.close();

                    //dolog();
                });
                Thread d = new Thread(task);
                d.setDaemon(true);
                d.start();
            });
    }
}
