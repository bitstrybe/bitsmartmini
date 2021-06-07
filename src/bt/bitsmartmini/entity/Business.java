/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.bitsmartmini.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Jscar
 */
@Entity
@Table(name = "business", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"license_key"})})
@NamedQueries({
    @NamedQuery(name = "Business.findAll", query = "SELECT b FROM Business b"),
    @NamedQuery(name = "Business.findByBName", query = "SELECT b FROM Business b WHERE b.bName = :bName"),
    @NamedQuery(name = "Business.findByBAddr", query = "SELECT b FROM Business b WHERE b.bAddr = :bAddr"),
    @NamedQuery(name = "Business.findByBMobile", query = "SELECT b FROM Business b WHERE b.bMobile = :bMobile"),
    @NamedQuery(name = "Business.findByBMobile1", query = "SELECT b FROM Business b WHERE b.bMobile1 = :bMobile1"),
    @NamedQuery(name = "Business.findByBEmail", query = "SELECT b FROM Business b WHERE b.bEmail = :bEmail"),
    @NamedQuery(name = "Business.findByBLogo", query = "SELECT b FROM Business b WHERE b.bLogo = :bLogo"),
    @NamedQuery(name = "Business.findByBCountry", query = "SELECT b FROM Business b WHERE b.bCountry = :bCountry"),
    @NamedQuery(name = "Business.findByBCurrency", query = "SELECT b FROM Business b WHERE b.bCurrency = :bCurrency")})
public class Business implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "b_name", nullable = false, length = 145)
    private String bName;
    @Basic(optional = false)
    @Column(name = "b_addr", nullable = false, length = 45)
    private String bAddr;
    @Basic(optional = false)
    @Column(name = "b_mobile", nullable = false, length = 45)
    private String bMobile;
    @Column(name = "b_mobile_1", length = 45)
    private String bMobile1;
    @Basic(optional = false)
    @Column(name = "b_email", nullable = false, length = 45)
    private String bEmail;
    @Column(name = "b_logo", length = 245)
    private String bLogo;
    @Basic(optional = false)
    @Column(name = "b_country", nullable = false, length = 245)
    private String bCountry;
    @Basic(optional = false)
    @Column(name = "b_currency", nullable = false, length = 45)
    private String bCurrency;
    @JoinColumn(name = "license_key", referencedColumnName = "license_id", nullable = false)
    @OneToOne(optional = false)
    private Licensing licenseKey;

    public Business() {
    }

    public Business(String bName) {
        this.bName = bName;
    }

    public Business(String bName, String bAddr, String bMobile, String bEmail, String bCountry, String bCurrency) {
        this.bName = bName;
        this.bAddr = bAddr;
        this.bMobile = bMobile;
        this.bEmail = bEmail;
        this.bCountry = bCountry;
        this.bCurrency = bCurrency;
    }

    public String getBName() {
        return bName;
    }

    public void setBName(String bName) {
        this.bName = bName;
    }

    public String getBAddr() {
        return bAddr;
    }

    public void setBAddr(String bAddr) {
        this.bAddr = bAddr;
    }

    public String getBMobile() {
        return bMobile;
    }

    public void setBMobile(String bMobile) {
        this.bMobile = bMobile;
    }

    public String getBMobile1() {
        return bMobile1;
    }

    public void setBMobile1(String bMobile1) {
        this.bMobile1 = bMobile1;
    }

    public String getBEmail() {
        return bEmail;
    }

    public void setBEmail(String bEmail) {
        this.bEmail = bEmail;
    }

    public String getBLogo() {
        return bLogo;
    }

    public void setBLogo(String bLogo) {
        this.bLogo = bLogo;
    }

    public String getBCountry() {
        return bCountry;
    }

    public void setBCountry(String bCountry) {
        this.bCountry = bCountry;
    }

    public String getBCurrency() {
        return bCurrency;
    }

    public void setBCurrency(String bCurrency) {
        this.bCurrency = bCurrency;
    }

    public Licensing getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(Licensing licenseKey) {
        this.licenseKey = licenseKey;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bName != null ? bName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Business)) {
            return false;
        }
        Business other = (Business) object;
        if ((this.bName == null && other.bName != null) || (this.bName != null && !this.bName.equals(other.bName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bt.bitsmartmini.entity.Business[ bName=" + bName + " ]";
    }
    
}
