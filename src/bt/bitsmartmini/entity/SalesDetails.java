/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.bitsmartmini.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
 * @author Jscar
 */
@Entity
@Table(name = "sales_details")
@NamedQueries({
    @NamedQuery(name = "SalesDetails.findAll", query = "SELECT s FROM SalesDetails s"),
    @NamedQuery(name = "SalesDetails.findBySalesDetailsId", query = "SELECT s FROM SalesDetails s WHERE s.salesDetailsId = :salesDetailsId"),
    @NamedQuery(name = "SalesDetails.findByMeasure", query = "SELECT s FROM SalesDetails s WHERE s.measure = :measure"),
    @NamedQuery(name = "SalesDetails.findByMunit", query = "SELECT s FROM SalesDetails s WHERE s.munit = :munit"),
    @NamedQuery(name = "SalesDetails.findByMqty", query = "SELECT s FROM SalesDetails s WHERE s.mqty = :mqty"),
    @NamedQuery(name = "SalesDetails.findByQuantity", query = "SELECT s FROM SalesDetails s WHERE s.quantity = :quantity"),
    @NamedQuery(name = "SalesDetails.findByCostPrice", query = "SELECT s FROM SalesDetails s WHERE s.costPrice = :costPrice"),
    @NamedQuery(name = "SalesDetails.findBySalesPrice", query = "SELECT s FROM SalesDetails s WHERE s.salesPrice = :salesPrice"),
    @NamedQuery(name = "SalesDetails.findByDiscount", query = "SELECT s FROM SalesDetails s WHERE s.discount = :discount"),
    @NamedQuery(name = "SalesDetails.findByEntryDate", query = "SELECT s FROM SalesDetails s WHERE s.entryDate = :entryDate"),
    @NamedQuery(name = "SalesDetails.findByModifiedDate", query = "SELECT s FROM SalesDetails s WHERE s.modifiedDate = :modifiedDate")})
public class SalesDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sales_details_id", nullable = false)
    private Integer salesDetailsId;
    @Column(name = "measure", length = 45)
    private String measure;
    @Column(name = "munit")
    private Integer munit;
    @Column(name = "mqty")
    private Integer mqty;
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
    @Column(name = "discount", nullable = false)
    private double discount;
    @Column(name = "entry_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryDate;
    @Column(name = "modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @JoinColumn(name = "upc", referencedColumnName = "upc", nullable = false)
    @ManyToOne(optional = false)
    private Items upc;
    @JoinColumn(name = "sale_id", referencedColumnName = "sales_id", nullable = false)
    @ManyToOne(optional = false)
    private Sales saleId;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "salesDetails")
    private RtdItem rtdItem;

    public SalesDetails() {
    }

    public SalesDetails(Integer salesDetailsId) {
        this.salesDetailsId = salesDetailsId;
    }

    public SalesDetails(Integer salesDetailsId, int quantity, double costPrice, double salesPrice, double discount) {
        this.salesDetailsId = salesDetailsId;
        this.quantity = quantity;
        this.costPrice = costPrice;
        this.salesPrice = salesPrice;
        this.discount = discount;
    }

    public Integer getSalesDetailsId() {
        return salesDetailsId;
    }

    public void setSalesDetailsId(Integer salesDetailsId) {
        this.salesDetailsId = salesDetailsId;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public Integer getMunit() {
        return munit;
    }

    public void setMunit(Integer munit) {
        this.munit = munit;
    }

    public Integer getMqty() {
        return mqty;
    }

    public void setMqty(Integer mqty) {
        this.mqty = mqty;
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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
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

    public Sales getSaleId() {
        return saleId;
    }

    public void setSaleId(Sales saleId) {
        this.saleId = saleId;
    }

    public RtdItem getRtdItem() {
        return rtdItem;
    }

    public void setRtdItem(RtdItem rtdItem) {
        this.rtdItem = rtdItem;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (salesDetailsId != null ? salesDetailsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalesDetails)) {
            return false;
        }
        SalesDetails other = (SalesDetails) object;
        if ((this.salesDetailsId == null && other.salesDetailsId != null) || (this.salesDetailsId != null && !this.salesDetailsId.equals(other.salesDetailsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bt.bitsmartmini.entity.SalesDetails[ salesDetailsId=" + salesDetailsId + " ]";
    }
    
}
