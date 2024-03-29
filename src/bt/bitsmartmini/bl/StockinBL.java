package bt.bitsmartmini.bl;

import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;
import bt.bitsmartmini.entity.Stockin;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JScare
 */
public class StockinBL extends DdsBL {

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
            Stockin c = em.find(Stockin.class, id);
            em.remove(c);
            em.getTransaction().commit();
            return 1;
        } catch (IllegalArgumentException ex) {
            return 0;
        }
    }

    public List<Stockin> getAllStockinBarcode(String u, int Page) {
        TypedQuery q = em.createQuery("SELECT s FROM Stockin s Where s.upc.upc = :u", Stockin.class);
        q.setParameter("u", u);
        q.setMaxResults(Page);
        return q.getResultList();
    }

    public List<Stockin> getAllStockinByDesc(String u, int Page) {
        TypedQuery q = em.createQuery("SELECT s FROM Stockin s Where s.upc.itemDesc = :u", Stockin.class);
        q.setParameter("u", u);
        q.setMaxResults(Page);
        return q.getResultList();
    }

    public List<Stockin> searchAllStockin(String p) {
        TypedQuery<Stockin> q = em.createQuery("SELECT s FROM Stockin s WHERE s.upc.upc LIKE :p OR s.upc.itemDesc LIKE :p1", Stockin.class);
        q.setParameter("p", "%" + p + "%");
        q.setParameter("p1", "%" + p + "%");
        //q.setParameter("p2", "%" + p + "%");
        q.setMaxResults(20);
        return q.getResultList();
    }

    public List<Stockin> getAllStockinGroup() {
        TypedQuery<Stockin> q = em.createQuery("SELECT s FROM Stockin s GROUP BY s.items.itemDesc,s.stockinId", Stockin.class);
        q.setMaxResults(20);
        return q.getResultList();
    }

    public List<Stockin> getAllStockinGroupBatch() {
        TypedQuery<Stockin> q = em.createQuery("SELECT s FROM Stockin s", Stockin.class);
        return q.getResultList();
    }

//    public List<Stockin> searchAllStockinGroup(String p) {
//        TypedQuery<Stockin> q = em.createQuery("SELECT s FROM Stockin s WHERE s.items.itemName LIKE :p OR s.items.itemDesc LIKE :p1 GROUP BY s.items.itemName", Stockin.class);
//        q.setParameter("p", "%" + p + "%");
//        q.setParameter("p1", "%" + p + "%");
//        //q.setParameter("p2", "%" + p + "%");
//        return q.getResultList();
//    }
    public List<String> getAllStockinGroupCode() {
        TypedQuery<String> q = em.createQuery("SELECT CONCAT(s.upc.itemDesc,SUM(s.quantity))AS title FROM Stockin s GROUP BY s.upc.itemDesc", String.class);
        return q.getResultList();
    }

    public List getStockinbyItem(String stockin) {
        TypedQuery<Stockin> q = em.createQuery("SELECT s FROM Stockin s WHERE s.upc.itemDesc = :item", Stockin.class);
        q.setParameter("item", stockin);
        return q.getResultList();
    }

    public List<Stockin> getItemStockinByUpc(String u) {
        TypedQuery<Stockin> q = em.createQuery("SELECT i FROM Stockin i WHERE i.upc.upc = :u", Stockin.class);
        q.setParameter("u", u);
        return q.getResultList();
    }

    public List<Stockin> getItemStockinByBarcode(String u) {
        TypedQuery<Stockin> q = em.createQuery("SELECT i FROM Stockin i WHERE i.upc.upc = :u", Stockin.class);
        q.setParameter("u", u);
        return q.getResultList();
    }

//    public float getStockBalance(){
//        
//    }
    public long getStockInTotal(String u) {
        try {
            TypedQuery<Long> q = em.createQuery("SELECT SUM(s.quantity) FROM Stockin s WHERE s.upc.upc =:u", Long.class);
            q.setParameter("u", u);
            return q.getSingleResult();
        } catch (Exception ex) {
            return 0l;
        }
    }

    public long getStockInTotal(Date s, Date e) {
        try {
            TypedQuery<Long> q = em.createQuery("SELECT SUM(s.quantity) FROM Stockin s WHERE s.stockinDate BETWEEN :s AND :e", Long.class);
            q.setParameter("s", s);
            q.setParameter("e", e);
            return q.getSingleResult();
        } catch (Exception ex) {
            return 0l;
        }
    }

    public long getStockOutTotal(String u) {
        try {
            TypedQuery<Long> q = em.createQuery("SELECT SUM(s.quantity) FROM Stockout s WHERE s.upc.upc =:u", Long.class);
            q.setParameter("u", u);
            return q.getSingleResult();
        } catch (Exception ex) {
            return 0l;
        }
    }

    public long getStockOutTotal(Date s, Date e) {
        try {
            TypedQuery<Long> q = em.createQuery("SELECT SUM(s.quantity) FROM Stockout s WHERE s.stkDate BETWEEN :s AND :e ", Long.class);
            q.setParameter("s", s);
            q.setParameter("e", e);
            return q.getSingleResult();
        } catch (Exception ex) {
            return 0l;
        }
    }

    public long getStockInTotalbyItem(String items) {
        try {
            TypedQuery<Long> q = em.createQuery("SELECT SUM(s.quantity) FROM Stockin s WHERE s.upc.itemDesc =:items", Long.class);
            q.setParameter("items", items);
            return q.getSingleResult();
        } catch (Exception ex) {
            return 0l;
        }
    }

    public List getStockOutTotal() {
        TypedQuery<Float> q = em.createQuery("SELECT SUM(s.quantity) FROM Stockin s GROUP BY s.upc.upc", Float.class);
        return q.getResultList();
    }

    public long getTotalReturns(String u) {
        try {
            TypedQuery<Long> q = em.createQuery("SELECT SUM(s.rtdQty) FROM RtdItem s WHERE s.salesDetails.upc.upc = :u", Long.class);
            q.setParameter("u", u);
            return q.getSingleResult();
        } catch (Exception ex) {
            return 0l;
        }
    }

    public long getTotalReturns(Date s, Date e) {
        try {
            TypedQuery<Long> q = em.createQuery("SELECT SUM(s.rtdQty) FROM RtdItem s WHERE s.rtdDate BETWEEN :s AND :e", Long.class);
            q.setParameter("s", s);
            q.setParameter("e", e);
            return q.getSingleResult();
        } catch (Exception ex) {
            return 0l;
        }
    }

    public long getStockBalance(String b) {
        SalesBL sb = new SalesBL();
        long stockin = getStockInTotal(b);
        long stockout = getStockOutTotal(b);
        long returns = getTotalReturns(b);
        long sales = sb.getSalesTotal(b);
        long stocks = (stockin + returns) - (stockout + sales);
        //long stockafreturns = stocks + retruns;
        return stocks;
    }

    public long getStockBalance(Date s, Date e) {
        SalesBL sb = new SalesBL();
        long stockin = getStockInTotal(s, e);
        long stockout = getStockOutTotal(s, e);
        long returns = getTotalReturns(s, e);
        long sales = sb.getSalesTotal(s, e);
        long stocks = (stockin + returns) - (stockout + sales);
        //long stockafreturns = stocks + retruns;
        return stocks;
    }

//    public String getBachnobyItemname(String itemname) {
//        TypedQuery<String> q = em.createQuery("SELECT s.batchNo FROM Stockin s WHERE s.items.itemDesc = :itemDesc", String.class);
//        q.setParameter("itemDesc", itemname);
//        return q.getSingleResult();
//    }

    public Long getStockinCount() {
        try {
            TypedQuery<Long> q = em.createQuery("SELECT COUNT(s) FROM Stockin s ORDER BY s.stockinId DESC", Long.class);
            q.setMaxResults(1);
            return q.getSingleResult();
        } catch (Exception ex) {
            Logger.getLogger(StockinBL.class.getName()).log(Level.SEVERE, "Error Updating Security DB", ex);
            return 0L;
        }
    }
    
    public Long getStockinCountByUPC(String u) {
        try {
            TypedQuery<Long> q = em.createQuery("SELECT COUNT(s) FROM Stockin s WHERE s.upc = :u", Long.class);
            q.setParameter("u", u);
            q.setMaxResults(1);
            return q.getSingleResult();
        } catch (Exception ex) {
            Logger.getLogger(StockinBL.class.getName()).log(Level.SEVERE, "Error Updating Security DB", ex);
            return 0L;
        }
    }
    
    public Long getStockinMax() {
        try {
            TypedQuery<Long> q = em.createQuery("SELECT MAX(s.stockinId) FROM Stockin s ORDER BY s.stockinId DESC", Long.class);
            q.setMaxResults(1);
            return q.getSingleResult();
        } catch (Exception ex) {
            Logger.getLogger(StockinBL.class.getName()).log(Level.SEVERE, "Error Updating Security DB", ex);
            return 0L;
        }
    }

//    public List<Stockin> getStockinReOrder() {
//        TypedQuery<Stockin> q = em.createQuery("SELECT s FROM Stockin s", Stockin.class);
////        q.setParameter("bal", bal);
//        return q.getResultList();
//
//    }
    public List<Stockin> getStockreorderBalnce(long bal) {
        TypedQuery<Stockin> q = em.createQuery("SELECT s FROM Stockin s WHERE s.items.rol = :bal GROUP BY s.batchNo", Stockin.class);
        q.setParameter("bal", bal);
        return q.getResultList();
    }

//    public String getSkockingbyItems(String fullname) {
//        try {
//            TypedQuery<String> q = em.createQuery("SELECT s.items.itemCodeFullname FROM Stockin s WHERE s.items.itemCodeFullname = :fullname", String.class);
//            q.setParameter("fullname", fullname);
//            return q.getSingleResult();
//        } catch (Exception ex) {
//            return null;
//        }
//
//    }
    public List<Stockin> getTwoWeekToExpiry(Date expiry, Date date1) {
        TypedQuery q = em.createQuery("SELECT s FROM Stockin s WHERE s.expiryDate BETWEEN :expiry AND :date1", Stockin.class);
        q.setParameter("expiry", expiry);
        q.setParameter("date1", date1);
        return q.getResultList();
    }

    public List getItemsListFromStockin(String upc) {
        try {
            TypedQuery<String> q = em.createQuery("SELECT r FROM Stockin r WHERE r.upc.upc = :upc", String.class);
            q.setParameter("upc", upc);
            q.setMaxResults(1);
            return q.getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

// public Stockin getFirstStockbyDate() {
//        TypedQuery<Stockin> q = em.createQuery("SELECT s FROM Stockin s WHERE s.stockinDate GROUP BY desc", Stockin.class);
//        q.setMaxResults(1);
//        return q.getSingleResult();
//    }
}
