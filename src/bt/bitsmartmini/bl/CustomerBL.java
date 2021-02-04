
package bt.bitsmartmini.bl;

import java.util.List;
import javax.persistence.TypedQuery;
import bt.bitsmartmini.entity.Customers;

/**
 *
 * @author JScare
 */
public class CustomerBL extends DdsBL{

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
            Customers c = em.find(Customers.class, id);
            em.remove(c);
            em.getTransaction().commit();
            return 1;
        } catch (IllegalArgumentException ex) {
            return 0;
        }
    }
    
    public List<Customers> getAllCustomers(){
        TypedQuery<Customers> q = em.createNamedQuery("Customers.findAll", Customers.class);
        return q.getResultList();
    }
    
    public List<Customers> getCustomers(int c){
        TypedQuery<Customers> q = em.createQuery("SELECT c FROM Customers c WHERE c.customerId = :c", Customers.class);
        q.setParameter("c", c);
        return q.getResultList();
    }
    
    public Customers getCustomersBySales(int s){
        TypedQuery<Customers> q = em.createQuery("SELECT s.customers FROM Sales s WHERE s.salesId = :s", Customers.class);
        q.setParameter("s", s);
        return q.getSingleResult();
    }
}
