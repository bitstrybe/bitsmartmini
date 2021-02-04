package bt.bitsmartmini.bl;

import javax.persistence.TypedQuery;
import bt.bitsmartmini.entity.Business;

/**
 *
 * @author scarface
 */
public class BusinessBL extends DdsBL {

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
        try {
            em.getTransaction().begin();
            Business c = em.find(Business.class, id);
            em.remove(c);
            em.getTransaction().commit();
            return 1;
        } catch (IllegalArgumentException ex) {
            return 0;
        }
    }

    public Business findBusiness() {
        try{
        TypedQuery<Business> q = em.createQuery("SELECT b FROM Business b", Business.class);
        return q.getSingleResult();
        }catch(Exception ex){
            return null;
        }

    }
}
