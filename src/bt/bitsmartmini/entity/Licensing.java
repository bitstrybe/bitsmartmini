/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.bitsmartmini.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author Jscar
 */
@Entity
@Table(name = "licensing")
@NamedQueries({
    @NamedQuery(name = "Licensing.findAll", query = "SELECT l FROM Licensing l"),
    @NamedQuery(name = "Licensing.findByLicenseId", query = "SELECT l FROM Licensing l WHERE l.licenseId = :licenseId"),
    @NamedQuery(name = "Licensing.findByLicenseKey", query = "SELECT l FROM Licensing l WHERE l.licenseKey = :licenseKey")})
public class Licensing implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "license_id", nullable = false)
    private Integer licenseId;
    @Basic(optional = false)
    @Column(name = "license_key", nullable = false, length = 2000)
    private String licenseKey;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "licenseKey")
    private Business business;

    public Licensing() {
    }

    public Licensing(Integer licenseId) {
        this.licenseId = licenseId;
    }

    public Licensing(Integer licenseId, String licenseKey) {
        this.licenseId = licenseId;
        this.licenseKey = licenseKey;
    }

    public Integer getLicenseId() {
        return licenseId;
    }

    public void setLicenseId(Integer licenseId) {
        this.licenseId = licenseId;
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (licenseId != null ? licenseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Licensing)) {
            return false;
        }
        Licensing other = (Licensing) object;
        if ((this.licenseId == null && other.licenseId != null) || (this.licenseId != null && !this.licenseId.equals(other.licenseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bt.bitsmartmini.entity.Licensing[ licenseId=" + licenseId + " ]";
    }
    
}
