/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.bitsmartmini.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.apache.commons.validator.EmailValidator;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class ChangePasswordController implements Initializable {

    @FXML
    public JFXPasswordField password;
    @FXML
    public JFXPasswordField repassword;
    @FXML
    public JFXTextField emailaddress;
    @FXML
    public JFXButton login;

    public EmailValidator validattor;
    @FXML
    public Label statusL;
    @FXML
    private Hyperlink hyperlink;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        statusL.setVisible(false);
        // TODO
//        doEmailValidate();
//        RegexValidator valid = new RegexValidator();
//        valid.setRegexPattern("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
//                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
//        emailaddress.setValidators(valid);
//        emailaddress.getValidators().get(0).setMessage("Email is not valid!");
    }

//    private void doEmailValidate() {
//        emailaddress.focusedProperty().addListener(new ChangeListener() {
//            @Override
//            public void changed(ObservableValue arg0, Object oldPropertyValue, Object newPropertyValue) {
//                if (!newPropertyValue.equals(true)) {
//                    boolean val = emailaddress.validate();
//                    if (val) {
//                        login.setDisable(val);
//                    }
//                }
//            }
//        });
//
//    }

    @FXML
    private void dolog(ActionEvent event) {
    }

    @FXML
    private void exit(ActionEvent event) {
        Stage stage = (Stage) login.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void resetElem(KeyEvent event) {
    }

}
