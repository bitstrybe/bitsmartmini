/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.bitsmartmini.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author scarface
 */
@Entity
@Table(name = "items_price")
@NamedQueries({
    @NamedQuery(name = "ItemsPrice.findAll", query = "SELECT i FROM ItemsPrice i")
    , @NamedQuery(name = "ItemsPrice.findByItemDesc", query = "SELECT i FROM ItemsPrice i WHERE i.itemDesc = :itemDesc")
    , @NamedQuery(name = "ItemsPrice.findByCostPrice", query = "SELECT i FROM ItemsPrice i WHERE i.costPrice = :costPrice")
    , @NamedQuery(name = "ItemsPrice.findBySalesPrice", query = "SELECT i FROM ItemsPrice i WHERE i.salesPrice = :salesPrice")
    , @NamedQuery(name = "ItemsPrice.findByEntryLog", query = "SELECT i FROM ItemsPrice i WHERE i.entryLog = :entryLog")
    , @NamedQuery(name = "ItemsPrice.findByLastModified", query = "SELECT i FROM ItemsPrice i WHERE i.lastModified = :lastModified")})
public class ItemsPrice implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "item_desc", nullable = false, length = 500)
    private String itemDesc;
    @Basic(optional = false)
    @Column(name = "cost_price", nullable = false)
    private double costPrice;
    @Basic(optional = false)
    @Column(name = "sales_price", nullable = false)
    private double salesPrice;
    @Basic(optional = false)
    @Column(name = "entry_log", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryLog;
    @Basic(optional = false)
    @Column(name = "last_modified", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @JoinColumn(name = "item_desc", referencedColumnName = "item_desc", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Items items;

    public ItemsPrice() {
    }

    public ItemsPrice(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public ItemsPrice(String itemDesc, double costPrice, double salesPrice, Date entryLog, Date lastModified) {
        this.itemDesc = itemDesc;
        this.costPrice = costPrice;
        this.salesPrice = salesPrice;
        this.entryLog = entryLog;
        this.lastModified = lastModified;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(double salesPrice) {
        this.salesPrice = salesPrice;
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

    public Items getItems() {
        return items;
    }

    public void setItems(Items items) {
        this.items = items;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itemDesc != null ? itemDesc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemsPrice)) {
            return false;
        }
        ItemsPrice other = (ItemsPrice) object;
        if ((this.itemDesc == null && other.itemDesc != null) || (this.itemDesc != null && !this.itemDesc.equals(other.itemDesc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bt.bitsmartmini.entity.ItemsPrice[ itemDesc=" + itemDesc + " ]";
    }
    
}
