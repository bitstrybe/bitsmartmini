package bt.bitsmartmini.bl;

import bt.bitsmartmini.entity.Uom;
import bt.bitsmartmini.entity.UomSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.TypedQuery;

/**
 *
 * @author JScare
 */
public class UomBL extends DdsBL {

    @Override
    public int insertData(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateData(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public int removeData(Object id) {
//        em.getTransaction().begin();
//        UomDef i = em.find(UomDef.class, id);
//        em.remove(i);
//        em.getTransaction().commit();
//        return 1;
//    }
//    
    public List getUoms() {
        try {
            TypedQuery<Uom> q = em.createQuery("SELECT u FROM Uom u ", Uom.class);
            //q.setParameter("itemCode", val);
            return q.getResultList();
        } catch (Exception ex) {
            Logger.getLogger(UomBL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List getUoms(String s) {
        try {
            TypedQuery<UomSet> q = em.createQuery("SELECT u FROM Uom u WHERE u.uomDesc LIKE :param ", UomSet.class);
            q.setParameter("param", s);
            return q.getResultList();
        } catch (Exception ex) {
            Logger.getLogger(UomBL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List getUomSets() {
        try {
            TypedQuery<UomSet> q = em.createQuery("SELECT u FROM UomSet u", UomSet.class);
//           
            return q.getResultList();
        } catch (Exception ex) {
            Logger.getLogger(UomBL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public UomSet getUomSets(String s) {
        try {
            TypedQuery<UomSet> q = em.createQuery("SELECT u FROM UomSet u WHERE u.uomSetCode = :s ", UomSet.class);
            q.setParameter("s", s);
            return q.getSingleResult();
        } catch (Exception ex) {
            Logger.getLogger(UomBL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    

    public List getUomSet(String s) {
        try {
            TypedQuery<UomSet> q = em.createQuery("SELECT u FROM UomSet u WHERE u.uomSetCode LIKE :param OR u.measure1.uomDesc LIKE :param1 OR u.measure2.uomDesc LIKE :param2  OR u.unit1 LIKE :param3 OR u.unit2 LIKE :param4 ", UomSet.class);
            q.setParameter("param", s);
            q.setParameter("param1", s);
            q.setParameter("param2", s);
            q.setParameter("param3", s);
            q.setParameter("param4", s);
            return q.getResultList();
        } catch (Exception ex) {
            Logger.getLogger(UomBL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public int removeData(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
