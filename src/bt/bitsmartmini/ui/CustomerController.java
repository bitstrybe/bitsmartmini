
package bt.bitsmartmini.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import bt.bitsmartmini.bl.CustomerBL;
import bt.bitsmartmini.bl.InsertUpdateBL;
import bt.bitsmartmini.bl.SalesBL;
import bt.bitsmartmini.entity.Customers;
import bt.bitsmartmini.tablemodel.CustomersTableModel;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class CustomerController extends ItemCartController implements Initializable {
     ObservableList<CustomersTableModel> data;

    @FXML
    private Button closebtn;
    @FXML
    private JFXTextField customertextfield;
    @FXML
    private JFXTextField customerphone;
    @FXML
    public JFXButton save;
    @FXML
    public Label displayinfo;
    @FXML
    public JFXSpinner spinner;
    @FXML
    public FontAwesomeIcon check;
    @FXML
    public FontAwesomeIcon duplicatelock;
    @FXML
    private JFXTextField searchbtn;
    @FXML
    private TableView<CustomersTableModel> customertable;
    @FXML
    private TableColumn<CustomersTableModel, String> phonetb;
    @FXML
    private TableColumn<CustomersTableModel, Boolean> action;
    @FXML
    private TableColumn<CustomersTableModel, String> fullnametb;
    @FXML
    private HBox statushbox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TableData();
    }

    @FXML
    public void closefrom() {
        Stage stage = (Stage) closebtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void saveAction(ActionEvent event) {}
    
     public void TableData() {
        List<Customers> c = new CustomerBL().getAllCustomers();
        data = FXCollections.observableArrayList();
        c.forEach((customer) -> {
            data.add(new CustomersTableModel(customer.getCustomerId(),customer.getFullname(),customer.getMobile()));
        });
        fullnametb.setCellValueFactory(cell -> cell.getValue().getCustomersProperty());
        phonetb.setCellValueFactory(cell->cell.getValue().getPhoneProperty());
        
        action.setSortable(false);
        action.setMaxWidth(120);

        action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<CustomersTableModel, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<CustomersTableModel, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });
        action.setCellFactory(new Callback<TableColumn<CustomersTableModel, Boolean>, TableCell<CustomersTableModel, Boolean>>() {
            @Override
            public TableCell<CustomersTableModel, Boolean> call(TableColumn<CustomersTableModel, Boolean> personBooleanTableColumn) {
                return new AddPersonCell();
            }
        });
        customertable.setItems(data);
        customertable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }
     
     public class AddPersonCell extends TableCell<CustomersTableModel, Boolean> {

        //Image img = new Image(getClass().getResourceAsStream("edit.png"));
        Image img2 = new Image(getClass().getResourceAsStream("delete.png"));

        // a button for adding a new person.
        JFXButton addButton = new JFXButton();

        // pads and centers the add button in the cell.
        HBox paddedButton = new HBox();

        JFXButton delButton = new JFXButton();
        // records the y pos of the last button press so that the add person dialog can be shown next to the cell.
        final DoubleProperty buttonY = new SimpleDoubleProperty();

        /**
         * AddPersonCell constructor
         *
         * @param stage the stage in which the table is placed.
         * @param table the table to which a new person can be added.
         */
        AddPersonCell() {
            paddedButton.setStyle("-fx-alignment: CENTER;");
            paddedButton.getChildren().add(delButton);
            delButton.setGraphic(new ImageView(img2));
            delButton.setRipplerFill(Paint.valueOf("#D8E1DC"));
            delButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override

                public void handle(ActionEvent event) {

                    try {

                        int selectdIndex = getTableRow().getIndex();
                        //Create a new table show details of the selected item
                        CustomersTableModel selectedRecord = (CustomersTableModel) customertable.getItems().get(selectdIndex);
                        Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Delete.fxml"));
                        Parent parent = (Parent) fxmlLoader.load();
                        DeleteController childController = fxmlLoader.getController();
                        childController.delete.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

                            Task<Void> task = new Task<Void>() {
                                @Override
                                protected Void call() throws Exception {
                                    childController.spinner.setVisible(true);
                                    updateMessage("PROCESSING PLS WAIT.....");
                                    Thread.sleep(1000);
                                    return null;
                                }
                            };
                            childController.displayinfo.textProperty().bind(task.messageProperty());
                            task.setOnSucceeded(f -> {
                                childController.displayinfo.textProperty().unbind();
                                List catname = new SalesBL().getSalesByCustomer(selectedRecord.getCustomerId());
                                if (catname.isEmpty()) {
                                    int result = new CustomerBL().removeData(selectedRecord.getCustomerId());
                                    switch (result) {
                                        case 1:
                                            childController.displayinfo.setText("SUCCESSFULLY DELETED");
                                            childController.spinner.setVisible(false);
                                            childController.check.setVisible(true);
                                            TableData();
                                            stage.close();
                                            break;
                                        default:
                                            childController.displayinfo.setText("NOTICE! AN ERROR OCCURED");
                                            childController.spinner.setVisible(false);
                                            childController.check.setVisible(false);
                                            break;

                                    }
                                } else {
                                    childController.displayinfo.setText("UNABLE TO DELETE RECORD");
                                    childController.spinner.setVisible(false);
                                    childController.check.setVisible(false);
                                }
                            });
                            Thread d = new Thread(task);
                            d.setDaemon(true);
                            d.start();

                        });
                        Scene scene = new Scene(parent);
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.initOwner(parent.getScene().getWindow());
                        stage.setScene(scene);
                        stage.initStyle(StageStyle.UNDECORATED);
                        stage.resizableProperty().setValue(false);
                        stage.showAndWait();

                    } catch (IOException ex) {
                        Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            });

        }

        /**
         * places an add button in the row only if the row is not empty.
         */
        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(paddedButton);
            } else {
                setGraphic(null);
            }
        }
    }
     
     private void clearAllForms() {
        customertextfield.clear();
        customerphone.clear();
    }

    private void closeTransition() {
        save.setDisable(true);
        clearAllForms();
        displayinfo.setText("SUCCESSFULLY SAVED");
        spinner.setVisible(false);
        check.setVisible(true);
        TableData();
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(closevnt -> {
            displayinfo.setText("");
            spinner.setVisible(false);
            check.setVisible(false);
        });
        delay.play();

    }

    public void saveTemplate() {
            displayinfo.textProperty().unbind();
            Customers cat = new Customers();
            cat.setFullname(customertextfield.getText());
            cat.setMobile(customerphone.getText());
            //cat.setUsers(new Users(LoginController.u.getUserid()));
            //cat.setEntryLog(new Date());
            int result = new InsertUpdateBL().insertData(cat);
            switch (result) {
                case 1:
                   closeTransition();
                    break;
                default:
                    displayinfo.setText("NOTICE! AN ERROR OCCURED");
                    spinner.setVisible(false);
                    check.setVisible(false);
                    break;

            }
        }

}
