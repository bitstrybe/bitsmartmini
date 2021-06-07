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
 * @author Jscar
 */
@Entity
@Table(name = "stockout")
@NamedQueries({
    @NamedQuery(name = "Stockout.findAll", query = "SELECT s FROM Stockout s"),
    @NamedQuery(name = "Stockout.findByStockoutId", query = "SELECT s FROM Stockout s WHERE s.stockoutId = :stockoutId"),
    @NamedQuery(name = "Stockout.findByStkDate", query = "SELECT s FROM Stockout s WHERE s.stkDate = :stkDate"),
    @NamedQuery(name = "Stockout.findByMeasure", query = "SELECT s FROM Stockout s WHERE s.measure = :measure"),
    @NamedQuery(name = "Stockout.findByUnitmeasure", query = "SELECT s FROM Stockout s WHERE s.unitmeasure = :unitmeasure"),
    @NamedQuery(name = "Stockout.findByMeasureqty", query = "SELECT s FROM Stockout s WHERE s.measureqty = :measureqty"),
    @NamedQuery(name = "Stockout.findByQuantity", query = "SELECT s FROM Stockout s WHERE s.quantity = :quantity"),
    @NamedQuery(name = "Stockout.findByRemarks", query = "SELECT s FROM Stockout s WHERE s.remarks = :remarks"),
    @NamedQuery(name = "Stockout.findByEntryLog", query = "SELECT s FROM Stockout s WHERE s.entryLog = :entryLog"),
    @NamedQuery(name = "Stockout.findByModifiedDate", query = "SELECT s FROM Stockout s WHERE s.modifiedDate = :modifiedDate")})
public class Stockout implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "stockout_id", nullable = false)
    private Integer stockoutId;
    @Basic(optional = false)
    @Column(name = "stk_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date stkDate;
    @Column(name = "measure", length = 45)
    private String measure;
    @Column(name = "unitmeasure")
    private Integer unitmeasure;
    @Column(name = "measureqty")
    private Integer measureqty;
    @Basic(optional = false)
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Basic(optional = false)
    @Column(name = "remarks", nullable = false, length = 145)
    private String remarks;
    @Basic(optional = false)
    @Column(name = "entry_log", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryLog;
    @Basic(optional = false)
    @Column(name = "modified_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @JoinColumn(name = "upc", referencedColumnName = "upc", nullable = false)
    @ManyToOne(optional = false)
    private Items upc;
    @JoinColumn(name = "users", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
    private Users users;

    public Stockout() {
    }

    public Stockout(Integer stockoutId) {
        this.stockoutId = stockoutId;
    }

    public Stockout(Integer stockoutId, Date stkDate, int quantity, String remarks, Date entryLog, Date modifiedDate) {
        this.stockoutId = stockoutId;
        this.stkDate = stkDate;
        this.quantity = quantity;
        this.remarks = remarks;
        this.entryLog = entryLog;
        this.modifiedDate = modifiedDate;
    }

    public Integer getStockoutId() {
        return stockoutId;
    }

    public void setStockoutId(Integer stockoutId) {
        this.stockoutId = stockoutId;
    }

    public Date getStkDate() {
        return stkDate;
    }

    public void setStkDate(Date stkDate) {
        this.stkDate = stkDate;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public Integer getUnitmeasure() {
        return unitmeasure;
    }

    public void setUnitmeasure(Integer unitmeasure) {
        this.unitmeasure = unitmeasure;
    }

    public Integer getMeasureqty() {
        return measureqty;
    }

    public void setMeasureqty(Integer measureqty) {
        this.measureqty = measureqty;
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

    public Items getUpc() {
        return upc;
    }

    public void setUpc(Items upc) {
        this.upc = upc;
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
