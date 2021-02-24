package bt.bitsmartmini.bl;

import bt.bitsmartmini.entity.RefundPolicy;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import bt.bitsmartmini.entity.RtdItem;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.RollbackException;

/**
 *
 * @author JScare
 */
public class ReturnBL extends DdsBL {

    @Override
    public int insertData(Object object) {
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
            em.refresh(object);
            em.getFlushMode();
            return 1;
        } catch (RollbackException ex) {
            Logger.getLogger(InsertUpdateBL.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    @Override
    public int updateData(Object object) {
        em.getTransaction().begin();
        em.merge(object);
        em.getTransaction().commit();
        return 1;
    }

    public int insertUpdate(Object o, Object a) {
        try {
            em.getTransaction().begin();
            em.merge(o);
            em.merge(a);
            em.getTransaction().commit();
            //em.refresh(o);
            //em.refresh(a);
            return 1;
        } catch (Exception ex) {
            return 0;
        }
    }

    @Override
    public int removeData(Object id) {
        try {
            em.getTransaction().begin();
            RtdItem c = em.find(RtdItem.class, id);
            em.remove(c);
            em.getTransaction().commit();
            return 1;
        } catch (IllegalArgumentException ex) {
            return 0;
        }
    }

    public RtdItem getRtdItemByID(int i) {
        try {
            TypedQuery<RtdItem> q = em.createQuery("SELECT r FROM RtdItem r WHERE r.salescode = :i ", RtdItem.class);
            q.setParameter("i", i);
            q.setMaxResults(1);
            return q.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    public RtdItem getRtdItemByID(int i, Date s, Date e) {
        try {
            TypedQuery<RtdItem> q = em.createQuery("SELECT r FROM RtdItem r WHERE r.salescode = :i AND r.rtdDate BETWEEN :s AND :e", RtdItem.class);
            q.setParameter("i", i);
            q.setParameter("s", s);
            q.setParameter("e", e);
            q.setMaxResults(1);
            return q.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    public RtdItem getRtdItemBySalesID(int i, Date s, Date e) {
        try {
            TypedQuery<RtdItem> q = em.createQuery("SELECT r FROM RtdItem r WHERE r.salesDetails.saleId.salesId = :i AND r.rtdDate BETWEEN :s AND :e", RtdItem.class);
            q.setParameter("i", i);
            q.setParameter("s", s);
            q.setParameter("e", e);
            q.setMaxResults(1);
            return q.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    public List<RtdItem> getRtdBySalesCode(Integer salecode) {
        try {
            TypedQuery<RtdItem> q = em.createQuery("SELECT s FROM RtdItem s WHERE s.salesDetails.saleId.salesId = :salecode", RtdItem.class);
            q.setParameter("salecode", salecode);
            return q.getResultList();
        } catch (NullPointerException e) {
            return null;
        }
    }

    public double getTotalRtdBySalesCode(Integer salecode) {
        try {
            TypedQuery<Double> q = em.createQuery("SELECT SUM(s.salesDetails.salesPrice * s.rtdQty) FROM RtdItem s WHERE s.salesDetails.saleId.salesId = :salecode", double.class);
            q.setParameter("salecode", salecode);
            return q.getSingleResult();
        } catch (NullPointerException e) {
            return 0.00;
        }
    }

    public double getTotalOfCostPriceRtdBySalesCode(Integer salecode) {
        try {
            TypedQuery<Double> q = em.createQuery("SELECT SUM(s.salesDetails.costPrice * s.rtdQty) FROM RtdItem s WHERE s.salesDetails.saleId.salesId = :salecode", double.class);
            q.setParameter("salecode", salecode);
            return q.getSingleResult();
        } catch (NullPointerException e) {
           // Logger.getLogger(ReturnBL.class.getName()).log(Level.SEVERE, null, e);
            return 0.00;
        }
    }

    public double getTotalRtdBySalesCode(Integer salecode, Date s, Date e) {
        try {
            TypedQuery<Double> q = em.createQuery("SELECT SUM(s.salesDetails.salesPrice * s.rtdQty) FROM RtdItem s WHERE s.salesDetails.saleId.salesId = :salecode AND s.rtdDate BETWEEN :s AND :e ", double.class);
            q.setParameter("salecode", salecode);
            q.setParameter("s", s);
            q.setParameter("e", e);
            return q.getSingleResult();
        } catch (NullPointerException ex) {
            return 0.00;
        }
    }

    public List<RtdItem> getAllRtdItembyBarcode(String u, int page) {
        TypedQuery<RtdItem> q = em.createQuery("SELECT s FROM RtdItem s WHERE s.salesDetails.upc.upc = :itemDesc AND s.salesDetails IS NOT NULL", RtdItem.class);
        q.setParameter("u", u);
        q.setMaxResults(page);
        return q.getResultList();
    }

    public List<RtdItem> getAllRtdItembyItemsDesc(String itemDesc, int page) {
        TypedQuery<RtdItem> q = em.createQuery("SELECT s FROM RtdItem s WHERE s.salesDetails.upc.itemDesc = :itemDesc AND s.salesDetails IS NOT NULL", RtdItem.class);
        q.setParameter("itemDesc", itemDesc);
        q.setMaxResults(page);
        return q.getResultList();
    }

    public List<RtdItem> searchAllReturnItems(String p) {
        TypedQuery<RtdItem> q = em.createQuery("SELECT s FROM RtdItem s WHERE s.salesDetails.upc.upc LIKE :p OR s.salesDetails.upc.itemDesc LIKE :p1 AND s.salesDetails IS NOT NULL", RtdItem.class);
        q.setParameter("p", "%" + p + "%");
        q.setParameter("p1", "%" + p + "%");
        // q.setParameter("p2", "%" + p + "%");
        q.setMaxResults(20);
        return q.getResultList();
    }

    public Long getTotalReturnsbyItemDesc(String itemDesc) {
        try {
            TypedQuery<Long> q = em.createQuery("SELECT SUM(s.rtdQty) FROM RtdItem s WHERE s.salesDetails.upc.itemDesc = :itemDesc", Long.class);
            q.setParameter("itemDesc", itemDesc);
            return q.getSingleResult();
        } catch (NoResultException ex) {
            return 0L;
        } catch (NullPointerException ex) {
            return 0l;
        }
    }

    public Double getTotalReturnsByDate(Date d) {
        try {
            TypedQuery<Double> q = em.createQuery("SELECT SUM(s.rtdQty * s.salesDetails.salesPrice) FROM RtdItem s WHERE s.rtdDate = :d", Double.class);
            q.setParameter("d", d);
            return q.getSingleResult();
        } catch (NoResultException ex) {
            return 0.00;
        } catch (NullPointerException ex) {
            return 0.00;
        }
    }

    public RefundPolicy findRefundPolicy() {
        try {
            TypedQuery<RefundPolicy> q = em.createQuery("SELECT f FROM RefundPolicy f", RefundPolicy.class);
            //q.setMaxResults(1);
            return q.getResultList().get(0);
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
}
