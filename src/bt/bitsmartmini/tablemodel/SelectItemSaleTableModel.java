package bt.bitsmartmini.tablemodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;

/**
 *
 * @author JScare
 */
public class SelectItemSaleTableModel {

    private SimpleStringProperty itemCode;
    private SimpleStringProperty itemName;
    private SimpleStringProperty quantity;
    private SimpleStringProperty cost;
    private SimpleStringProperty price;
    private SimpleStringProperty total;
    private SimpleStringProperty discountval;
    private SimpleStringProperty uom;
    private ImageView image;

    public SelectItemSaleTableModel() {
    }

    public SelectItemSaleTableModel(String itemCode, String itemName, String quantity, String cost, String price, String total, String discountval, ImageView img) {
        this.itemCode = new SimpleStringProperty(itemCode);
        this.itemName = new SimpleStringProperty(itemName);
        this.quantity = new SimpleStringProperty(quantity);
        this.cost = new SimpleStringProperty(cost);
        this.price = new SimpleStringProperty(price);
        this.total = new SimpleStringProperty(total);
        this.discountval = new SimpleStringProperty(discountval);
        //this.uom = new SimpleStringProperty(uom);
        this.image = img;

    }
    
    public SelectItemSaleTableModel(String itemCode, String itemName, String quantity, String cost, String price, String total, String discountval) {
        this.itemCode = new SimpleStringProperty(itemCode);
        this.itemName = new SimpleStringProperty(itemName);
        this.quantity = new SimpleStringProperty(quantity);
        this.cost = new SimpleStringProperty(cost);
        this.price = new SimpleStringProperty(price);
        this.total = new SimpleStringProperty(total);
        this.discountval = new SimpleStringProperty(discountval);
        //this.uom = new SimpleStringProperty(uom);

    }
    
    public String getItemCode() {
        return itemCode.get();
    }

    public SimpleStringProperty getItemCodeProperty() {
        return itemCode;
    }

    public void setItemCodeProperty(String itemCode) {
        this.itemCode = new SimpleStringProperty(itemCode);
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

    public String getQuantity() {
        return quantity.get();
    }

    public SimpleStringProperty getQuantityProperty() {
        return quantity;
    }

    public void setQuantityProperty(String quantity) {
        this.quantity = new SimpleStringProperty(quantity);
    }

    public String getCost() {
        return cost.get();
    }

    public SimpleStringProperty getCostProperty() {
        return cost;
    }

    public void setCostProperty(String cost) {
        this.cost = new SimpleStringProperty(cost);
    }

    public String getPrice() {
        return price.get();
    }

    public SimpleStringProperty getPriceProperty() {
        return price;
    }

    public void setPriceProperty(String price) {
        this.price = new SimpleStringProperty(price);
    }

    public String getTotal() {
        return total.get();
    }

    public SimpleStringProperty getTotalProperty() {
        return total;
    }

    public void setTotalProperty(String total) {
        this.total = new SimpleStringProperty(total);
    }

    public String getDiscountValue() {
        return discountval.get();
    }

    public SimpleStringProperty getDiscountValueProperty() {
        return discountval;
    }

    public void setDiscountValueProperty(String discountval) {
        this.discountval = new SimpleStringProperty(discountval);
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
