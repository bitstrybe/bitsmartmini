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
import bt.bitsmartmini.bl.StockinBL;
import bt.bitsmartmini.bl.UomBL;
import bt.bitsmartmini.entity.Items;
import bt.bitsmartmini.entity.Stockin;
import bt.bitsmartmini.entity.UomSet;
import bt.bitsmartmini.entity.Users;
import bt.bitsmartmini.utils.Utilities;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import org.apache.commons.text.WordUtils;

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
    @FXML
    private DatePicker expirydate;
    @FXML
    private ComboBox<String> uomcombo;

    public void getItemList(String p) {
        ItemsBL ib = new ItemsBL();
        List<String> item = ib.getAllItemsForList();
        if (p != null && p.length() > 0) {
            item = new ItemsBL().searchItemsForList(p);
        } else {
            item = new ItemsBL().getAllItemsForList();
        }
        ObservableList<String> result = FXCollections.observableArrayList(item);
        itemlist.getItems().clear();
        itemlist.setItems(result);
    }

    public void getUomsets(String s) {
        //System.out.println("sosket: "+s);
        uomcombo.getItems().clear();
        UomSet uoms = new UomBL().getUomSets(s);
        //System.out.println("UOM"+uoms. );
        List uomsets = new ArrayList<>();
        uomsets.add(uoms.getMeasure1().getUomDesc() + " - " + uoms.getUnit1());
        uomsets.add(uoms.getMeasure2().getUomDesc() + " - " + uoms.getUnit2());
        ObservableList<String> result = FXCollections.observableArrayList(uomsets);
        result.forEach((man) -> {
            //System.out.println("man:" + man);
            uomcombo.getItems().add(WordUtils.capitalize(man));
        });
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Utilities.repeatFocus(search);
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
                //System.out.println("URL:" + its.getItemImg());
                input = new FileInputStream(its.getItemImg());
                Image image = new Image(input);
                itemimage.setImage(image);
                save.setDisable(false);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AddStockInController.class.getName()).log(Level.SEVERE, null, ex);
            }
            getUomsets(itemlist.getSelectionModel().getSelectedItem().split(":")[5]);
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
        //itemimage.setImage(null);
        //itembarcode.setText(null);
        //itemname.setText(null);
        // itembrand.setText(null);
        //itemqty.setText(null);
    }

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
        displayinfo.setText("Transaction was successful");
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

    public int saveTemplate() {
        displayinfo.textProperty().unbind();
        Stockin cat = new Stockin();
        int stkc = new StockinBL().getStockinCount();
        if (stkc <= 0) {
            //int stkval = 1;
            cat.setStockinId(1);
        } else {
            //int stkval = stkc;
            cat.setStockinId(++stkc);
        }
        
        cat.setUpc(new Items(itembarcode.getText()));
        int qty = Integer.parseInt(qnttextfield.getText());
        int unit = Integer.parseInt(uomcombo.getValue().split("-")[1].trim());
       //System.out.println("saving stockin0..."+qty+" : "+cat.getQuantity()+"U: "+unit);
        cat.setMeasure(uomcombo.getValue().split("-")[0].trim());
        //System.out.println("saving stockin1..."+qty+" : "+cat.getQuantity());
        cat.setMeasureqty(qty);
        cat.setUnitmeasure(qty);
        cat.setQuantity((qty * unit));
        //System.out.println("saving stockin1..."+qty+" : "+cat.getQuantity());
        cat.setStockinDate(new Date());
        //System.out.println("saving stockin2..."+qty+" : "+cat.getQuantity());
        cat.setExpiryDate(Utilities.convertToDateViaSqlDate(expirydate.getValue()));
        cat.setUsers(new Users(LoginController.u.getUserid()));
        cat.setEntryLog(new Date());
        cat.setLastModified(new Date());
        int result = new InsertUpdateBL().insertData(cat);
        
        return result;
//        switch (result) {
//            case 1:
//                if (result == 1) {
//                    closeTransition();
//                }
//                break;
//            default:
//                displayinfo.setText("There was an error, check and try again.");
//                spinner.setVisible(false);
//                check.setVisible(false);
//                break;
//        }
    }

    public void saveTrans() {
        displayinfo.setText(MainAppController.SUCCESS_MESSAGE);
        clearAllForms();
        spinner.setVisible(false);
        check.setVisible(true);
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
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(closevnt -> {
            displayinfo.setText("");
            spinner.setVisible(false);
            check.setVisible(false);
        });
        delay.play();

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
