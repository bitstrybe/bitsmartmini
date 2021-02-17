package bt.bitsmartmini.bl;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.RollbackException;

/**
 *
 * @author JScare
 */
public class InsertUpdateBL extends DdsBL {

    @Override
    public int insertData(Object object) {
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
            em.refresh(object);
            em.getFlushMode();
            return 1;
        } catch (RollbackException ex) {
            Logger.getLogger(InsertUpdateBL.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    @Override
    public int updateData(Object object) {
        try {
            em.getTransaction().begin();
            em.merge(object);
            em.getTransaction().commit();
            return 1;
        } catch (RollbackException ex) {
            Logger.getLogger(InsertUpdateBL.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public int insertUpdate(Object o, Object a) {
        try {
            em.getTransaction().begin();
            em.merge(o);
            em.merge(a);
            em.getTransaction().commit();
            //em.refresh(o);
            //em.refresh(a);
            return 1;
        } catch (Exception ex) {
            return 0;
        }
    }

    @Override
    public int removeData(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
