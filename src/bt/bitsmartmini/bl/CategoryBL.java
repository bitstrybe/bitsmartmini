
package bt.bitsmartmini.bl;

import bt.bitsmartmini.entity.Category;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author JScare
 */
public class CategoryBL extends DdsBL {

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
            Category c = em.find(Category.class, id);
            em.remove(c);
            em.getTransaction().commit();
            return 1;
        } catch (IllegalArgumentException ex) {
            return 0;
        }
    }

    public List<Category> getAllCategory() {
        TypedQuery<Category> q = em.createNamedQuery("Category.findAll", Category.class);
        return q.getResultList();
    }

    public String getCategoryById(String categoryName) {
        try {
            TypedQuery<String> q = em.createQuery("SELECT s.formName FROM Category s WHERE s.formName = :formName", String.class);
            q.setParameter("formName", categoryName);
            return q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public List<Category> searchAllCategory(String p) {
        TypedQuery<Category> q = em.createQuery("SELECT s FROM Category s WHERE s.formName LIKE :p", Category.class);
        q.setParameter("p", "%" + p + "%");
        return q.getResultList();
    }

}
