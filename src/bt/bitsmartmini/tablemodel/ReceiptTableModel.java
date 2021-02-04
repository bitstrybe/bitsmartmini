
package bt.bitsmartmini.tablemodel;

import java.io.Serializable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author JScare
 */
public class ReceiptTableModel implements Serializable {

    private SimpleIntegerProperty receiptId;
    private SimpleIntegerProperty salesId;
    private SimpleStringProperty amountPaid;
    private SimpleStringProperty date;
    private SimpleStringProperty pmode;

    public ReceiptTableModel() {
    }

    
    public ReceiptTableModel(Integer receiptId,Integer salesid, String mode, String amountPaid, String date) {
        this.receiptId = new SimpleIntegerProperty(receiptId);
        this.salesId = new SimpleIntegerProperty(salesid);
        this.amountPaid = new SimpleStringProperty(amountPaid);
        this.date = new SimpleStringProperty(date);
        this.pmode = new SimpleStringProperty(mode);
    }

    public Integer getReceiptId() {
        return receiptId.get();
    }
    public SimpleIntegerProperty getReceiptIdProperty() {
        return receiptId;
    }

    public void setReceiptIdProperty(Integer receiptId) {
        this.receiptId = new SimpleIntegerProperty(receiptId);
    }
    
     public Integer getSalesId() {
        return salesId.get();
    }
    public SimpleIntegerProperty getSalesIdProperty() {
        return salesId;
    }

    public void setSalesIdProperty(Integer salesId) {
        this.salesId = new SimpleIntegerProperty(salesId);
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
