package bt.bitsmartmini.utils;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import bt.bitsmartmini.bl.CustomerBL;
import bt.bitsmartmini.bl.ReceiptBL;
import bt.bitsmartmini.entity.Customers;
import bt.bitsmartmini.entity.Receipt;
import bt.bitsmartmini.reportmodel.DebtorsByCustomerReportModel;
import bt.bitsmartmini.reportmodel.DebtorsBySalesReportModel;
import bt.bitsmartmini.reportmodel.ItemListReportModel;
import bt.bitsmartmini.reportmodel.ReceiptReportModel;
import bt.bitsmartmini.reportmodel.SalesReceiptReportModel;
import bt.bitsmartmini.reportmodel.SalesReportModel;
import bt.bitsmartmini.reportmodel.StockDetailsReportModel;
import bt.bitsmartmini.reportmodel.StockReorderReportModel;
import bt.bitsmartmini.reportmodel.StockReportModel;
import bt.bitsmartmini.ui.LoginController;
import bt.bitsmartmini.ui.MainAppController;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import lxe.utility.date.DateUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author JScare
 */
public class PrintReport extends JFrame {

    private static final long serialVersionUID = 1L;
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    HashMap param = new HashMap();

    private HashMap setBasiParam() {
        param.put("BNAME", MainAppController.B.getBName());
        param.put("BADDR", MainAppController.B.getBAddr());
        param.put("BCTRY", MainAppController.B.getBEmail());
        param.put("BEMAIL", MainAppController.B.getBEmail());
        param.put("BMOBILE", MainAppController.B.getBMobile());
        param.put("BCURR", MainAppController.B.getBCurrency());
        return param;
    }

    public void showReceiptReport(String id) throws JRException, ClassNotFoundException, SQLException, IOException {
        ReceiptBL rbl = new ReceiptBL();
        CustomerBL cb = new CustomerBL();
        Receipt r = rbl.getReciptbyCode(Integer.parseInt(id));
        param = setBasiParam();
        Customers c = cb.getCustomersBySales(r.getSalesId().getSalesId());
        InputStream inputStream = getClass().getResourceAsStream("/bt/bitsmartmini/reports/TerminalReceiptReport.jasper");
        File f = new File(MainAppController.B.getBLogo());
        BufferedImage image = ImageIO.read(f);
        BufferedImage imageback = ImageIO.read(getClass().getResourceAsStream("/bt/resources/logoback.png"));
        param.put("LOGO", image);
        param.put("LOGO1", imageback);
        param.put("RECEIPTCODE", String.format("%06d", r.getReceiptId()));
        param.put("RECEIPTDATE", DateUtil.format2(r.getReceiptDate()));
        param.put("AMNT", r.getAmountPaid());
        param.put("MODE", r.getPayMode());
        param.put("CUSTOMER", c.getFullname());
        param.put("CUSTMOBILE", c.getMobile());
        param.put("REFUND_POLICY", r.getReturnPolicy());
        param.put("SOLDBY", r.getUsers().getUserid().toString());
        ReceiptReportModel sm = new ReceiptReportModel(r.getSalesId().getSalesId());
        JRTableModelDataSource jrtmds = new JRTableModelDataSource(sm);
        JasperPrint print = JasperFillManager.fillReport(inputStream, param, jrtmds);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(dim);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }

    public void showSalesReceipteport(Date start, Date end) throws JRException, ClassNotFoundException, SQLException, IOException {

        try {
            InputStream inputStream = getClass().getResourceAsStream("/bt/bitsmartmini/reports/SalesReceiptReport.jasper");
            // Fields for report
            param = setBasiParam();
            File f = new File(MainAppController.B.getBLogo());
            BufferedImage image = ImageIO.read(f);
            param.put("LOGO", image);
            param.put("SD", DateUtil.format3(start));
            param.put("ED", DateUtil.format3(end));
            SalesReceiptReportModel sm = new SalesReceiptReportModel(start, end);
            JRTableModelDataSource jrtmds = new JRTableModelDataSource(sm);
            JasperPrint print = JasperFillManager.fillReport(inputStream, param, jrtmds);
            JRViewer viewer = new JRViewer(print);
            viewer.setOpaque(true);
            viewer.setVisible(true);
            this.add(viewer);
            this.setSize(dim);
            this.setVisible(true);
            this.setAlwaysOnTop(true);
        } catch (Exception ex) {
            Logger.getLogger(PrintReport.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void showSalesDateRangeReport(Date start, Date end) throws JRException, ClassNotFoundException, SQLException, IOException {
        InputStream inputStream = getClass().getResourceAsStream("/bt/bitsmartmini/reports/SalesReportA.jasper");
        // Fields for report
        param = setBasiParam();
        File f = new File(MainAppController.B.getBLogo());
        BufferedImage image = ImageIO.read(f);
        param.put("LOGO", image);
        param.put("SD", DateUtil.format3(start));
        param.put("ED", DateUtil.format3(end));
        param.put("UROLE", LoginController.u.getRoles());
        SalesReportModel sm = new SalesReportModel(start, end);
        JRTableModelDataSource jrtmds = new JRTableModelDataSource(sm);
        JasperPrint print = JasperFillManager.fillReport(inputStream, param, jrtmds);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(dim);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }

    public void showUserSalesDateRangeReport(Date start, Date end) throws JRException, ClassNotFoundException, SQLException, IOException {
        InputStream inputStream = getClass().getResourceAsStream("/bt/bitsmartmini/reports/SalesReportB.jasper");
        param = setBasiParam();
        File f = new File(MainAppController.B.getBLogo());
        BufferedImage image = ImageIO.read(f);
        param.put("LOGO", image);
        param.put("SD", DateUtil.format3(start));
        param.put("ED", DateUtil.format3(end));
        SalesReportModel sm = new SalesReportModel(start, end);
        JRTableModelDataSource jrtmds = new JRTableModelDataSource(sm);
        JasperPrint print = JasperFillManager.fillReport(inputStream, param, jrtmds);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(dim);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }

    public void showStockReorderReport() throws JRException, ClassNotFoundException, SQLException, IOException {
        InputStream inputStream = getClass().getResourceAsStream("/bt/bitsmartmini/reports/StockReorderLevelReport.jasper");
        param = setBasiParam();
        StockReorderReportModel sm = new StockReorderReportModel();
        JRTableModelDataSource jrtmds = new JRTableModelDataSource(sm);
        JasperPrint print = JasperFillManager.fillReport(inputStream, param, jrtmds);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(dim);
        this.setVisible(true);
        this.setAlwaysOnTop(true);

    }

    public void showStocksReport() throws JRException, ClassNotFoundException, SQLException, IOException {
        InputStream inputStream = getClass().getResourceAsStream("/bt/bitsmartmini/reports/StockReport.jasper");
        // Fields for report
        param = setBasiParam();
        StockReportModel sm = new StockReportModel();
        JRTableModelDataSource jrtmds = new JRTableModelDataSource(sm);
        JasperPrint print = JasperFillManager.fillReport(inputStream, param, jrtmds);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(dim);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }

    public void showStocksDetailsReport() throws JRException, ClassNotFoundException, SQLException, IOException {
        InputStream inputStream = getClass().getResourceAsStream("/bt/bitsmartmini/reports/StockDetailsReport.jasper");
        // Fields for report
        param = setBasiParam();
        StockDetailsReportModel sm = new StockDetailsReportModel();
        JRTableModelDataSource jrtmds = new JRTableModelDataSource(sm);
        JasperPrint print = JasperFillManager.fillReport(inputStream, param, jrtmds);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(dim);
        this.setVisible(true);
        this.setAlwaysOnTop(true);

    }

    public void showItemListReport() throws JRException, ClassNotFoundException, SQLException, IOException {
        InputStream inputStream = getClass().getResourceAsStream("/bt/bitsmartmini/reports/ItemListReport.jasper");
        param = setBasiParam();
        ItemListReportModel im = new ItemListReportModel();
        JRTableModelDataSource jrtmds = new JRTableModelDataSource(im);
        JasperPrint print = JasperFillManager.fillReport(inputStream, param, jrtmds);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(dim);
        this.setVisible(true);
        this.setAlwaysOnTop(true);

    }

    public void showDebtorsByCustomerReport(String c) throws JRException, ClassNotFoundException, SQLException, IOException {
        InputStream inputStream = getClass().getResourceAsStream("/bt/bitsmartmini/reports/DebtorsReport.jasper");
        param = setBasiParam();
        File f = new File(MainAppController.B.getBLogo());
        BufferedImage image = ImageIO.read(f);
        param.put("LOGO", image);
        DebtorsByCustomerReportModel dm = new DebtorsByCustomerReportModel(c);
        JRTableModelDataSource jrtmds = new JRTableModelDataSource(dm);
        JasperPrint print = JasperFillManager.fillReport(inputStream, param, jrtmds);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(dim);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }

    public void showDebtorsBySalesReport() throws JRException, ClassNotFoundException, SQLException, IOException {
        InputStream inputStream = getClass().getResourceAsStream("/bt/bitsmartmini/reports/DebtorsReport.jasper");
        param = setBasiParam();
        File f = new File(MainAppController.B.getBLogo());
        BufferedImage image = ImageIO.read(f);
        param.put("LOGO", image);
        DebtorsBySalesReportModel dm = new DebtorsBySalesReportModel();
        JRTableModelDataSource jrtmds = new JRTableModelDataSource(dm);
        JasperPrint print = JasperFillManager.fillReport(inputStream, param, jrtmds);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(dim);
        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }
}
