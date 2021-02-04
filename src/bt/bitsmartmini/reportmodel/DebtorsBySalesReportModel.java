package bt.bitsmartmini.reportmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import bt.bitsmartmini.bl.CustomerBL;
import bt.bitsmartmini.bl.ReceiptBL;
import bt.bitsmartmini.bl.SalesBL;
import bt.bitsmartmini.entity.Sales;
import lxe.utility.math.DecimalUtil;

/**
 *
 * @author scarface
 */
public class DebtorsBySalesReportModel extends AbstractTableModel implements Runnable {

    private Object data[][];
    private List<Sales> c;
    String[] colnames = {"0", "1", "2", "3", "4"};
    List<Sales> s;
    SalesBL sb = new SalesBL();
    CustomerBL cb = new CustomerBL();
    ReceiptBL rb = new ReceiptBL();
    int size = 0;
    HashMap ts, tp, tb;
    //public String customer;

    public DebtorsBySalesReportModel() {
        //run();
        convertListToReportData();
    }

    private int findDebtors() {
        c = sb.getAllSales();
        ts = new HashMap();
        tp = new HashMap();
        tb = new HashMap();
        for (Sales sa : c) {
            //Sales l = s.get(x);
            double outs = sb.getOutStandingSales(sa.getSalesId());
//            System.out.println("ts: " + totals);
//            double totalp = rb.getTotalPaidByCustomer(l.getCustomers().getCustomerId());
//            System.out.println("db: " + totalp);
//            double totalb = totals - totalp;
            //System.out.println("db: " + totalb);
            if (outs > 0) {
                c.add(sa);
//                ts.put(l.getCustomers().getCustomerId(), totals);
//                tp.put(l.getCustomers().getCustomerId(), totalp);
//                tb.put(l.getCustomers().getCustomerId(), totalb);
            }
        }
        System.out.println("size: " + c.size());
        return c.size();
    }

    private void convertListToReportData() {
        //c = sb.getAllSales();
        size = findDebtors();
        //  = data = new Object[size][colnames.length];
        for (int x = 0; x < size; x++) {
            Sales l = c.get(x);
            data[x][0] = l.getSalesId();
            data[x][1] = l.getCustomers().getFullname();
            data[x][2] = l.getCustomers().getMobile();
            double ts = sb.getActualTotalSales(l.getSalesId());
            double pd = rb.getTotalPaidbySalesCode(l.getSalesId());
            data[x][3] = DecimalUtil.format2(ts);
            data[x][4] = DecimalUtil.format2(pd);
            data[x][5] = DecimalUtil.format2(ts - pd);
        }

    }

    @Override
    public void run() {
        // convertListToReportData();
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
