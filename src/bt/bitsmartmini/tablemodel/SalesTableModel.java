
package bt.bitsmartmini.tablemodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author JScare
 */
public class SalesTableModel {

    private SimpleIntegerProperty salescode;
    private SimpleStringProperty customer;
    private SimpleStringProperty salesprice;
    private SimpleStringProperty amountpaid;
    private SimpleStringProperty rtd_itm;
    private SimpleStringProperty balance;
    private SimpleStringProperty date;
    private SimpleStringProperty actuals;
    private SimpleStringProperty user;
    private SimpleStringProperty elog;

    public SalesTableModel() {
    }

    public SalesTableModel(Integer salescode, String customer, String salesprice, String amountpaid,String rtd_itm, String balance, String date, String actuals, String user, String elog) {
        this.salescode = new SimpleIntegerProperty(salescode);
        this.customer = new SimpleStringProperty(customer);
        this.salesprice = new SimpleStringProperty(salesprice);
        this.amountpaid = new SimpleStringProperty(amountpaid);
         this.rtd_itm = new SimpleStringProperty(rtd_itm);
        this.balance = new SimpleStringProperty(balance);
        this.date = new SimpleStringProperty(date);
        this.actuals = new SimpleStringProperty(actuals);
        this.user = new SimpleStringProperty(user);
        this.elog = new SimpleStringProperty(elog);
    }

    public Integer getSalescode() {
        return salescode.get();
    }

    public SimpleIntegerProperty getSalescodeProperty() {
        return salescode;
    }

    public void setSalesIdProperty(Integer salescode) {
        this.salescode = new SimpleIntegerProperty(salescode);
    }

    public String getCustomer() {
        return customer.get();
    }

    public SimpleStringProperty getCustomerProperty() {
        return customer;
    }

    public void setCustomerProperty(String customer) {
        this.customer = new SimpleStringProperty(customer);
    }
    

    public String getSalesprice() {
        return salesprice.get();
    }

    public SimpleStringProperty getSalespriceProperty() {
        return salesprice;
    }

    public void setSalespriceProperty(String salesprice) {
        this.salesprice = new SimpleStringProperty(salesprice);
    }
    public String getAmountpaid() {
        return amountpaid.get();
    }

    public SimpleStringProperty getAmountpaidProperty() {
        return amountpaid;
    }

    public void setAmountpaidProperty(String amountpaid) {
        this.amountpaid = new SimpleStringProperty(amountpaid);
    }
    public String getReturnItem() {
        return rtd_itm.get();
    }

    public SimpleStringProperty getReturnItemProperty() {
        return rtd_itm;
    }

    public void setReturnItemProperty(String rtd_itm) {
        this.rtd_itm = new SimpleStringProperty(rtd_itm);
    }

    public String getBalance() {
        return balance.get();
    }

    public SimpleStringProperty getBalanceProperty() {
        return balance;
    }

    public void setBalanceProperty(String balance) {
        this.balance = new SimpleStringProperty(balance);
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
    
    public String getActuals() {
        return actuals.get();
    }

    public SimpleStringProperty getActualsProperty() {
        return actuals;
    }

    public void setActualsProperty(String a) {
        this.actuals = new SimpleStringProperty(a);
    }
    
    public String getUser() {
        return user.get();
    }

    public SimpleStringProperty getUserProperty() {
        return user;
    }

    public void setUserProperty(String a) {
        this.user = new SimpleStringProperty(a);
    }
    
    public String getElog() {
        return elog.get();
    }

    public SimpleStringProperty getElogProperty() {
        return elog;
    }

    public void setElogrProperty(String a) {
        this.elog = new SimpleStringProperty(a);
    }

}
