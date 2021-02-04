
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import bt.bitsmartmini.bl.InsertUpdateBL;
import bt.bitsmartmini.bl.ItemsBL;
import bt.bitsmartmini.bl.UomBL;
import bt.bitsmartmini.entity.Items;
import bt.bitsmartmini.entity.Stockout;
import bt.bitsmartmini.entity.Users;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class AddStockOutController implements Initializable {

    @FXML
    public DatePicker stockoutdate;
    @FXML
    public JFXTextField qnttextfield;
    @FXML
    public JFXButton save;
    private Button closeButton;
    @FXML
    public Label displayinfo;
    @FXML
    public JFXSpinner spinner;
    @FXML
    public FontAwesomeIcon check;
    @FXML
    public FontAwesomeIcon duplicatelock;
    @FXML
    public Label itemname;
    @FXML
    private Button closebtn;
    @FXML
    private JFXTextField search;
    @FXML
    private JFXListView<String> itemlist;
    @FXML
    private ImageView itemimage;
    @FXML
    private Label uomitem;
    @FXML
    public TextArea remarks;

    public void getItemList() {
        List<String> item = new ItemsBL().getAllItemsName();
        ObservableList<String> result = FXCollections.observableArrayList(item);
        itemlist.getItems().clear();
        itemlist.setItems(result);
//        Utilities.searchListView(itemlist.getItems(), search, itemlist);
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
        getItemList();
        search.textProperty().addListener(e -> {
            if (search.getText().length() > 4) {
                searchItemList(search.getText());
            } else {
                getItemList();
            }
        });

        itemlist.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            itemname.setText(itemlist.getSelectionModel().getSelectedItem());
            //UomDef ud = new UomBL().getUombyItemId(itemlist.getSelectionModel().getSelectedItem());
            Items its = new ItemsBL().getImageItembyCode(itemlist.getSelectionModel().getSelectedItem());
            //uomitem.setText(ud.getUomCode().getUomDesc() + " " + ud.getUomNm() + " X " + ud.getUomDm());
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
        remarks.clear();
        stockoutdate.getEditor().clear();
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
    public void saveTemplate(){
        
                displayinfo.textProperty().unbind();
                Stockout cat = new Stockout();
                cat.setBatchNo(String.valueOf(cat.getStockoutId()));
                cat.setItems(new Items(itemname.getText()));
                cat.setQuantity(Integer.parseInt(qnttextfield.getText()));
//                cat.setCostPrice(Utilities.roundToTwoDecimalPlace(Float.parseFloat(childController.costpiecestextfield.getText()), 2));
//                cat.setSalesPrice(Utilities.roundToTwoDecimalPlace(Float.parseFloat(childController.salespiecetextfield.getText()), 2));
                cat.setStkDate(new Date());
                cat.setRemarks(remarks.getText());
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

}
