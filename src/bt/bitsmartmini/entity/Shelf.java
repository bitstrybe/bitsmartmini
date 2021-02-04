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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author scarface
 */
@Entity
@Table(name = "shelf")
@NamedQueries({
    @NamedQuery(name = "Shelf.findAll", query = "SELECT s FROM Shelf s")
    , @NamedQuery(name = "Shelf.findByShelfDesc", query = "SELECT s FROM Shelf s WHERE s.shelfDesc = :shelfDesc")})
public class Shelf implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "shelf_desc", nullable = false, length = 250)
    private String shelfDesc;

    public Shelf() {
    }

    public Shelf(String shelfDesc) {
        this.shelfDesc = shelfDesc;
    }

    public String getShelfDesc() {
        return shelfDesc;
    }

    public void setShelfDesc(String shelfDesc) {
        this.shelfDesc = shelfDesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shelfDesc != null ? shelfDesc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shelf)) {
            return false;
        }
        Shelf other = (Shelf) object;
        if ((this.shelfDesc == null && other.shelfDesc != null) || (this.shelfDesc != null && !this.shelfDesc.equals(other.shelfDesc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bt.bitsmartmini.entity.Shelf[ shelfDesc=" + shelfDesc + " ]";
    }
    
}
