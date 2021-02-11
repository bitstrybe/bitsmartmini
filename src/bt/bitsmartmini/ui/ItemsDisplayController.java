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
    public ImageView outofstockshape;
    public Label outofstocklabel;
    public Label curr;
    @FXML
    public Button adminstockin;
    //public Button returns;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        catalogstaus.getChildren().remove(adminstockin);
        catalogstaus.getChildren().remove(adminstockout);
        catalogstaus.getChildren().remove(addtocart);
        //itemsdesc.wrappingWidthProperty().bind(tabPane.widthProperty());

//        if ("Administrator".equals(LoginController.u.getRoles())) {
//            catalogstaus.getChildren().add(adminstockin);
//            //catalogstaus.getChildren().add(adminstockout);
//            catalogstaus.getChildren().remove(addtocart);
//        } else {
//            catalogstaus.getChildren().add(addtocart);
//            //addtocart.setVisible(true);
////            adminstockin.setVisible(false);
//            catalogstaus.getChildren().remove(adminstockin);
//            catalogstaus.getChildren().remove(adminstockout);
//            //returns.setVisible(true);
//        }
    }

}
