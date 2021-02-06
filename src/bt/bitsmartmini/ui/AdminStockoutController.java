
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

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class AdminStockoutController implements Initializable {

    @FXML
    private Button closebtn;
    public ImageView itemimage;
    public Label itemname;
    public Label uomitem;
    private JFXTextField skoutqnttextfield;
    private DatePicker skoutdate;
    private JFXTextArea skoutremarks;
    public JFXButton save;
    public Label displayinfo;
    public FontAwesomeIcon check;
    public JFXSpinner spinner;
    @FXML
    public Label itembarcode;
    @FXML
    public Label itembrand;
    @FXML
    public Label itemqty;
    @FXML
    public Label itemsp;
    @FXML
    public JFXTextField qnttextfield;
    @FXML
    private Label displayinfo1;
    @FXML
    private FontAwesomeIcon check1;
    @FXML
    private FontAwesomeIcon duplicatelock1;
    @FXML
    private JFXSpinner spinner1;

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
        skoutqnttextfield.clear();
        skoutremarks.clear();
        skoutdate.getEditor().clear();
        itemimage.setImage(null);
    }

    private void closeTransition() {
        displayinfo.setText("Saved ");
        itemname.setText(null);
        spinner.setVisible(false);
        check.setVisible(true);
        clearAllForms();
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(closevnt -> {
            displayinfo.setText("");
            spinner.setVisible(false);
            check.setVisible(false);
        });
        delay.play();

    }
    public void saveTemplate(){
                displayinfo.textProperty().unbind();
                Stockout cat = new Stockout();
                //cat.setBatchNo(String.valueOf(cat.getStockoutId()));
                cat.setUpc(new Items(itemname.getText()));
                cat.setQuantity(Integer.parseInt(skoutqnttextfield.getText()));
//                cat.setCostPrice(Utilities.roundToTwoDecimalPlace(Float.parseFloat(childController.costpiecestextfield.getText()), 2));
//                cat.setSalesPrice(Utilities.roundToTwoDecimalPlace(Float.parseFloat(childController.salespiecetextfield.getText()), 2));
                cat.setStkDate(new Date());
                cat.setRemarks(skoutremarks.getText());
//                cat.setExpiryDate(Utilities.convertToDateViaSqlDate(childController.expirydate.getValue()));
                cat.setUsers(new Users(LoginController.u.getUserid()));
                cat.setEntryLog(new Date());
                cat.setModifiedDate(new Date());
                int result = new InsertUpdateBL().insertData(cat);
                switch (result) {
                    case 1:
                     closeTransition();
                        break;
                    default:
                        displayinfo.setText("NOTICE! AN ERROR OCCURED");
                       spinner.setVisible(false);
                       check.setVisible(false);
                        break;

                }
           
    }

    @FXML
    private void minusqnty(ActionEvent event) {
    }

    @FXML
    private void plusqnty(ActionEvent event) {
    }
    
    
}
