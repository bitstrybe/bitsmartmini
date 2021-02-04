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
import javafx.application.Platform;
import javafx.concurrent.Task;
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
import bt.bitsmartmini.bl.SalesBL;
import bt.bitsmartmini.entity.RtdItem;
import bt.bitsmartmini.entity.SalesDetails;
import bt.bitsmartmini.entity.Users;
import lxe.utility.math.DecimalUtil;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class ReturnItemsController implements Initializable {

    @FXML
    private Button closebtn;
    @FXML
    public ImageView itemimage;
    @FXML
    public Label itemname;
    @FXML
    public Label uomitem;
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
    @FXML
    public JFXTextField returnqnty;

    private DatePicker returndate;
    @FXML
    private JFXTextArea returnremarks;
    @FXML
    public JFXTextField itemcost;
    @FXML
    public Label pay;
    @FXML
    public Label curr;

    Integer rQty = 0;
    Double pays = 0.00;

    private static String REGEX_VALID_INTEGER;
    @FXML
    public Label itemcode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //calculateReturnParam();
        rQty = Integer.parseInt(returnqnty.getText());
        pays = Double.parseDouble(pay.getText());

    }

    @FXML
    private void closefrom(ActionEvent event) {
        Stage stage = (Stage) closebtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void saveAction(ActionEvent event) {
    }

    @FXML
    private void calculateReturnParam() {
        SalesDetails sd = new SalesBL().getSalesDetailsById(Integer.parseInt(itemcode.getText()));
        Task<Void> longRunningTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("called event handler");
                        //rQty = Integer.parseInt(returnqnty.getText());
                        //pays = Double.parseDouble(pay.getText());
                        int qty = 0;
                        if (!returnqnty.getText().isEmpty()) {
                            if (Integer.parseInt(returnqnty.getText()) > sd.getQuantity()) {
                                pay.setText(String.valueOf(sd.getSalesPrice() * sd.getQuantity()));
                                returnqnty.setText(String.valueOf(sd.getQuantity()));
                            }
                            qty = Integer.parseInt(returnqnty.getText());
                            double ic = Double.parseDouble(itemcost.getText());
                            double total = qty * ic;
                            pay.setText(DecimalUtil.format2(total));
                            //System.out.println("called event handler");
                        } else {
                            pay.setText(String.valueOf(sd.getSalesPrice() * sd.getQuantity()));
                            returnqnty.setText(String.valueOf(sd.getQuantity()));
                        }
                        //pay.setText(DecimalUtil.format2(total));
                    }
                });
                return null;
            }
        };
        new Thread(longRunningTask).start();
    }

    private void clearAllForms() {
        returnqnty.clear();
        itemcode.setText(null);
        itemname.setText(null);
        returnremarks.clear();
        //returndate.getEditor().clear();
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

    public void saveTemplate(int scode) {
        displayinfo.textProperty().unbind();
        RtdItem cat = new RtdItem();
        cat.setSalescode(scode);
        cat.setRtdQty(Integer.valueOf(returnqnty.getText()));
        cat.setRtdDate(new Date(System.currentTimeMillis()));
        cat.setRtdTime(new Date(System.currentTimeMillis()));
        cat.setRemarks(returnremarks.getText());
        cat.setUsers(new Users(LoginController.u.getUserid()));
        cat.setEntryLog(new Date());
        cat.setLastModified(new Date());
        int result = new InsertUpdateBL().insertData(cat);
        switch (result) {
            case 1:
                closeTransition();
                break;
            default:
                displayinfo.setText("INFO! AN ERROR OCCURED");
                spinner.setVisible(false);
                check.setVisible(false);
                break;
        }

    }

}
