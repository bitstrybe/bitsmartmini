package bt.bitsmartmini.bl;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.TypedQuery;
import bt.bitsmartmini.entity.Sales;
import bt.bitsmartmini.entity.SalesDetails;

/**
 *
 * @author JScare
 */
public class SalesBL extends DdsBL {

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
            Sales c = em.find(Sales.class, id);
            em.refresh(c);
            em.remove(c);
            em.getTransaction().commit();
            em.clear();
            return 1;
        } catch (IllegalArgumentException ex) {
            return 0;
        }
    }

    public List<Sales> getAllSales() {
        TypedQuery q = em.createQuery("SELECT s FROM Sales s", Sales.class);
        return q.getResultList();
    }

    public List<Sales> getSalesByCustomer(int c) {
        TypedQuery q = em.createQuery("SELECT s FROM Sales s WHERE s.customers.customerId = :c", Sales.class);
        q.setParameter("c", c);
        return q.getResultList();

    }

    public List<SalesDetails> getAllSalesDetails() {
        TypedQuery q = em.createQuery("SELECT s FROM SalesDetails s", SalesDetails.class);
        return q.getResultList();
    }

    public List<SalesDetails> getAllSalesDetailsbySalesCode(int salesCode) {
        TypedQuery q = em.createQuery("SELECT s FROM SalesDetails s WHERE s.saleId.salesId = :salesid", SalesDetails.class);
        q.setParameter("salesid", salesCode);
        return q.getResultList();

    }

    public Sales getAllSalesbySalesCode(int salesCode) {
        TypedQuery<Sales> q = em.createQuery("SELECT s FROM Sales s WHERE s.salesId = :salesid", Sales.class);
        q.setParameter("salesid", salesCode);
        return q.getSingleResult();
    }

    public Double getTotalCost(Integer salesid) {
        try {
            TypedQuery<Double> q = em.createQuery("SELECT SUM(s.quantity * s.costPrice) FROM SalesDetails s WHERE s.saleId.salesId = :salesid", Double.class);
            q.setParameter("salesid", salesid);
            return q.getSingleResult();
        } catch (Exception ex) {
            Logger.getLogger(SalesBL.class.getName()).log(Level.SEVERE, null, ex);
            return 0.00;
        }
    }

    public Double getTotalSalesByCustomer(Integer c) {
        try {
            TypedQuery<Double> q = em.createQuery("SELECT SUM(s.quantity * s.salesPrice) FROM SalesDetails s WHERE s.saleId.customers.customerId = :c ", Double.class);
            q.setParameter("c", c);
            return q.getSingleResult();
        } catch (Exception ex) {
            Logger.getLogger(SalesBL.class.getName()).log(Level.SEVERE, null, ex);
            return 0.00;
        }
    }

    public Double getTotalSales() {
        try {
            TypedQuery<Double> q = em.createQuery("SELECT SUM(s.salesPrice * s.quantity) FROM SalesDetails s", double.class);
            //q.setParameter("salecode", salecode);
            return q.getSingleResult();
        } catch (NullPointerException e) {
            return 0.00;
        }
    }

    public Double getTotalSales(Integer salecode) {
        try {
            TypedQuery<Double> q = em.createQuery("SELECT SUM(s.salesPrice * s.quantity) FROM SalesDetails s WHERE s.saleId.salesId = :salecode", double.class);
            q.setParameter("salecode", salecode);
            return q.getSingleResult();
        } catch (NullPointerException e) {
            return 0.00;
        }
    }

    public long getSalesTotal(String u) {
        try {
            TypedQuery<Long> q = em.createQuery("SELECT SUM(s.quantity) FROM SalesDetails s WHERE s.upc.upc = :u", Long.class);
            q.setParameter("u", u);
            return q.getSingleResult();
        } catch (Exception ex) {
            return 0l;
        }
    }

    public long getSalesTotal(Date s, Date e) {
        try {
            TypedQuery<Long> q = em.createQuery("SELECT SUM(s.quantity) FROM SalesDetails s WHERE s.entryDate BETWEEN :s AND :e ", Long.class);
            q.setParameter("s", s);
            q.setParameter("e", e);
            return q.getSingleResult();
        } catch (Exception ex) {
            return 0l;
        }
    }

    public Double getActualTotalDiscountedSales(Integer salecode) {
        try {
            double atsales = 0.00;
            List<SalesDetails> sd = getAllSalesDetailsbySalesCode(salecode);
            for (SalesDetails d : sd) {
                double discount;
                double discsales = 0.00;
                //double tsales = 0.00;
                if (d.getDiscount() > 0) {
                    //System.out.println("finding discount... ");
                    discount = 100 - d.getDiscount();
                    discsales = d.getQuantity() * ((discount * d.getSalesPrice()) / 100);
                } else {
                    discsales += d.getSalesPrice() * d.getQuantity();
                }
                atsales += discsales;
            }
            return atsales;
        } catch (Exception e) {
            return 0.00;
        }
    }

    public Double getActualDiscountValue(Integer salecode) {
        try {
            double atsales = 0.00;
            List<SalesDetails> sd = getAllSalesDetailsbySalesCode(salecode);
            for (SalesDetails d : sd) {
                double discount;
                double discsales = 0.00;
                double tsales;
                if (d.getDiscount() > 0) {
                    tsales = d.getSalesPrice() * d.getQuantity();
                    discount = 100 - d.getDiscount();
                    discsales += d.getQuantity() * (discount * d.getSalesPrice()) / 100;
                    tsales -= discsales;
                } else {
                    tsales = 0;
                }
                //System.out.println(tsales);
                atsales += tsales;
            }
            //System.out.println("t:"+atsales);
            return atsales;
        } catch (Exception e) {
            return 0.00;
        }
    }

    public Double getActualTotalSales(Integer salecode) {
        try {
            double atsales = 0.00;
            //TypedQuery<SalesDetails> q = em.createQuery("SELECT s FROM SalesDetails s WHERE s.saleId.salesId = :salecode", SalesDetails.class);
            //q.setParameter("salecode", salecode);
            List<SalesDetails> sd = getAllSalesDetailsbySalesCode(salecode);
            for (SalesDetails d : sd) {
                double discount;
                double tsales = 0.00;
                if (d.getDiscount() > 0) {
                    discount = 100 - d.getDiscount();
                    tsales += d.getQuantity() * (discount * d.getSalesPrice()) / 100;
                } else {
                    tsales += (d.getQuantity() * d.getSalesPrice());
                }
                if (d.getRtdItem() != null) {
                    tsales -= (d.getRtdItem().getRtdQty() * d.getSalesPrice());
                }
                //System.out.println(tsales);
                atsales += tsales;
            }
            //System.out.println("t:"+atsales);
            return atsales;
        } catch (Exception e) {
            return 0.00;
        }
    }

    public Double getOutStandingSales(Integer salescode) {
        try {
            ReceiptBL rb = new ReceiptBL();
            double bal = 0.00;
            List<Sales> ss = getAllSales();

            for (Sales s : ss) {
                //List<SalesDetails> sd = getAllSalesDetailsbySalesCode(s.getSalesId());
                double as = getActualTotalSales(s.getSalesId());
                double tp = rb.getTotalPaidbySalesCode(salescode);
                bal = as - tp;
            }
            return bal;
        } catch (Exception e) {
            return 0.00;
        }
    }

    public List<Sales> getSalesDateRange(Date startdate, Date enddate) {
        TypedQuery q = em.createQuery("SELECT s FROM Sales s WHERE s.salesDate BETWEEN :date1 AND :date2", Sales.class);
        q.setParameter("date1", startdate);
        q.setParameter("date2", enddate);
        q.setHint("org.hibernate.cacheMode", "IGNORE");
        return q.getResultList();
    }

    public List<Sales> getCustomerFullnamebysalescode(String cusid) {
        TypedQuery<Sales> q = em.createQuery("SELECT s FROM Sales s WHERE s.customer.customerId = :cusid", Sales.class);
        q.setParameter("cusid", cusid);
        return q.getResultList();
    }

    public List<Integer> getSalesCount() {
        TypedQuery<Integer> q = em.createQuery("SELECT s.salesId FROM Sales s ORDER BY s.salesId DESC", Integer.class);
        q.setMaxResults(1);
        return q.getResultList();
    }

    public List<SalesDetails> getSalesDetailsbySalesId(int salesid) {
        TypedQuery q = em.createQuery("SELECT s FROM SalesDetails s WHERE s.saleId.salesId = :salesid", List.class);
        q.setParameter("salesid", salesid);
        return q.getResultList();
    }

    public SalesDetails getSalesDetailsById(int salesid) {
        TypedQuery<SalesDetails> q = em.createQuery("SELECT s FROM SalesDetails s WHERE s.salesDetailsId = :i", SalesDetails.class);
        q.setParameter("i", salesid);
        return q.getSingleResult();
    }

    public List getUsersFromSales(int userscode) {
        TypedQuery<Sales> q = em.createQuery("SELECT s FROM Sales s WHERE s.users.userid = :userid", Sales.class);
        q.setParameter("userid", userscode);
        q.setMaxResults(1);
        return q.getResultList();
    }

    public List<SalesDetails> getAllSalesDetailsbyItemsDesc(String u, int page) {
        TypedQuery<SalesDetails> q = em.createQuery("SELECT s FROM SalesDetails s WHERE s.upc.itemDesc = :u", SalesDetails.class);
        q.setParameter("u", u);
        q.setMaxResults(page);
        return q.getResultList();
    }

    public List<SalesDetails> getAllSalesDetailsbyBarcode(String u, int page) {
        TypedQuery<SalesDetails> q = em.createQuery("SELECT s FROM SalesDetails s WHERE s.upc.upc = :u", SalesDetails.class);
        q.setParameter("u", u);
        q.setMaxResults(page);
        return q.getResultList();
    }
}
