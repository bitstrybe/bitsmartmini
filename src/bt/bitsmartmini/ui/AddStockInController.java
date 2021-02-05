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
//    @FXML
//    public JFXTextField costtextfield;
//    @FXML
//    public JFXTextField salestextfield;
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
    private Label uomitem;
    @FXML
    private FontAwesomeIcon duplicatelock;

    AtomicInteger rowCounter = new AtomicInteger(0);

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

//        costtextfield.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                if (!newValue.matches("\\d*")) {
//                    costtextfield.setText(newValue.replaceAll("[^\\d\\.]", ""));
//                }
//            }
//        });
//        salestextfield.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                if (!newValue.matches("\\d*")) {
//                    salestextfield.setText(newValue.replaceAll("[^\\d\\.]", ""));
//                }
//            }
//        });
        qnttextfield.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    qnttextfield.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
//        costtextfield.textProperty().addListener(e -> {
//            try {
//                if (costtextfield.getLength() >= 1) {
//                    float val = Float.parseFloat(costtextfield.getText()) / Float.parseFloat(qnttextfield.getText());
//                    costpiecestextfield.setText(String.valueOf(val));
//                }
//            } catch (NumberFormatException ex) {
//                System.out.println("formatting error");
//            }
//
//        });
//        salestextfield.textProperty().addListener(e -> {
//            try {
//                if (salestextfield.getLength() >= 1) {
//                    float val = Float.parseFloat(salestextfield.getText()) / Float.parseFloat(qnttextfield.getText());
//                    salespiecetextfield.setText(String.valueOf(val));
//                }
//            } catch (NumberFormatException ex) {
//                System.out.println("formatting error");
//            }
//        });
//        nhistextfield.textProperty().addListener(e -> {
//            try {
//                if (nhistextfield.getLength() >= 1) {
//                    float val = Float.parseFloat(nhistextfield.getText()) * Float.parseFloat(qnttextfield.getText());
//                    nhispiecetextfield.setText(String.valueOf(val));
//                }
//            } catch (NumberFormatException ex) {
//                System.out.println("formatting error");
//            }
//        });
//        qnttextfield.textProperty().addListener(e -> {
//            try {
//                if (qnttextfield.getText().length() >= 1) {
//                    float valcost = Float.parseFloat(costtextfield.getText()) * Float.parseFloat(qnttextfield.getText());
//                    float valsales = Float.parseFloat(salestextfield.getText()) * Float.parseFloat(qnttextfield.getText());
//               }
//            } catch (Exception ex) {
//                System.out.println("formatting error");
//            }
//
//        });

//        expirydate.setConverter(new StringConverter<LocalDate>() {
//            private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//admin
//            @Override
//            public String toString(LocalDate object) {
//                if (object == null) {
//                    return "";
//                }
//                return dateTimeFormatter.format(object);
//            }
//
//            @Override
//            public LocalDate fromString(String datestring) {
//                if (datestring == null || datestring.trim().isEmpty()) {
//                    return null;
//                }
//                return LocalDate.parse(datestring, dateTimeFormatter);
//            }
//
//        });
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
//        costtextfield.clear();
//        salestextfield.clear();
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
        //cat.setUpc(String.valueOf(cat.getStockinId()));
        cat.setUpc(new Items(itemname.getText()));
        cat.setQuantity(Integer.parseInt(qnttextfield.getText()));
        //cat.setC(Utilities.roundToTwoDecimalPlace(Double.parseDouble(costtextfield.getText()), 2));
        //cat.setSalesPrice(Utilities.roundToTwoDecimalPlace(Double.parseDouble(salestextfield.getText()), 2));
//                cat.setNhisPrice(Utilities.roundToTwoDecimalPlace(Double.parseDouble(childController.nhistextfield.getText()), 2));
        cat.setStockinDate(new Date());
        cat.setExpiryDate(new Date());
        cat.setUsers(new Users(LoginController.u.getUserid()));
        cat.setEntryLog(new Date());
        cat.setLastModified(new Date());
        int result = new InsertUpdateBL().insertData(cat);
        switch (result) {
            case 1:
                //Items itprice = new Items();
                //itprice.setItems(cat.getItems());
                //itprice.setItemDesc(cat.getItems().getItemDesc());
                //itprice.setCostPrice(Utilities.roundToTwoDecimalPlace(Double.parseDouble(costtextfield.getText()), 2));
                //itprice.setSalesPrice(Utilities.roundToTwoDecimalPlace(Double.parseDouble(salestextfield.getText()), 2));
//                itprice.setEntryLog(new Date());
//                itprice.setLastModified(new Date());
//                int resultprice = new InsertUpdateBL().updateData(itprice);
                if (result == 1) {
                    closeTransition();
                }

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
        if (rowCounter.get() > 0) {
            qnttextfield.setText(Integer.toString(rowCounter.decrementAndGet()));
        } else {
            rowCounter.set(0);
        }
    }

    @FXML
    private void plusqnty(ActionEvent event) {
        qnttextfield.setText(Integer.toString(rowCounter.incrementAndGet()));
    }
}
