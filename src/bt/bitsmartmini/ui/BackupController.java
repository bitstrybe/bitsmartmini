package bt.bitsmartmini.ui;

import bt.bitsmartmini.bl.BackupLogBL;
import bt.bitsmartmini.entity.BackupLog;
import com.jfoenix.controls.JFXSpinner;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author scarface
 */
public class BackupController implements Initializable {

    @FXML
    private Label displayinfo;
    @FXML
    private JFXSpinner spinner;
    @FXML
    private FontAwesomeIcon check;
    @FXML
    private FontAwesomeIcon duplicatelock;
    @FXML
    private Button closebtn;
    @FXML
    private HBox statushbox; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        

    }

    public static int Backupdbtosql() {
        try {
            BackupLogBL b = new BackupLogBL();

            /*NOTE: Getting path to the Jar file being executed*/
 /*NOTE: YourImplementingClass-> replace with the class executing the code*/
            String username = System.getProperty("user.name");
            Path path = FileSystems.getDefault().getPath("C:\\Users\\", username, "\\AppData\\Roaming");
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
            boolean bool = f1.mkdir();
            Files.setAttribute(f1.toPath(), "dos:hidden", true);
            if (bool) {
                System.out.println("Directory created successfully");
            } else {
                System.out.println("Sorry couldn’t create specified directory");
            }

            /*NOTE: Creating Path Constraints for backup saving*/
 /*NOT      E: Here the backup is saved in a folder called backup with the name backup.sql*/
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
                b.insertData(new BackupLog(null, new Date(System.currentTimeMillis())));
                if (processComplete == 1) {
                    processComplete = 0;
                    System.out.println("Backup process was successful");
                } else {
                    processComplete = 1;
                }
            } else {
                processComplete = 1;
                System.out.println("Backup process failed");
            }
            return processComplete;

        } catch (IOException | InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Error at Backuprestore" + ex.getMessage());
        }
        return 0;
    }

    @FXML
    private void closefrom() {
        Stage stage = (Stage) displayinfo.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void backupAction(ActionEvent event) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                spinner.setVisible(true);
                updateMessage("Processing...");
                Thread.sleep(500);
                return null;
            }
        };
        displayinfo.textProperty().bind(task.messageProperty());
        task.setOnSucceeded(s -> {
            displayinfo.textProperty().unbind();
            if (Backupdbtosql() == 0) {
                displayinfo.setText("Backup process was successful");
                spinner.setVisible(false);
                check.setVisible(true);
                closefrom();
            } else {
                displayinfo.setText("Backup process failed");
            }
        });
        Thread d = new Thread(task);
        d.setDaemon(true);
        d.start();
    }

//    private static void setHiddenAttrib(Path filePath) {
//        try {
//            DosFileAttributes attr = Files.readAttributes(filePath, DosFileAttributes.class);
//            System.out.println(filePath.getFileName() + " Hidden attribute is " + attr.isHidden());
//            Files.setAttribute(filePath, "dos:hidden", true);
//            attr = Files.readAttributes(filePath, DosFileAttributes.class);
//            System.out.println(filePath.getFileName() + " Hidden attribute is " + attr.isHidden());
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
}
