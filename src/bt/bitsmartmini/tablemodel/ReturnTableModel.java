
package bt.bitsmartmini.tablemodel;

import java.io.Serializable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author JScare
 */
public class ReturnTableModel implements Serializable {

    private SimpleIntegerProperty returnId;
    private SimpleStringProperty item;
    private SimpleIntegerProperty quantity;
    private SimpleStringProperty remarks;
    private SimpleStringProperty date;
    private SimpleStringProperty amount;
    private SimpleStringProperty unitprice;
   

    public ReturnTableModel() {
    }

    public ReturnTableModel( int returnId,String item, int quantity, String unitprice, String amount, String remarks, String date) {
        this.returnId = new SimpleIntegerProperty(returnId);
        this.item = new SimpleStringProperty(item);
        this.amount = new SimpleStringProperty(amount);
        this.unitprice = new SimpleStringProperty(unitprice);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.remarks = new SimpleStringProperty(remarks);
        this.date = new SimpleStringProperty(date);
    }

    public Integer getReturnsId() {
        return returnId.get();
    }
    public SimpleIntegerProperty getReturnsIdProperty() {
        return returnId;
    }

    public void setReturnsIdProperty(Integer stockoutId) {
        this.returnId = new SimpleIntegerProperty(stockoutId);
    }

    
      public String getItem() {
        return item.get();
    }
     public SimpleStringProperty getItemProperty() {
        return item;
    }

    public void setItemProperty(String item) {
        this.item = new SimpleStringProperty(item);
    }

    public int getQuantity() {
        return quantity.get();
    }
    public SimpleIntegerProperty getQuantityProperty() {
        return quantity;
    }

    public void setQuantityProperty(int quantity) {
        this.quantity = new SimpleIntegerProperty(quantity);
    }
    
    public String getUnitPrice() {
        return unitprice.get();
    }
    public SimpleStringProperty getUnitPriceProperty() {
        return unitprice;
    }

    public void setUnitPriceProperty(String a) {
        this.unitprice = new SimpleStringProperty(a);
    }
    
    public String getAmount() {
        return amount.get();
    }
    public SimpleStringProperty getAmountProperty() {
        return amount;
    }

    public void setAmountProperty(String a) {
        this.amount = new SimpleStringProperty(a);
    }

    public String getRemarks() {
        return remarks.get();
    }
    public SimpleStringProperty getRemarksProperty() {
        return remarks;
    }

    public void setRemarksProperty(String remarks) {
        this.remarks = new SimpleStringProperty(remarks);
    }
     public String getDate() {
        return date.get();
    }
    public SimpleStringProperty getDateProperty() {
        return date;
    }

    public void setDateProperty(String date) {
        this.date = new SimpleStringProperty(date);
    }
   
   
}
