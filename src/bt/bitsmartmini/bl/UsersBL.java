
package bt.bitsmartmini.bl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.TypedQuery;
import bt.bitsmartmini.entity.Users;

/**
 *
 * @author JScare
 */
public class UsersBL extends DdsBL {

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
            Users c = em.find(Users.class, id);
            em.remove(c);
            em.getTransaction().commit();
            return 1;
        } catch (IllegalArgumentException ex) {
            return 0;
        }
    }

    public List<Users> getAllUsers() {
        try {
            TypedQuery<Users> q = em.createNamedQuery("Users.findAll", Users.class);
            return q.getResultList();
        } catch (Exception ex) {
            Logger.getLogger(UsersBL.class.getName()).log(Level.SEVERE, null, ex);
            return null;

        }
    }
    
}
