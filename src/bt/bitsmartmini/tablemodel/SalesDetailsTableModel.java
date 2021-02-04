
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
    private SimpleIntegerProperty quantity;
    private SimpleStringProperty itemscost;
    private SimpleIntegerProperty itemsrtd;
    private SimpleStringProperty itemsprice;
    private SimpleDoubleProperty discount;
    private SimpleStringProperty date;

    public SalesDetailsTableModel() {
    }

    public SalesDetailsTableModel(Integer salesDetailscode, String itemsname, Integer quantity, String itemscost, String itemsprice, Integer rtd, double discount, String date) {
        this.salesDetailscode = new SimpleIntegerProperty(salesDetailscode);
        this.itemsname = new SimpleStringProperty(itemsname);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.itemscost = new SimpleStringProperty(itemscost);
        this.itemsprice = new SimpleStringProperty(itemsprice);
        this.itemsrtd = new SimpleIntegerProperty(rtd);
        this.discount = new SimpleDoubleProperty(discount);
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

    public double getDiscount() {
        return discount.get();
    }

    public SimpleDoubleProperty getDiscountProperty() {
        return discount;
    }

    public void setDoubleProperty(double discountsalesdetailstb) {
        this.discount = new SimpleDoubleProperty(discountsalesdetailstb);
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
