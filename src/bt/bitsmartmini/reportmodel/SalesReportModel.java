package bt.bitsmartmini.reportmodel;

import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import bt.bitsmartmini.bl.ReceiptBL;
import bt.bitsmartmini.bl.ReturnBL;
import bt.bitsmartmini.bl.SalesBL;
import bt.bitsmartmini.entity.Sales;
import bt.bitsmartmini.utils.Utilities;

/**
 *
 * @author JScare
 */
public class SalesReportModel extends AbstractTableModel implements Runnable {

    private Object data[][];
    String[] colnames = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};
    List<Sales> s;

    public Date start, edate;

    public SalesReportModel(Date start, Date end) {
        // run();
        convertListToReportData(start, end);
    }

    private void convertListToReportData(Date start, Date end) {
        ReceiptBL rbl = new ReceiptBL();
        ReturnBL rd = new ReturnBL();
        s = new SalesBL().getSalesDateRange(start, end);
        data = new Object[s.size()][colnames.length];
        for (int x = 0; x < s.size(); x++) {
            SalesBL sb = new SalesBL();
            Sales c = s.get(x);
            String salescode = String.format("%06d", c.getSalesId());
            data[x][0] = salescode;
            data[x][1] = c.getCustomers().getFullname(); //Utilities.convertDateToString(start);
            double totalcost = Utilities.roundToTwoDecimalPlace(sb.getTotalCost(c.getSalesId()), 2);
            double totalCPR = Utilities.roundToTwoDecimalPlace(rd.getTotalOfCostPriceRtdBySalesCode(c.getSalesId()), 2);
            System.out.println("tc: "+c.getSalesId()+ " = "+totalcost);
            System.out.println("tr: "+c.getSalesId()+ " = "+totalCPR);
            data[x][2] = totalcost - totalCPR;//Utilities.convertDateToString(end);
            double totalsales = Utilities.roundToTwoDecimalPlace(sb.getActualTotalDiscountedSales(c.getSalesId()), 2);
            data[x][3] = totalsales;
            double totalpaid;
            try {
                totalpaid = Utilities.roundToTwoDecimalPlace(rbl.getTotalPaidbySalesCode(c.getSalesId()), 2);
            } catch (Exception ex) {
                totalpaid = 0;
            }
            data[x][4] = totalpaid;
            double balance = Utilities.roundToTwoDecimalPlace(totalsales - totalpaid, 2);
            data[x][5] = balance;
            double totalR = Utilities.roundToTwoDecimalPlace(rd.getTotalRtdBySalesCode(c.getSalesId()), 2);
            data[x][6] = totalR;
            data[x][7] = Utilities.roundToTwoDecimalPlace(totalpaid - totalR, 2);
            double profit = ((totalpaid - totalR) - (totalcost - totalCPR));
            data[x][8] = Utilities.roundToTwoDecimalPlace(profit, 2);
            double discount = sb.getActualDiscountValue(c.getSalesId());
            data[x][9] = Utilities.roundToTwoDecimalPlace(discount, 2);
        }
    }

    @Override
    public void run() {
        convertListToReportData(start, edate);
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
