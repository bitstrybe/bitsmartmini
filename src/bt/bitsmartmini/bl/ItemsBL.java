package bt.bitsmartmini.bl;

import java.util.List;
import javax.persistence.TypedQuery;
import bt.bitsmartmini.entity.Items;

/**
 *
 * @author JScare
 */
public class ItemsBL extends DdsBL {

    @Override
    public int insertData(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateData(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int removeData(Object id) {
        em.getTransaction().begin();
        Items i = em.find(Items.class, id);
        em.remove(i);
        em.getTransaction().commit();
        return 1;
    }

    public List<Items> getItemsPerPage(int pages) {
        TypedQuery<Items> q = em.createQuery("SELECT i FROM Items i", Items.class);
        q.setMaxResults(pages);
        return q.getResultList();
    }
    
    public List<Items> getAllItems() {
        TypedQuery<Items> q = em.createQuery("SELECT i FROM Items i", Items.class);
        return q.getResultList();
    }
    

    public List<String> getAllItemsName() {
        TypedQuery<String> q = em.createQuery("SELECT i.itemDesc FROM Items i", String.class);
        return q.getResultList();
    }

    public List<Items> searchAllItems(String p) {
        TypedQuery<Items> q = em.createQuery("SELECT s FROM Items s WHERE s.upc LIKE :p OR s.itemDesc LIKE :p1 ", Items.class);
        q.setParameter("p", "%" + p + "%");
        q.setParameter("p1", "%" + p + "%");
        //q.setParameter("p2", "%" + p + "%");
        q.setMaxResults(10);
        return q.getResultList();
    }

    public List<String> searchItemsNames(String param) {
        TypedQuery<String> q = em.createQuery("SELECT i.itemDesc FROM Items i WHERE i.upc LIKE :p1 OR i.itemDesc LIKE :p2", String.class);
        q.setParameter("p1", "%" + param + "%");
        q.setParameter("p2", "%" + param + "%");
        return q.getResultList();
    }

    public List getItemsFromForm(String catid) {
        try {
            TypedQuery<String> q = em.createQuery("SELECT r FROM Items r WHERE r.category.categoryName = :catid", String.class);
            q.setParameter("catid", catid);
//        System.out.println(q.getResultList());
            q.setMaxResults(1);
            return q.getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public List findItemsbyBrand(String b) {
        try {
            TypedQuery<Items> q = em.createQuery("SELECT r FROM Items r WHERE r.brand.brandName = :b", Items.class);
            q.setParameter("b", b);
//        System.out.println(q.getResultList());
            q.setMaxResults(1);
            return q.getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public Items getImageItembyCode(String val) {
        TypedQuery<Items> q = em.createQuery("SELECT i FROM Items i WHERE i.itemDesc = :itemDesc", Items.class);
        q.setParameter("itemDesc", val);
        return q.getSingleResult();
    }
    
//    public ItemsPrice getItemsPriceByItemDesc(String itemdesc){
//        TypedQuery<ItemsPrice> q = em.createQuery("SELECT i FROM ItemsPrice i WHERE i.itemDesc = :itemDesc", ItemsPrice.class);
//        q.setParameter("itemDesc", itemdesc);
//        return q.getSingleResult();
//    }

}
