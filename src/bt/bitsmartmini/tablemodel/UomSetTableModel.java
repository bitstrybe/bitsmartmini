package bt.bitsmartmini.tablemodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author JScare
 */
public class UomSetTableModel {

    private SimpleStringProperty uomset;
    private SimpleStringProperty uomM1;
    private SimpleIntegerProperty uomU1;
    private SimpleStringProperty uomM2;
    private SimpleIntegerProperty uomU2;

    public UomSetTableModel() {
    }

    public UomSetTableModel(String uomset, String uomM1, Integer uomU1) {
        this.uomset = new SimpleStringProperty(uomset);
        this.uomM1 = new SimpleStringProperty(uomM1);
        this.uomU1 = new SimpleIntegerProperty(uomU1);
    }

    public UomSetTableModel(String uomset, String uomM1, Integer uomU1, String uomM2, Integer uomU2) {
        this.uomset = new SimpleStringProperty(uomset);
        this.uomM1 = new SimpleStringProperty(uomM1);
        this.uomU1 = new SimpleIntegerProperty(uomU1);
        this.uomM2 = new SimpleStringProperty(uomM2);
        this.uomU2 = new SimpleIntegerProperty(uomU2);
    }

    public String getUomSet() {
        return uomset.get();
    }

    public SimpleStringProperty getUomSetProperty() {
        return uomset;
    }

    public void setUomSetProperty(String uom) {
        this.uomset = new SimpleStringProperty(uom);
    }

    public String getUomM1() {
        return uomM1.get();
    }

    public SimpleStringProperty getUomM1Property() {
        return uomM1;
    }

    public void setUomM1Property(String uom) {
        this.uomM1 = new SimpleStringProperty(uom);
    }
    
     public String getUomM2() {
        return uomM2.get();
    }

    public SimpleStringProperty getUomM2Property() {
        return uomM2;
    }

    public void setUomM2Property(String uom) {
        this.uomM2 = new SimpleStringProperty(uom);
    }

    public Integer getUomU1() {
        return uomU1.get();
    }

    public SimpleIntegerProperty getUomU1Property() {
        return uomU1;
    }

    public void setUomU1Property(Integer uom) {
        this.uomU1 = new SimpleIntegerProperty(uom);
    }
    
    public Integer getUomU2() {
        return uomU2.get();
    }

    public SimpleIntegerProperty getUomU2Property() {
        return uomU2;
    }

    public void setUomU2Property(Integer uom) {
        this.uomU2 = new SimpleIntegerProperty(uom);
    }
}
