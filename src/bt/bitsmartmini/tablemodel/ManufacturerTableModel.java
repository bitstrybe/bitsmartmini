/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.bitsmartmini.tablemodel;

import javafx.beans.property.SimpleStringProperty;
/**
 *
 * @author JScare
 */

public class ManufacturerTableModel {

   
    private SimpleStringProperty manufacturer;
   
    public ManufacturerTableModel() {
    }

    public ManufacturerTableModel(String manufacturer) {
        this.manufacturer = new SimpleStringProperty(manufacturer);
    }

    public String getManufacturer() {
        return manufacturer.get();
    }
    public SimpleStringProperty getManufacturerProperty() {
        return manufacturer;
    }

    public void setManufacturerProperty(String manucaturer) {
        this.manufacturer = new SimpleStringProperty(manucaturer);
    }

    
    
}
