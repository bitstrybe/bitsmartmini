package bt.bitsmartmini.ui;

import bt.bitsmartmini.utils.Utilities;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.smattme.MysqlExportService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.sql.SQLException;
import java.util.Date;
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
import javax.swing.JOptionPane;
import org.joda.time.DateTime;

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
            if (Backupdbtosql() == 0) {
                displayinfo.setText("BACKUP SUCESSFUL");
                spinner.setVisible(false);
                check.setVisible(true);
                closebtn();
            } else {

            }
        });
        Thread d = new Thread(task);
        d.setDaemon(true);
        d.start();
    }

    @FXML
    private void closebtn() {
        Stage stage = (Stage) displayinfo.getScene().getWindow();
        stage.close();
    }

    public static int Backupdbtosql() {
        try {

            /*NOTE: Getting path to the Jar file being executed*/
 /*NOTE: YourImplementingClass-> replace with the class executing the code*/
            String username = System.getProperty("user.name");
            Path path = FileSystems.getDefault().getPath("C:\\Users\\", username, "\\AppData\\Roaming\\Backup");
            Path databasepath = FileSystems.getDefault().getPath("C:\\Program Files (x86)\\Bitsmartsmini\\DatabaseFiles\\bin\\mysqldump.exe");
            System.out.println(path.toString());

            /*NOTE: Creating Database Constraints*/
            String dbName = "bitsmartmini";
            String dbUser = "root";
            String dbPass = "bitstrybe@21";

            /*NOTE: Creating Path Constraints for folder saving*/
            /*NOTE: Here the backup folder is created for saving inside it*/
            String folderPath = path + "\\backup";

            /*NOTE: Creating Folder if it does not exist*/
            File f1 = new File(folderPath);
            f1.mkdir();

            /*NOTE: Creating Path Constraints for backup saving*/
            /*NOTE: Here the backup is saved in a folder called backup with the name backup.sql*/
            
            String savePath = "\"" + path + "\\backup\\" + "LastBackup.sql\"";

            /*NOTE: Used to create a cmd command*/
            String executeCmd = databasepath + " -u" + dbUser + " -p" + dbPass + " --opt --routines --triggers --databases " + dbName + " -r " + savePath;
            System.out.println(executeCmd);

            /*NOTE: Executing the command here*/
            Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
            int processComplete = runtimeProcess.waitFor();
            System.out.println(processComplete);

            /*NOTE: processComplete=0 if correctly executed, will contain other values if not*/
            if (processComplete == 0) {
                System.out.println("Backup Complete");
            } else {
                System.out.println("Backup Failure");
            }
            return processComplete;

        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
        }
        return 0;
    }
}
