
package bt.bitsmartmini.ui;

import com.fazecast.jSerialComm.SerialPort;
import java.io.IOException;
import java.net.ServerSocket;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author jexshizzle
 */
public class Login extends Application {

    private static ServerSocket SERVER_SOCKET;
    
    public static SerialPort comport;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(getClass().getResource("style.css").toExternalForm());
        Image icon = new Image(getClass().getResourceAsStream("/bt/resources/bitsmart-logo-black.jpg"));
        stage.getIcons().add(icon);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        SERVER_SOCKET = new ServerSocket(1334);
        comport = SerialPort.getCommPort("COM4");
        comport.setComPortParameters(9600, 8, 1, SerialPort.NO_PARITY);
        comport.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
        comport.openPort();
        launch(args);
    }

}
