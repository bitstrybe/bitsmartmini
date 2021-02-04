
package bt.bitsmartmini.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class AddNHISController implements Initializable {

    @FXML
    public JFXButton addnhis;
    @FXML
    public Button closeButton;
    @FXML
    public JFXToggleButton nhistoggle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void closemtd(ActionEvent event) {
    }
    
}
