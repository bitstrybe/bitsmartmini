package bt.bitsmartmini.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import bt.bitsmartmini.bl.CategoryBL;
import bt.bitsmartmini.bl.InsertUpdateBL;
import bt.bitsmartmini.bl.ItemsBL;
import bt.bitsmartmini.bl.BrandBL;
import bt.bitsmartmini.bl.StockinBL;
import bt.bitsmartmini.bl.UomBL;
import bt.bitsmartmini.entity.Brands;
import bt.bitsmartmini.entity.Category;
import bt.bitsmartmini.entity.Items;
import bt.bitsmartmini.entity.Users;
import bt.bitsmartmini.tablemodel.ItemTableModel;
import bt.bitsmartmini.utils.FilterComboBox;
import bt.bitsmartmini.utils.Utilities;
import java.io.FileNotFoundException;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.text.WordUtils;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class AddItemsController implements Initializable {

    final FileChooser fileChooser = new FileChooser();

    ObservableList<ItemTableModel> data;

    @FXML
    private ComboBox<String> categorycombo;
    @FXML
    private ComboBox<String> brandscombo;
    @FXML
    private JFXTextField searchbtn;
    @FXML
    private TableView<ItemTableModel> itemtableview;
    @FXML
    private TableColumn<ItemTableModel, ImageView> itemimage;
    @FXML
    private TableColumn<ItemTableModel, String> itemname;
    @FXML
    private TableColumn<ItemTableModel, String> category;
    @FXML
    private TableColumn<ItemTableModel, Number> rol;
    @FXML
    private TableColumn<ItemTableModel, Number> costpricetb;
    @FXML
    private TableColumn<ItemTableModel, Number> salespricetb;
    @FXML
    private TableColumn<ItemTableModel, Boolean> action;
    private JFXTextField costtextfield;
    private JFXTextField selltextfield;
    @FXML
    private Button closebtn;
    @FXML
    private Button browse;
    @FXML
    private ImageView itemimages;

    byte[] item_image = null;

    InputStream initialStream;
    ItemsBL itembl = new ItemsBL();
    UomBL uombl = new UomBL();
    File ifile;
    BufferedImage resizeImage;
    private JFXTextField itmtextfield;
    @FXML
    private Label displayinfo;
    @FXML
    private JFXSpinner spinner;
    @FXML
    private FontAwesomeIcon check;
    @FXML
    private FontAwesomeIcon duplicatelock;
    @FXML
    private TableColumn<ItemTableModel, String> brand;
    @FXML
    private TableColumn<ItemTableModel, String> barcode;
    @FXML
    public JFXTextField barcodetxt;
    @FXML
    private JFXTextField cptxt;
    @FXML
    private JFXTextField sptxt;
    @FXML
    private JFXTextField roltxt;
    @FXML
    private JFXTextField itemdesctxt;

    public void getBrands() {
        brandscombo.getItems().clear();
        List<Brands> list = new BrandBL().getAllBrands();
        ObservableList<Brands> result = FXCollections.observableArrayList(list);
        result.forEach((man) -> {
            brandscombo.getItems().add(WordUtils.capitalizeFully(man.getBrandName()));
        });
    }

    public void getCategory() {
        categorycombo.getItems().clear();
        List<Category> list = new CategoryBL().getAllCategory();
        ObservableList<Category> result = FXCollections.observableArrayList(list);
        result.forEach(e -> {
            categorycombo.getItems().add(WordUtils.capitalizeFully(e.getCategoryName()));
        });
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Utilities.repeatFocus(barcodetxt);
        categorycombo.setOnShown(e -> {
            getCategory();
        });
        brandscombo.setOnShown(v -> {
            getBrands();
        });
        ifile = new File("./img/DEFAULT.png");
        TableData("");
        searchbtn.textProperty().addListener((e, oldValue, newValue) -> {
            TableData(searchbtn.getText());
        });
        brandscombo.setOnKeyReleased((KeyEvent event) -> {
            String s = FilterComboBox.jumpTo(event.getText(), brandscombo.getValue(), brandscombo.getItems());
            if (s != null) {
                brandscombo.setValue(s);
            }
        });

//        categorycombo.setOnKeyReleased((KeyEvent event) -> {
//            String s = FilterComboBox.jumpTo(event.getText(), categorycombo.getValue(), categorycombo.getItems());
//            if (s != null) {
//                categorycombo.setValue(s);
//            }
//        });
        browse.setOnAction(new EventHandler<ActionEvent>() {
            Stage st = new Stage();

            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                //Set extension filter
                FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
                FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
                fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
                //Show open file dialog
                ifile = fileChooser.showOpenDialog(null);
                //File ofile = new File
                try {
                    BufferedImage bufferedImage = ImageIO.read(ifile);
                    int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
                    resizeImage = Utilities.resizeImage(bufferedImage, type, 450, 340);

                    Image image = SwingFXUtils.toFXImage(resizeImage, null);
                    itemimages.setImage(image);
                    itemimages.setPreserveRatio(true);
                    itemimages.scaleXProperty();
                    itemimages.scaleYProperty();
                    itemimages.setSmooth(true);
                    itemimages.setCache(true);
                } catch (IOException | IllegalArgumentException ex) {
                }
            }
        });
    }

    @FXML
    public void saveAction() {
        Task<Integer> task = new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                spinner.setVisible(true);
                check.setVisible(false);
                updateMessage("PROCESSING PLS WAIT.....");
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
        List<Items> c;
        if (p.length() > 0) {
            c = itembl.searchAllItems(p);
        } else {
            c = itembl.getItemsPerPage(10);
        }
        data = FXCollections.observableArrayList();
        c.forEach((item) -> {
            try {
                ImageView imageitems = new ImageView();
                File file = new File(item.getItemImg());
                Image image = new Image(file.toURI().toString());
                imageitems.setImage(image);
                imageitems.setFitWidth(70);
                imageitems.setFitHeight(70);
                imageitems.setPreserveRatio(true);
                imageitems.scaleXProperty();
                imageitems.scaleYProperty();
                imageitems.setSmooth(true);
                imageitems.setCache(true);
                data.add(new ItemTableModel(item.getUpc(), item.getItemDesc(), item.getCategory().getCategoryName(), item.getBrand().getBrandName(), item.getRol(), item.getCp(), item.getSp(), imageitems));
            } catch (Exception ex) {
                Logger.getLogger(AddItemsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        barcode.setCellValueFactory(cell -> cell.getValue().getBarcodeProperty());
        itemname.setCellValueFactory(cell -> cell.getValue().getItemNameProperty());
        category.setCellValueFactory(cell -> cell.getValue().getCategoryProperty());
        brand.setCellValueFactory(cell -> cell.getValue().getBrandProperty());
        rol.setCellValueFactory(cell -> cell.getValue().getRolProperty());
        costpricetb.setCellValueFactory(cell -> cell.getValue().getCostPriceProperty());
        salespricetb.setCellValueFactory(cell -> cell.getValue().getSalePriceProperty());
        itemimage.setCellValueFactory(new PropertyValueFactory<>("image"));
        itemimage.setPrefWidth(65);
        action.setSortable(false);

        action.setCellValueFactory((TableColumn.CellDataFeatures<ItemTableModel, Boolean> features) -> new SimpleBooleanProperty(features.getValue() != null));
        action.setCellFactory((TableColumn<ItemTableModel, Boolean> personBooleanTableColumn) -> new AddPersonCell());
        itemtableview.setItems(data);
        itemtableview.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

    }

    @FXML
    private void addCategoryAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddCategory.fxml"));
        Parent parent = (Parent) fxmlLoader.load();
        AddCategoryController childController = fxmlLoader.getController();
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setMaximized(true);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(parent.getScene().getWindow());
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    @FXML
    private void addBrandsAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddBrand.fxml"));
        Parent parent = (Parent) fxmlLoader.load();
        getBrands();
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        stage.setMaximized(true);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(parent.getScene().getWindow());
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
    }

    public class AddPersonCell extends TableCell<ItemTableModel, Boolean> {

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
                int selectdIndex = getTableRow().getIndex();
                //Create a new table show details of the selected item
                ItemTableModel selectedRecord = (ItemTableModel) itemtableview.getItems().get(selectdIndex);
                try {
                    Stage stage = new Stage();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Delete.fxml"));
                    Parent parent1 = (Parent) fxmlLoader.load();
                    DeleteController childController = fxmlLoader.getController();
                    childController.delete.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                        Task<Integer> task = new Task<Integer>() {
                            @Override
                            protected Integer call() throws Exception {
                                childController.spinner.setVisible(true);
                                updateMessage("PROCESSING PLS WAIT.....");
                                Thread.sleep(500);
                                List list = new StockinBL().getItemStockinByBarcode(selectedRecord.getBarcode());
                                if (list.isEmpty()) {
                                    return deleteTemplate(selectedRecord.getBarcode());
                                } else {
                                    return 0;
                                }
                            }
                        };
                        childController.displayinfo.textProperty().bind(task.messageProperty());
                        task.setOnSucceeded(f -> {
                            childController.displayinfo.textProperty().unbind();
                            if (task.getValue() == 1) {
                                childController.displayinfo.setText(MainAppController.DELETE_MESSAGE);
                                childController.spinner.setVisible(false);
                                childController.check.setVisible(true);
                                TableData("");
                                stage.close();
                            } else {
                                childController.displayinfo.setText(MainAppController.ERROR_MESSAGE);
                                childController.spinner.setVisible(false);
                                childController.check.setVisible(false);

                            }

                        });
                        Thread d = new Thread(task);
                        d.setDaemon(true);
                        d.start();

                    });
                    Scene scene1 = new Scene(parent1);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initOwner(parent1.getScene().getWindow());
                    stage.setScene(scene1);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.resizableProperty().setValue(false);
                    stage.showAndWait();
                } catch (IOException ex) {
                    Logger.getLogger(AddItemsController.class.getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    public void closefrom() {
        Stage stage = (Stage) closebtn.getScene().getWindow();
        stage.close();
    }

    private void clearAllForms() {
//        barcodetxt.clear();
//        itemdesctxt.clear();
//        closefrom();
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
        Items cat = new Items();
        cat.setUpc(barcodetxt.getText());
        cat.setItemDesc(itemdesctxt.getText());
        cat.setCategory(new Category(categorycombo.getValue()));
        cat.setBrand(new Brands(brandscombo.getValue()));
        cat.setRol(Integer.parseInt(roltxt.getText()));
        cat.setUsers(new Users(LoginController.u.getUserid()));
        cat.setEntryLog(new Date());
        cat.setLastModified(new Date());
        cat.setCp(Double.parseDouble(cptxt.getText()));
        cat.setSp(Double.parseDouble(sptxt.getText()));
        //adding image file to directory
        initialStream = new FileInputStream(ifile);
        if (!ifile.getName().equals("DEFAULT.png")) {
            cat.setItemImg("./img/" + barcodetxt.getText() + "." + FilenameUtils.getExtension(ifile.getName()));
        } else {
            cat.setItemImg("./img/DEFAULT.png");
        }
        int result = new InsertUpdateBL().insertData(cat);
        if (!ifile.getName().equals("DEFAULT.png")) {
            ImageIO.write(resizeImage, FilenameUtils.getExtension(ifile.getName()), new File("./img/" + barcodetxt.getText() + "." + FilenameUtils.getExtension(ifile.getName())));
        }

        return result;
    }

    public int deleteTemplate(String value) {
        int result = new ItemsBL().removeData(value);
        return result;
    }

}
