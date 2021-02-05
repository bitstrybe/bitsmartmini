package bt.bitsmartmini.reportmodel;

import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import bt.bitsmartmini.bl.ReceiptBL;
import bt.bitsmartmini.entity.Receipt;
import lxe.utility.date.DateUtil;

/**
 *
 * @author scarface
 */
public class SalesReceiptReportModel extends AbstractTableModel implements Runnable{

    private Object data[][];
    String[] colnames = {"0", "1", "2", "3", "4", "5", "6"};
    List<Receipt> s;
    ReceiptBL rbl = new ReceiptBL();
    //public Date sd, ed;

    public SalesReceiptReportModel(Date sd, Date ed) {
        s = new ReceiptBL().getReceiptsDateRange(sd, ed);
        convertListToReportData(s);
        
       // run();
    }

    private void convertListToReportData(List<Receipt> s) {
        
        data = new Object[s.size()][colnames.length];
        for (int x = 0; x < s.size(); x++) {
            // Rece sb = new SalesBL();
            Receipt c = s.get(x);
            //String receiptNo = String.valueOf(c.getReceiptId());
            data[x][0] = String.format("%06d", c.getReceiptId());
//            String cus = new CustomerBL().getCustomerNamebyId(c.getCustomer().getCustomerId());
            data[x][1] = DateUtil.format2(c.getReceiptDate());
            data[x][2] = c.getPayMode();
            data[x][3] = c.getAmountPaid();
        }
    }
    
    @Override
    public void run() {
        //convertListToReportData();
    }

    @Override
    public int getRowCount() {
        return this.s.size();
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
