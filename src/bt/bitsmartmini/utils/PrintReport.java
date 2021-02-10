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
import bt.bitsmartmini.bl.ReturnBL;
import bt.bitsmartmini.entity.Customers;
import bt.bitsmartmini.entity.Receipt;
import bt.bitsmartmini.entity.RefundPolicy;
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
        ReturnBL rb = new ReturnBL();
        Receipt r = rbl.getReciptbyCode(Integer.parseInt(id));
        param = setBasiParam();
        Customers c = cb.getCustomersBySales(r.getSalesId().getSalesId());
        RefundPolicy f = rb.findRefundPolicy();
        System.out.println("F: "+f.getRefundCustomMsg());
        // First, compile jrxml file.
        InputStream inputStream = getClass().getResourceAsStream("/bt/bitsmartmini/reports/TerminalReceiptReport.jasper");
        // Fields for report

        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/bt/resources/logo.png"));
        BufferedImage imageback = ImageIO.read(getClass().getResourceAsStream("/bt/resources/logoback.png"));
        param.put("LOGO", image);
        param.put("LOGO1", imageback);
        param.put("RECEIPTCODE", String.format("%06d", r.getReceiptId()));
        param.put("RECEIPTDATE", DateUtil.format2(r.getReceiptDate()));
        param.put("AMNT", r.getAmountPaid());
        param.put("MODE", r.getPayMode());
        param.put("CUSTOMER", c.getFullname());
        param.put("CUSTMOBILE", c.getMobile());
        param.put("CUSTMOBILE", c.getMobile());
        param.put("REFUND_POLICY", f.getRefundCustomMsg().replace("?", f.getRefundPeriodVal() + " " + f.getRefundPeriod()));
        param.put("SOLDBY", r.getUsers().getUserid().toString());
        ReceiptReportModel sm = new ReceiptReportModel(r.getSalesId().getSalesId());
        //sm.salesId = r.getSalesId().getSalesId();
        //Thread t = new Thread(sm);
        //t.start();
        JRTableModelDataSource jrtmds = new JRTableModelDataSource(sm);
        JasperPrint print = JasperFillManager.fillReport(inputStream, param, jrtmds);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(dim);
        this.setVisible(true);
    }

    public void showSalesReceipteport(Date start, Date end) throws JRException, ClassNotFoundException, SQLException, IOException {

        try {
            InputStream inputStream = getClass().getResourceAsStream("/bt/bitsmartmini/reports/SalesReceiptReport.jasper");
            // Fields for report
            param = setBasiParam();
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/bt/resources/logo.png"));
            param.put("LOGO", image);
            param.put("SD", DateUtil.format3(start));
            param.put("ED", DateUtil.format3(end));
            SalesReceiptReportModel sm = new SalesReceiptReportModel(start, end);
            //sm.sd = start;
            //sm.ed = end;
            //Thread t = new Thread(sm);
            //t.start();
            JRTableModelDataSource jrtmds = new JRTableModelDataSource(sm);
            JasperPrint print = JasperFillManager.fillReport(inputStream, param, jrtmds);
            JRViewer viewer = new JRViewer(print);
            viewer.setOpaque(true);
            viewer.setVisible(true);
//        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
            this.add(viewer);
            this.setSize(dim);
            this.setVisible(true);
//            System.out.print("Done!");
        } catch (Exception ex) {
            Logger.getLogger(PrintReport.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void showSalesDateRangeReport(Date start, Date end) throws JRException, ClassNotFoundException, SQLException, IOException {

        //String reportSrcFile = "data/Blank_A4.jrxml";
        // First, compile jrxml file.
        InputStream inputStream = getClass().getResourceAsStream("/bt/bitsmartmini/reports/SalesReportA.jasper");
        // Fields for report
        param = setBasiParam();
        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/bt/resources/logo.png"));
        param.put("LOGO", image);
        param.put("SD", DateUtil.format3(start));
        param.put("ED", DateUtil.format3(end));
        param.put("UROLE", LoginController.u.getRoles());
        //param.put("SP", LoginController.u.getRoles());
        SalesReportModel sm = new SalesReportModel(start, end);
        JRTableModelDataSource jrtmds = new JRTableModelDataSource(sm);
        JasperPrint print = JasperFillManager.fillReport(inputStream, param, jrtmds);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
//        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.add(viewer);
        this.setSize(dim);
        this.setVisible(true);
        // System.out.print("Done!");

    }

    public void showUserSalesDateRangeReport(Date start, Date end) throws JRException, ClassNotFoundException, SQLException, IOException {

        //String reportSrcFile = "data/Blank_A4.jrxml";
        // First, compile jrxml file.
        InputStream inputStream = getClass().getResourceAsStream("/bt/bitsmartmini/reports/SalesReportB.jasper");
        // Fields for report
        param = setBasiParam();
        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/bt/resources/logo.png"));
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
    }

    public void showStockReorderReport() throws JRException, ClassNotFoundException, SQLException, IOException {

        //String reportSrcFile = "data/Blank_A4.jrxml";
        // First, compile jrxml file.
        InputStream inputStream = getClass().getResourceAsStream("/bt/bitsmartmini/reports/StockReorderLevelReport.jasper");
        // Fields for report
        param = setBasiParam();
//        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/bt/resources/dodtitle1.png"));
//        BufferedImage image1 = ImageIO.read(getClass().getResourceAsStream("/bt/resources/dodtitle2.png"));
//        param.put("LOGO", image);
//        param.put("LOGO1", image1);
        StockReorderReportModel sm = new StockReorderReportModel();
        //Thread t = new Thread(sm);
        //t.start();
        JRTableModelDataSource jrtmds = new JRTableModelDataSource(sm);
        JasperPrint print = JasperFillManager.fillReport(inputStream, param, jrtmds);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
//        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.add(viewer);
        this.setSize(dim);
        this.setVisible(true);
        // System.out.print("Done!");

    }

    public void showStocksReport() throws JRException, ClassNotFoundException, SQLException, IOException {

        //String reportSrcFile = "data/Blank_A4.jrxml";
        // First, compile jrxml file.
        InputStream inputStream = getClass().getResourceAsStream("/bt/bitsmartmini/reports/StockReport.jasper");
        // Fields for report
        param = setBasiParam();
//        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/bt/resources/dodtitle1.png"));
//        BufferedImage image1 = ImageIO.read(getClass().getResourceAsStream("/bt/resources/dodtitle2.png"));
//        param.put("LOGO", image);
//        param.put("LOGO1", image1);
        StockReportModel sm = new StockReportModel();
        //Thread t = new Thread(sm);
        //t.start();
        JRTableModelDataSource jrtmds = new JRTableModelDataSource(sm);
        JasperPrint print = JasperFillManager.fillReport(inputStream, param, jrtmds);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
//        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.add(viewer);
        this.setSize(dim);
        this.setVisible(true);
        //System.out.print("Done!");

    }

    public void showStocksDetailsReport() throws JRException, ClassNotFoundException, SQLException, IOException {

        //String reportSrcFile = "data/Blank_A4.jrxml";
        // First, compile jrxml file.
        InputStream inputStream = getClass().getResourceAsStream("/bt/bitsmartmini/reports/StockDetailsReport.jasper");
        // Fields for report
        param = setBasiParam();
//        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/bt/resources/dodtitle1.png"));
//        BufferedImage image1 = ImageIO.read(getClass().getResourceAsStream("/bt/resources/dodtitle2.png"));
//        param.put("LOGO", image);
//        param.put("LOGO1", image1);
        StockDetailsReportModel sm = new StockDetailsReportModel();
        //Thread t = new Thread(sm);
        //t.start();
        JRTableModelDataSource jrtmds = new JRTableModelDataSource(sm);
        JasperPrint print = JasperFillManager.fillReport(inputStream, param, jrtmds);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
//        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.add(viewer);
        this.setSize(dim);
        this.setVisible(true);
        //System.out.print("Done!");

    }

    public void showItemListReport() throws JRException, ClassNotFoundException, SQLException, IOException {

        //String reportSrcFile = "data/Blank_A4.jrxml";
        // First, compile jrxml file.
        InputStream inputStream = getClass().getResourceAsStream("/bt/bitsmartmini/reports/ItemListReport.jasper");
        // Fields for report
        param = setBasiParam();
//        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/bt/resources/dodtitle1.png"));
//        BufferedImage image1 = ImageIO.read(getClass().getResourceAsStream("/bt/resources/dodtitle2.png"));
//        param.put("LOGO", image);
//        param.put("LOGO1", image1);
        ItemListReportModel im = new ItemListReportModel();
//        Thread t = new Thread(im);
//        t.start();
        JRTableModelDataSource jrtmds = new JRTableModelDataSource(im);
        JasperPrint print = JasperFillManager.fillReport(inputStream, param, jrtmds);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
//        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.add(viewer);
        this.setSize(dim);
        this.setVisible(true);
        //System.out.print("Done!");

    }

    public void showDebtorsByCustomerReport(String c) throws JRException, ClassNotFoundException, SQLException, IOException {
        //String reportSrcFile = "data/Blank_A4.jrxml";
        // First, compile jrxml file.
        InputStream inputStream = getClass().getResourceAsStream("/bt/bitsmartmini/reports/DebtorsReport.jasper");
        // Fields for report
        param = setBasiParam();
        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/bt/resources/logo.png"));
        param.put("LOGO", image);
        //param.put("SD", DateUtil.format3(start));
        //param.put("ED", DateUtil.format3(end));
        DebtorsByCustomerReportModel dm = new DebtorsByCustomerReportModel(c);
        //dm.dbname = c;
        //Thread t = new Thread(dm);
        //t.start();
        JRTableModelDataSource jrtmds = new JRTableModelDataSource(dm);
        JasperPrint print = JasperFillManager.fillReport(inputStream, param, jrtmds);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
//        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.add(viewer);
        this.setSize(dim);
        this.setVisible(true);
        //System.out.print("Done!");

    }

    public void showDebtorsBySalesReport() throws JRException, ClassNotFoundException, SQLException, IOException {

        //String reportSrcFile = "data/Blank_A4.jrxml";
        // First, compile jrxml file.
        InputStream inputStream = getClass().getResourceAsStream("/bt/bitsmartmini/reports/DebtorsReport.jasper");
        // Fields for report
        param = setBasiParam();
        BufferedImage image = ImageIO.read(getClass().getResourceAsStream("/bt/resources/logo.png"));
        param.put("LOGO", image);
        //param.put("SD", DateUtil.format3(start));
        //param.put("ED", DateUtil.format3(end));
        DebtorsBySalesReportModel dm = new DebtorsBySalesReportModel();
        //dm.customer = c;
        //Thread t = new Thread(dm);
        //t.start();
        JRTableModelDataSource jrtmds = new JRTableModelDataSource(dm);
        JasperPrint print = JasperFillManager.fillReport(inputStream, param, jrtmds);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
//        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.add(viewer);
        this.setSize(dim);
        this.setVisible(true);
        //System.out.print("Done!");

    }
}
