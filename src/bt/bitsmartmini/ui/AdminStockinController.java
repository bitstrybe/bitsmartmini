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
import bt.bitsmartmini.entity.Stockin;
import bt.bitsmartmini.entity.Users;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class AdminStockinController implements Initializable {

    @FXML
    private Button closebtn;
    public ImageView itemimage;
    public Label itemname;
    public Label uomitem;
    @FXML
    private JFXTextField qnttextfield;
    private JFXTextField costtextfield;
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

    AtomicInteger rowCounter = new AtomicInteger(0);
    @FXML
    public Label itembarcode;
    @FXML
    public Label itembrand;
    @FXML
    public Label itemqty;
    @FXML
    public Label itemsp;

    //ItemsBL ib = new ItemsBL();
    StockinBL sb = new StockinBL();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ItemsDisplay.fxml"));
//            Parent parent = (Parent) fxmlLoader.load();
//            ItemsDisplayController childController = fxmlLoader.getController();
//            itembarcode.setText(childController.itembcode.getText());
//            itemname.setText(childController.medsname.getText());
//            itembrand.setText(childController.brand.getText());
//            long qty = sb.getStockBalance(childController.itembcode.getText());
//            itemqty.setText(Long.valueOf(qty).toString() + " Remaining");
//            itemsp.setText(MainAppController.B.getBCurrency() + " " + childController.qty.getText());
//            Items its = new ItemsBL().getImageItembyCode(childController.itembcode.getText());
//            FileInputStream input;
//            try {
//                input = new FileInputStream(its.getItemImg());
//                Image image = new Image(input);
//                itemimage.setImage(image);
//                save.setDisable(false);
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(AddStockInController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } catch (IOException ex) {
//            Logger.getLogger(AdminStockinController.class.getName()).log(Level.SEVERE, null, ex);
//        }
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

    private void refreshView() {
        qnttextfield.setText("1");
        //itemimage.setImage(null);
        //itembarcode.setText(null);
        //itemname.setText(null);
        // itembrand.setText(null);
        Long qty = sb.getStockBalance(itembarcode.getText());
        itemqty.setText(qty.toString() + " Remainig");
    }

    private void closeTransition() {
        displayinfo.setText("SUCCESSFULLY SAVED");
        spinner.setVisible(false);
        check.setVisible(true);
        refreshView();
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(closevnt -> {
            displayinfo.setText("");
            spinner.setVisible(false);
            check.setVisible(false);
        });
        delay.play();

    }

    public void saveTemplate() {
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
        cat.setExpiryDate(new Date());
        cat.setUsers(new Users(LoginController.u.getUserid()));
        cat.setEntryLog(new Date());
        cat.setLastModified(new Date());
        int result = new InsertUpdateBL().insertData(cat);
        switch (result) {
            case 1:
                if (result == 1) {
                    closeTransition();
                }
                break;
            default:
                displayinfo.setText("There was an error, check and try again.");
                spinner.setVisible(false);
                check.setVisible(false);
                break;
        }

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
