package bt.bitsmartmini.ui;

import bt.bitsmartmini.bl.BackupLogBL;
import bt.bitsmartmini.utils.Utilities;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lxe.utility.date.DateUtil;

/**
 *
 * @author JScare
 */
public class DataMgntController implements Initializable {

    private Button closebtn;

    Utilities utl = new Utilities();

    @FXML
    private Text lastbackup;

    BackupLogBL b = new BackupLogBL();

    Date d = b.findLastBackup();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (d == null) {
            lastbackup.setText("you havent done any backup");
        } else {
            lastbackup.setText(DateUtil.formatDate(d, "yyyy-MM-dd HH:mm:ss"));
        }

    }

    private void closefrom(ActionEvent event) {
        Stage s = (Stage) closebtn.getScene().getWindow();
        s.close();
    }

    @FXML
    private void backupAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Backup.fxml"));
            Parent parent = (Parent) fxmlLoader.load();
            Scene scene = new Scene(parent);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setMaximized(true);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DataMgntController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void RestoreAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Restore.fxml"));
            Parent parent = (Parent) fxmlLoader.load();
            Scene scene = new Scene(parent);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setMaximized(true);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(DataMgntController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
