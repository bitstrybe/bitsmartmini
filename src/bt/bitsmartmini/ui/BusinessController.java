package bt.bitsmartmini.ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXSpinner;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import bt.bitsmartmini.bl.InsertUpdateBL;
import bt.bitsmartmini.entity.Business;
import bt.bitsmartmini.entity.Licensing;
import bt.bitsmartmini.utils.Utilities;
import java.awt.image.BufferedImage;
import java.io.File;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import lxe.utility.encryptor.UrlEncryptor;
import org.apache.commons.io.FilenameUtils;
import org.joda.time.DateTime;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class BusinessController implements Initializable {

    @FXML
    private Button closebtn;
    @FXML
    private JFXTextField bnametextfield;
    @FXML
    private JFXTextField baddtextfield;
    @FXML
    private JFXTextField bmobtextfield;
    @FXML
    private JFXTextField bemailtextfield;
    @FXML
    private ComboBox<String> countrychoicebox;
    @FXML
    private Label currencylabel;
    @FXML
    private Hyperlink hyperlink;
//    Image icon = new Image(getClass().getResourceAsStream("/resources/meds_logo.png"));
    String Key;
    @FXML
    private ImageView logoviewer;
    @FXML
    private Button browse;

    final FileChooser fileChooser = new FileChooser();
    File ifile;
    BufferedImage resizeImage;
    @FXML
    private Label displayinfo;
    @FXML
    private FontAwesomeIcon check;
    @FXML
    private FontAwesomeIcon duplicatelock;
    @FXML
    private JFXSpinner spinner;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        HashMap licensetype = new HashMap();
        licensetype.put("trial", 30);
        licensetype.put("annual", 1);
        countrylist();
        currencylabel.setText("GH¢");
        countrychoicebox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                String select = countrychoicebox.getSelectionModel().getSelectedItem();
                if (select.equals("Ghana")) {
                    currencylabel.setText("GH¢");
                } else if (select.equals("Nigeria")) {
                    currencylabel.setText("₦");
                }
            }
        });
        //checkLicenseKey(Key);

        //saveTemplate(k);
    }

    @FXML
    public void closeform() {
        Stage stage = (Stage) closebtn.getScene().getWindow();
        stage.close();
    }

    private void clearAllForms() {
        bnametextfield.clear();
    }

    private void closeTransition() {
//        save.setDisable(true);
        clearAllForms();
//        displayinfo.setText("SUCCESSFULLY SAVED");
//        spinner.setVisible(false);
//        check.setVisible(true);
//        TableData();
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(closevnt -> {
//            displayinfo.setText("");
//            spinner.setVisible(false);
//            check.setVisible(false);
        });
        delay.play();
    }

    public void checkLicenseKey(String k) {
        try {
            if (!k.equals("trial")) { //if empty, create a 30 days trail key
                UrlEncryptor enc = new UrlEncryptor();
                //String key = LicensingUtil.cookDesktopKey(bnametextfield.getText(), bmobtextfield.getText(), k, sd.toDate(), ed.toDate() );
                //System.out.println("hfff: " + bnametextfield.getText().replace(' ', '-'));
                //String key = LicensingUtil.cookDesktopKey(bnametextfield.getText().replace(' ', '-'), sd.toDate(), ed.toDate());
                // Key = enc.doEncrypt(key);
                //System.out.println(l);
                Key = k;
                String etext = enc.doDecrypt(Key);
                //System.out.println("d: " + etext);
                String[] pt = etext.split(":");
                bnametextfield.setText(pt[0].replace('-', ' '));
                bnametextfield.setDisable(true);
                bemailtextfield.setText(pt[1]);
                bemailtextfield.setDisable(true);
            } else {

            }

        } catch (Exception ex) {
            Logger.getLogger(BusinessController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void countrylist() {
        countrychoicebox.getItems().addAll("Ghana", "Nigeria", "USA", "Canada");
        countrychoicebox.getSelectionModel().select("Ghana");
    }

    public void getMainApp() {
        try {
            //Stage st = (Stage) login.getScene().getWindow();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("MainApp.fxml"));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.resizableProperty().setValue(false);
//            stage.getIcons().add(icon);
            stage.setTitle("Pharmabits");
            stage.setMaximized(true);
            stage.setScene(new Scene(root));
            stage.show();
            //st.hide();
//
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void previousAction(ActionEvent event) throws IOException {
        closeform();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("VerifyLicense.fxml"));
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.resizableProperty().setValue(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void nextAction(ActionEvent event) {

        Task<Integer> task = new Task<Integer>() {
            @Override
            protected Integer call() throws Exception {
                spinner.setVisible(true);
                updateMessage(MainAppController.PROCESS_MESSAGE);
                Thread.sleep(500);
                return saveTemplate();
            }
        };

        displayinfo.textProperty().bind(task.messageProperty());
        task.setOnSucceeded(s -> {
            displayinfo.textProperty().unbind();
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

    @FXML
    private void browseAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        //Show open file dialog
        ifile = fileChooser.showOpenDialog(null);
        //File ofile = new File
        BufferedImage bufferedImage = ImageIO.read(ifile);
        int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
        resizeImage = Utilities.resizeImage(bufferedImage, type, 100, 100);

        Image image = SwingFXUtils.toFXImage(resizeImage, null);
        logoviewer.setImage(image);
        logoviewer.setPreserveRatio(true);
        logoviewer.scaleXProperty();
        logoviewer.scaleYProperty();
        logoviewer.setSmooth(true);
        logoviewer.setCache(true);
    }

    private void saveTrans() {
        displayinfo.setText(MainAppController.SUCCESS_MESSAGE);
        clearAllForms();
        spinner.setVisible(false);
        check.setVisible(true);
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(closevnt -> {
            displayinfo.setText("");
            spinner.setVisible(false);
            check.setVisible(false);
            closeform();
            getMainApp();
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

    public int saveTemplate() {

        Business bus = new Business();
        bus.setBName(bnametextfield.getText());
        bus.setBAddr(baddtextfield.getText());
        bus.setBEmail(bemailtextfield.getText());
        bus.setBCountry(countrychoicebox.getSelectionModel().getSelectedItem());
        bus.setBCurrency(currencylabel.getText());
        bus.setBMobile(bmobtextfield.getText());
        String dbpath = new File("src/bt/resources/").getPath();
        if (!ifile.getName().equals("logo-default.png")) {
            System.out.println("Image File: " + "/bt/resources/" + bnametextfield.getText() + "." + FilenameUtils.getExtension(ifile.getName()));
//            cat.setItemImg(dbpath + "\\img\\" + barcodetxt.getText() + "." + FilenameUtils.getExtension(ifile.getName()));
            bus.setBLogo("/bt/resources/" + bnametextfield.getText() + "." + FilenameUtils.getExtension(ifile.getName()));
        } else {

            bus.setBLogo("/bt/resources/logo-default.png");
        }
        //            int result = new InsertUpdateBL().insertData(cat);
        if (!ifile.getName().equals("DEFAULT.png")) {
            try {
                ImageIO.write(resizeImage, FilenameUtils.getExtension(ifile.getName()), new File(dbpath + "/" + bnametextfield.getText() + "." + FilenameUtils.getExtension(ifile.getName())));
            } catch (IOException ex) {
                Logger.getLogger(BusinessController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        Licensing l = new Licensing();
        DateTime sd = new DateTime(new Date());
        DateTime ed;
        ed = sd.plusDays(31);
        UrlEncryptor enc = new UrlEncryptor();
        //                String key = LicensingUtil.cookDesktopKey(bnametextfield.getText().replace(' ', '-'), bemailtextfield.getText(), sd.toDate(), ed.toDate());
//                Key = enc.doEncrypt(key);
//            System.out.println(enc.doEncrypt(Key));
        l.setLicenseKey(Key);
        l.setBusiness(bus);
        bus.setLicenseKey(l);
        int result = new InsertUpdateBL().insertUpdate(bus, l);
//        switch (result) {
//            case 1:
//                closeTransition();
//                getMainApp();
//                break;
//            default:
////                    displayinfo.setText("NOTICE! AN ERROR OCCURED");
////                    spinner.setVisible(false);
////                    check.setVisible(false);
//                break;
//        }
//        if (result == 1) {
//            closeform();
//        }
        return result;

    }
}
