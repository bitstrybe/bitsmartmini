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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jscar
 */
@Entity
@Table(name = "items")
@NamedQueries({
    @NamedQuery(name = "Items.findAll", query = "SELECT i FROM Items i"),
    @NamedQuery(name = "Items.findByUpc", query = "SELECT i FROM Items i WHERE i.upc = :upc"),
    @NamedQuery(name = "Items.findByItemDesc", query = "SELECT i FROM Items i WHERE i.itemDesc = :itemDesc"),
    @NamedQuery(name = "Items.findByItemImg", query = "SELECT i FROM Items i WHERE i.itemImg = :itemImg"),
    @NamedQuery(name = "Items.findByRol", query = "SELECT i FROM Items i WHERE i.rol = :rol"),
    @NamedQuery(name = "Items.findByCp", query = "SELECT i FROM Items i WHERE i.cp = :cp"),
    @NamedQuery(name = "Items.findBySp", query = "SELECT i FROM Items i WHERE i.sp = :sp"),
    @NamedQuery(name = "Items.findByEntryLog", query = "SELECT i FROM Items i WHERE i.entryLog = :entryLog"),
    @NamedQuery(name = "Items.findByLastModified", query = "SELECT i FROM Items i WHERE i.lastModified = :lastModified")})
public class Items implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "upc", nullable = false, length = 45)
    private String upc;
    @Basic(optional = false)
    @Column(name = "item_desc", nullable = false, length = 200)
    private String itemDesc;
    @Basic(optional = false)
    @Column(name = "item_img", nullable = false, length = 245)
    private String itemImg;
    @Basic(optional = false)
    @Column(name = "rol", nullable = false)
    private int rol;
    @Basic(optional = false)
    @Column(name = "cp", nullable = false)
    private double cp;
    @Basic(optional = false)
    @Column(name = "sp", nullable = false)
    private double sp;
    @Basic(optional = false)
    @Column(name = "entry_log", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryLog;
    @Basic(optional = false)
    @Column(name = "last_modified", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "upc")
    private Collection<SalesDetails> salesDetailsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "upc")
    private Collection<Stockout> stockoutCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "upc")
    private Collection<Stockin> stockinCollection;
    @JoinColumn(name = "brand", referencedColumnName = "brand_name", nullable = false)
    @ManyToOne(optional = false)
    private Brands brand;
    @JoinColumn(name = "category", referencedColumnName = "category_name", nullable = false)
    @ManyToOne(optional = false)
    private Category category;
    @JoinColumn(name = "uomset", referencedColumnName = "uom_set_code")
    @ManyToOne
    private UomSet uomset;
    @JoinColumn(name = "users", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
    private Users users;

    public Items() {
    }

    public Items(String upc) {
        this.upc = upc;
    }

    public Items(String upc, String itemDesc, String itemImg, int rol, double cp, double sp, Date entryLog, Date lastModified) {
        this.upc = upc;
        this.itemDesc = itemDesc;
        this.itemImg = itemImg;
        this.rol = rol;
        this.cp = cp;
        this.sp = sp;
        this.entryLog = entryLog;
        this.lastModified = lastModified;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemImg() {
        return itemImg;
    }

    public void setItemImg(String itemImg) {
        this.itemImg = itemImg;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public double getCp() {
        return cp;
    }

    public void setCp(double cp) {
        this.cp = cp;
    }

    public double getSp() {
        return sp;
    }

    public void setSp(double sp) {
        this.sp = sp;
    }

    public Date getEntryLog() {
        return entryLog;
    }

    public void setEntryLog(Date entryLog) {
        this.entryLog = entryLog;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Collection<SalesDetails> getSalesDetailsCollection() {
        return salesDetailsCollection;
    }

    public void setSalesDetailsCollection(Collection<SalesDetails> salesDetailsCollection) {
        this.salesDetailsCollection = salesDetailsCollection;
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

    public Brands getBrand() {
        return brand;
    }

    public void setBrand(Brands brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public UomSet getUomset() {
        return uomset;
    }

    public void setUomset(UomSet uomset) {
        this.uomset = uomset;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (upc != null ? upc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Items)) {
            return false;
        }
        Items other = (Items) object;
        if ((this.upc == null && other.upc != null) || (this.upc != null && !this.upc.equals(other.upc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bt.bitsmartmini.entity.Items[ upc=" + upc + " ]";
    }
    
}
