package bt.bitsmartmini.tablemodel;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;

/**
 *
 * @author JScare
 */
public class SelectItemSaleTableModel {

    private SimpleStringProperty itemName;
    private SimpleIntegerProperty quantity;
    private SimpleDoubleProperty cost;
    private SimpleDoubleProperty price;
    private SimpleDoubleProperty total;
    private SimpleDoubleProperty discountval;
    private SimpleStringProperty uom;
    private ImageView image;

    public SelectItemSaleTableModel() {
    }

    public SelectItemSaleTableModel(String itemName, int quantity, double cost, double price, double total, double discountval, ImageView img) {
        this.itemName = new SimpleStringProperty(itemName);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.cost = new SimpleDoubleProperty(cost);
        this.price = new SimpleDoubleProperty(price);
        this.total = new SimpleDoubleProperty(total);
        this.discountval = new SimpleDoubleProperty(discountval);
        //this.uom = new SimpleStringProperty(uom);
        this.image = img;

    }
    
    public SelectItemSaleTableModel(String itemName, int quantity, double cost, double price, double total, double discountval) {
        this.itemName = new SimpleStringProperty(itemName);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.cost = new SimpleDoubleProperty(cost);
        this.price = new SimpleDoubleProperty(price);
        this.total = new SimpleDoubleProperty(total);
        this.discountval = new SimpleDoubleProperty(discountval);
        //this.uom = new SimpleStringProperty(uom);

    }

    public String getItemName() {
        return itemName.get();
    }

    public SimpleStringProperty getItemNameProperty() {
        return itemName;
    }

    public void setItemNameProperty(String itemName) {
        this.itemName = new SimpleStringProperty(itemName);
    }

    public Integer getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty getQuantityProperty() {
        return quantity;
    }

    public void setQuantityProperty(int quantity) {
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    public double getCost() {
        return cost.get();
    }

    public SimpleDoubleProperty getCostProperty() {
        return cost;
    }

    public void setCostProperty(double cost) {
        this.cost = new SimpleDoubleProperty(cost);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty getPriceProperty() {
        return price;
    }

    public void setPriceProperty(double price) {
        this.price = new SimpleDoubleProperty(price);
    }

    public double getTotal() {
        return total.get();
    }

    public SimpleDoubleProperty getTotalProperty() {
        return total;
    }

    public void setTotalProperty(double total) {
        this.total = new SimpleDoubleProperty(total);
    }

    public Double getDiscountValue() {
        return discountval.get();
    }

    public SimpleDoubleProperty getDiscountValueProperty() {
        return discountval;
    }

    public void setDiscountValueProperty(Double discountval) {
        this.discountval = new SimpleDoubleProperty(discountval);
    }

    public String getUom() {
        return uom.get();
    }

    public SimpleStringProperty getUomProperty() {
        return uom;
    }

    public void setUomProperty(String uom) {
        this.uom = new SimpleStringProperty(uom);
    }

    public void setImage(ImageView value) {
        image = value;
    }

    public ImageView getImage() {
        return image;
    }
}
