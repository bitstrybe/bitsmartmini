package bt.bitsmartmini.ui;

import bt.bitsmartmini.bl.ReturnBL;
import bt.bitsmartmini.entity.RefundPolicy;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author scarface
 */
public class RefundPolicyController implements Initializable {

    @FXML
    private Button closebtn;
//    @FXML
//    private Spinner<Integer> duration_val;
    @FXML
    private Button discountbtn1;

    ReturnBL r = new ReturnBL();
    RefundPolicy f = r.findRefundPolicy();
    @FXML
    private JFXTextArea refundmsg;

    @FXML
    private Spinner<Integer> duration_val;
    @FXML
    private ChoiceBox<String> duration_choicbox;

//    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getDuration();
        getDurationValue();
        refundmsg.setText("Based on our return policy terms, we will accept a return of this item only within a ? period, and an undisputed reason for such.");
    }

    @FXML
    private void closeForm(ActionEvent event) {
        Stage stage = (Stage) closebtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void salesReceiptReport(ActionEvent event) {
    }

    public void getDuration() {
        duration_choicbox.getItems().add("MINUTES");
        duration_choicbox.getItems().add("HOURS");
        duration_choicbox.getItems().add("DAYS");
        duration_choicbox.getItems().add("MONTHS");
        duration_choicbox.getSelectionModel().select(1);
    }

    public void getDurationValue() {
        SpinnerValueFactory<Integer> factoryValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 24);
        duration_val.setValueFactory(factoryValue);
//        duration_choicbox.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
//            if(newValue.equals("MINUTES")){
//                System.out.println("na mintues we they");
//               
//            }else if(newValue.equals("HOURS")){
//                System.out.println("na hours we they");
//                SpinnerValueFactory<Integer> factoryValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24, 1);
//                duration_val.setValueFactory(factoryValue);
//            }
//        });
    }

}
