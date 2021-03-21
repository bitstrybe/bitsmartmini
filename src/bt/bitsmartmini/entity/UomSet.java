/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.bitsmartmini.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author scarface
 */
@Entity
@Table(name = "uom_set")
@NamedQueries({
    @NamedQuery(name = "UomSet.findAll", query = "SELECT u FROM UomSet u"),
    @NamedQuery(name = "UomSet.findByUomSetCode", query = "SELECT u FROM UomSet u WHERE u.uomSetCode = :uomSetCode"),
    @NamedQuery(name = "UomSet.findByUnit", query = "SELECT u FROM UomSet u WHERE u.unit = :unit"),
    @NamedQuery(name = "UomSet.findByMeasure", query = "SELECT u FROM UomSet u WHERE u.measure = :measure")})
public class UomSet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uom_set_code", nullable = false, length = 45)
    private String uomSetCode;
    @Basic(optional = false)
    @Column(name = "unit", nullable = false)
    private int unit;
    @Basic(optional = false)
    @Column(name = "measure", nullable = false)
    private int measure;
    @OneToMany(mappedBy = "uomset")
    private Collection<Items> itemsCollection;

    public UomSet() {
    }

    public UomSet(String uomSetCode) {
        this.uomSetCode = uomSetCode;
    }

    public UomSet(String uomSetCode, int unit, int measure) {
        this.uomSetCode = uomSetCode;
        this.unit = unit;
        this.measure = measure;
    }

    public String getUomSetCode() {
        return uomSetCode;
    }

    public void setUomSetCode(String uomSetCode) {
        this.uomSetCode = uomSetCode;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getMeasure() {
        return measure;
    }

    public void setMeasure(int measure) {
        this.measure = measure;
    }

    public Collection<Items> getItemsCollection() {
        return itemsCollection;
    }

    public void setItemsCollection(Collection<Items> itemsCollection) {
        this.itemsCollection = itemsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uomSetCode != null ? uomSetCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UomSet)) {
            return false;
        }
        UomSet other = (UomSet) object;
        if ((this.uomSetCode == null && other.uomSetCode != null) || (this.uomSetCode != null && !this.uomSetCode.equals(other.uomSetCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bt.bitsmartmini.entity.UomSet[ uomSetCode=" + uomSetCode + " ]";
    }
    
}
