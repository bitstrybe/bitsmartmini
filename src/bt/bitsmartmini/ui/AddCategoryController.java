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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import bt.bitsmartmini.bl.CategoryBL;
import bt.bitsmartmini.bl.InsertUpdateBL;
import bt.bitsmartmini.bl.ItemsBL;
import bt.bitsmartmini.entity.Category;
import bt.bitsmartmini.tablemodel.CategoryTableModel;
import bt.bitsmartmini.utils.Utilities;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.stage.WindowEvent;

public class AddCategoryController implements Initializable {

    ObservableList<CategoryTableModel> data;
    @FXML
    public Button closebtn;
    @FXML
    private JFXTextField cattextfield;
    @FXML
    private JFXButton save;
    @FXML
    private JFXTextField searchbtn;
    @FXML
    private TableView<CategoryTableModel> cattableview;
    @FXML
    private TableColumn<CategoryTableModel, String> category;
    @FXML
    private TableColumn<CategoryTableModel, Boolean> action;
    @FXML
    private Label displayinfo;
    @FXML
    private FontAwesomeIcon duplicatelock;
    @FXML
    private FontAwesomeIcon check;
    @FXML
    private JFXSpinner spinner;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        save.setDisable(true);
        Utilities.repeatFocus(cattextfield);
        CategoryBL form = new CategoryBL();
        cattextfield.textProperty().addListener(e -> {
//            check.setVisible(false);
            if (cattextfield.getLength() > 0) {
                save.setDisable(false);
                Category value = form.getCategoryById(cattextfield.getText());
                System.out.println(value);
                if (value != null) {
                    save.setDisable(true);
                    displayinfo.setText("DUPLICATE FORUND!!!");
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
        TableData("");

        searchbtn.textProperty().addListener(e -> {
            if (searchbtn.getText().length() > 1) {
                TableData(searchbtn.getText());
            } else {
                TableData("");
            }
        });

        cattextfield.setOnKeyPressed((KeyEvent event) -> {
            if (!cattextfield.getText().isEmpty()) {
                if (event.getCode() == KeyCode.ENTER) {
                    saveAction(null);
                }
            } else {
                displayinfo.setText("!FIELD IS EMPTY");
            }
        });

    }

    public void clearall() {
        cattextfield.clear();
        displayinfo.setText(null);
        duplicatelock.setVisible(false);
    }

    @FXML
    private void saveAction(ActionEvent event) {
        save.setDisable(true);
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

    public void TableData(String p) {
        List<Category> c;
        if (searchbtn.getLength() > 2) {
            c = new CategoryBL().searchAllCategory(p);
        } else {
            c = new CategoryBL().getAllCategory();
        }

        data = FXCollections.observableArrayList();
        c.forEach((form) -> {
            data.add(new CategoryTableModel(form.getCategoryName()));
        });
        category.setCellValueFactory(cell -> cell.getValue().getCategoryNameProperty());
        action.setSortable(false);
        action.setMaxWidth(120);

        action.setCellValueFactory((TableColumn.CellDataFeatures<CategoryTableModel, Boolean> features) -> new SimpleBooleanProperty(features.getValue() != null));
        action.setCellFactory((TableColumn<CategoryTableModel, Boolean> personBooleanTableColumn) -> new AddPersonCell());
        cattableview.setItems(data);
        cattableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    @FXML
    private void closefrom(ActionEvent event) {
        Stage stage = (Stage) closebtn.getScene().getWindow();
        stage.close();

    }

    public class AddPersonCell extends TableCell<CategoryTableModel, Boolean> {

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
            delButton.setOnAction((ActionEvent event) -> {
                try {
                    int selectdIndex = getTableRow().getIndex();
                    //Create a new table show details of the selected item
                    CategoryTableModel selectedRecord = (CategoryTableModel) cattableview.getItems().get(selectdIndex);
                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Delete.fxml"));
                    Parent parent = (Parent) fxmlLoader.load();
                    DeleteController childController = fxmlLoader.getController();
                    childController.delete.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

                        Task<Integer> task = new Task<Integer>() {
                            @Override
                            protected Integer call() throws Exception {
                                childController.spinner.setVisible(true);
                                updateMessage(MainAppController.PROCESS_MESSAGE);
                                Thread.sleep(500);
                                List catname = new ItemsBL().getItemsFromForm(selectedRecord.getCategoryName());
                                if (catname.isEmpty()) {
                                    return deleteTemplate(selectedRecord.getCategoryName());
                                } else {
                                    return 0;
                                }

                            }
                        };
                        childController.displayinfo.textProperty().bind(task.messageProperty());
                        task.setOnSucceeded(f -> {
                            childController.displayinfo.textProperty().unbind();
                            if (task.getValue() == 1) {
                                childController.deleteTrans();
                            } else {
                                childController.errorTrans();
                            }

                        });
                        Thread d = new Thread(task);
                        d.setDaemon(true);
                        d.start();

                    });
                    Scene scene = new Scene(parent);
                    scene.setFill(Color.TRANSPARENT);
                    stage.setMaximized(true);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initOwner(parent.getScene().getWindow());
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(AddCategoryController.class.getName()).log(Level.SEVERE, null, ex);
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
        cattextfield.clear();
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

    public int saveTemplate() {
        displayinfo.textProperty().unbind();
        Category cat = new Category();
        cat.setCategoryName(cattextfield.getText());
        int result = new InsertUpdateBL().insertData(cat);
        return result;
    }

    public int deleteTemplate(String value) {
        int result = new CategoryBL().removeData(value);
        return result;
    }

}
