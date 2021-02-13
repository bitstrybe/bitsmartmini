package bt.bitsmartmini.utils;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author JScare
 */
public class Utilities {

    public static String convertDateToString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/YYYY");
        return df.format(date);
    }

    public static Date convertStringToDate(String date) throws ParseException {
        Date dateval = new SimpleDateFormat("dd/MM/yyyy").parse(date);
        return dateval;
    }

    public static LocalDate convertDateToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

//    public static LocalDate parseDate(String formattedDate, String dateFormat) throws ParseException {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
//        String date = formattedDate;
//        LocalDate localDate = LocalDate.parse(date, formatter);
//        return localDate;
//    }
    public static final LocalDate LOCAL_DATE(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    public static Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

//    public static void clearAllField(AnchorPane pane) {
//        for (Node node : pane.getChildren()) {
//            if (node instanceof JFXTextField) {
//                ((JFXTextField) node).clear();
//            } else if (node instanceof ChoiceBox) {
//                ((ChoiceBox) (node)).getSelectionModel().clearSelection();
//            } else if (node instanceof DatePicker) {
//                ((DatePicker) (node)).setValue(null);
//            } else if (node instanceof ComboBox) {
//                ((ComboBox) (node)).setValue(null);
//            } 
//        }
//    }
    public static ArrayList<Node> getAllNode(Parent root) {
        ArrayList<Node> nodes = new ArrayList<>();
        addAllDecendant(root, nodes);
        return nodes;
    }

    private static void addAllDecendant(Parent parent, ArrayList<Node> nodes) {
        for (Node node : parent.getChildrenUnmodifiable()) {
            if (node instanceof JFXTextField) {
                ((JFXTextField) node).clear();
            }
        }
    }

    public static double sumList(List<Double> list) {
        float sum = 0l;
        for (double i : list) {
            sum += i;
        }
        return sum;
    }

    public static void searchListView(ObservableList<String> data, JFXTextField text, JFXListView view) {
        FilteredList<String> filteredData = new FilteredList<>(data, p -> true);
        text.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(e -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (e.toLowerCase().contains(lowerCaseFilter)) {
                    return true; //filter matches first name
                } else if (e.toLowerCase().contains(lowerCaseFilter)) {
                    return true; //filter matches last name
                }
                return false; //Does not match
            });
        });
        view.setItems(filteredData);

    }

    public static double roundToTwoDecimalPlace(double d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }

    public static String convertDigitToCurrency(String val) {
        String newvalue = val;
        // String convert;
        String convert2;
        //String newvalue = String.valueOf(2641231.10);
        int val1 = Integer.parseInt(newvalue.split("\\.")[0]);
        String convert1 = numberToWord(val1).concat(" Ghana Cedis ");
        //  String decimalval = newvalue.split("\\.")[1];

        int val2 = Integer.parseInt(newvalue.split("\\.")[1]);
        if (val2 != 0) {
            String convert = numberToWord(val2).concat(" peswes only");
            convert2 = "and " + convert;
        } else {
            convert2 = "only";
        }
        return convert1.toUpperCase() + convert2.toUpperCase();

    }

    private static String numberToWord(int number) {
        // variable to hold string representation of number 
        String words = "";
        String unitsArray[] = {"zero", "one", "two", "three", "four", "five", "six",
            "seven", "eight", "nine", "ten", "eleven", "twelve",
            "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
            "eighteen", "nineteen"};
        String tensArray[] = {"zero", "ten", "twenty", "thirty", "forty", "fifty",
            "sixty", "seventy", "eighty", "ninety"};

        if (number == 0) {
            return "zero";
        }
        // add minus before conversion if the number is less than 0
        if (number < 0) { // convert the number to a string 
            String numberStr = "" + number;
            // remove minus before the number 
            numberStr = numberStr.substring(1);
            // add minus before the number and convert the rest of number 
            return "minus " + numberToWord(Integer.valueOf(numberStr));
        }
        // check if number is divisible by 1 million
        if ((number / 1000000) > 0) {
            words += numberToWord(number / 1000000) + " million, ";
            number %= 1000000;
        }
        // check if number is divisible by 1 thousand
        if ((number / 1000) > 0) {
            if (number != 1000) {
                words += numberToWord(number / 1000) + " thousand, ";
                number %= 1000;
            } else {
                words += numberToWord(number / 1000) + " thousand ";
                number %= 1000;
            }

        }
        // check if number is divisible by 1 hundred
        if ((number / 100) > 0) {
            int tensvalue = number % 100;
            if (tensvalue != 0) {
                words += numberToWord(number / 100) + " hundred and ";
                number %= 100;
            } else {
                words += numberToWord(number / 100) + " hundred ";
                number %= 100;
            }

        }

        if (number > 0) {
            // check if number is within teens
            if (number < 20) {
                // fetch the appropriate value from unit array 
                words += unitsArray[number];
            } else {
                // fetch the appropriate value from tens array 
                words += tensArray[number / 10];
                if ((number % 10) > 0) {
                    words += "-" + unitsArray[number % 10];
                }
            }
        }

        return words;
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int type, int IMG_WIDTH, int IMG_HEIGHT) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }

    public static void repeatFocus(Node node) {
        Platform.runLater(() -> {
            if (!node.isFocused()) {
                node.requestFocus();
                repeatFocus(node);
            }
        });
    }

}
