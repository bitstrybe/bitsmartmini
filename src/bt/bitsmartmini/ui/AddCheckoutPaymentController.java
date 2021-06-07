package bt.bitsmartmini.ui;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextArea;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class AddCheckoutPaymentController implements Initializable {

    @FXML
    private Button closebtn;
    @FXML
    public Button chekoutpaybtn;
    @FXML
    public TextField checkoutpaytextfield;
    public ToggleGroup tg;
    @FXML
    private JFXTextArea receiptremarks;
    @FXML
    public Label displayinfo;
    @FXML
    public FontAwesomeIcon check;
    @FXML
    public FontAwesomeIcon duplicatelock;
    @FXML
    public JFXSpinner spinner;
    @FXML
    private Label titletext;
    @FXML
    private JFXRadioButton cash;
    @FXML
    private JFXRadioButton momo;
    @FXML
    private JFXRadioButton cc;
    @FXML
    public TextField amountpaid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tg = new ToggleGroup();
        // TODO
        checkoutpaytextfield.setText("0.00");
        cash.setToggleGroup(tg);
        momo.setToggleGroup(tg);
        cc.setToggleGroup(tg);
        titletext.setText("Confirm Cash Out In " + MainAppController.B.getBCurrency());
    }

    @FXML
    private void closeForm(ActionEvent event) {
        Stage stage = (Stage) closebtn.getScene().getWindow();
        stage.close();
    }

}
