package bt.bitsmartmini.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import bt.bitsmartmini.bl.BusinessBL;
import bt.bitsmartmini.entity.Business;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class BusinessDisplayController implements Initializable {

    @FXML
    private Button closebtn;
    @FXML
    private Text bnametextfield;
    @FXML
    private Text baddtextfield;
    @FXML
    private Text bmobtextfield;
    @FXML
    private Text bemailtextfield;
    @FXML
    private Text country;
    @FXML
    private Text currency;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Business bb = new BusinessBL().findBusiness();
        bnametextfield.setText(bb.getBName());
        baddtextfield.setText(bb.getBAddr() + ", ");
        if (bb.getBMobile1() != null) {
            bmobtextfield.setText(bb.getBMobile() + " / " + bb.getBMobile1());
        } else {
            bmobtextfield.setText(bb.getBMobile());
        }
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
