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
 * @author scarface
 */
@Entity
@Table(name = "forms")
@NamedQueries({
    @NamedQuery(name = "Forms.findAll", query = "SELECT f FROM Forms f")
    , @NamedQuery(name = "Forms.findByFormName", query = "SELECT f FROM Forms f WHERE f.formName = :formName")})
public class Forms implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "form_name", nullable = false, length = 250)
    private String formName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "form")
    private Collection<Items> itemsCollection;

    public Forms() {
    }

    public Forms(String formName) {
        this.formName = formName;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
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
        hash += (formName != null ? formName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Forms)) {
            return false;
        }
        Forms other = (Forms) object;
        if ((this.formName == null && other.formName != null) || (this.formName != null && !this.formName.equals(other.formName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bt.bitsmartmini.entity.Forms[ formName=" + formName + " ]";
    }
    
}
