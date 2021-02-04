
package bt.bitsmartmini.bl;

import java.util.List;
import javax.persistence.TypedQuery;
import bt.bitsmartmini.entity.Stockout;

/**
 *
 * @author JScare
 */
public class StockoutBL extends DdsBL{

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
            Stockout c = em.find(Stockout.class, id);
            em.remove(c);
            em.getTransaction().commit();
            return 1;
        } catch (IllegalArgumentException ex) {
            return 0;
        }
    }
    
    public List<Stockout> getAllStockout(){
        TypedQuery<Stockout> q = em.createNamedQuery("Stockout.findAll",Stockout.class);
        return q.getResultList();
    }
    public List<Stockout> getAllStockoutbyItems(String itemDesc, int page){
        TypedQuery<Stockout> q = em.createQuery("SELECT s FROM Stockout s WHERE s.items.itemDesc = :itemDesc",Stockout.class);
        q.setParameter("itemDesc", itemDesc);
        q.setMaxResults(page);
        return q.getResultList();
    }
     public List<Stockout> searchAllStockout(String p) {
        TypedQuery<Stockout> q = em.createQuery("SELECT s FROM Stockout s WHERE s.batchNo LIKE :p OR s.items.itemDesc LIKE :p1 OR s.items.itemName LIKE :p2", Stockout.class);
        q.setParameter("p", "%" + p + "%");
        q.setParameter("p1", "%" + p + "%");
        q.setParameter("p2", "%" + p + "%");
        q.setMaxResults(20);
        return q.getResultList();
    }
    
    
    public Long getTotalStockoutbyItemDesc(String itemDesc){
        try{
        TypedQuery<Long> q = em.createQuery("SELECT SUM(s.quantity) FROM Stockout s WHERE s.items.itemDesc = :itemDesc", Long.class);
        q.setParameter("itemDesc", itemDesc);
        return q.getSingleResult();
        }catch(NullPointerException ex){
            return 0l;
        }
    }
    
    
}
