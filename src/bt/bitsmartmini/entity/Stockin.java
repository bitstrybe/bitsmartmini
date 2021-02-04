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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author scarface
 */
@Entity
@Table(name = "stockin")
@NamedQueries({
    @NamedQuery(name = "Stockin.findAll", query = "SELECT s FROM Stockin s")
    , @NamedQuery(name = "Stockin.findByStockinId", query = "SELECT s FROM Stockin s WHERE s.stockinId = :stockinId")
    , @NamedQuery(name = "Stockin.findByBatchNo", query = "SELECT s FROM Stockin s WHERE s.batchNo = :batchNo")
    , @NamedQuery(name = "Stockin.findByQuantity", query = "SELECT s FROM Stockin s WHERE s.quantity = :quantity")
    , @NamedQuery(name = "Stockin.findByCostPrice", query = "SELECT s FROM Stockin s WHERE s.costPrice = :costPrice")
    , @NamedQuery(name = "Stockin.findBySalesPrice", query = "SELECT s FROM Stockin s WHERE s.salesPrice = :salesPrice")
    , @NamedQuery(name = "Stockin.findByStockinDate", query = "SELECT s FROM Stockin s WHERE s.stockinDate = :stockinDate")
    , @NamedQuery(name = "Stockin.findByExpiryDate", query = "SELECT s FROM Stockin s WHERE s.expiryDate = :expiryDate")
    , @NamedQuery(name = "Stockin.findByEntryLog", query = "SELECT s FROM Stockin s WHERE s.entryLog = :entryLog")
    , @NamedQuery(name = "Stockin.findByLastModified", query = "SELECT s FROM Stockin s WHERE s.lastModified = :lastModified")})
public class Stockin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "stockin_id", nullable = false)
    private Integer stockinId;
    @Basic(optional = false)
    @Column(name = "batch_no", nullable = false, length = 250)
    private String batchNo;
    @Basic(optional = false)
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Basic(optional = false)
    @Column(name = "cost_price", nullable = false)
    private double costPrice;
    @Basic(optional = false)
    @Column(name = "sales_price", nullable = false)
    private double salesPrice;
    @Basic(optional = false)
    @Column(name = "stockin_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date stockinDate;
    @Basic(optional = false)
    @Column(name = "expiry_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expiryDate;
    @Basic(optional = false)
    @Column(name = "entry_log", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryLog;
    @Basic(optional = false)
    @Column(name = "last_modified", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @JoinColumn(name = "users", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
    private Users users;
    @JoinColumn(name = "items", referencedColumnName = "item_desc", nullable = false)
    @ManyToOne(optional = false)
    private Items items;

    public Stockin() {
    }

    public Stockin(Integer stockinId) {
        this.stockinId = stockinId;
    }

    public Stockin(Integer stockinId, String batchNo, int quantity, double costPrice, double salesPrice, Date stockinDate, Date expiryDate, Date entryLog, Date lastModified) {
        this.stockinId = stockinId;
        this.batchNo = batchNo;
        this.quantity = quantity;
        this.costPrice = costPrice;
        this.salesPrice = salesPrice;
        this.stockinDate = stockinDate;
        this.expiryDate = expiryDate;
        this.entryLog = entryLog;
        this.lastModified = lastModified;
    }

    public Integer getStockinId() {
        return stockinId;
    }

    public void setStockinId(Integer stockinId) {
        this.stockinId = stockinId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public Date getStockinDate() {
        return stockinDate;
    }

    public void setStockinDate(Date stockinDate) {
        this.stockinDate = stockinDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
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

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
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
        hash += (stockinId != null ? stockinId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stockin)) {
            return false;
        }
        Stockin other = (Stockin) object;
        if ((this.stockinId == null && other.stockinId != null) || (this.stockinId != null && !this.stockinId.equals(other.stockinId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bt.bitsmartmini.entity.Stockin[ stockinId=" + stockinId + " ]";
    }
    
}
