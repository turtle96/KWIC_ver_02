package sg.edu.nus.comp.cs3219;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import sg.edu.nus.comp.cs3219.ui.MainView;
import sg.edu.nus.comp.cs3219.ui.UiController;
import sg.edu.nus.comp.cs3219.ui.UiController.KwicUi;

public class App {
	public static void main(String[] args) {
	    
	    setLookAndFeel();
	    
		KwicUi view = new MainView();
		UiController controller = new UiController(view);
		view.setController(controller);	
	}

    private static void setLookAndFeel() {

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
