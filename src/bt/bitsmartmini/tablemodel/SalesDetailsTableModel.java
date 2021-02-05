
package bt.bitsmartmini.tablemodel;

import java.io.Serializable;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author JScare
 */
public class SalesDetailsTableModel implements Serializable {

    private SimpleIntegerProperty salesDetailscode;
    private SimpleStringProperty itemsname;
    private SimpleStringProperty itemscode;
    private SimpleIntegerProperty quantity;
    private SimpleStringProperty itemscost;
    private SimpleIntegerProperty itemsrtd;
    private SimpleStringProperty itemsprice;
    private SimpleStringProperty discount;
    private SimpleStringProperty refunds;
    private SimpleStringProperty actuals;
    private SimpleStringProperty date;

    public SalesDetailsTableModel() {
    }

    public SalesDetailsTableModel(String itemcode, String itemsname, Integer quantity, String itemsprice, Integer rtd, String discount, String refunds, String actuals, String date) {
        //this.salesDetailscode = new SimpleIntegerProperty(salesDetailscode);
        this.itemscode = new SimpleStringProperty(itemcode);
        this.itemsname = new SimpleStringProperty(itemsname);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.actuals = new SimpleStringProperty(actuals);
        this.refunds = new SimpleStringProperty(refunds);
        this.itemsprice = new SimpleStringProperty(itemsprice);
        this.itemsrtd = new SimpleIntegerProperty(rtd);
        this.discount = new SimpleStringProperty(discount);
        this.date = new SimpleStringProperty(date);
    }

    public Integer getSalesDetailscode() {
        return salesDetailscode.get();
    }

    public SimpleIntegerProperty getSalesDetailscodeProperty() {
        return salesDetailscode;
    }

    public void setSalesDetailscodeProperty(Integer salesDetailscode) {
        this.salesDetailscode = new SimpleIntegerProperty(salesDetailscode);
    }
    
    public String getItemsCode() {
        return itemscode.get();
    }

    public SimpleStringProperty getItemsCodeProperty() {
        return itemscode;
    }

    public void setItemsCodeProperty(String c) {
        this.itemscode = new SimpleStringProperty(c);
    }

    public String getItemsname() {
        return itemsname.get();
    }

    public SimpleStringProperty getItemsnameProperty() {
        return itemsname;
    }

    public void setItemsnameProperty(String salesId) {
        this.itemsname = new SimpleStringProperty(salesId);
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

    public String getItemscost() {
        return itemscost.get();
    }

    public SimpleStringProperty getItemscostProperty() {
        return itemscost;
    }

    public void setItemscostProperty(String itemscost) {
        this.itemscost = new SimpleStringProperty(itemscost);
    }

    public String getItemsPrice() {
        return itemsprice.get();
    }

    public SimpleStringProperty getItemsPriceProperty() {
        return itemsprice;
    }

    public void setItemsPriceProperty(String itemsprice) {
        this.itemsprice = new SimpleStringProperty(itemsprice);
    }
    
    public Integer getItemsRtd() {
        return itemsrtd.get();
    }

    public SimpleIntegerProperty getItemsRtdProperty() {
        return itemsrtd;
    }

    public void setItemsRtdProperty(Integer itemsrtd) {
        this.itemsrtd = new SimpleIntegerProperty(itemsrtd);
    }

    public String getDiscount() {
        return discount.get();
    }

    public SimpleStringProperty getDiscountProperty() {
        return discount;
    }

    public void setDoubleProperty(String discountsalesdetailstb) {
        this.discount = new SimpleStringProperty(discountsalesdetailstb);
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
    public String getRefunds() {
        return refunds.get();
    }

    public SimpleStringProperty getRefundsProperty() {
        return refunds;
    }

    public void setRefundsProperty(String refunds) {
        this.refunds = new SimpleStringProperty(refunds);
    }
    
    public String getActuals() {
        return actuals.get();
    }

    public SimpleStringProperty getActualsProperty() {
        return actuals;
    }

    public void setActualsProperty(String actuals) {
        this.actuals = new SimpleStringProperty(actuals);
    }

}
