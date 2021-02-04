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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "stockout")
@NamedQueries({
    @NamedQuery(name = "Stockout.findAll", query = "SELECT s FROM Stockout s")
    , @NamedQuery(name = "Stockout.findByStockoutId", query = "SELECT s FROM Stockout s WHERE s.stockoutId = :stockoutId")
    , @NamedQuery(name = "Stockout.findByBatchNo", query = "SELECT s FROM Stockout s WHERE s.batchNo = :batchNo")
    , @NamedQuery(name = "Stockout.findByQuantity", query = "SELECT s FROM Stockout s WHERE s.quantity = :quantity")
    , @NamedQuery(name = "Stockout.findByRemarks", query = "SELECT s FROM Stockout s WHERE s.remarks = :remarks")
    , @NamedQuery(name = "Stockout.findByStkDate", query = "SELECT s FROM Stockout s WHERE s.stkDate = :stkDate")
    , @NamedQuery(name = "Stockout.findByEntryLog", query = "SELECT s FROM Stockout s WHERE s.entryLog = :entryLog")
    , @NamedQuery(name = "Stockout.findByModifiedDate", query = "SELECT s FROM Stockout s WHERE s.modifiedDate = :modifiedDate")})
public class Stockout implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "stockout_id", nullable = false)
    private Integer stockoutId;
    @Basic(optional = false)
    @Column(name = "batch_no", nullable = false, length = 150)
    private String batchNo;
    @Basic(optional = false)
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Basic(optional = false)
    @Column(name = "remarks", nullable = false, length = 145)
    private String remarks;
    @Basic(optional = false)
    @Column(name = "stk_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date stkDate;
    @Basic(optional = false)
    @Column(name = "entry_log", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryLog;
    @Basic(optional = false)
    @Column(name = "modified_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @JoinColumn(name = "users", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
    private Users users;
    @JoinColumn(name = "items", referencedColumnName = "item_desc", nullable = false)
    @ManyToOne(optional = false)
    private Items items;

    public Stockout() {
    }

    public Stockout(Integer stockoutId) {
        this.stockoutId = stockoutId;
    }

    public Stockout(Integer stockoutId, String batchNo, int quantity, String remarks, Date stkDate, Date entryLog, Date modifiedDate) {
        this.stockoutId = stockoutId;
        this.batchNo = batchNo;
        this.quantity = quantity;
        this.remarks = remarks;
        this.stkDate = stkDate;
        this.entryLog = entryLog;
        this.modifiedDate = modifiedDate;
    }

    public Integer getStockoutId() {
        return stockoutId;
    }

    public void setStockoutId(Integer stockoutId) {
        this.stockoutId = stockoutId;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getStkDate() {
        return stkDate;
    }

    public void setStkDate(Date stkDate) {
        this.stkDate = stkDate;
    }

    public Date getEntryLog() {
        return entryLog;
    }

    public void setEntryLog(Date entryLog) {
        this.entryLog = entryLog;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
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
        hash += (stockoutId != null ? stockoutId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stockout)) {
            return false;
        }
        Stockout other = (Stockout) object;
        if ((this.stockoutId == null && other.stockoutId != null) || (this.stockoutId != null && !this.stockoutId.equals(other.stockoutId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bt.bitsmartmini.entity.Stockout[ stockoutId=" + stockoutId + " ]";
    }
    
}
