
package bt.bitsmartmini.bl;

import javax.persistence.TypedQuery;
import bt.bitsmartmini.entity.ItemsPrice;

/**
 *
 * @author JScare
 */
public class ItemspriceBL extends DdsBL {

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
        em.getTransaction().begin();
        ItemsPrice i = em.find(ItemsPrice.class, id);
        em.remove(i);
        em.getTransaction().commit();
        return 1;
    }
    
    public ItemsPrice getItemspricebyItemName(String itemDesc){
        //System.out.println(itemDesc);
        TypedQuery<ItemsPrice> q = em.createQuery("SELECT s FROM ItemsPrice s Where s.items.itemDesc = :itemDesc", ItemsPrice.class);
        q.setParameter("itemDesc", itemDesc);
        return q.getSingleResult();
    }

}
