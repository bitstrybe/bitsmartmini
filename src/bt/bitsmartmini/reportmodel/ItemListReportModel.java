package bt.bitsmartmini.reportmodel;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import bt.bitsmartmini.bl.ItemsBL;
import bt.bitsmartmini.entity.Items;
import lxe.utility.math.DecimalUtil;

/**
 *
 * @author JScare
 */
public class ItemListReportModel extends AbstractTableModel implements Runnable {

    private Object data[][];
    String[] colnames = {"0", "1", "2", "3", "4", "5"};
    List<Items> s;

    public ItemListReportModel() {
        s = new ItemsBL().getAllItems();
        convertListToReportData(s);
    }

    private void convertListToReportData(List<Items> s) {
        
        data = new Object[s.size()][colnames.length];
        for (int x = 0; x < s.size(); x++) {
            Items c = s.get(x);
            data[x][0] = c.getUpc();
            data[x][1] = c.getItemDesc();
            data[x][2] = c.getBrand().getBrandName();
            //String dose = c.getDosageDef() != null ? c.getDosageDef() + " " + c.getDosage() : "";
            //String vom = c.getVomDef() != null ? c.getVomDef() + " " + c.getVom() : "";
            data[x][3] = c.getCategory().getCategoryName();
            //data[x][3] = c.get;
            data[x][4] = DecimalUtil.format2(c.getCp());
            data[x][5] = DecimalUtil.format2(c.getSp());
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
