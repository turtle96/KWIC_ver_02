package sg.edu.nus.comp.cs3219.ui;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/** Setup UI design and colour
 * */
public class UiSetup {
    
    private static final String COLOUR_BACKGROUND = "#E1F5FE";
    private static final String COLOUR_SCROLLBAR_PANEL = "#B3E5FC";
    private static final String COLOUR_SCROLLBAR = "#01579B";
    
    public static void setup() {
        
        UIManager.put("nimbusBase", Color.decode(COLOUR_SCROLLBAR));
        UIManager.put("nimbusBlueGrey", Color.decode(COLOUR_SCROLLBAR_PANEL));
        UIManager.put("control", Color.decode(COLOUR_BACKGROUND));

        try {
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                    | UnsupportedLookAndFeelException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        
    }

}
