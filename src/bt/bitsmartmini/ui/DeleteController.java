
package bt.bitsmartmini.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class DeleteController implements Initializable {

    @FXML
    public JFXButton delete;
    @FXML
    public Label displayinfo;
    @FXML
    public JFXSpinner spinner;
    @FXML
    public FontAwesomeIcon check;
    @FXML
    public FontAwesomeIcon duplicatelock;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  

    @FXML
    private void closebtn(ActionEvent event) {
        Stage stage = (Stage) delete.getScene().getWindow();
        stage.close();
    }
   
    
}
