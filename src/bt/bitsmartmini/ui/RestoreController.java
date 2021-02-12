/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.bitsmartmini.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.smattme.MysqlImportService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Service;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void restoreAction(ActionEvent event) throws IOException {
        Restoredbfromsql("LastBackup.sql");
    }

    public void Restoredbfromsql(String s) throws IOException {
        /*NOTE: String s is the mysql file name including the .sql in its name*/
 /*NOTE: Getting path to the Jar file being executed*/
 /*NOTE: YourImplementingClass-> replace with the class executing the code*/
        String username = System.getProperty("user.name");

        Path path = FileSystems.getDefault().getPath("C:\\Users\\", username, "\\AppData\\Roaming");
        Path databasepath = FileSystems.getDefault().getPath("C:\\Program Files (x86)\\Bitsmartsmini\\DatabaseFiles\\bin\\mysql.exe");
        System.out.println(path.toString());

        /*NOTE: Creating Database Constraints*/
        String dbName = "bitsmartmini";
        String dbUser = "root";
        String dbPass = "bitstrybe@21";

        /*NOTE: Creating Path Constraints for restoring*/
        String restorePath = path + "\\backup" + "\\" + s;
        System.out.println(restorePath);

        System.out.println(databasepath.toString());

        /*NOTE: Used to create a cmd command*/
 /*NOTE: Do not create a single large string, this will cause buffer locking, use string array*/
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

}
