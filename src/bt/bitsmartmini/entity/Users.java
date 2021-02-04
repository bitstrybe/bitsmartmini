/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.bitsmartmini.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author scarface
 */
@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findByUserid", query = "SELECT u FROM Users u WHERE u.userid = :userid")
    , @NamedQuery(name = "Users.findByTitle", query = "SELECT u FROM Users u WHERE u.title = :title")
    , @NamedQuery(name = "Users.findByFname", query = "SELECT u FROM Users u WHERE u.fname = :fname")
    , @NamedQuery(name = "Users.findByLname", query = "SELECT u FROM Users u WHERE u.lname = :lname")
    , @NamedQuery(name = "Users.findByMobile", query = "SELECT u FROM Users u WHERE u.mobile = :mobile")
    , @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email")
    , @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username")
    , @NamedQuery(name = "Users.findByPwd", query = "SELECT u FROM Users u WHERE u.pwd = :pwd")
    , @NamedQuery(name = "Users.findByRoles", query = "SELECT u FROM Users u WHERE u.roles = :roles")
    , @NamedQuery(name = "Users.findByDateCreated", query = "SELECT u FROM Users u WHERE u.dateCreated = :dateCreated")
    , @NamedQuery(name = "Users.findByModifiedDate", query = "SELECT u FROM Users u WHERE u.modifiedDate = :modifiedDate")
    , @NamedQuery(name = "Users.findByPwdStatus", query = "SELECT u FROM Users u WHERE u.pwdStatus = :pwdStatus")
    , @NamedQuery(name = "Users.findByActive", query = "SELECT u FROM Users u WHERE u.active = :active")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "userid", nullable = false)
    private Integer userid;
    @Basic(optional = false)
    @Column(name = "title", nullable = false, length = 30)
    private String title;
    @Basic(optional = false)
    @Column(name = "fname", nullable = false, length = 145)
    private String fname;
    @Basic(optional = false)
    @Column(name = "lname", nullable = false, length = 145)
    private String lname;
    @Basic(optional = false)
    @Column(name = "mobile", nullable = false, length = 45)
    private String mobile;
    @Column(name = "email", length = 100)
    private String email;
    @Basic(optional = false)
    @Column(name = "username", nullable = false, length = 45)
    private String username;
    @Basic(optional = false)
    @Column(name = "pwd", nullable = false, length = 45)
    private String pwd;
    @Basic(optional = false)
    @Column(name = "roles", nullable = false, length = 100)
    private String roles;
    @Basic(optional = false)
    @Column(name = "date_created", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Basic(optional = false)
    @Column(name = "pwd_status", nullable = false)
    private int pwdStatus;
    @Column(name = "active")
    private Integer active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Collection<RefundPolicy> refundPolicyCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<Sales> salesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<Stockout> stockoutCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<Stockin> stockinCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "username")
    private Collection<Userlogs> userlogsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<RtdItem> rtdItemCollection;
    @OneToMany(mappedBy = "users")
    private Collection<Receipt> receiptCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Collection<Items> itemsCollection;

    public Users() {
    }

    public Users(Integer userid) {
        this.userid = userid;
    }

    public Users(Integer userid, String title, String fname, String lname, String mobile, String username, String pwd, String roles, Date dateCreated, int pwdStatus) {
        this.userid = userid;
        this.title = title;
        this.fname = fname;
        this.lname = lname;
        this.mobile = mobile;
        this.username = username;
        this.pwd = pwd;
        this.roles = roles;
        this.dateCreated = dateCreated;
        this.pwdStatus = pwdStatus;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getPwdStatus() {
        return pwdStatus;
    }

    public void setPwdStatus(int pwdStatus) {
        this.pwdStatus = pwdStatus;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Collection<RefundPolicy> getRefundPolicyCollection() {
        return refundPolicyCollection;
    }

    public void setRefundPolicyCollection(Collection<RefundPolicy> refundPolicyCollection) {
        this.refundPolicyCollection = refundPolicyCollection;
    }

    public Collection<Sales> getSalesCollection() {
        return salesCollection;
    }

    public void setSalesCollection(Collection<Sales> salesCollection) {
        this.salesCollection = salesCollection;
    }

    public Collection<Stockout> getStockoutCollection() {
        return stockoutCollection;
    }

    public void setStockoutCollection(Collection<Stockout> stockoutCollection) {
        this.stockoutCollection = stockoutCollection;
    }

    public Collection<Stockin> getStockinCollection() {
        return stockinCollection;
    }

    public void setStockinCollection(Collection<Stockin> stockinCollection) {
        this.stockinCollection = stockinCollection;
    }

    public Collection<Userlogs> getUserlogsCollection() {
        return userlogsCollection;
    }

    public void setUserlogsCollection(Collection<Userlogs> userlogsCollection) {
        this.userlogsCollection = userlogsCollection;
    }

    public Collection<RtdItem> getRtdItemCollection() {
        return rtdItemCollection;
    }

    public void setRtdItemCollection(Collection<RtdItem> rtdItemCollection) {
        this.rtdItemCollection = rtdItemCollection;
    }

    public Collection<Receipt> getReceiptCollection() {
        return receiptCollection;
    }

    public void setReceiptCollection(Collection<Receipt> receiptCollection) {
        this.receiptCollection = receiptCollection;
    }

    public Collection<Items> getItemsCollection() {
        return itemsCollection;
    }

    public void setItemsCollection(Collection<Items> itemsCollection) {
        this.itemsCollection = itemsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bt.bitsmartmini.entity.Users[ userid=" + userid + " ]";
    }
    
}
