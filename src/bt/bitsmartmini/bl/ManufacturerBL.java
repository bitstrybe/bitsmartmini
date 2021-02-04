
package bt.bitsmartmini.bl;

import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import bt.bitsmartmini.entity.Manufacturer;

/**
 *
 * @author JScare
 */
public class ManufacturerBL extends DdsBL{

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
        Manufacturer c = em.find(Manufacturer.class, id);
        em.remove(c);
        em.getTransaction().commit();
        return 1;
    }
    
    public List<Manufacturer> getAllManufacturer(){
        TypedQuery<Manufacturer> q = em.createNamedQuery("Manufacturer.findAll",Manufacturer.class);
        return q.getResultList();
    }
    
    public String getManufacturerbyId(String Manufacturer){
        try{
        TypedQuery<String> q = em.createQuery("SELECT m.manufacturer FROM Manufacturer m WHERE m.manufacturer = :manufacturer", String.class);
        q.setParameter("manufacturer", Manufacturer);
        return q.getSingleResult();
        }catch(NoResultException ex){
            return null;
            
        }
    }
    
    public List<Manufacturer> searchAllManufacturer(String p) {
        TypedQuery<Manufacturer> q = em.createQuery("SELECT s FROM Manufacturer s WHERE s.manufacturer LIKE :p", Manufacturer.class);
        q.setParameter("p", "%" + p + "%");
        return q.getResultList();
    }
    
}
