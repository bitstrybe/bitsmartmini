
package bt.bitsmartmini.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
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
    public ImageView itemsimage;
    @FXML
    public HBox outofstockbackground;
    @FXML
    public Label outofstocklabel;
    @FXML
    public Text itembcode;
    @FXML
    public Text medsname;
    @FXML
    public Text brand;
    @FXML
    public Text qty;
    @FXML
    public Label curr;
    @FXML
    public Text exp;

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
