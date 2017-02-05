package sg.edu.nus.comp.cs3219;

import sg.edu.nus.comp.cs3219.ui.MainView;
import sg.edu.nus.comp.cs3219.ui.UiController;
import sg.edu.nus.comp.cs3219.ui.UiController.KwicUi;
import sg.edu.nus.comp.cs3219.ui.UiSetup;

public class App {

    public static void main(String[] args) {
	    
	    UiSetup.setup();
	    
		KwicUi view = new MainView();
		UiController controller = new UiController(view);
		view.setController(controller);	
	}
    
}
