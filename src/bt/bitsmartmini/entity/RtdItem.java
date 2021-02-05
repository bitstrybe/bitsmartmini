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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author scarface
 */
@Entity
@Table(name = "rtd_item")
@NamedQueries({
    @NamedQuery(name = "RtdItem.findAll", query = "SELECT r FROM RtdItem r")
    , @NamedQuery(name = "RtdItem.findBySalescode", query = "SELECT r FROM RtdItem r WHERE r.salescode = :salescode")
    , @NamedQuery(name = "RtdItem.findByRtdDate", query = "SELECT r FROM RtdItem r WHERE r.rtdDate = :rtdDate")
    , @NamedQuery(name = "RtdItem.findByRtdTime", query = "SELECT r FROM RtdItem r WHERE r.rtdTime = :rtdTime")
    , @NamedQuery(name = "RtdItem.findByRtdQty", query = "SELECT r FROM RtdItem r WHERE r.rtdQty = :rtdQty")
    , @NamedQuery(name = "RtdItem.findByRemarks", query = "SELECT r FROM RtdItem r WHERE r.remarks = :remarks")
    , @NamedQuery(name = "RtdItem.findByEntryLog", query = "SELECT r FROM RtdItem r WHERE r.entryLog = :entryLog")
    , @NamedQuery(name = "RtdItem.findByLastModified", query = "SELECT r FROM RtdItem r WHERE r.lastModified = :lastModified")})
public class RtdItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "salescode", nullable = false)
    private Integer salescode;
    @Basic(optional = false)
    @Column(name = "rtd_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date rtdDate;
    @Basic(optional = false)
    @Column(name = "rtd_time", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date rtdTime;
    @Basic(optional = false)
    @Column(name = "rtd_qty", nullable = false)
    private int rtdQty;
    @Column(name = "remarks", length = 500)
    private String remarks;
    @Basic(optional = false)
    @Column(name = "entry_log", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryLog;
    @Basic(optional = false)
    @Column(name = "last_modified", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModified;
    @JoinColumn(name = "salescode", referencedColumnName = "sales_details_id", nullable = false, insertable = false, updatable = false)
    @OneToOne(optional = false)
    private SalesDetails salesDetails;
    @JoinColumn(name = "users", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
    private Users users;

    public RtdItem() {
    }

    public RtdItem(Integer salescode) {
        this.salescode = salescode;
    }

    public RtdItem(Integer salescode, Date rtdDate, Date rtdTime, int rtdQty, Date entryLog, Date lastModified) {
        this.salescode = salescode;
        this.rtdDate = rtdDate;
        this.rtdTime = rtdTime;
        this.rtdQty = rtdQty;
        this.entryLog = entryLog;
        this.lastModified = lastModified;
    }

    public Integer getSalescode() {
        return salescode;
    }

    public void setSalescode(Integer salescode) {
        this.salescode = salescode;
    }

    public Date getRtdDate() {
        return rtdDate;
    }

    public void setRtdDate(Date rtdDate) {
        this.rtdDate = rtdDate;
    }

    public Date getRtdTime() {
        return rtdTime;
    }

    public void setRtdTime(Date rtdTime) {
        this.rtdTime = rtdTime;
    }

    public int getRtdQty() {
        return rtdQty;
    }

    public void setRtdQty(int rtdQty) {
        this.rtdQty = rtdQty;
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

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public SalesDetails getSalesDetails() {
        return salesDetails;
    }

    public void setSalesDetails(SalesDetails salesDetails) {
        this.salesDetails = salesDetails;
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
        hash += (salescode != null ? salescode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RtdItem)) {
            return false;
        }
        RtdItem other = (RtdItem) object;
        if ((this.salescode == null && other.salescode != null) || (this.salescode != null && !this.salescode.equals(other.salescode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bt.bitsmartmini.entity.RtdItem[ salescode=" + salescode + " ]";
    }
    
}
