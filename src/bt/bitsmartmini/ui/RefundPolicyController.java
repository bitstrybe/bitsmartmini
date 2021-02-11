package bt.bitsmartmini.ui;

import bt.bitsmartmini.bl.ReturnBL;
import bt.bitsmartmini.entity.RefundPolicy;
import bt.bitsmartmini.entity.Users;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextArea;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author scarface
 */
public class RefundPolicyController implements Initializable {

    @FXML
    private Button closebtn;

    ReturnBL r = new ReturnBL();

    @FXML
    private JFXTextArea refundmsg;

    @FXML
    private Spinner<Integer> duration_val;
    @FXML
    private ChoiceBox<String> duration_choicbox;
    @FXML
    private Button applybtn;
    @FXML
    private HBox statushbox;
    @FXML
    private Label displayinfo;
    @FXML
    private FontAwesomeIcon check;
    @FXML
    private FontAwesomeIcon duplicatelock;
    @FXML
    private JFXSpinner spinner;
    RefundPolicy f;

//    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        f = r.findRefundPolicy();
        getDuration();
        getDurationValue();
        if (f != null) {
            refundmsg.setText(f.getRefundCustomMsg());
        } else {
            refundmsg.setText("Based on our return policy terms, we will accept a return of this item only within a ? period, and an undisputed reason for such.");
        }
    }

    @FXML
    private void closeForm(ActionEvent event) {
        Stage stage = (Stage) closebtn.getScene().getWindow();
        stage.close();
    }

    public void getDuration() {
        duration_choicbox.getItems().add("minute");
        duration_choicbox.getItems().add("hour");
        duration_choicbox.getItems().add("day(s)");
        duration_choicbox.getItems().add("weeks(s)");
        duration_choicbox.getItems().add("month(s)");
        duration_choicbox.getItems().add("year(s)");
        duration_choicbox.getSelectionModel().select(1);
    }

    public void getDurationValue() {
        SpinnerValueFactory<Integer> factoryValue = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 24);
        duration_val.setValueFactory(factoryValue);
    }

    private void closeTransition() {
        applybtn.setDisable(true);
        // clearAllCategory();
        displayinfo.setText(MainAppController.SUCCESS_MESSAGE);
        spinner.setVisible(false);
        check.setVisible(true);
        //TableData();
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(closevnt -> {
            displayinfo.setText("");
            spinner.setVisible(false);
            check.setVisible(false);
        });
        delay.play();

    }

    @FXML
    private void saveAction(ActionEvent event) {
        applybtn.setDisable(true);
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                spinner.setVisible(true);
                check.setVisible(false);
                updateMessage("PROCESSING PLS WAIT.....");
                Thread.sleep(1000);
                return null;
            }
        };
        displayinfo.textProperty().bind(task.messageProperty());
        task.setOnSucceeded(s -> {
            saveTemplate();
        });
        Thread d = new Thread(task);
        d.setDaemon(true);
        d.start();
    }

    public void saveTemplate() {
        displayinfo.textProperty().unbind();
        f.setRefundCustomMsg(refundmsg.getText());
        f.setRefundPeriod(duration_choicbox.getSelectionModel().getSelectedItem());
        f.setRefundPeriodVal(duration_val.getValue());
        f.setUser(new Users(LoginController.u.getUserid()));
        f.setRefundElog(new Date(System.currentTimeMillis()));
        int result = new ReturnBL().updateData(f);
        switch (result) {
            case 1:
                closeTransition();
                break;
            default:
                displayinfo.setText(MainAppController.ERROR_MESSAGE);
                spinner.setVisible(false);
                check.setVisible(false);
                break;

        }
    }

}
