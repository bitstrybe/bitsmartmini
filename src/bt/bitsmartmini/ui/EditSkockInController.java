/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.bitsmartmini.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class EditSkockInController implements Initializable {

    @FXML
    public JFXTextField batchtextfield;
    @FXML
    public DatePicker expirydate;
    @FXML
    public JFXTextField costtextfield;
    @FXML
    public JFXTextField salestextfield;
    @FXML
    public JFXTextField nhistextfield;
    @FXML
    public JFXTextField qnttextfield;
    @FXML
    public JFXButton save;
    @FXML
    private Button closeButton;
    @FXML
    public JFXTextField costpiecestextfield;
    @FXML
    public JFXTextField salespiecetextfield;
    @FXML
    public Label itemname;
    @FXML
    public JFXTextField nhispiecetextfield;
    @FXML
    public Label displayinfo;
    @FXML
    public JFXSpinner spinner;
    @FXML
    public FontAwesomeIcon check;
    @FXML
    public FontAwesomeIcon duplicatelock;
    @FXML
    public AnchorPane stockpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         expirydate.setConverter(new StringConverter<LocalDate>() {
            private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            @Override
            public String toString(LocalDate object) {
                if (object == null) {
                    return "";
                }
                return dateTimeFormatter.format(object);
            }

            @Override
            public LocalDate fromString(String datestring) {
                if (datestring == null || datestring.trim().isEmpty()) {
                    return null;
                }
                return LocalDate.parse(datestring, dateTimeFormatter);
            }

        });
    }    

    @FXML
    private void closemtd(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
}
