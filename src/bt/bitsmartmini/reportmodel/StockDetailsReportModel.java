
package bt.bitsmartmini.reportmodel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import bt.bitsmartmini.bl.SalesBL;
import bt.bitsmartmini.bl.StockinBL;
import bt.bitsmartmini.entity.Stockin;

/**
 *
 * @author scarface
 */
public class StockDetailsReportModel extends AbstractTableModel implements Runnable{
     private Object data[][];
    String[] colnames = {"0", "1", "2", "3", "4", "5", "6"};

    long balv;
    int size = 0;

    StockinBL skb = new StockinBL();
    SalesBL sb = new SalesBL();
    List<Stockin> sk;
//    List<Stockin> skn = new ArrayList<>();

    public StockDetailsReportModel() {
        sk = skb.getAllStockinGroupBatch();
        convertListToReportData(sk);
    }

    private void convertListToReportData(List<Stockin> sk) {
        size = sk.size();
        data = new Object[size][colnames.length];
        for (int x = 0; x < sk.size(); x++) {
            Stockin e = sk.get(x);
            balv = skb.getStockBalance(e.getItems().getItemDesc());
            data[x][0] = e.getItems().getItemDesc();
            data[x][1] = skb.getStockInTotal(e.getItems().getItemDesc());
            data[x][2] = skb.getTotalReturns(e.getItems().getItemDesc());
            data[x][3] = sb.getSalesTotal(e.getItems().getItemDesc());
            data[x][4] = skb.getStockOutTotal(e.getItems().getItemDesc());
            data[x][5] = skb.getStockBalance(e.getItems().getItemDesc());
            data[x][6] = Long.valueOf(e.getItems().getRol());
        }
    }
    
    @Override
    public void run() {
        //convertListToReportData();
    }

    @Override
    public int getRowCount() {
        return this.size;
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
        //System.out.println("ri:"+rowIndex);
        return this.data[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        this.fireTableCellUpdated(row, col);
    }

    
}
