package bt.bitsmartmini.ui;

import bt.bitsmartmini.bl.ReturnBL;
import bt.bitsmartmini.entity.RefundPolicy;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

//    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        duration_val = new Spinner<>(0, 100, 0, 1);
        SpinnerValueFactory<Integer> intFactory = duration_val.getValueFactory();
        //int imin = intFactory.setValue(Integer.MIN_VALUE); // 0
        //int imax = intFactory.getMax(); // 10
        //int istep = intFactory.getAmountToStepBy(); // 1
//        System.out.println("msg: " + f.getRefundCustomMsg());
//        System.out.println("msg: " + imin);
//        System.out.println("msg: " + imax);
//        System.out.println("msg: " + istep);
        refundmsg.setText(f.getRefundCustomMsg());
        // SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 0);

        duration_val.setValueFactory(intFactory);

//        duration_val.valueProperty().addListener((obs, oldValue, newValue) -> {
//            if (oldValue.intValue() == 59 && newValue.intValue() == 0) {
//                duration_val.increment();
//            }
//            if (oldValue.intValue() == 0 && newValue.intValue() == 59) {
//                duration_val.decrement();
//            }
//        });
    }

    @FXML
    private void closeForm(ActionEvent event) {
        Stage stage = (Stage) closebtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void salesReceiptReport(ActionEvent event) {
    }
    
//     @FXML
//    public void IncrementValue(ActionEvent event) {
//        //StockinBL s = new StockinBL();
//        //Qty = s.getStockBalance(itemnamelabel.getText());
//        //Qty = itemqty.getText()//s.getStockBalance(itemnamelabel.getText());
//        //System.out.println("itemname: " + itemnamelabel.getText());
//        //System.out.println("qty: " + itemqty.getText());
//        if (rowCounter.get() < Integer.valueOf(itemqty.getText())) {
//            qnttextfield.setText(Integer.toString(rowCounter.incrementAndGet()));
//        }
//    }
//
//    @FXML
//    public void DecrementValue(ActionEvent event) {
//        if (rowCounter.get() > 0) {
//            qnttextfield.setText(Integer.toString(rowCounter.decrementAndGet()));
//        } else {
//            rowCounter.set(0);
//        }
//
//    }

}
