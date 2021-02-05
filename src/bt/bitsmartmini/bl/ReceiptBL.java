package bt.bitsmartmini.bl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.TypedQuery;
import bt.bitsmartmini.entity.Receipt;

/**
 *
 * @author JScare
 */
public class ReceiptBL extends DdsBL {

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
            Receipt c = em.find(Receipt.class, id);
            em.remove(c);
            em.getTransaction().commit();
            return 1;
        } catch (IllegalArgumentException ex) {
            return 0;
        }

    }

    public Receipt getReciptbyCode(Integer r) {
        TypedQuery q = em.createQuery("SELECT r FROM Receipt r WHERE r.receiptId = :receipt", Receipt.class);
        q.setParameter("receipt", r);
        return (Receipt) q.getSingleResult();
    }

    public List<Receipt> getAllReciptbySalescode(Integer salesid) {
        TypedQuery q = em.createQuery("SELECT r FROM Receipt r WHERE r.salesId.salesId = :salesid", Receipt.class);
        q.setParameter("salesid", salesid);
        return q.getResultList();
    }

    public List<Receipt> getReceiptsDateRange(Date startdate, Date enddate) {
        TypedQuery q = em.createQuery("SELECT s FROM Receipt s WHERE s.receiptDate BETWEEN :date1 AND :date2", Receipt.class);
        q.setParameter("date1", startdate);
        q.setParameter("date2", enddate);
        //q.setHint("org.hibernate.cacheMode", "IGNORE");
        return q.getResultList();
    }

    public Double getTotalPaidbySalesCodeGrp(Integer salesid) {
        try {
            TypedQuery<Double> q = em.createQuery("SELECT SUM(r.amountPaid) FROM Receipt r WHERE r.salesId.salesId = :salescode GROUP BY r.receiptDate", Double.class);
            q.setParameter("salescode", salesid);
            return q.getSingleResult();
        } catch (Exception ex) {
            return 0.00;

        }
    }
    
    public Double getTotalPaidbySalesCode(Integer salesid) {
        try {
            TypedQuery<Double> q = em.createQuery("SELECT SUM(r.amountPaid) FROM Receipt r WHERE r.salesId.salesId = :salescode", Double.class);
            q.setParameter("salescode", salesid);
            return q.getSingleResult();
        } catch (Exception ex) {
            return 0.00;

        }
    }

    public List getSalesbyReceipt(Integer salesid) {
        try {
            TypedQuery<Integer> q = em.createQuery("SELECT r.salesId.salesId FROM Receipt r WHERE r.salesId.salesId = :salesid ", Integer.class);
            q.setParameter("salesid", salesid);
            return q.getResultList();
        } catch (Exception ex) {
            return null;
        }
    }

    public double getTotalPaidByCustomer(Integer c) {
        try {
            TypedQuery<Double> q = em.createQuery("SELECT SUM(r.amountPaid) FROM Receipt r WHERE r.salesId.customers.customerId = :c ", Double.class);
            q.setParameter("c", c);
            return q.getSingleResult();
        } catch (Exception ex) {
            Logger.getLogger(ReceiptBL.class.getName()).log(Level.SEVERE, null, ex);
            return 0.00;
        }
    }

    public List<Integer> getReceiptCount() {
        TypedQuery<Integer> q = em.createQuery("SELECT s.receiptId FROM Receipt s ORDER BY s.receiptId DESC", Integer.class);
        q.setMaxResults(1);
        return q.getResultList();

    }

    public double getDailySalesReceipt(Date today) {
        try {
            TypedQuery<Double> q = em.createQuery("SELECT SUM(s.amountPaid) FROM Receipt s WHERE s.receiptDate = :edate", Double.class);
            q.setParameter("edate", today);
            return q.getSingleResult();

        } catch (Exception ex) {
            return 0.0;
        }

    }
    
    public double getDailySalesReturned(Date today) {
        try {
            TypedQuery<Double> q = em.createQuery("SELECT s.salesDetails.salesPrice FROM RtdItem s WHERE s.rtdDate = :edate", Double.class);
            q.setParameter("edate", today);
            return q.getSingleResult();

        } catch (Exception ex) {
            return 0.0;
        }
    }

    public double getDurationSalesReceipt(Date s, Date d) {
        try {
            TypedQuery<Double> q = em.createQuery("SELECT SUM(s.amountPaid) FROM Receipt s WHERE s.receiptDate BETWEEN :s AND :d", Double.class);
            q.setParameter("s", s);
            q.setParameter("d", d);
            return BigDecimal.valueOf(q.getSingleResult()).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
        } catch (Exception ex) {
            return 0.00;
        }

    }
    
    public double getDailySalesDurationReceipt(Time s, Time d) {
        try {
            TypedQuery<Double> q = em.createQuery("SELECT SUM(s.amountPaid) FROM Receipt s WHERE s.receiptTime BETWEEN :s AND :d", Double.class);
            q.setParameter("s", s);
            q.setParameter("d", d);
            return BigDecimal.valueOf(q.getSingleResult()).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
        } catch (Exception ex) {
            return 0.00;
        }
    }
    
    public double getDailyRefundsDurationReceipt(Time s, Time d) {
        try {
            TypedQuery<Double> q = em.createQuery("SELECT SUM(s.rtdQty * s.salesDetails.salesPrice) FROM RtdItem s WHERE s.rtdTime BETWEEN :s AND :d", Double.class);
            q.setParameter("s", s);
            q.setParameter("d", d);
            return BigDecimal.valueOf(q.getSingleResult()).setScale(2, RoundingMode.HALF_EVEN).doubleValue();
        } catch (Exception ex) {
            return 0.00;
        }
    }

}
