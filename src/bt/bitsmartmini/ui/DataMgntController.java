/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.bitsmartmini.ui;

import bt.bitsmartmini.utils.Utilities;
import com.jfoenix.controls.JFXSpinner;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author JScare
 */
public class DataMgntController {

    @FXML
    private Button closebtn;
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
    
    Utilities utl = new Utilities();

    @FXML
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
