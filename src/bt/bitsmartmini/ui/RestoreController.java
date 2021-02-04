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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class RestoreController implements Initializable {

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
    private void closebtn(ActionEvent event) {
    }

    @FXML
    private void backupDB(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        String username = System.getProperty("user.name");
        String sql = new String(Files.readAllBytes(Paths.get("C:\\Users\\", username, "\\Desktop\\Backup\\Restore\\6_5_2020_12_07_26_drugstore_database_dump.sql")));

        boolean res = MysqlImportService.builder()
                .setDatabase("drugstore")
                .setSqlString(sql)
                .setUsername("root")
                .setPassword("1234")
                .setDeleteExisting(true)
                .setDropExisting(true)
                .importDatabase();

        assert(res);
        System.out.println("restore successfully");
    }

}
