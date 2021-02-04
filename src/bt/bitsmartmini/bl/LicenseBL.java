/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.bitsmartmini.bl;

import javax.persistence.TypedQuery;
import bt.bitsmartmini.entity.Licensing;

/**
 *
 * @author JScare
 */
public class LicenseBL extends DdsBL {

    @Override
    public int insertData(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateData(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int removeData(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Licensing findLicenses() {
        try {
            TypedQuery<Licensing> q = em.createQuery("SELECT l FROM Licensing l", Licensing.class);
            return q.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
    

    }
