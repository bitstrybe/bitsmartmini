package bt.bitsmartmini.ui;

import bt.bitsmartmini.bl.InsertUpdateBL;
import bt.bitsmartmini.bl.ItemsBL;
import bt.bitsmartmini.bl.UomBL;
import bt.bitsmartmini.entity.Uom;
import bt.bitsmartmini.entity.UomSet;
import bt.bitsmartmini.tablemodel.UomSetTableModel;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.text.WordUtils;

/**
 * FXML Controller class
 *
 * @author scarface
 */
public class AddUomSetController implements Initializable {

    @FXML
    private Button closebtn;
    @FXML
    private JFXButton save;
    @FXML
    private JFXTextField searchbtn;
    @FXML
    private TableView<UomSetTableModel> uomtable;
    @FXML
    private TableColumn<UomSetTableModel, Boolean> action;
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
    private ComboBox<String> measure1;
    @FXML
    private JFXTextField untitxt1;
    @FXML
    private ComboBox<String> measure2;
    @FXML
    private JFXTextField untitxt2;

    UomBL uomb = new UomBL();

    ObservableList<UomSetTableModel> data;
    @FXML
    private TableColumn<UomSetTableModel, String> m1;
    @FXML
    private TableColumn<UomSetTableModel, Number> u1;
    @FXML
    private TableColumn<UomSetTableModel, String> m2;
    @FXML
    private TableColumn<UomSetTableModel, Number> u2;
    @FXML
    private FontAwesomeIcon c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        measure1.setOnShown(e -> {
            getMeasurements(measure1);
        });

        measure2.setOnShown(e -> {
            getMeasurements(measure2);
        });

        TableData("");
    }

    @FXML
    private void closefrom(ActionEvent event) {
        Stage stage = (Stage) closebtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void saveAction() {
        Task<Integer> task = new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                spinner.setVisible(true);
                check.setVisible(false);
                updateMessage(MainAppController.PROCESS_MESSAGE);
                Thread.sleep(500);
                return saveTemplate();
            }
        };
        displayinfo.textProperty().bind(task.messageProperty());
        task.setOnSucceeded(s -> {
            if (task.getValue() == 1) {
                saveTrans();
            } else {
                errorTrans();
            }
        });
        Thread d = new Thread(task);
        d.setDaemon(true);
        d.start();
    }

    public void getMeasurements(ComboBox<String> m) {
        m.getItems().clear();
        //measure2.getItems().clear();
        List<Uom> list = new UomBL().getUoms();
        ObservableList<Uom> result = FXCollections.observableArrayList(list);
        result.forEach((u) -> {
            m.getItems().add(WordUtils.capitalizeFully(u.getUomDesc()));
            //measure2.getItems().add(WordUtils.capitalizeFully(u.getUomDesc()));
        });
    }

    private void clearAllForms() {
        untitxt1.clear();
        untitxt2.clear();
        //closefrom();
    }

    private void saveTrans() {
        displayinfo.setText(MainAppController.SUCCESS_MESSAGE);
        clearAllForms();
        spinner.setVisible(false);
        check.setVisible(true);
        TableData("");
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(closevnt -> {
            displayinfo.setText("");
            spinner.setVisible(false);
            check.setVisible(false);
        });
        delay.play();

    }

    private void errorTrans() {
        displayinfo.setText(MainAppController.ERROR_MESSAGE);
        spinner.setVisible(false);
        check.setVisible(false);
        TableData("");
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(closevnt -> {
            displayinfo.setText("");
            spinner.setVisible(false);
            check.setVisible(false);
        });
        delay.play();
    }

    private int saveTemplate() throws FileNotFoundException, IOException {
        displayinfo.textProperty().unbind();
        UomSet cat = new UomSet();
        cat.setMeasure1(new Uom(measure1.getValue()));
        cat.setUnit1(Integer.parseInt(untitxt1.getText()));
        if (measure2.getValue().length() > 0) {
            cat.setMeasure2(new Uom(measure2.getValue()));
            cat.setUnit2(Integer.parseInt(untitxt2.getText()));
            cat.setUomSetCode(measure1.getValue() + "-" + measure2.getValue() + " " + untitxt1.getText() + " X " + untitxt2.getText());
            //System.out.println("UC: "+cat.getUomSetCode());
        } else {
            cat.setUomSetCode(measure1.getValue() + " OF " + untitxt1.getText());
        }
        int result = new InsertUpdateBL().updateData(cat);
        return result;
    }

    public int deleteTemplate(String value) {
        int result = new ItemsBL().removeData(value);
        return result;
    }

    public void TableData(String p) {
        List<UomSet> c;
        if (p.length() > 0) {
            c = uomb.getUomSets(p);
        } else {
            c = uomb.getUomSets();
        }
        data = FXCollections.observableArrayList();
        c.forEach((item) -> {
            try {
                data.add(new UomSetTableModel(item.getUomSetCode(), item.getMeasure1().getUomDesc(), item.getUnit1(), item.getMeasure2().getUomDesc(), item.getUnit2()));
            } catch (Exception ex) {
                Logger.getLogger(AddItemsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        m1.setCellValueFactory(cell -> cell.getValue().getUomM1Property());
        u1.setCellValueFactory(cell -> cell.getValue().getUomU1Property());
        m2.setCellValueFactory(cell -> cell.getValue().getUomM2Property());
        u2.setCellValueFactory(cell -> cell.getValue().getUomU2Property());

        action.setSortable(false);
        action.setCellValueFactory((TableColumn.CellDataFeatures<UomSetTableModel, Boolean> features) -> new SimpleBooleanProperty(features.getValue() != null));
        //action.setCellFactory((TableColumn<UomSetTableModel, Boolean> personBooleanTableColumn) -> new AddUomSetController().ItemDeleteCell());
        uomtable.setItems(data);
        uomtable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
    }

}
