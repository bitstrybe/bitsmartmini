
package bt.bitsmartmini.bl;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import bt.bitsmartmini.entity.Forms;

/**
 *
 * @author JScare
 */
public class FormsBL extends DdsBL {

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
            Forms c = em.find(Forms.class, id);
            em.remove(c);
            em.getTransaction().commit();
            return 1;
        } catch (IllegalArgumentException ex) {
            return 0;
        }
    }

    public List<Forms> getAllCategory() {
        TypedQuery<Forms> q = em.createNamedQuery("Forms.findAll", Forms.class);
        return q.getResultList();
    }

    public String getCategoryById(String categoryName) {
        try {
            TypedQuery<String> q = em.createQuery("SELECT s.formName FROM Forms s WHERE s.formName = :formName", String.class);
            q.setParameter("formName", categoryName);
            return q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public List<Forms> searchAllCategory(String p) {
        TypedQuery<Forms> q = em.createQuery("SELECT s FROM Forms s WHERE s.formName LIKE :p", Forms.class);
        q.setParameter("p", "%" + p + "%");
        return q.getResultList();
    }

}
