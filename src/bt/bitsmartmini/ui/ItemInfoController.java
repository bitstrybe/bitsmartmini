
package bt.bitsmartmini.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class ItemInfoController implements Initializable {

    @FXML
    private Button closebtn;
    @FXML
    public ImageView itemimagename;
    @FXML
    public Label iteminfoname;
    @FXML
    public Label iteminfouom;
    @FXML
    public ImageView outofstockshape;
    @FXML
    public Label itemqty;
    @FXML
    public Label itemccost;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void closefrom(ActionEvent event) {
        Stage stage = (Stage) closebtn.getScene().getWindow();
        stage.close();
    }
    
}
