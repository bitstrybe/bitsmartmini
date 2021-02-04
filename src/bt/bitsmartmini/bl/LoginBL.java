package bt.bitsmartmini.bl;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import bt.bitsmartmini.entity.Userlogs;
import bt.bitsmartmini.entity.Users;
import bt.bitsmartmini.ui.MainAppController;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author JScare
 */
public class LoginBL extends DdsBL {
    
    public static final String DKEY = "LXES3KURITICHECKSALT";

    @Override
    public int insertData(Object object) {
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
            em.refresh(object);
            return 1;
        } catch (Exception ex) {
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
        } catch (Exception ex) {
            return 0;
        }
    }

    @Override
    public int removeData(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Users validateUser(Users user) {
        try {
            TypedQuery<Users> q = em.createQuery("SELECT u FROM Users u WHERE u.username =:username AND u.pwd =:password", Users.class);
            q.setParameter("username", user.getUsername());
            q.setParameter("password", DigestUtils.md5Hex(new StringBuilder().append(user.getPwd()).append(LoginBL.DKEY).toString()));
            return q.getSingleResult();
        } catch (NoResultException | NullPointerException ex) {
            em.clear();
            return new Users();
        }
    }

    public Userlogs getLogById(int id) {
        Userlogs l = em.find(Userlogs.class, id);
        return l;
    }
}
