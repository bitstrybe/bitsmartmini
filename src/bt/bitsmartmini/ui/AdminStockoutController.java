
package bt.bitsmartmini.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import bt.bitsmartmini.bl.InsertUpdateBL;
import bt.bitsmartmini.entity.Items;
import bt.bitsmartmini.entity.Stockout;
import bt.bitsmartmini.entity.Users;
import bt.bitsmartmini.utils.Utilities;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class AdminStockoutController implements Initializable {

    @FXML
    private Button closebtn;
    @FXML
    public ImageView itemimage;
    @FXML
    public Text itembarcode;
    @FXML
    public Text itemname;
    @FXML
    public Text itembrand;
    @FXML
    public Text itemqty;
    @FXML
    public Text itemsp;
    @FXML
    public JFXTextField qnttextfield;
    @FXML
    public JFXButton save;
    @FXML
    public FontAwesomeIcon check;
    @FXML
    public FontAwesomeIcon duplicatelock;
    @FXML
    public JFXSpinner spinner;
    @FXML
    public Label displayinfo;
    @FXML
    private JFXTextArea remarks;
    
    AtomicInteger rowCounter = new AtomicInteger(1);

    

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

    @FXML
    private void saveAction(ActionEvent event) {
    }
    
      private void clearAllForms() {
//        skoutqnttextfield.clear();
//        skoutremarks.clear();
//        skoutdate.getEditor().clear();
        itemimage.setImage(null);
    }

    public void saveTrans() {
        displayinfo.setText(MainAppController.SUCCESS_MESSAGE);
        clearAllForms();
        spinner.setVisible(false);
        check.setVisible(true);
//        TableData("");
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(closevnt -> {
            displayinfo.setText("");
            spinner.setVisible(false);
            check.setVisible(false);
        });
        delay.play();

    }

    public void errorTrans() {
        displayinfo.setText(MainAppController.ERROR_MESSAGE);
        spinner.setVisible(false);
        check.setVisible(false);
//        TableData("");
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(closevnt -> {
            displayinfo.setText("");
            spinner.setVisible(false);
            check.setVisible(false);
        });
        delay.play();

    }
    public int saveTemplate(){
                displayinfo.textProperty().unbind();
                Stockout cat = new Stockout();
                cat.setUpc(new Items(itemname.getText()));
                cat.setQuantity(Integer.parseInt(qnttextfield.getText()));
                cat.setStkDate(new Date());
                cat.setRemarks(remarks.getText());
                cat.setUsers(new Users(LoginController.u.getUserid()));
                cat.setEntryLog(new Date());
                cat.setModifiedDate(new Date());
                int result = new InsertUpdateBL().insertData(cat);
                return result;
           
    }

   @FXML
    private void minusqnty(ActionEvent event) {
        if (rowCounter.get() > 1) {
            qnttextfield.setText(Integer.toString(rowCounter.decrementAndGet()));
        } else {
            rowCounter.set(1);
        }
    }

    @FXML
    private void plusqnty(ActionEvent event) {
        qnttextfield.setText(Integer.toString(rowCounter.incrementAndGet()));
    }
    
    
}
