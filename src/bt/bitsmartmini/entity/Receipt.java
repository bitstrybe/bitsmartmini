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
 * @author Jscar
 */
@Entity
@Table(name = "receipt")
@NamedQueries({
    @NamedQuery(name = "Receipt.findAll", query = "SELECT r FROM Receipt r"),
    @NamedQuery(name = "Receipt.findByReceiptId", query = "SELECT r FROM Receipt r WHERE r.receiptId = :receiptId"),
    @NamedQuery(name = "Receipt.findByReceiptDate", query = "SELECT r FROM Receipt r WHERE r.receiptDate = :receiptDate"),
    @NamedQuery(name = "Receipt.findByReceiptTime", query = "SELECT r FROM Receipt r WHERE r.receiptTime = :receiptTime"),
    @NamedQuery(name = "Receipt.findByAmountDue", query = "SELECT r FROM Receipt r WHERE r.amountDue = :amountDue"),
    @NamedQuery(name = "Receipt.findByAmountPaid", query = "SELECT r FROM Receipt r WHERE r.amountPaid = :amountPaid"),
    @NamedQuery(name = "Receipt.findByPayMode", query = "SELECT r FROM Receipt r WHERE r.payMode = :payMode"),
    @NamedQuery(name = "Receipt.findByReturnPolicy", query = "SELECT r FROM Receipt r WHERE r.returnPolicy = :returnPolicy"),
    @NamedQuery(name = "Receipt.findByRemarks", query = "SELECT r FROM Receipt r WHERE r.remarks = :remarks"),
    @NamedQuery(name = "Receipt.findByEntryLog", query = "SELECT r FROM Receipt r WHERE r.entryLog = :entryLog"),
    @NamedQuery(name = "Receipt.findByModifiedDate", query = "SELECT r FROM Receipt r WHERE r.modifiedDate = :modifiedDate")})
public class Receipt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "receipt_id", nullable = false)
    private Integer receiptId;
    @Basic(optional = false)
    @Column(name = "receipt_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date receiptDate;
    @Basic(optional = false)
    @Column(name = "receipt_time", nullable = false)
    @Temporal(TemporalType.TIME)
    private Date receiptTime;
    @Basic(optional = false)
    @Column(name = "amount_due", nullable = false)
    private double amountDue;
    @Basic(optional = false)
    @Column(name = "amount_paid", nullable = false)
    private double amountPaid;
    @Basic(optional = false)
    @Column(name = "pay_mode", nullable = false, length = 25)
    private String payMode;
    @Column(name = "return_policy", length = 545)
    private String returnPolicy;
    @Column(name = "remarks", length = 500)
    private String remarks;
    @Column(name = "entry_log")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryLog;
    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @JoinColumn(name = "sales_id", referencedColumnName = "sales_id", nullable = false)
    @ManyToOne(optional = false)
    private Sales salesId;
    @JoinColumn(name = "users", referencedColumnName = "userid")
    @ManyToOne
    private Users users;

    public Receipt() {
    }

    public Receipt(Integer receiptId) {
        this.receiptId = receiptId;
    }

    public Receipt(Integer receiptId, Date receiptDate, Date receiptTime, double amountDue, double amountPaid, String payMode) {
        this.receiptId = receiptId;
        this.receiptDate = receiptDate;
        this.receiptTime = receiptTime;
        this.amountDue = amountDue;
        this.amountPaid = amountPaid;
        this.payMode = payMode;
    }

    public Integer getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Integer receiptId) {
        this.receiptId = receiptId;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public Date getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(Date receiptTime) {
        this.receiptTime = receiptTime;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public String getReturnPolicy() {
        return returnPolicy;
    }

    public void setReturnPolicy(String returnPolicy) {
        this.returnPolicy = returnPolicy;
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

    public Sales getSalesId() {
        return salesId;
    }

    public void setSalesId(Sales salesId) {
        this.salesId = salesId;
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
        hash += (receiptId != null ? receiptId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Receipt)) {
            return false;
        }
        Receipt other = (Receipt) object;
        if ((this.receiptId == null && other.receiptId != null) || (this.receiptId != null && !this.receiptId.equals(other.receiptId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bt.bitsmartmini.entity.Receipt[ receiptId=" + receiptId + " ]";
    }
    
}
