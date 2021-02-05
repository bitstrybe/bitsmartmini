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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import bt.bitsmartmini.bl.InsertUpdateBL;
import bt.bitsmartmini.bl.ItemsBL;
import bt.bitsmartmini.bl.StockinBL;
import bt.bitsmartmini.entity.Items;
import bt.bitsmartmini.entity.Stockout;
import bt.bitsmartmini.entity.Users;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class AddStockOutController implements Initializable {

    public DatePicker stockoutdate;
    @FXML
    public JFXTextField qnttextfield;
    public JFXButton save;
    private Button closeButton;
    public Label displayinfo;
    public JFXSpinner spinner;
    public FontAwesomeIcon check;
    public FontAwesomeIcon duplicatelock;
    public Label itemname;
    @FXML
    private Button closebtn;
    @FXML
    private JFXTextField search;
    @FXML
    private JFXListView<String> itemlist;
    @FXML
    private ImageView itemimage;
    public TextArea remarks;
    @FXML
    public Label itembarcode;
    @FXML
    private Label itembrand;
    @FXML
    private Label itemqty;
    @FXML
    private Label itemsp;
    @FXML
    private Label displayinfo1;
    @FXML
    private FontAwesomeIcon check1;
    @FXML
    private FontAwesomeIcon duplicatelock1;
    @FXML
    private JFXSpinner spinner1;
    AtomicInteger rowCounter = new AtomicInteger(0);

    ItemsBL ib = new ItemsBL();
    StockinBL sb = new StockinBL();

    public void getItemList(String p) {
        List<String> item;
        if (p!= null && p.length() > 0) {
            item = new ItemsBL().searchItemsForList(p);
        } else {
            item = new ItemsBL().getAllItemsForList();
        }

        ObservableList<String> result = FXCollections.observableArrayList(item);
        itemlist.getItems().clear();
        itemlist.setItems(result);
    }

    public void searchItemList(String p) {
        List<String> item = new ItemsBL().searchItemsNames(p);
        ObservableList<String> result = FXCollections.observableArrayList(item);
        itemlist.getItems().clear();
        itemlist.setItems(result);
//        Utilities.searchListView(itemlist.getItems(), search, itemlist);
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
            itemqty.setText(Long.toString(qty) + " Remaining");
            itemsp.setText(MainAppController.B.getBCurrency() + " " + itemlist.getSelectionModel().getSelectedItem().split(":")[4]);
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
            if(qty > 0){
                save.setDisable(false);
            }else{
                save.setDisable(true);
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
        itembarcode.setText(null);
        itemname.setText(null);
        itembrand.setText(null);
        itemqty.setText(null);
    }

    private void closeTransition() {
        displayinfo.setText("SUCCESSFULLY SAVED");
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
        Stockout cat = new Stockout();
        cat.setUpc(new Items(itembarcode.getText()));
        cat.setQuantity(Integer.parseInt(qnttextfield.getText()));
        cat.setStkDate(new Date());
        cat.setRemarks(remarks.getText());
        cat.setUsers(new Users(LoginController.u.getUserid()));
        cat.setEntryLog(new Date());
        cat.setModifiedDate(new Date());
        int result = new InsertUpdateBL().insertData(cat);
        switch (result) {
            case 1:
                closeTransition();
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
