package bt.bitsmartmini.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class VerifyLicenseController implements Initializable {

    @FXML
    private Label statusL;
    @FXML
    private Hyperlink hyperlink;
    @FXML
    public JFXTextField licensetextfield;
    @FXML
    public JFXButton licenseAction;
    @FXML
    private Button closebtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
        
        

    @FXML
    private void closeform(ActionEvent event) {
        Stage stage = (Stage) closebtn.getScene().getWindow();
        stage.close();
    }

    public void doBusiness(String k) {
        try {
            Stage st = (Stage) closebtn.getScene().getWindow();
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Business.fxml"));
            Parent parent = (Parent) fxmlLoader.load();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.resizableProperty().setValue(false);
            stage.setScene(new Scene(parent));
            stage.show();
            st.close();
            BusinessController childController = fxmlLoader.getController();
            //System.out.println("ad: " + k);
            childController.checkLicenseKey(k);
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } //}
    }

    @FXML
    private void validateLicense(ActionEvent event) {
        try {
            if (licensetextfield.getText().isEmpty()) {
                doBusiness("trial");
            } else {
                doBusiness(licensetextfield.getText());
            }
        } catch (Exception ex) {

        }
    }

    

}
