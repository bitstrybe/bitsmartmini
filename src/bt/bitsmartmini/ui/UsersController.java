package bt.bitsmartmini.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
import javafx.scene.control.ComboBox;
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
import bt.bitsmartmini.bl.InsertUpdateBL;
import bt.bitsmartmini.bl.LoginBL;
import bt.bitsmartmini.bl.SalesBL;
import bt.bitsmartmini.bl.UsersBL;
import bt.bitsmartmini.entity.Users;
import bt.bitsmartmini.tablemodel.UsersTableModel;
import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcons;
import javafx.scene.paint.Color;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class UsersController implements Initializable {

    ObservableList<UsersTableModel> data;

    @FXML
    private Button closebtn;
    @FXML
    private ComboBox<String> titlechoickbox;
    @FXML
    private JFXTextField fnametextfield;
    @FXML
    private JFXTextField lnametextfield;
    @FXML
    private JFXTextField mobiletextfield;
    @FXML
    private JFXTextField emailtextfield;
    @FXML
    private JFXTextField usernametextfield;
    @FXML
    private JFXTextField pswtextfield;
    @FXML
    private ComboBox<String> roleschoicebox;
    @FXML
    private ComboBox<String> accstuchoicebox;
    @FXML
    private JFXButton save;
    @FXML
    private Label displayinfo;
    @FXML
    private FontAwesomeIcon duplicatelock;
    @FXML
    private JFXTextField searchbtn;
    @FXML
    private TableView<UsersTableModel> userstable;
    @FXML
    private TableColumn<UsersTableModel, String> fullnametb;
    @FXML
    private TableColumn<UsersTableModel, String> mobiletb;
    @FXML
    private TableColumn<UsersTableModel, String> emailtb;
    @FXML
    private TableColumn<UsersTableModel, String> usernametb;
    @FXML
    private TableColumn<UsersTableModel, String> rolestb;
    @FXML
    private TableColumn<UsersTableModel, String> statustb;
    @FXML
    private TableColumn<UsersTableModel, Boolean> action;
    @FXML
    private FontAwesomeIcon check;
    @FXML
    private JFXSpinner spinner;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        TableData();
        roleschoicebox.getItems().add("Sales");
        roleschoicebox.getSelectionModel().select("Sales");
        roleschoicebox.getSelectionModel().select("Supervisor");

        accstuchoicebox.getItems().add("Active");
        accstuchoicebox.getItems().add("Inactive");
        accstuchoicebox.getSelectionModel().select("Active");

        titlechoickbox.getItems().add("Mr");
        titlechoickbox.getItems().add("Mrs");
        titlechoickbox.getSelectionModel().select("Mr");

    }

    @FXML
    private void closefrom(ActionEvent event) {
        Stage s = (Stage) closebtn.getScene().getWindow();
        s.close();
    }

    @FXML
    private void saveAction(ActionEvent event) {
        save.setDisable(true);
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                spinner.setVisible(true);
                check.setVisible(false);
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
        List<Users> c = new UsersBL().getAllUsers();
        data = FXCollections.observableArrayList();
        c.forEach((users) -> {
            String strstatus;
            String straccount;
            if (null == users.getActive()) {
                strstatus = "Inactive";
            } else {
                switch (users.getPwdStatus()) {
                    case 1:
                        strstatus = "Active";
                        break;
                    default:
                        strstatus = "Inactive";
                        break;
                }
            }
            if (null == users.getActive()) {
                straccount = "Inactive";
            } else {
                switch (users.getActive()) {
                    case 1:
                        straccount = "Active";
                        break;
                    default:
                        straccount = "Inactive";
                        break;
                }
            }
            String fullname = users.getTitle() + " " + users.getFname() + " " + users.getLname();
            data.add(new UsersTableModel(users.getUserid(), users.getTitle(), fullname, users.getFname(), users.getLname(), users.getMobile(), users.getEmail(), users.getUsername(), users.getRoles(), strstatus, straccount));
        });
        fullnametb.setCellValueFactory(cell -> cell.getValue().getFullnameProperty());
        mobiletb.setCellValueFactory(cell -> cell.getValue().getMobileProperty());
        emailtb.setCellValueFactory(cell -> cell.getValue().getEmailProperty());
        usernametb.setCellValueFactory(cell -> cell.getValue().getUsernameProperty());
        rolestb.setCellValueFactory(cell -> cell.getValue().getRolesProperty());
        statustb.setCellValueFactory(cell -> cell.getValue().getStatusProperty());
        action.setSortable(false);
        action.setMaxWidth(480);

        action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<UsersTableModel, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<UsersTableModel, Boolean> features) {
                return new SimpleBooleanProperty(features.getValue() != null);
            }
        });
        action.setCellFactory(new Callback<TableColumn<UsersTableModel, Boolean>, TableCell<UsersTableModel, Boolean>>() {
            @Override
            public TableCell<UsersTableModel, Boolean> call(TableColumn<UsersTableModel, Boolean> personBooleanTableColumn) {
                return new AddPersonCell();
            }
        });
        userstable.setItems(data);
//        clientTable.getColumns().add(action);
        userstable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    public class AddPersonCell extends TableCell<UsersTableModel, Boolean> {

//        Image img = new Image(getClass().getResourceAsStream("edit.png"));
        Image img2 = new Image(getClass().getResourceAsStream("delete.png"));

        // pads and centers the add button in the cell.
        HBox paddedButton = new HBox();

        JFXButton delButton = new JFXButton();
        JFXButton reset = new JFXButton();
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
            paddedButton.getChildren().addAll(reset, delButton);
            reset.setGraphic(GlyphsDude.createIcon(FontAwesomeIcons.EDIT));
            delButton.setGraphic(new ImageView(img2));
            delButton.setRipplerFill(Paint.valueOf("#D8E1DC"));

            reset.setOnAction(value -> {
                try {
                    int selectdIndex = getTableRow().getIndex();
                    //Create a new table show details of the selected item
                    UsersTableModel selectedRecord = (UsersTableModel) userstable.getItems().get(selectdIndex);

                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChangePassword.fxml"));
                    Parent parent = (Parent) fxmlLoader.load();
                    Scene scene = new Scene(parent);
                    scene.setFill(Color.TRANSPARENT);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(scene);
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.show();
                    ChangePasswordController childController = fxmlLoader.getController();
//            System.out.println("Reached");
                    childController.login.addEventHandler(MouseEvent.MOUSE_CLICKED, v -> {
                        Task<Void> task = new Task<Void>() {
                            @Override
                            protected Void call() throws Exception {
//                        childController.spinner.setVisible(true);
                                updateMessage("Data Processing...");
                                Thread.sleep(1000);
                                return null;
                            }
                        };
                        childController.login.textProperty().bind(task.messageProperty());
                        task.setOnSucceeded(s -> {
                            childController.login.textProperty().unbind();
                            if (!childController.repassword.getText().equals(childController.password.getText())) {
                                //System.out.println("Password does match");
                            } else {
                                Users user = new Users();
                                user.setUserid(selectedRecord.getUserId());
                                user.setTitle(selectedRecord.getTitle());
                                user.setFname(selectedRecord.getFname());
                                user.setLname(selectedRecord.getLname());
                                user.setMobile(selectedRecord.getMobile());
                                user.setEmail(childController.emailaddress.getText());
                                user.setUsername(selectedRecord.getUsername());
                                user.setPwd(DigestUtils.md5Hex(new StringBuilder().append(childController.password.getText()).append(LoginBL.DKEY).toString()));
                                user.setRoles(selectedRecord.getRoles());
                                user.setDateCreated(new Date());
                                user.setModifiedDate(new Date());
                                user.setPwdStatus(0);
                                user.setActive(1);
                                int result = new InsertUpdateBL().updateData(user);
                                System.out.println("match");
                                childController.login.setText("Password Changed");
                                TableData();
                            }
                            stage.close();

                            //dolog();
                        });
                        Thread d = new Thread(task);
                        d.setDaemon(true);
                        d.start();
                    });
                } catch (IOException ex) {
                    Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
                }

            });
            delButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override

                public void handle(ActionEvent event) {
                    try {
                        int selectdIndex = getTableRow().getIndex();
                        //Create a new table show details of the selected item
                        UsersTableModel selectedRecord = (UsersTableModel) userstable.getItems().get(selectdIndex);
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
                                List man = new SalesBL().getUsersFromSales(selectedRecord.getUserId());
                                if (man.isEmpty()) {
                                    int result = new UsersBL().removeData(selectedRecord.getUserId());
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
                                    childController.displayinfo.setText("DATA IS IN USE CAN NOT DELETE");
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
        titlechoickbox.getSelectionModel().selectFirst();
        fnametextfield.clear();
        lnametextfield.clear();
        mobiletextfield.clear();
        emailtextfield.clear();
        usernametextfield.clear();
        pswtextfield.clear();
        roleschoicebox.getSelectionModel().selectFirst();
        accstuchoicebox.getSelectionModel().selectFirst();
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

    private void saveTemplate() {

        displayinfo.textProperty().unbind();
        Users users = new Users();
        users.setTitle(titlechoickbox.getSelectionModel().getSelectedItem());
        users.setFname(fnametextfield.getText());
        users.setLname(lnametextfield.getText());
        users.setMobile(mobiletextfield.getText());
        users.setEmail(emailtextfield.getText());
        users.setUsername(usernametextfield.getText());
        users.setPwd(DigestUtils.md5Hex(new StringBuilder().append(pswtextfield.getText()).append("LXES3KURITICHECKSALT").toString()));
        users.setRoles(roleschoicebox.getSelectionModel().getSelectedItem());
        users.setPwdStatus(0);
        if ("Active".equals(accstuchoicebox.getSelectionModel().getSelectedItem())) {
            users.setActive(1);
        } else if ("Inactive".equals(accstuchoicebox.getSelectionModel().getSelectedItem())) {
            users.setActive(0);
        }
        users.setDateCreated(new Date());
        users.setModifiedDate(new Date());

        int result = new InsertUpdateBL().insertData(users);
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

    public void getChangePasswordTemplate() throws IOException {
    }

}
