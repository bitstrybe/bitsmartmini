
package bt.bitsmartmini.bl;

import bt.bitsmartmini.entity.Brands;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author JScare
 */
public class BrandBL extends DdsBL{

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
        Brands c = em.find(Brands.class, id);
        em.remove(c);
        em.getTransaction().commit();
        return 1;
    }
    
    public List<Brands> getAllBrands(){
        TypedQuery<Brands> q = em.createNamedQuery("Brands.findAll",Brands.class);
        return q.getResultList();
    }
    
    public Brands getBrandsbyId(String Brands){
        try{
        TypedQuery<Brands> q = em.createQuery("SELECT m FROM Brands m WHERE m.brandName = :brand", Brands.class);
        q.setParameter("brand", Brands);
        return q.getSingleResult();
        }catch(NoResultException ex){
            return null;
            
        }
    }
    
    public List<Brands> searchAllBrands(String p) {
        TypedQuery<Brands> q = em.createQuery("SELECT s FROM Brands s WHERE s.brandName LIKE :p", Brands.class);
        q.setParameter("p", "%" + p + "%");
        return q.getResultList();
    }
    
}
