package bt.bitsmartmini.tablemodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Justus O Adams
 */
public class CustomersTableModel {

    private SimpleIntegerProperty customer_id;
    private SimpleStringProperty customers;
    private SimpleStringProperty phone;

    public CustomersTableModel() {
    }

    public CustomersTableModel(Integer customer_id, String customers, String phone) {
        this.customer_id = new SimpleIntegerProperty(customer_id);
        this.customers = new SimpleStringProperty(customers);
        this.phone = new SimpleStringProperty(phone);
    }

    public Integer getCustomerId() {
        return customer_id.get();
    }

    public SimpleIntegerProperty getCustomerIdProperty() {
        return customer_id;
    }

    public void setCustomerIdProperty(Integer customer_id) {
        this.customer_id = new SimpleIntegerProperty(customer_id);
    }
    
    
    public String getCustomers(){
        return customers.get();
    }
    public SimpleStringProperty getCustomersProperty(){
        return customers;
    }
    public void setCustomerProperty(String customers){
        this.customers = new SimpleStringProperty(customers);
    }
    
    public String getPhone(){
        return phone.get();
    }
    public SimpleStringProperty getPhoneProperty(){
        return phone;
    }
    public void setPhoneProperty(String phone){
        this.phone = new SimpleStringProperty(phone);
    }

}
