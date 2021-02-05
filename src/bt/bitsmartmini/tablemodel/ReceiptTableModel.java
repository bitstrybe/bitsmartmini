
package bt.bitsmartmini.tablemodel;

import java.io.Serializable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author JScare
 */
public class ReceiptTableModel implements Serializable {

    private SimpleStringProperty receiptId;
    private SimpleStringProperty salesId;
    private SimpleStringProperty amountPaid;
    private SimpleStringProperty date;
    private SimpleStringProperty pmode;

    public ReceiptTableModel() {
    }

    
    public ReceiptTableModel(String receiptId,String salesid, String mode, String amountPaid, String date) {
        this.receiptId = new SimpleStringProperty(receiptId);
        this.salesId = new SimpleStringProperty(salesid);
        this.amountPaid = new SimpleStringProperty(amountPaid);
        this.date = new SimpleStringProperty(date);
        this.pmode = new SimpleStringProperty(mode);
    }

    public String getReceiptId() {
        return receiptId.get();
    }
    public SimpleStringProperty getReceiptIdProperty() {
        return receiptId;
    }

    public void setReceiptIdProperty(String receiptId) {
        this.receiptId = new SimpleStringProperty(receiptId);
    }
    
     public String getSalesId() {
        return salesId.get();
    }
    public SimpleStringProperty getSalesIdProperty() {
        return salesId;
    }

    public void setSalesIdProperty(String salesId) {
        this.salesId = new SimpleStringProperty(salesId);
    }

    public String getAmountPaid() {
        return amountPaid.get();
    }
    public SimpleStringProperty getAmountPaidProperty() {
        return amountPaid;
    }

    public void setAmountPaidProperty(String amountPaid) {
        this.amountPaid =new SimpleStringProperty(amountPaid);
    }

    public String getDate() {
        return date.get();
    }
    public SimpleStringProperty getDateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date = new SimpleStringProperty(date);
    }
    
        public String getPmode() {
        return pmode.get();
    }

    public SimpleStringProperty getPmodeProperty() {
        return pmode;
    }

    public void setPmodeProperty(String mode) {
        this.pmode = new SimpleStringProperty(mode);
    }


}
