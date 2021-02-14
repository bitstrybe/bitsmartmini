
package bt.bitsmartmini.bl;

import java.util.List;
import javax.persistence.TypedQuery;
import bt.bitsmartmini.entity.SalesDetails;

/**
 *
 * @author JScare
 */
public class SalesDetailsBL extends DdsBL {

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
            SalesDetails c = em.find(SalesDetails.class, id);
            em.refresh(c);
            em.remove(c);
            em.getTransaction().commit();
            em.clear();
            return 1;
        } catch (IllegalArgumentException ex) {
            return 0;
        }
    }

    public List getStockinFromSalesDetails(String batchno) {
        TypedQuery<String> q = em.createQuery("SELECT s FROM SalesDetails s WHERE s.upc.itemDesc = :itemName", String.class);
        q.setParameter("itemName", batchno);
        q.setMaxResults(1);
        return q.getResultList();
    }

}
