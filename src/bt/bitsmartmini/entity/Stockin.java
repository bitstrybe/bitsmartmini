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
    @NamedQuery(name = "Stockin.findAll", query = "SELECT s FROM Stockin s"),
    @NamedQuery(name = "Stockin.findByStockinId", query = "SELECT s FROM Stockin s WHERE s.stockinId = :stockinId"),
    @NamedQuery(name = "Stockin.findByStockinDate", query = "SELECT s FROM Stockin s WHERE s.stockinDate = :stockinDate"),
    @NamedQuery(name = "Stockin.findByMeasure", query = "SELECT s FROM Stockin s WHERE s.measure = :measure"),
    @NamedQuery(name = "Stockin.findByUnitmeasure", query = "SELECT s FROM Stockin s WHERE s.unitmeasure = :unitmeasure"),
    @NamedQuery(name = "Stockin.findByMeasureqty", query = "SELECT s FROM Stockin s WHERE s.measureqty = :measureqty"),
    @NamedQuery(name = "Stockin.findByQuantity", query = "SELECT s FROM Stockin s WHERE s.quantity = :quantity"),
    @NamedQuery(name = "Stockin.findByExpiryDate", query = "SELECT s FROM Stockin s WHERE s.expiryDate = :expiryDate"),
    @NamedQuery(name = "Stockin.findByEntryLog", query = "SELECT s FROM Stockin s WHERE s.entryLog = :entryLog"),
    @NamedQuery(name = "Stockin.findByLastModified", query = "SELECT s FROM Stockin s WHERE s.lastModified = :lastModified")})
public class Stockin implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "stockin_id", nullable = false)
    private Integer stockinId;
    @Basic(optional = false)
    @Column(name = "stockin_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date stockinDate;
    @Column(name = "measure", length = 45)
    private String measure;
    @Column(name = "unitmeasure")
    private Integer unitmeasure;
    @Column(name = "measureqty")
    private Integer measureqty;
    @Basic(optional = false)
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Column(name = "expiry_date")
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
    @JoinColumn(name = "upc", referencedColumnName = "upc", nullable = false)
    @ManyToOne(optional = false)
    private Items upc;
    @JoinColumn(name = "users", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
    private Users users;

    public Stockin() {
    }

    public Stockin(Integer stockinId) {
        this.stockinId = stockinId;
    }

    public Stockin(Integer stockinId, Date stockinDate, int quantity, Date entryLog, Date lastModified) {
        this.stockinId = stockinId;
        this.stockinDate = stockinDate;
        this.quantity = quantity;
        this.entryLog = entryLog;
        this.lastModified = lastModified;
    }

    public Integer getStockinId() {
        return stockinId;
    }

    public void setStockinId(Integer stockinId) {
        this.stockinId = stockinId;
    }

    public Date getStockinDate() {
        return stockinDate;
    }

    public void setStockinDate(Date stockinDate) {
        this.stockinDate = stockinDate;
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
