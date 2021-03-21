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
@Table(name = "refund_policy")
@NamedQueries({
    @NamedQuery(name = "RefundPolicy.findAll", query = "SELECT r FROM RefundPolicy r"),
    @NamedQuery(name = "RefundPolicy.findByRefundPeriod", query = "SELECT r FROM RefundPolicy r WHERE r.refundPeriod = :refundPeriod"),
    @NamedQuery(name = "RefundPolicy.findByRefundPeriodVal", query = "SELECT r FROM RefundPolicy r WHERE r.refundPeriodVal = :refundPeriodVal"),
    @NamedQuery(name = "RefundPolicy.findByRefundCustomMsg", query = "SELECT r FROM RefundPolicy r WHERE r.refundCustomMsg = :refundCustomMsg"),
    @NamedQuery(name = "RefundPolicy.findByRefundElog", query = "SELECT r FROM RefundPolicy r WHERE r.refundElog = :refundElog")})
public class RefundPolicy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "refund_period", nullable = false, length = 45)
    private String refundPeriod;
    @Basic(optional = false)
    @Column(name = "refund_period_val", nullable = false)
    private int refundPeriodVal;
    @Basic(optional = false)
    @Column(name = "refund_custom_msg", nullable = false, length = 1045)
    private String refundCustomMsg;
    @Basic(optional = false)
    @Column(name = "refund_elog", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date refundElog;
    @JoinColumn(name = "user", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
    private Users user;

    public RefundPolicy() {
    }

    public RefundPolicy(String refundPeriod) {
        this.refundPeriod = refundPeriod;
    }

    public RefundPolicy(String refundPeriod, int refundPeriodVal, String refundCustomMsg, Date refundElog) {
        this.refundPeriod = refundPeriod;
        this.refundPeriodVal = refundPeriodVal;
        this.refundCustomMsg = refundCustomMsg;
        this.refundElog = refundElog;
    }

    public String getRefundPeriod() {
        return refundPeriod;
    }

    public void setRefundPeriod(String refundPeriod) {
        this.refundPeriod = refundPeriod;
    }

    public int getRefundPeriodVal() {
        return refundPeriodVal;
    }

    public void setRefundPeriodVal(int refundPeriodVal) {
        this.refundPeriodVal = refundPeriodVal;
    }

    public String getRefundCustomMsg() {
        return refundCustomMsg;
    }

    public void setRefundCustomMsg(String refundCustomMsg) {
        this.refundCustomMsg = refundCustomMsg;
    }

    public Date getRefundElog() {
        return refundElog;
    }

    public void setRefundElog(Date refundElog) {
        this.refundElog = refundElog;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (refundPeriod != null ? refundPeriod.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RefundPolicy)) {
            return false;
        }
        RefundPolicy other = (RefundPolicy) object;
        if ((this.refundPeriod == null && other.refundPeriod != null) || (this.refundPeriod != null && !this.refundPeriod.equals(other.refundPeriod))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bt.bitsmartmini.entity.RefundPolicy[ refundPeriod=" + refundPeriod + " ]";
    }
    
}
