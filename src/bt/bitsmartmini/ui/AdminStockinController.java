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
import bt.bitsmartmini.bl.StockinBL;
import bt.bitsmartmini.bl.UomBL;
import bt.bitsmartmini.entity.Items;
import bt.bitsmartmini.entity.Stockin;
import bt.bitsmartmini.entity.UomSet;
import bt.bitsmartmini.entity.Users;
import bt.bitsmartmini.utils.Utilities;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class AdminStockinController implements Initializable {

    StockinBL sb = new StockinBL();
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

    AtomicInteger rowCounter = new AtomicInteger(1);
    @FXML
    private DatePicker expirydate;
    @FXML
    private ComboBox<String> uomcombo;
    
    
//    public void getUomsets() {
//        uomcombo.getItems().clear();
//        List<UomSet> list = new UomBL().getUomSets();
//        ObservableList<Brands> result = FXCollections.observableArrayList(list);
//        result.forEach((man) -> {
//            brandscombo.getItems().add(WordUtils.capitalizeFully(man.getBrandName()));
//        });
//    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        qnttextfield.setText("1");
    }

    @FXML
    private void closefrom(ActionEvent event) {
        Stage stage = (Stage) closebtn.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void saveAction(ActionEvent event) {
    }

//    private void clearAllForms() {
//        qnttextfield.clear();
//        costtextfield.clear();
//        salestextfield.clear();
//        itemimage.setImage(null);
//    }
    private void clearAllForms() {
        qnttextfield.setText("1");
        //itemimage.setImage(null);
        //itembarcode.setText(null);
        //itemname.setText(null);
        // itembrand.setText(null);
        Long qty = sb.getStockBalance(itembarcode.getText());
        itemqty.setText(qty.toString() + " Remainig");
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

    public int saveTemplate() {
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
        cat.setUpc(new Items(itembarcode.getText()));
        cat.setQuantity(Integer.parseInt(qnttextfield.getText()));
        cat.setStockinDate(new Date());
        cat.setExpiryDate(Utilities.convertToDateViaSqlDate(expirydate.getValue()));
        cat.setUsers(new Users(LoginController.u.getUserid()));
        cat.setEntryLog(new Date());
        cat.setLastModified(new Date());
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
