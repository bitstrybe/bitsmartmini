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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    @NamedQuery(name = "UomSet.findByUnit1", query = "SELECT u FROM UomSet u WHERE u.unit1 = :unit1"),
    @NamedQuery(name = "UomSet.findByUnit2", query = "SELECT u FROM UomSet u WHERE u.unit2 = :unit2")})
public class UomSet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uom_set_code", nullable = false, length = 45)
    private String uomSetCode;
    @Basic(optional = false)
    @Column(name = "unit_1", nullable = false)
    private int unit1;
    @Column(name = "unit_2")
    private Integer unit2;
    @JoinColumn(name = "measure_1", referencedColumnName = "uom_desc", nullable = false)
    @ManyToOne(optional = false)
    private Uom measure1;
    @JoinColumn(name = "measure_2", referencedColumnName = "uom_desc")
    @ManyToOne
    private Uom measure2;
    @OneToMany(mappedBy = "uomset")
    private Collection<Items> itemsCollection;

    public UomSet() {
    }

    public UomSet(String uomSetCode) {
        this.uomSetCode = uomSetCode;
    }

    public UomSet(String uomSetCode, int unit1) {
        this.uomSetCode = uomSetCode;
        this.unit1 = unit1;
    }

    public String getUomSetCode() {
        return uomSetCode;
    }

    public void setUomSetCode(String uomSetCode) {
        this.uomSetCode = uomSetCode;
    }

    public int getUnit1() {
        return unit1;
    }

    public void setUnit1(int unit1) {
        this.unit1 = unit1;
    }

    public Integer getUnit2() {
        return unit2;
    }

    public void setUnit2(Integer unit2) {
        this.unit2 = unit2;
    }

    public Uom getMeasure1() {
        return measure1;
    }

    public void setMeasure1(Uom measure1) {
        this.measure1 = measure1;
    }

    public Uom getMeasure2() {
        return measure2;
    }

    public void setMeasure2(Uom measure2) {
        this.measure2 = measure2;
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
