package bt.bitsmartmini.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import bt.bitsmartmini.bl.InsertUpdateBL;
import bt.bitsmartmini.bl.ItemsBL;
import bt.bitsmartmini.bl.SalesBL;
import bt.bitsmartmini.bl.StockinBL;
import bt.bitsmartmini.entity.Items;
import bt.bitsmartmini.entity.Stockin;
import bt.bitsmartmini.entity.Users;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class AddStockInController implements Initializable {

    public JFXTextField batchtextfield;
    @FXML
    public JFXTextField qnttextfield;
    @FXML
    public JFXButton save;
    @FXML
    public Label displayinfo;
    @FXML
    public JFXSpinner spinner;
    @FXML
    public FontAwesomeIcon check;
    @FXML
    public Label itemname;
    @FXML
    public JFXListView<String> itemlist;
    @FXML
    private JFXTextField search;
    @FXML
    private Button closebtn;
    @FXML
    private ImageView itemimage;
    @FXML
    private FontAwesomeIcon duplicatelock;

    AtomicInteger rowCounter = new AtomicInteger(0);
    @FXML
    public Label itembarcode;
    @FXML
    private Label itembrand;
    @FXML
    private Label itemqty;
    @FXML
    private Label itemsp;

    ItemsBL ib = new ItemsBL();
    StockinBL sb = new StockinBL();

    public void getItemList(String p) {
        List<String> item = new ItemsBL().getAllItemsForList();
        if (p.length() > 0) {
            item = new ItemsBL().searchItemsForList(p);
        } else {
            item = new ItemsBL().getAllItemsForList();
        }
        ObservableList<String> result = FXCollections.observableArrayList(item);
        itemlist.getItems().clear();
        itemlist.setItems(result);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getItemList(search.getText());
        search.textProperty().addListener(e -> {
            getItemList(search.getText());

        });
        itemlist.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            itembarcode.setText(itemlist.getSelectionModel().getSelectedItem().split(":")[0]);
            itemname.setText(itemlist.getSelectionModel().getSelectedItem().split(":")[1]);
            itembrand.setText(itemlist.getSelectionModel().getSelectedItem().split(":")[2]);
            long qty = sb.getStockBalance(itemlist.getSelectionModel().getSelectedItem().split(":")[0]);
            itemqty.setText(Long.valueOf(qty).toString()+" Remaining");
            itemsp.setText(MainAppController.B.getBCurrency()+" "+itemlist.getSelectionModel().getSelectedItem().split(":")[4]);
            Items its = new ItemsBL().getImageItembyCode(itemlist.getSelectionModel().getSelectedItem().split(":")[0]);
            FileInputStream input;
            try {
                input = new FileInputStream(its.getItemImg());
                Image image = new Image(input);
                itemimage.setImage(image);
                save.setDisable(false);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AddStockInController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        qnttextfield.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    qnttextfield.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
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
        qnttextfield.setText("1");
        itemimage.setImage(null);
    }

    private void closeTransition() {
        displayinfo.setText("Transaction was successful");
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
