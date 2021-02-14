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
import lxe.utility.encryptor.UrlEncryptor;
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
    public JFXButton save;
    public Label displayinfo;
    public FontAwesomeIcon check;
    public FontAwesomeIcon duplicatelock;
    public JFXSpinner spinner;
    @FXML
    private Label currencylabel;
    @FXML
    private Hyperlink hyperlink;
    Image icon = new Image(getClass().getResourceAsStream("/resources/meds_logo.png"));
    String Key;

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
        displayinfo.setText("SUCCESSFULLY SAVED");
        spinner.setVisible(false);
        check.setVisible(true);
//        TableData();
        PauseTransition delay = new PauseTransition(Duration.seconds(3));
        delay.setOnFinished(closevnt -> {
            displayinfo.setText("");
            spinner.setVisible(false);
            check.setVisible(false);
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
            }
            displayinfo.textProperty().unbind();
            //Collection bc = new ArrayList();            
        } catch (Exception ex) {
            Logger.getLogger(BusinessController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void countrylist() {
        countrychoicebox.getItems().addAll("Ghana", "Nigeria", "USA", "Canada");
        countrychoicebox.getSelectionModel().select("Ghana");
    }

    @FXML
    private void saveAction(ActionEvent event) {

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                //childController.spinner.setVisible(true);
                updateMessage(MainAppController.PROCESS_MESSAGE);
                Thread.sleep(500);
                return null;
            }
        };

        displayinfo.textProperty().bind(task.messageProperty());
        task.setOnSucceeded(s -> {
            displayinfo.textProperty().unbind();
            Business bus = new Business();
            bus.setBName(bnametextfield.getText());
            bus.setBAddr(baddtextfield.getText());
            bus.setBEmail(bemailtextfield.getText());
            bus.setBCountry(countrychoicebox.getSelectionModel().getSelectedItem());
            bus.setBCurrency(currencylabel.getText());
            bus.setBMobile(bmobtextfield.getText());
            bus.setBLogo(null);
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
            switch (result) {
                case 1:
                    closeTransition();
                    getMainApp();
                    break;
                default:
                    displayinfo.setText("NOTICE! AN ERROR OCCURED");
                    spinner.setVisible(false);
                    check.setVisible(false);
                    break;
            }
            if (result == 1) {
                closeform();
            }
        });
        Thread d = new Thread(task);
        d.setDaemon(true);
        d.start();
    }

    public void getMainApp() {
        try {
            //Stage st = (Stage) login.getScene().getWindow();
            Parent root;
            root = FXMLLoader.load(getClass().getResource("MainApp.fxml"));
            Stage stage = new Stage();
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.resizableProperty().setValue(false);
            stage.getIcons().add(icon);
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
}
