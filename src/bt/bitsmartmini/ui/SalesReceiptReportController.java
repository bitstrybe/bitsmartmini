package bt.bitsmartmini.ui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import bt.bitsmartmini.utils.PrintReport;
import bt.bitsmartmini.utils.Utilities;
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author scarface
 */
public class SalesReceiptReportController implements Initializable {

    @FXML
    private Button closebtn;
    @FXML
    private DatePicker startdate;
    @FXML
    private DatePicker enddate;
    @FXML
    private Button discountbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void closeForm(ActionEvent event) {
        Stage stage = (Stage) closebtn.getScene().getWindow();
        stage.close();
    }
    

    @FXML
    private void salesReceiptReport(ActionEvent event) {
        try {
            System.out.println("Start date" + Utilities.convertToDateViaSqlDate(startdate.getValue()));
            System.out.println("Start date" + Utilities.convertToDateViaSqlDate(startdate.getValue()));
            new PrintReport().showSalesReceipteport(Utilities.convertToDateViaSqlDate(startdate.getValue()), Utilities.convertToDateViaSqlDate(enddate.getValue()));
        } catch (JRException ex) {
            Logger.getLogger(SalesReceiptReportController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SalesReceiptReportController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SalesReceiptReportController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SalesReceiptReportController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}
