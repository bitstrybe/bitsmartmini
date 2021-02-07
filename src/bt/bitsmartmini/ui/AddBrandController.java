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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import bt.bitsmartmini.bl.InsertUpdateBL;
import bt.bitsmartmini.bl.ItemsBL;
import bt.bitsmartmini.bl.BrandBL;
import bt.bitsmartmini.entity.Brands;
import bt.bitsmartmini.entity.Manufacturer;
import bt.bitsmartmini.tablemodel.BrandTableModel;
import org.apache.commons.text.WordUtils;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class AddBrandController implements Initializable {

    ObservableList<BrandTableModel> data;

    @FXML
    public Label displayinfo;
    @FXML
    public JFXTextField manutextfield;
    @FXML
    public FontAwesomeIcon duplicatelock;
    @FXML
    private JFXTextField searchbtn;
     @FXML
    private TableView<BrandTableModel> brandtable;
    @FXML
    private TableColumn<BrandTableModel, String> brand;
    @FXML
    private TableColumn<BrandTableModel, Boolean> action;
    @FXML
    private JFXButton save;
    @FXML
    private Button closebtn;
    private AnchorPane clearpane;
    @FXML
    private HBox statushbox;
    @FXML
    private FontAwesomeIcon check;
    @FXML
    private JFXSpinner spinner;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        BrandBL man = new BrandBL();
        // TODO
        manutextfield.textProperty().addListener(e -> {
            //  System.out.println(cattextfield.getText());
//            check.setVisible(false);
            if (manutextfield.getLength() > 0) {
                String value = man.getBrandsbyId(manutextfield.getText());
                if (value != null) {
                    save.setDisable(true);
                    displayinfo.setText("Duplicate Found!!!");
                    duplicatelock.setVisible(true);
                } else if (value == null) {
                    save.setDisable(false);
                    displayinfo.setText(null);
                    duplicatelock.setVisible(false);
                }
            } else {
                save.setDisable(true);
            }

        });
        TableData();
        searchbtn.textProperty().addListener(e -> {
            if (searchbtn.getText().length() > 1) {
                TableData(searchbtn.getText());
            } else {
                TableData();
            }
        });

        manutextfield.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (!manutextfield.getText().isEmpty()) {
                    if (event.getCode() == KeyCode.ENTER) {

                        save.setDisable(true);
                        Task<Void> task = new Task<Void>() {
                            @Override
                            protected Void call() throws Exception {
//                            spinner.setVisible(true);
                                updateMessage("PROCESSING PLS WAIT.....");
                                Thread.sleep(1000);
                                return null;
                            }
                        };
                        displayinfo.textProperty().bind(task.messageProperty());
                        task.setOnSucceeded(s -> {
                            saveTemplate();
                        });
                        Thread d = new Thread(task);
                        d.setDaemon(true);
                        d.start();

                    }
                } else {
                    displayinfo.setText("!FIELD IS EMPTY");
                    spinner.setVisible(false);
                    check.setVisible(false);
                }

            }

        });
    }

    @FXML
    private void saveAction(ActionEvent event) {
        save.setDisable(true);
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                spinner.setVisible(true);
                updateMessage("PROCESSING PLS WAIT.....");
                Thread.sleep(1000);
                return null;
            }
        };
        displayinfo.textProperty().bind(task.messageProperty());
        task.setOnSucceeded(s -> {
            saveTemplate();
        });
        Thread d = new Thread(task);
        d.setDaemon(true);
        d.start();
    }

    public void TableData() {
        List<Brands> c = new BrandBL().getAllBrands();
        data = FXCollections.observableArrayList();
        c.forEach((brand) -> {
            data.add(new BrandTableModel(brand.getBrandName()));
        });
        brand.setCellValueFactory(cell -> cell.getValue().getBrandProperty());
        action.setSortable(false);
        action.setMaxWidth(480);
        action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BrandTableModel, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<BrandTableModel, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });
        action.setCellFactory(new Callback<TableColumn<BrandTableModel, Boolean>, TableCell<BrandTableModel, Boolean>>() {
            @Override
            public TableCell<BrandTableModel, Boolean> call(TableColumn<BrandTableModel, Boolean> personBooleanTableColumn) {
                return new AddPersonCell();
            }
        });
        brandtable.setItems(data);
//        clientTable.getColumns().add(action);
        brandtable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    public void TableData(String p) {
        List<Brands> c = new BrandBL().searchAllBrands(p);
        data = FXCollections.observableArrayList();

        c.forEach((brand) -> {
            data.add(new BrandTableModel(brand.getBrandName()));
        });
        brand.setCellValueFactory(cell -> cell.getValue().getBrandProperty());
        action.setSortable(false);
        action.setMaxWidth(480);

        action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<BrandTableModel, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<BrandTableModel, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });
        action.setCellFactory(new Callback<TableColumn<BrandTableModel, Boolean>, TableCell<BrandTableModel, Boolean>>() {
            @Override
            public TableCell<BrandTableModel, Boolean> call(TableColumn<BrandTableModel, Boolean> personBooleanTableColumn) {
                return new AddPersonCell();
            }
        });
        brandtable.setItems(data);
//        clientTable.getColumns().add(action);
        brandtable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    @FXML
    private void closefrom(ActionEvent event) {
        Stage s = (Stage) closebtn.getScene().getWindow();
        s.close();
    }

    public class AddPersonCell extends TableCell<BrandTableModel, Boolean> {

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
                        BrandTableModel selectedRecord = (BrandTableModel) brandtable.getItems().get(selectdIndex);
                        Stage stage = new Stage();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Delete.fxml"));
                        Parent parent = (Parent) fxmlLoader.load();
                        DeleteController childController = fxmlLoader.getController();
                        childController.delete.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                            Task<Void> task = new Task<Void>() {
                                @Override
                                protected Void call() throws Exception {
                                    childController.spinner.setVisible(true);
                                    childController.check.setVisible(false);
                                    updateMessage("PROCESSING PLS WAIT.....");
                                    Thread.sleep(1000);
                                    return null;
                                }
                            };
                            childController.displayinfo.textProperty().bind(task.messageProperty());
                            task.setOnSucceeded(f -> {
                                childController.displayinfo.textProperty().unbind();
                                List man = new ItemsBL().findItemsbyBrand(selectedRecord.getBrand());
                                if (man.isEmpty()) {
                                    int result = new BrandBL().removeData(selectedRecord.getBrand());
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
                        Logger.getLogger(AddBrandController.class.getName()).log(Level.SEVERE, null, ex);
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
        manutextfield.clear();
    }

    private void closeTransition() {
        displayinfo.setText("SUCCESSFULLY SAVED");
        clearAllForms();
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

    private void saveTemplate() {

        displayinfo.textProperty().unbind();
        Manufacturer cat = new Manufacturer();
        cat.setManufacturer(WordUtils.capitalizeFully(manutextfield.getText()));
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
