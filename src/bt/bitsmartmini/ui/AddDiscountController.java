
package bt.bitsmartmini.ui;

import bt.bitsmartmini.utils.Utilities;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class AddDiscountController implements Initializable {

    @FXML
    private Button closebtn;
    @FXML
    public TextField discounttextfield;
    @FXML
    public Button discountbtn;
    @FXML
    private Label addtocartinfo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Utilities.repeatFocus(discounttextfield);
        // TODO
    }    

    @FXML
    private void closeForm(ActionEvent event) {
        Stage stage = (Stage) closebtn.getScene().getWindow();
        stage.close();
    }
    
}
