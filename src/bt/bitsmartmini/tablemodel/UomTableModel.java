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
public class UomTableModel {
     private SimpleStringProperty uom;

    public UomTableModel() {
    }

    public UomTableModel(String uom) {
        this.uom = new SimpleStringProperty(uom);
    }

    public String getUomName() {
        return uom.get();
    }

    public SimpleStringProperty getUomNameProperty() {
        return uom;
    }

    public void setUomNameProperty(String uom) {
        this.uom = new SimpleStringProperty(uom);
    }
}
