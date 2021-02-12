package bt.bitsmartmini.ui;

import bt.bitsmartmini.bl.InsertUpdateBL;
import bt.bitsmartmini.entity.BackupLog;
import com.jfoenix.controls.JFXSpinner;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.EncryptionMethod;

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
    @FXML
    private TextField dirtextfield;
    
    DirectoryChooser dir = new DirectoryChooser();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        

    }

    public void Backupdbtosql(File path) throws IOException {
        String dbpath = new File(".").getCanonicalPath();
//        System.out.println("Application Folder: "+pathc);
        Path databasepath = FileSystems.getDefault().getPath(dbpath + "\\DatabaseFiles\\bin\\mysqldump.exe");
        String savePath = "\"" + path + "\\backup\\" + "LastBackup.sql\"";
        InsertUpdateBL b = new InsertUpdateBL();

        String dbName = "bitsmartmini";
        String dbUser = "root";
        String dbPass = "bitstrybe@21";

        String folderPath = path + "\\backup";

        File f1 = new File(folderPath);
        boolean bool = f1.mkdir();
//        Files.setAttribute(f1.toPath(), "dos:hidden", true);
        if (bool) {
            System.out.println("Directory created successfully");
        } else {
            System.out.println("Sorry couldn’t create specified directory");
        }

        String executeCmd = databasepath + " -u" + dbUser + " -p" + dbPass + " --opt --routines --triggers --databases " + dbName + " -r " + savePath;

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                spinner.setVisible(true);
//                restore.setDisable(true);
                closebtn.setDisable(true);
                updateMessage("BACKING UP DATA PLEASE WAIT");
                try {
                    b.insertData(new BackupLog(null, new Date(System.currentTimeMillis())));
                    Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                    runtimeProcess.waitFor();
                } catch (IOException ex) {
                    Logger.getLogger(RestoreController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        };
        displayinfo.textProperty().bind(task.messageProperty());
        task.setOnSucceeded(e -> {
            displayinfo.textProperty().unbind();
            displayinfo.setText("BACK UP COMPLETE");
            spinner.setVisible(false);
            check.setVisible(true);
            closebtn.setDisable(false);
            try {
                ZipParameters zipParameters = new ZipParameters();
                zipParameters.setEncryptFiles(true);
                zipParameters.setEncryptionMethod(EncryptionMethod.AES);
                zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);

                ZipFile zipFile = new ZipFile(path+"/backup/LastBackup.zip", "bitstrybe@21".toCharArray());
                zipFile.addFile(path+"/backup/LastBackup.sql", zipParameters);
                File f = new File(path+"/backup/LastBackup.sql");
                f.delete();
                
            } catch (net.lingala.zip4j.exception.ZipException ex) {
                Logger.getLogger(BackupController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
        Thread d = new Thread(task);
        d.setDaemon(true);
        d.start();

    }

    @FXML
    private void closefrom() {
        Stage stage = (Stage) displayinfo.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void backupAction(ActionEvent event) throws IOException {
        Backupdbtosql(new File(dirtextfield.getText()));

    }

    @FXML
    private void backupfilechooser(ActionEvent event) {
        
        File file = dir.showDialog(null);
        dirtextfield.setText(file.getAbsolutePath());

    }

}
