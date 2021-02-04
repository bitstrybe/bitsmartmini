/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.bitsmartmini.tablemodel;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author JScare
 */
public class StockinTableModel {

    private SimpleIntegerProperty stockin_code;
    private SimpleStringProperty batchNo;
    private SimpleStringProperty item;
    private SimpleIntegerProperty quantity;
    private SimpleStringProperty stockindate;
    private SimpleStringProperty expirydate;
    private SimpleDoubleProperty stkcostprice;
    private SimpleDoubleProperty stksalesprice;
//    private SimpleDoubleProperty nhisprice;

    public StockinTableModel() {
    }

    public StockinTableModel(int stockin_code, String batchNo, String item, Integer quantity, String stockindate, String expirydate,Double stkcostprice, Double stksalesprice) {
        this.stockin_code = new SimpleIntegerProperty(stockin_code);
        this.batchNo = new SimpleStringProperty(batchNo);
        this.item = new SimpleStringProperty(item);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.stockindate = new SimpleStringProperty(stockindate);
        this.expirydate = new SimpleStringProperty(expirydate);
        this.stkcostprice = new SimpleDoubleProperty(stkcostprice);
        this.stksalesprice = new SimpleDoubleProperty(stksalesprice);
//        this.nhisprice = new SimpleDoubleProperty(nhisprice);
    }

    public Integer getStockinCode() {
        return stockin_code.get();
    }

    public SimpleIntegerProperty getStockinCodeProperty() {
        return stockin_code;
    }

    public void setStockinCodeProperty(Integer stockin_code) {
        this.stockin_code = new SimpleIntegerProperty(stockin_code);
    }

    public String getBatchNo() {
        return batchNo.get();
    }

    public SimpleStringProperty getBatchNoProperty() {
        return batchNo;
    }

    public void setBatchNoProperty(String batchNo) {
        this.batchNo = new SimpleStringProperty(batchNo);
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

    public Integer getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty getQuantityProperty() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    public String getStockinDate() {
        return stockindate.get();
    }

    public SimpleStringProperty getStockinDateProperty() {
        return stockindate;
    }

    public void setStockinDate(String stockindate) {
        this.stockindate = new SimpleStringProperty(stockindate);
    }
    
    public String getExpiryDate() {
        return expirydate.get();
    }

    public SimpleStringProperty getExpiryDateProperty() {
        return expirydate;
    }

    public void setExpiryDate(String expirydate) {
        this.expirydate = new SimpleStringProperty(expirydate);
    }
     public Double getCostPrice() {
        return stkcostprice.get();
    }

    public SimpleDoubleProperty getCostPriceProperty() {
        return stkcostprice;
    }

    public void setCostPriceProperty(Double stkcostprice) {
        this.stkcostprice = new SimpleDoubleProperty(stkcostprice);
    }
     public Double getSalesPrice() {
        return stksalesprice.get();
    }

    public SimpleDoubleProperty getSalesPriceProperty() {
        return stksalesprice;
    }

    public void getSalesPriceProperty(Double stksalesprice) {
        this.stksalesprice = new SimpleDoubleProperty(stksalesprice);
    }
//      public Double getNHISPrice() {
//        return nhisprice.get();
//    }
//
//    public SimpleDoubleProperty  getNHISPriceProperty() {
//        return nhisprice;
//    }
//
//    public void  getNHISPriceProperty(Double nhisprice) {
//        this.nhisprice = new SimpleDoubleProperty(nhisprice);
//    }

}
