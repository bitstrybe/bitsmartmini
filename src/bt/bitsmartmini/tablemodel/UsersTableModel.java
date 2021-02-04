/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.bitsmartmini.tablemodel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author JScare
 */
public class UsersTableModel {
private SimpleIntegerProperty usersid;
    private SimpleStringProperty fullname;
    private SimpleStringProperty mobile;
    private SimpleStringProperty email;
    private SimpleStringProperty username;
    private SimpleStringProperty roles;
    private SimpleStringProperty status;
    private SimpleStringProperty account;

    public UsersTableModel(Integer usersid,String fullname, String mobile, String email, String username, String roles, String status, String account) {
        this.usersid = new SimpleIntegerProperty(usersid);
        this.fullname = new SimpleStringProperty(fullname);
        this.mobile = new SimpleStringProperty(mobile);
        this.email = new SimpleStringProperty(email);
        this.username = new SimpleStringProperty(username);
        this.roles = new SimpleStringProperty(roles);
        this.status = new SimpleStringProperty(status);
        this.account = new SimpleStringProperty(account);
    }

      public Integer getUserId() {
        return usersid.get();
    }

    public SimpleIntegerProperty getUserIdProperty() {
        return usersid;
    }

    public void setUserIdProperty(Integer usersid) {
        this.usersid = new SimpleIntegerProperty(usersid);
    }
    
    public String getFullname() {
        return fullname.get();
    }

    public SimpleStringProperty getFullnameProperty() {
        return fullname;
    }

    public void setFullnameProperty(String fullname) {
        this.fullname = new SimpleStringProperty(fullname);
    }
    
    public String getMobile() {
        return mobile.get();
    }

    public SimpleStringProperty getMobileProperty() {
        return mobile;
    }

    public void setMobileProperty(String mobile) {
        this.mobile = new SimpleStringProperty(mobile);
    }
      public String getEmail() {
        return email.get();
    }

    public SimpleStringProperty getEmailProperty() {
        return email;
    }

    public void setEmailProperty(String email) {
        this.email = new SimpleStringProperty(email);
    }
     public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty getUsernameProperty() {
        return username;
    }

    public void setUsernameProperty(String username) {
        this.username = new SimpleStringProperty(username);
    }
      public String getRoles() {
        return roles.get();
    }

    public SimpleStringProperty getRolesProperty() {
        return roles;
    }

    public void setRolesProperty(String roles) {
        this.roles = new SimpleStringProperty(roles);
    }
       public String getStatus() {
        return status.get();
    }

    public SimpleStringProperty getStatusProperty() {
        return status;
    }

    public void setStatusProperty(String status) {
        this.status = new SimpleStringProperty(status);
    }
       public String getAccount() {
        return account.get();
    }

    public SimpleStringProperty getAccountProperty() {
        return account;
    }

    public void setAccountProperty(String status) {
        this.account = new SimpleStringProperty(status);
    }

}
