package bt.bitsmartmini.ui;

import bt.bitsmartmini.bl.BusinessBL;
import bt.bitsmartmini.bl.InsertUpdateBL;
import bt.bitsmartmini.entity.Business;
import bt.bitsmartmini.utils.Utilities;
import com.jfoenix.controls.JFXSpinner;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;
import org.apache.commons.io.FilenameUtils;

/**
 * FXML Controller class
 *
 * @author scarface
 */
public class AddBusinessLogoController implements Initializable {

    @FXML
    private Button closebtn;
    @FXML
    private ImageView logoviewer;
    @FXML
    private Button browse;
    @FXML
    private FontAwesomeIcon check;
    @FXML
    private FontAwesomeIcon duplicatelock;
    @FXML
    private JFXSpinner spinner;
    @FXML
    private Label displayinfo;

    File ifile;
    BufferedImage resizeImage;
    byte[] item_image = null;
    InputStream initialStream;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ifile = new File("./img/DEFAULT.png");
        browse.setOnAction(new EventHandler<ActionEvent>() {
            Stage st = new Stage();
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser = new FileChooser();
                //Set extension filter
                FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
                FileChooser.ExtensionFilter extFilterJPEG = new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.JPEG");
                FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
                fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG, extFilterJPEG);
                //Show open file dialog
                ifile = fileChooser.showOpenDialog(null);
                //File ofile = new File
                try {
                    BufferedImage bufferedImage = ImageIO.read(ifile);
                    int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
                    resizeImage = Utilities.resizeImage(bufferedImage, type, 150, 150);
                    Image image = SwingFXUtils.toFXImage(resizeImage, null);
                    logoviewer.setImage(image);
                    logoviewer.setPreserveRatio(true);
                    logoviewer.scaleXProperty();
                    logoviewer.scaleYProperty();
                    logoviewer.setSmooth(true);
                    logoviewer.setCache(true);
                } catch (IOException | IllegalArgumentException ex) {
                }
            }
        });
    }

    @FXML
    private void closefrom(ActionEvent event) {
        Stage stage = (Stage) closebtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void browseAction(ActionEvent event) {
    }

    private void saveTrans() {
        displayinfo.setText(MainAppController.SUCCESS_MESSAGE);
        //clearAllForms();
        spinner.setVisible(false);
        check.setVisible(true);
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
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(closevnt -> {
            displayinfo.setText("");
            spinner.setVisible(false);
            check.setVisible(false);
        });
        delay.play();
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

    private int saveTemplate() throws FileNotFoundException, IOException {
        try {
            displayinfo.textProperty().unbind();
            Business b = new BusinessBL().findBusiness(MainAppController.B.getBName());
            //adding image file to directory
            initialStream = new FileInputStream(ifile);
            if (!ifile.getName().equals("DEFAULT.png")) {
               // String FILEPATH = File.separator;
                b.setBLogo("."+File.separator+"img"+File.separator+"LOGO." + FilenameUtils.getExtension(ifile.getName()));
                MainAppController.B.setBLogo(b.getBLogo());
                ImageIO.write(resizeImage, FilenameUtils.getExtension(ifile.getName()), new File("."+File.separator+"img"+File.separator+"LOGO." + FilenameUtils.getExtension(ifile.getName())));
            } else {
                b.setBLogo("."+File.separator+"img"+File.separator+"LOGO.png");
                MainAppController.B.setBLogo(b.getBLogo());
                ImageIO.write(resizeImage, FilenameUtils.getExtension(ifile.getName()), new File("."+File.separator+"img"+File.separator+"LOGO." + FilenameUtils.getExtension(ifile.getName())));
            }
            int result = new InsertUpdateBL().updateData(b);
            return result;
        } catch (IOException | IllegalArgumentException ex) {
            Logger.getLogger(AddItemsController.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

}
