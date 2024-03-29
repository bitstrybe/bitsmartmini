package bt.bitsmartmini.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class ItemsDisplayController implements Initializable {

    @FXML
    public ImageView itemsimage;
    @FXML
    public Text exp;
    @FXML
    public Button addtocart;
    @FXML
    public Button adminstockin;
    @FXML
    public Button adminstockout;
    @FXML
    public HBox catalogstaus;
    @FXML
    public HBox outofstockbackground;
    @FXML
    public Text brand;
    @FXML
    public Text qty;
    @FXML
    public Text itembcode;
    @FXML
    public Text itemsdesc;
    @FXML
    public Label outofstocklabel;
    @FXML
    public Label curr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        catalogstaus.getChildren().remove(adminstockin);
        catalogstaus.getChildren().remove(adminstockout);
        catalogstaus.getChildren().remove(addtocart);
    }

}
