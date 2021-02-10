package bt.bitsmartmini.reportmodel;

import java.text.DecimalFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import bt.bitsmartmini.bl.ReceiptBL;
import bt.bitsmartmini.bl.SalesBL;
import bt.bitsmartmini.bl.StockinBL;
import bt.bitsmartmini.entity.SalesDetails;
import bt.bitsmartmini.entity.Stockin;
import bt.bitsmartmini.utils.Utilities;

/**
 *
 * @author JScare
 */
public class ReceiptReportModel extends AbstractTableModel implements Runnable {

    private Object data[][];
    String[] colnames = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
    List<SalesDetails> s;
    StockinBL stkibl = new StockinBL();
    ReceiptBL recbl = new ReceiptBL();
    SalesBL sbl = new SalesBL();
    //public int salesId;

    public ReceiptReportModel(Integer salesId) {
        //run();
        convertListToReportData(salesId);
    }

    private void convertListToReportData(Integer salesId) {
        s = sbl.getAllSalesDetailsbySalesCode(salesId);
        data = new Object[s.size()][colnames.length];
        for (int x = 0; x < s.size(); x++) {
            //SalesBL sb = new SalesBL();
            SalesDetails c = s.get(x);
            String salescode =  String.format("%06d", c.getSaleId().getSalesId());
            data[x][0] = salescode;
            data[x][1] = c.getUpc().getItemDesc();
            data[x][2] = c.getQuantity();
            data[x][3] = Utilities.roundToTwoDecimalPlace(c.getSalesPrice(), 2);
            data[x][4] = null;
            double totalsalestb;
            int aqty;
            if (c.getRtdItem() != null) {
                aqty = c.getQuantity() - c.getRtdItem().getRtdQty();
            } else {
                aqty = c.getQuantity();
            }
            double totalpv = (aqty * c.getSalesPrice());
            totalsalestb = totalpv - (totalpv * (c.getDiscount() / 100));
            data[x][5] = Utilities.roundToTwoDecimalPlace(totalsalestb, 2);
            data[x][6] = Utilities.roundToTwoDecimalPlace(totalsalestb, 2);
            List<Stockin> sk = stkibl.getItemStockinByBarcode(c.getUpc().getUpc());
            data[x][7] = sk.get(0).getUpc().getItemDesc();
            double totalpaid;
            double bal;
            try {
                totalpaid = recbl.getTotalPaidbySalesCode(c.getSaleId().getSalesId());
                bal = (totalpaid - totalsalestb);
                data[x][8] = totalpaid;
                data[x][9] = Utilities.roundToTwoDecimalPlace(bal, 2);
            } catch (NullPointerException ex) {
                data[x][8] = totalpaid = 0.00;
                data[x][9] = totalpaid - totalsalestb;
            }
            data[x][10] = Utilities.convertDigitToCurrency(new DecimalFormat("0.00").format(totalpaid));
            data[x][11] = c.getSaleId().getCustomers().getFullname();
            data[x][12] = c.getDiscount();
            if(c.getRtdItem() != null){
                data[x][13] = true;
            }else{
                data[x][13] = false;
            }
            
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
