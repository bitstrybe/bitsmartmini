
package bt.bitsmartmini.tablemodel;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.ImageView;

/**
 *
 * @author JScare
 */
public class ItemTableModel {

    //private SimpleIntegerProperty itemCode;
    private SimpleStringProperty barcode;
    private SimpleStringProperty itemName;
    private SimpleStringProperty category;
    private SimpleStringProperty brand;
    private SimpleStringProperty uom;
    //private SimpleIntegerProperty uomitem;
    private SimpleStringProperty vom_val;
    private SimpleStringProperty dose_val;
    private SimpleLongProperty rol;
    private SimpleDoubleProperty costprice;
    private SimpleDoubleProperty saleprice;
    
    private ImageView image;

    public ItemTableModel() {
    }

    public ItemTableModel(String barcode, String itemName, String category, String brand, long rol,Double costprice, Double saleprice, ImageView img) {
        this.barcode = new SimpleStringProperty(barcode);
        this.itemName = new SimpleStringProperty(itemName);
        this.category = new SimpleStringProperty(category);
        this.brand = new SimpleStringProperty(brand);
        this.rol = new SimpleLongProperty(rol);
        this.costprice = new SimpleDoubleProperty(costprice);
        this.saleprice = new SimpleDoubleProperty(saleprice);
        this.image = img;

    }

    public String getBarcode() {
        return barcode.get();
    }

    public SimpleStringProperty getBarcodeProperty() {
        return barcode;
    }

    public void setBarcodeProperty(String barcode) {
        this.barcode = new SimpleStringProperty(barcode);
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

    public String getCategory() {
        return category.get();
    }

    public SimpleStringProperty getCategoryProperty() {
        return category;
    }

    public void setCategoryProperty(String category) {
        this.category = new SimpleStringProperty(category);
    }

    public String getBrand() {
        return brand.get();
    }

    public SimpleStringProperty getBrandProperty() {
        return brand;
    }

    public void setBrandProperty(String brand) {
        this.brand = new SimpleStringProperty(brand);
    }

    public String getUmo() {
        return uom.get();
    }

    public SimpleStringProperty getUomProperty() {
        return uom;
    }

    public void setUomProperty(String uom) {
        this.uom = new SimpleStringProperty(uom);
    }
   
    public String getVmo() {
        return vom_val.get();
    }

    public SimpleStringProperty getVomProperty() {
        return vom_val;
    }

    public void setVomProperty(String vom) {
        this.vom_val = new SimpleStringProperty(vom);
    }
    
    public String getDose() {
        return dose_val.get();
    }

    public SimpleStringProperty getDoseProperty() {
        return dose_val;
    }

    public void setDoseProperty(String dose) {
        this.dose_val = new SimpleStringProperty(dose);
    }

    public Long getRol() {
        return rol.get();
    }

    public SimpleLongProperty getRolProperty() {
        return rol;
    }

    public void setRolProperty(Long uom) {
        this.rol = new SimpleLongProperty(uom);
    }
    
     public Double getCostPrice() {
        return costprice.get();
    }

    public SimpleDoubleProperty getCostPriceProperty() {
        return costprice;
    }

    public void setCostPriceProperty(Double costprice) {
        this.costprice = new SimpleDoubleProperty(costprice);
    }
    
     public Double getSalePrice() {
        return saleprice.get();
    }

    public SimpleDoubleProperty getSalePriceProperty() {
        return saleprice;
    }

    public void setSalePriceProperty(Double saleprice) {
        this.saleprice = new SimpleDoubleProperty(saleprice);
    }

    public void setImage(ImageView value) {
        image = value;
    }

    public ImageView getImage() {
        return image;
    }
}
