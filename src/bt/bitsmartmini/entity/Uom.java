/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.bitsmartmini.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jscar
 */
@Entity
@Table(name = "uom")
@NamedQueries({
    @NamedQuery(name = "Uom.findAll", query = "SELECT u FROM Uom u"),
    @NamedQuery(name = "Uom.findByUomDesc", query = "SELECT u FROM Uom u WHERE u.uomDesc = :uomDesc")})
public class Uom implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "uom_desc", nullable = false, length = 50)
    private String uomDesc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "measure1")
    private Collection<UomSet> uomSetCollection;
    @OneToMany(mappedBy = "measure2")
    private Collection<UomSet> uomSetCollection1;

    public Uom() {
    }

    public Uom(String uomDesc) {
        this.uomDesc = uomDesc;
    }

    public String getUomDesc() {
        return uomDesc;
    }

    public void setUomDesc(String uomDesc) {
        this.uomDesc = uomDesc;
    }

    public Collection<UomSet> getUomSetCollection() {
        return uomSetCollection;
    }

    public void setUomSetCollection(Collection<UomSet> uomSetCollection) {
        this.uomSetCollection = uomSetCollection;
    }

    public Collection<UomSet> getUomSetCollection1() {
        return uomSetCollection1;
    }

    public void setUomSetCollection1(Collection<UomSet> uomSetCollection1) {
        this.uomSetCollection1 = uomSetCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (uomDesc != null ? uomDesc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Uom)) {
            return false;
        }
        Uom other = (Uom) object;
        if ((this.uomDesc == null && other.uomDesc != null) || (this.uomDesc != null && !this.uomDesc.equals(other.uomDesc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bt.bitsmartmini.entity.Uom[ uomDesc=" + uomDesc + " ]";
    }
    
}
