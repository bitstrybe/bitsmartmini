
package bt.bitsmartmini.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import bt.bitsmartmini.bl.BusinessBL;
import bt.bitsmartmini.entity.Business;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class BusinessDisplayController implements Initializable {

    @FXML
    private Button closebtn;
    @FXML
    private Label bnametextfield;
    @FXML
    private Label baddtextfield;
    @FXML
    private Label bmobtextfield;
    @FXML
    private Label bemailtextfield;
    @FXML
    private Label country;
    @FXML
    private Label currency;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Business bb = new BusinessBL().findBusiness();
        bnametextfield.setText(bb.getBName());
        baddtextfield.setText(bb.getBAddr());
        bmobtextfield.setText(bb.getBMobile());
        bemailtextfield.setText(bb.getBEmail());
        country.setText(bb.getBCountry());
        currency.setText(bb.getBCurrency());
    }    

    @FXML
    private void closefrom(ActionEvent event) {
         Stage stage = (Stage) closebtn.getScene().getWindow();
        stage.close();
    }
    
}
