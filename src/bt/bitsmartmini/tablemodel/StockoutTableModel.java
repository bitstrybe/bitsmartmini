/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.bitsmartmini.tablemodel;

import java.io.Serializable;
import java.util.Date;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author JScare
 */
public class StockoutTableModel implements Serializable {

    private SimpleIntegerProperty stockoutId;
    private SimpleStringProperty item;
    private SimpleIntegerProperty quantity;
    private SimpleStringProperty remarks;
    private SimpleStringProperty date;
   

    public StockoutTableModel() {
    }

    public StockoutTableModel( int stockoutId, String item, int quantity, String remarks,  String date) {
        this.stockoutId = new SimpleIntegerProperty(stockoutId);
        this.item = new SimpleStringProperty(item);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.remarks = new SimpleStringProperty(remarks);
        this.date = new SimpleStringProperty(date);
    }

    public Integer getStockoutId() {
        return stockoutId.get();
    }
    public SimpleIntegerProperty getStockoutIdProperty() {
        return stockoutId;
    }

    public void setStockoutIdProperty(Integer stockoutId) {
        this.stockoutId = new SimpleIntegerProperty(stockoutId);
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
