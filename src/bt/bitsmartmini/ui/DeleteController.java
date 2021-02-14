
package bt.bitsmartmini.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class DeleteController implements Initializable {

    @FXML
    public JFXButton delete;
    @FXML
    public Label displayinfo;
    @FXML
    public JFXSpinner spinner;
    @FXML
    public FontAwesomeIcon check;
    @FXML
    public FontAwesomeIcon duplicatelock;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
     public void deleteTrans() {
        displayinfo.setText(MainAppController.DELETE_MESSAGE);
        spinner.setVisible(false);
        check.setVisible(true);
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(closevnt -> {
            closebtn(null);
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
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(closevnt -> {
            displayinfo.setText("");
            spinner.setVisible(false);
            check.setVisible(false);
        });
        delay.play();

    }

    @FXML
    private void closebtn(ActionEvent event) {
        Stage stage = (Stage) delete.getScene().getWindow();
        stage.close();
    }

   
    
}
