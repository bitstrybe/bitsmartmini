package bt.bitsmartmini.reportmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import bt.bitsmartmini.bl.ItemsBL;
import bt.bitsmartmini.bl.StockinBL;
import bt.bitsmartmini.entity.Items;
import lxe.utility.math.DecimalUtil;

/**
 *
 * @author JScare
 */
public class StockReorderReportModel extends AbstractTableModel implements Runnable{

    private Object data[][];
    String[] colnames = {"0", "1", "2", "3", "4"};
    long balv;
    int size = 0;
    StockinBL skb = new StockinBL();
    ItemsBL ib = new ItemsBL();
    List<Items> skn = new ArrayList<>();
    private HashMap co;

    public StockReorderReportModel() {
        //run();
        convertListToReportData();
    }

    public int getListSize() {
        co = new HashMap();
        List<Items> sk = ib.getAllItems();
        sk.forEach(e -> {
            balv = skb.getStockBalance(e.getItemDesc());
            if (balv <= e.getRol()) {
                skn.add(e);
                co.put(e.getItemDesc(), balv);
            }
        });
        return skn.size();
    }

    private void convertListToReportData() {
        size = getListSize();
        data = new Object[size][colnames.length];
        for (int x = 0; x < skn.size(); x++) {
            Items e = skn.get(x);
            data[x][0] = e.getItemDesc();
            System.out.println("sk: "+e.getItemDesc());
            data[x][1] = DecimalUtil.format2(e.getItemsPrice().getSalesPrice());
            data[x][2] = e.getRol();
            data[x][3] = co.get(e.getItemDesc());
        }
    }
    
    @Override
    public void run() {
        convertListToReportData();
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
