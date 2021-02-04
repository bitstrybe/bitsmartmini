package bt.bitsmartmini.reportmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import bt.bitsmartmini.bl.CustomerBL;
import bt.bitsmartmini.bl.ReceiptBL;
import bt.bitsmartmini.bl.SalesBL;
import bt.bitsmartmini.entity.Customers;
import lxe.utility.math.DecimalUtil;

/**
 *
 * @author scarface
 */
public class DebtorsByCustomerReportModel extends AbstractTableModel implements Runnable {

    private Object data[][];
    private List<Customers> c;
    String[] colnames = {"0", "1", "2", "3", "4"};
    List<Customers> s;
    SalesBL sb = new SalesBL();
    CustomerBL cb = new CustomerBL();
    ReceiptBL rb = new ReceiptBL();
    int size = 0;
    HashMap ts, tp, tb;
    //public String dbname;

    public DebtorsByCustomerReportModel(String dbname) {
        //run();
        convertListToReportData(dbname);
    }

    private int findDebtors(String dbname) {
        c = new ArrayList();
        if (dbname != null && dbname.length() > 0) {
           // System.out.println("n: " + dbname);
            s = cb.getCustomers(Integer.parseInt(dbname));
        } else {
            s = cb.getAllCustomers();
        }
        ts = new HashMap();
        tp = new HashMap();
        tb = new HashMap();
        for (int x = 0; x < s.size(); x++) {
            Customers l = s.get(x);
            double totals = sb.getTotalSalesByCustomer(l.getCustomerId());
            System.out.println("ts: " + totals);
            double totalp = rb.getTotalPaidByCustomer(l.getCustomerId());
            System.out.println("db: " + totalp);
            double totalb = totals - totalp;
            System.out.println("db: " + totalb);
            if (totalb > 0) {
                c.add(l);
                ts.put(l.getCustomerId(), totals);
                tp.put(l.getCustomerId(), totalp);
                tb.put(l.getCustomerId(), totalb);
            }
        }
       // System.out.println("size: " + c.size());
        return c.size();
    }

    private void convertListToReportData(String dbname) {
        size = findDebtors(dbname);
        data = new Object[size][colnames.length];
        System.out.println("size: "+size);
        for (int x = 0; x < size; x++) {
            Customers l = c.get(x);
            System.out.println("c: "+l.getFullname());
            data[x][0] = l.getFullname();
            data[x][1] = l.getMobile();
            data[x][2] = DecimalUtil.format2(ts.get(l.getCustomerId()));
            data[x][3] = DecimalUtil.format2(tp.get(l.getCustomerId()));
            data[x][4] = DecimalUtil.format2(tb.get(l.getCustomerId()));
        }
    }

    @Override
    public void run() {
        //convertListToReportData();
    }

    @Override
    public int getRowCount() {
        return size;
    }

    @Override
    public int getColumnCount() {
        return this.colnames.length;
    }

    @Override
    public String getColumnName(int col) {
        return colnames[col];
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.data[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        this.fireTableCellUpdated(row, col);
    }

}

