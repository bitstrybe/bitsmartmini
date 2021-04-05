package bt.bitsmartmini.ui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import bt.bitsmartmini.bl.StockinBL;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class AddToCartController implements Initializable {

    @FXML
    public Button closebtn;
    @FXML
    private Button plusButton;
    @FXML
    private Button minusButton;
    @FXML
    public Button addtocartbtn;

    AtomicInteger rowCounter = new AtomicInteger(0);
    public Button addtocartbtn1;
    @FXML
    public Label addtocartinfo;
    @FXML
    public Label itemnamelabel;
    @FXML
    public TextField qnttextfield;
    @FXML
    public Label itemqty;

    //Long Qty = 0L;
    StockinBL s;
    @FXML
    private Label itemqty1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        s = new StockinBL();

    }

    @FXML
    private void closeForm(ActionEvent event) {
        Stage stage = (Stage) closebtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void IncrementValue(ActionEvent event) {
        if (rowCounter.get() < Integer.valueOf(itemqty.getText())) {
            qnttextfield.setText(Integer.toString(rowCounter.incrementAndGet()));
        }
    }

    @FXML
    public void DecrementValue(ActionEvent event) {
        if (rowCounter.get() > 1) {
            qnttextfield.setText(Integer.toString(rowCounter.decrementAndGet()));
        } else {
            rowCounter.set(1);
        }

    }

}
