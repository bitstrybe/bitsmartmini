/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.bitsmartmini.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.lingala.zip4j.ZipFile;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class RestoreController implements Initializable {

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
    private JFXButton restore;
    @FXML
    private TextField restoretextfield;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void restoreAction(ActionEvent event) throws IOException {
        Restoredbfromsql(new File(restoretextfield.getText()));
    }

    public void Restoredbfromsql(File s) throws IOException {
        String username = System.getProperty("user.name");

//        Path path = FileSystems.getDefault().getPath("C:\\Users\\", username, "\\AppData\\Roaming");
        Path databasepath = FileSystems.getDefault().getPath("C:\\Program Files (x86)\\Bitsmartsmini\\DatabaseFiles\\bin\\mysql.exe");
//        System.out.println(path.toString());
        System.out.println("File " + s);
        System.out.println("Path " + s.getParent());
        new ZipFile(s, "bitstrybe@21".toCharArray()).extractFile("LastBackup.sql", s.getParent());

        String dbName = "bitsmartmini";
        String dbUser = "root";
        String dbPass = "bitstrybe@21";

        String restorePath = s.getParent() + "\\LastBackup.sql";
        System.out.println("Restore Path : "+restorePath);

        System.out.println(databasepath.toString());

        String[] executeCmd = new String[]{databasepath.toString(), dbName, "-u" + dbUser, "-p" + dbPass, "-e", " source " + restorePath};
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                spinner.setVisible(true);
                restore.setDisable(true);
                closebtn.setDisable(true);
                updateMessage("RESTORING DATA PLEASE WAIT");

                try {
                    Process runtimeProcess = Runtime.getRuntime().exec(executeCmd);
                    runtimeProcess.waitFor();
                    return null;
                } catch (IOException ex) {
                    Logger.getLogger(RestoreController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        };
        displayinfo.textProperty().bind(task.messageProperty());
        task.setOnSucceeded(v -> {
            displayinfo.textProperty().unbind();
            displayinfo.setText("RESTORE SUCESSFULLY");
            spinner.setVisible(false);
            check.setVisible(true);
            closebtn.setDisable(false);
            File f = new File(s.getParent() + "\\LastBackup.sql");
            f.delete();

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
    private void chooseRestoreFile(ActionEvent event) {
        FileChooser files = new FileChooser();
        File file = files.showOpenDialog(null);
        restoretextfield.setText(file.getAbsolutePath());

    }

}
