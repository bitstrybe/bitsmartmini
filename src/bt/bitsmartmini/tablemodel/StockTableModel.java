
package bt.bitsmartmini.tablemodel;


import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author JScare
 */
public class StockTableModel {

//    private Integer stockinId;
    private SimpleStringProperty itemcode;
    private SimpleStringProperty items;
    private SimpleLongProperty stkinqty;
    private SimpleLongProperty stkoutqty;
    private SimpleLongProperty returns;
    private SimpleLongProperty salesqty;
    private SimpleLongProperty stkbal;
    private SimpleStringProperty stkcostprice;
    private SimpleStringProperty stksalesprice;
    private SimpleStringProperty exprofit;
    //private SimpleDoubleProperty acprofit;
    
//    private SimpleDoubleProperty nhisprice;

    public StockTableModel() {
    }

    public StockTableModel( String code, String items, long stkinqty, long stkoutqty,long returns, long salesqty, long stkbal, String stkcostprice, String stksalesprice, String exp) {
        this.itemcode = new SimpleStringProperty(code);
        this.items = new SimpleStringProperty(items);
        this.stkinqty = new SimpleLongProperty(stkinqty);
        this.stkoutqty = new SimpleLongProperty(stkoutqty);
        this.returns = new SimpleLongProperty(returns);
        this.salesqty = new SimpleLongProperty(salesqty);
        this.stkbal = new SimpleLongProperty(stkbal);
        this.stkcostprice = new SimpleStringProperty(stkcostprice);
        this.stksalesprice = new SimpleStringProperty(stksalesprice);
        this.exprofit = new SimpleStringProperty(exp);
        //this.acprofit = new SimpleDoubleProperty(acp);
//        this.nhisprice = new SimpleDoubleProperty(nhisprice);
    }

    public String getItemCode() {
        return itemcode.get();
    }

    public SimpleStringProperty getItemCodeProperty() {
        return itemcode;
    }

    public void setItemCodeProperty(String code) {
        this.itemcode = new SimpleStringProperty(code);
    }

    public String getItems() {
        return items.get();
    }

    public SimpleStringProperty getItemsProperty() {
        return items;
    }

    public void setItemsProperty(String items) {
        this.items = new SimpleStringProperty(items);
    }

    public long getStockinQty() {
        return stkinqty.get();
    }

    public SimpleLongProperty getStockinQtyProperty() {
        return stkinqty;
    }

    public void setStockinQtyProperty(long stkinqty) {
        this.stkinqty = new SimpleLongProperty(stkinqty);
    }

    public long getStockoutQty() {
        return stkoutqty.get();
    }

    public SimpleLongProperty getStockoutQtyProperty() {
        return stkoutqty;
    }

    public void setStockoutQtyProperty(long stkoutqty) {
        this.stkoutqty = new SimpleLongProperty(stkoutqty);
    }
    
     public long getReturnQty() {
        return returns.get();
    }

    public SimpleLongProperty getReturnQtyProperty() {
        return returns;
    }

    public void setReturnQtyProperty(long returns) {
        this.returns = new SimpleLongProperty(returns);
    }

    public long getSalesQty() {
        return salesqty.get();
    }

    public SimpleLongProperty getSalesQtyProperty() {
        return salesqty;
    }

    public void setSalesQtyProperty(long salesqty) {
        this.salesqty = new SimpleLongProperty(salesqty);
    }

    public long getStockbal() {
        return stkbal.get();
    }

    public SimpleLongProperty getStockbalProperty() {
        return stkbal;
    }

    public void setStockbalProperty(int stkbal) {
        this.stkbal = new SimpleLongProperty(stkbal);
    }

    public String getStockCostPrice() {
        return stkcostprice.get();
    }

    public SimpleStringProperty getStockCostPriceProperty() {
        return stkcostprice;
    }

    public void setStockCostPriceProperty(String stkcostprice) {
        this.stkcostprice = new SimpleStringProperty(stkcostprice);
    }

    public String getStockSalesPrice() {
        return stksalesprice.get();
    }

    public SimpleStringProperty getStockSalesPriceProperty() {
        return stksalesprice;
    }

    public void setStockSalesPriceProperty(String stksalesprice) {
        this.stksalesprice = new SimpleStringProperty(stksalesprice);
    }
    
    public String getExprofit() {
        return exprofit.get();
    }

    public SimpleStringProperty getExprofitProperty() {
        return exprofit;
    }

    public void setExprofitProperty(String exp) {
        this.exprofit = new SimpleStringProperty(exp);
    }
    
    
//     public double getNhisPrice() {
//        return nhisprice.get();
//    }
//
//    public SimpleDoubleProperty getNhisPriceProperty() {
//        return nhisprice;
//    }
//
//    public void setNhisPriceProperty(double nhisprice) {
//        this.nhisprice = new SimpleDoubleProperty(nhisprice);
//    }

}
