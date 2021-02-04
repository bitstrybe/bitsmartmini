
package bt.bitsmartmini.utils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import bt.bitsmartmini.ui.MainAppController;

/**
 *
 * @author scarface
 */
public class Connector {
        public static void Connecting(MainAppController controller) {
            System.out.println("Connector.Connecting(): Called");
            controller.setLabelText("Bye World");
        }
    }
