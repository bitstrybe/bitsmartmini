package bt.bitsmartmini.ui;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.util.Duration;
import bt.bitsmartmini.bl.ReceiptBL;
import bt.bitsmartmini.bl.ReturnBL;
import bt.bitsmartmini.bl.StockinBL;
import bt.bitsmartmini.entity.Stockin;
import lxe.utility.date.DateUtil;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

/**
 * FXML Controller class
 *
 * @author JScare
 */
public class DashboardController implements Initializable {

    @FXML
    private Text dailysales;
    @FXML
    private Text weeklysales;
    @FXML
    private Text monthlysales;
    @FXML
    private Text quaterlysales;
    DecimalFormat df = new DecimalFormat("#,###.00");
    @FXML
    private ListView<String> expirylist;
    //private LineChart<String, Number> linechart;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private ChoiceBox<Integer> yearchoicbox;
    @FXML
    private LineChart<String, Number> linechartA;
    @FXML
    private LineChart<String, Number> linechartB;
    @FXML
    private NumberAxis yAxis1;
    @FXML
    private CategoryAxis xAxis1;
    @FXML
    private FlowPane chartbox;

    int year = (Integer.parseInt(DateUtil.formatYY(new Date())));
    @FXML
    private Text refunds;
    @FXML
    private Text credits;
    @FXML
    private Text actualsales;
    @FXML
    private Label refundsCurr;
    @FXML
    private Label CreditsCurr;
    @FXML
    private Label actualsalesCurr;
    @FXML
    private FlowPane dashboardcards;
    @FXML
    private HBox dailysalescard;
    @FXML
    private HBox weeklysalescard;
    @FXML
    private HBox monthlysalescard;
    @FXML
    private HBox annualsalescard;
    @FXML
    private HBox refundscard;
    @FXML
    private HBox actualsalescard;
    @FXML
    private HBox creditscard;
    @FXML
    private Label dailysalesCurr;
    @FXML
    private Label weeklySalesCurr;
    @FXML
    private Label monthlySalesCurr;
    @FXML
    private Label yearlySalesCurr;

    ReceiptBL rc = new ReceiptBL();
    StockinBL sb = new StockinBL();
    ReturnBL rd = new ReturnBL();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        linechartA.getData().clear();
        linechartB.getData().clear();
        yearchoicbox.getItems().add(year - 1);
        yearchoicbox.getItems().add(year);
        if (LoginController.u.getRoles().equals("Administrator") || LoginController.u.getRoles().equals("Supervisor")) {
            getDailySales(year);
            getWeeklySales();
            getMonthlySales();
            getAnnualSales();
            getRefunds();
            getCredits();
            yearchoicbox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                    getSaleChart(yearchoicbox.getItems().get((Integer) number2));
                    getStockChart(yearchoicbox.getItems().get((Integer) number2));
                    getDailySales(yearchoicbox.getItems().get((Integer) number2));
                    getWeeklySales();
                    getMonthlySales();
                    getAnnualSales();
                    getRefunds();
                    getCredits();
                }
            });
            dashboardcards.getChildren().remove(actualsalescard);
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(1.3), e -> {
                        getSaleChart(year);
                        getStockChart(year);
                    })
            );
            timeline.play();

        } else if (LoginController.u.getRoles().equals("Sales")) {
            dashboardcards.getChildren().remove(weeklysalescard);
            dashboardcards.getChildren().remove(monthlysalescard);
            dashboardcards.getChildren().remove(annualsalescard);
            getDailySales(year);
            getRefunds();
            getCredits();
            chartbox.getChildren().remove(linechartA);
            yearchoicbox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                    getStockChart(yearchoicbox.getItems().get((Integer) number2));
                    getDailySales(yearchoicbox.getItems().get((Integer) number2));
                }
            });
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(1.3), e -> {
                        getStockChart(year);
                    })
            );
            timeline.play();
        }
        try {
            getExiryList();
        } catch (Exception ex) {
            expirylist.getItems();
        }
        yearchoicbox.getSelectionModel().select(1);
    }

    public void getExiryList() {
        DateTime today = DateTime.now();
        DateTime dt = new DateTime().plusMonths(1);
        List<Stockin> list = sb.getTwoWeekToExpiry(today.toDate(), dt.toDate());
        ObservableList<Stockin> result = FXCollections.observableArrayList(list);
        expirylist.getItems().clear();
        result.forEach((man) -> {
            String uom = man.getItems().getVomDef() + "" + man.getItems().getVom();
            expirylist.getItems().add(man.getItems().getItemName() + " " + man.getItems().getForm().getFormName() + " " + uom);
        });
    }

    public void getDailySales(int year) {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.5), e -> {
                    //DateTime today = new DateTime(System.currentTimeMillis());
                    DateTime dt = new DateTime().withYear(year).dayOfMonth().getDateTime();
                    System.out.println("today: " + dt.toDate());
                    double val = rc.getDailySalesReceipt(dt.toDate());
                    dailysalesCurr.setText(MainAppController.B.getBCurrency());
                    if (val > 0) {
                        dailysales.setText(String.valueOf(df.format(val)));
                        dailysales.setFill(Paint.valueOf("#6ba16f"));
                    } else {
                        dailysales.setText("0");
                        dailysales.setFill(Paint.valueOf("#999999"));
                    }
                })
        );
        timeline.play();
    }

    public void getWeeklySales() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    DateTime today = new DateTime(System.currentTimeMillis());
                    DateTime sd = new DateTime().withDayOfWeek(DateTimeConstants.MONDAY);
                    double val = rc.getDurationSalesReceipt(sd.toDate(), today.toDate());
                    weeklySalesCurr.setText(MainAppController.B.getBCurrency());
                    if (val > 0) {
                        weeklysales.setText(String.valueOf(df.format(val)));
                        weeklysales.setFill(Paint.valueOf("#8d4747"));
                    }
                })
        );
        timeline.play();
    }

    public void getMonthlySales() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1.5), e -> {
                    DateTime today = new DateTime(System.currentTimeMillis());
                    DateTime sd = new DateTime().dayOfMonth().withMinimumValue();
                    double val = rc.getDurationSalesReceipt(sd.toDate(), today.toDate());
                    monthlySalesCurr.setText(MainAppController.B.getBCurrency());
                    if (val > 0) {
                        monthlysales.setText(String.valueOf(df.format(val)));
                        monthlysales.setFill(Paint.valueOf("#5a4c97"));
                    }
                })
        );
        timeline.play();
    }

    public void getAnnualSales() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(2), e -> {
                    DateTime today = new DateTime(System.currentTimeMillis());
                    double val = rc.getDurationSalesReceipt(getStartOfYear(), today.toDate());
                    yearlySalesCurr.setText(MainAppController.B.getBCurrency());
                    if (val > 0) {
                        quaterlysales.setText(String.valueOf(df.format(val)));
                        quaterlysales.setFill(Paint.valueOf("#a1a187"));
                    }
                })
        );
        timeline.play();
    }

    public void getRefunds() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    DateTime today = new DateTime(System.currentTimeMillis());
                    Double val = rd.getTotalReturnsByDate(today.toDate());
                    if (val != null && val > 0) {
                        refundsCurr.setText(MainAppController.B.getBCurrency());
                        refunds.setText(String.valueOf(df.format(val)));
                        //if (val > 0) {
                        refunds.setFill(Paint.valueOf("#6ba16f"));
                    }
                })
        );
        timeline.play();
    }

    public void getCredits() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1.5), e -> {
                    DateTime today = new DateTime(System.currentTimeMillis());
                    Double val = rd.getTotalReturnsByDate(today.toDate());
                    if (val != null && val > 0) {
                        CreditsCurr.setText(MainAppController.B.getBCurrency());
                        credits.setText(String.valueOf(df.format(val)));
                        //if (val > 0) {
                        credits.setFill(Paint.valueOf("#6ba16f"));
                    }
                })
        );
        timeline.play();
    }

    public void getSaleChart(int year) {
        linechartA.getData().clear();
        xAxis.setLabel("Months");
        yAxis.setLabel("Sales " + MainAppController.B.getBCurrency());
        linechartA.setTitle(year + " Monthly Sales Chart");
        XYChart.Series series = new XYChart.Series();

        series.setName("Monthly Sales Analysis Chart");
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        for (int i = 0; i < 12; i++) {
            double var = rc.getDurationSalesReceipt(getFirstDayOftheMonth(i + 1, year), getLastDayOftheMonth(i + 1, year));
            series.getData().add(new XYChart.Data(months[i], var));
        }
        linechartA.getData().add(series);
        if (LoginController.u.getRoles().equals("Administrator")) {
            for (XYChart.Series<String, Number> s : linechartA.getData()) {
                for (XYChart.Data<String, Number> d : s.getData()) {
                    Tooltip.install(d.getNode(), new Tooltip(
                            "Month : " + d.getXValue().toString() + "\n"
                            + "Sales : " + df.format(d.getYValue())));
                    //Adding class on hover
                    d.getNode().setOnMouseEntered(event -> d.getNode().setStyle("-fx-background-color:  #000"));
                    //Removing class on exit
                    d.getNode().setOnMouseExited(event -> d.getNode().setStyle("-fx-background-color: #000, #ffffff;"));
                }
            }
        }
    }

    public void getStockChart(int year) {
        linechartB.getData().clear();
        xAxis1.setLabel("Months");
        yAxis1.setLabel("Stock");
        linechartB.setTitle(year + " Monthly Stock Chart");
        XYChart.Series series = new XYChart.Series();
        series.setName("Monthly Stock Analysis Chart");
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        for (int i = 0; i < 12; i++) {
            double var = sb.getStockBalance(getFirstDayOftheMonth(i + 1, year), getLastDayOftheMonth(i + 1, year));
            series.getData().add(new XYChart.Data(months[i], var));
        }
        linechartB.getData().add(series);
        for (XYChart.Series<String, Number> s : linechartB.getData()) {
            for (XYChart.Data<String, Number> d : s.getData()) {
                Tooltip.install(d.getNode(), new Tooltip(
                        "Month : " + d.getXValue().toString() + "\n"
                        + "Stock : " + df.format(d.getYValue())));
                //Adding class on hover
                d.getNode().setOnMouseEntered(event -> d.getNode().setStyle("-fx-background-color:  #000"));
                //Removing class on exit
                d.getNode().setOnMouseExited(event -> d.getNode().setStyle("-fx-background-color: #000, #ffffff;"));
            }
        }
    }

    public Date getStartOfYear() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2020);
        cal.set(Calendar.DAY_OF_YEAR, 1);
        Date start = cal.getTime();
        return start;
    }

    public Date getFirstDayOftheMonth(int month, int year) {
        DateTime dt = new DateTime().withYear(year).withMonthOfYear(month).dayOfMonth().withMinimumValue();
        return dt.toDate();
    }

    public Date getLastDayOftheMonth(int month, int year) {
        DateTime dt = new DateTime().withYear(year).withMonthOfYear(month).dayOfMonth().withMaximumValue();
        return dt.toDate();
    }
}
