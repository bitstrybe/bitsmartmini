/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.bitsmartmini.ui;

import com.jfoenix.controls.JFXSpinner;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

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

    @FXML
    private void closefrom(ActionEvent event) {
        Stage s = (Stage) closebtn.getScene().getWindow();
        s.close();
    }

    @FXML
    private void backupAction(ActionEvent event) {
    }

    @FXML
    private void RestoreAction(ActionEvent event) {
    }
    
}
