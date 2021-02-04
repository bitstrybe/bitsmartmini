package bt.bitsmartmini.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.smattme.MysqlExportService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author scarface
 */
public class BackupController implements Initializable {

    @FXML
    private JFXButton backup;
    @FXML
    private Label displayinfo;
    @FXML
    private JFXSpinner spinner;
    @FXML
    private FontAwesomeIcon check;
    @FXML
    private FontAwesomeIcon duplicatelock;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    public void backupDB() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                spinner.setVisible(true);
                updateMessage("PROCESSING PLS WAIT.....");
                Thread.sleep(2000);
                return null;
            }
        };
        displayinfo.textProperty().bind(task.messageProperty());
        task.setOnSucceeded(s -> {
            displayinfo.textProperty().unbind();
            try {
                //required properties for exporting of db
                Properties properties = new Properties();
                properties.setProperty(MysqlExportService.DB_NAME, "pharmabitsmini");
                properties.setProperty(MysqlExportService.DB_USERNAME, "root");
                properties.setProperty(MysqlExportService.DB_PASSWORD, "1234");
//                System.out.println(System.getProperty("user.name"));
                String username = System.getProperty("user.name");
                Path path = Paths.get("C:\\Users\\", username, "\\AppData\\Roaming\\Backup");
                properties.setProperty(MysqlExportService.TEMP_DIR, path.toString());
                properties.setProperty(MysqlExportService.PRESERVE_GENERATED_ZIP, "true");
                MysqlExportService mysqlExportService = new MysqlExportService(properties);
                File file = mysqlExportService.getGeneratedZipFile();
                mysqlExportService.clearTempFiles(false);
                mysqlExportService.export();
                displayinfo.setText("BACKUP SUCCESSFUL");
                spinner.setVisible(false);
                check.setVisible(true);
                Stage stage = (Stage) backup.getScene().getWindow();
                stage.close();
            } catch (IOException ex) {
                Logger.getLogger(BackupController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(BackupController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BackupController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        Thread d = new Thread(task);
        d.setDaemon(true);
        d.start();
    }

    @FXML
    private void closebtn(ActionEvent event) {
        Stage stage = (Stage) displayinfo.getScene().getWindow();
        stage.close();
    }

}
