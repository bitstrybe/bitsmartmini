
package bt.bitsmartmini.tablemodel;

import javafx.beans.property.SimpleStringProperty;
/**
 *
 * @author JScare
 */

public class BrandTableModel {

   
    private SimpleStringProperty brand;
   
    public BrandTableModel() {
    }

    public BrandTableModel(String manufacturer) {
        this.brand = new SimpleStringProperty(manufacturer);
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

    
    
}
