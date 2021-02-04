package bt.bitsmartmini.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import bt.bitsmartmini.bl.InsertUpdateBL;
import bt.bitsmartmini.bl.ItemsBL;
import bt.bitsmartmini.bl.StockinBL;
import bt.bitsmartmini.entity.Items;
import bt.bitsmartmini.entity.ItemsPrice;
import bt.bitsmartmini.entity.Stockin;
import bt.bitsmartmini.entity.Users;
import bt.bitsmartmini.utils.Utilities;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class AdminStockinController implements Initializable {

    @FXML
    private Button closebtn;
    @FXML
    public ImageView itemimage;
    @FXML
    public Label itemname;
    @FXML
    public Label uomitem;
    @FXML
    private JFXTextField qnttextfield;
    @FXML
    private JFXTextField costtextfield;
    @FXML
    private JFXTextField salestextfield;
    @FXML
    public JFXButton save;
    @FXML
    public Label displayinfo;
    @FXML
    public FontAwesomeIcon check;
    @FXML
    public FontAwesomeIcon duplicatelock;
    @FXML
    public JFXSpinner spinner;

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
        qnttextfield.clear();
        costtextfield.clear();
        salestextfield.clear();
        itemimage.setImage(null);
    }

    private void closeTransition() {
        displayinfo.setText("SUCCESSFULLY SAVED");
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

    public void saveTemplate() {
        ItemsBL ib = new ItemsBL();
        displayinfo.textProperty().unbind();
        Stockin cat = new Stockin();
        List<Integer> stcinid = new StockinBL().getStockinCount();
        if (stcinid.isEmpty()) {
            int stkval = 1;
            cat.setStockinId(stkval);
        } else {
            int stkval = stcinid.get(0);
            cat.setStockinId(++stkval);
        }

        //cat.setBatchNo(childController.batchtextfield.getText());
        cat.setBatchNo(String.valueOf(cat.getStockinId()));
        cat.setItems(new Items(itemname.getText()));
        cat.setQuantity(Integer.parseInt(qnttextfield.getText()));
        cat.setCostPrice(Utilities.roundToTwoDecimalPlace(Double.parseDouble(costtextfield.getText()), 2));
        cat.setSalesPrice(Utilities.roundToTwoDecimalPlace(Double.parseDouble(salestextfield.getText()), 2));
//                cat.setNhisPrice(Utilities.roundToTwoDecimalPlace(Double.parseDouble(childController.nhistextfield.getText()), 2));
        cat.setStockinDate(new Date());
        cat.setExpiryDate(new Date());
        cat.setUsers(new Users(LoginController.u.getUserid()));
        cat.setEntryLog(new Date());
        cat.setLastModified(new Date());
        ItemsPrice itprice = ib.getItemsPriceByItemDesc(cat.getItems().getItemDesc());
        //itprice.setItems(cat.getItems());
        itprice.setItemDesc(cat.getItems().getItemDesc());
        itprice.setCostPrice(Utilities.roundToTwoDecimalPlace(Double.parseDouble(costtextfield.getText()), 2));
        itprice.setSalesPrice(Utilities.roundToTwoDecimalPlace(Double.parseDouble(salestextfield.getText()), 2));
        itprice.setEntryLog(new Date());
        itprice.setLastModified(new Date());
        itprice.setItems(cat.getItems());
        cat.getItems().setItemsPrice(itprice);
        int result = new InsertUpdateBL().insertUpdate(cat, itprice);
        if (result == 1) {
           // int i = new InsertUpdateBL().updateData(cat.getItems());
            closeTransition();
        } else {
            displayinfo.setText("NOTICE! AN ERROR OCCURED");
            spinner.setVisible(false);
            check.setVisible(false);
            //break;
        }

    }
}
