package Display;

import cw2.ControlCenter;

public class GUI_test {
 public static void main(String args[]){
 Menu app = new Menu();
  app.register(app, null);
  app.perform();
	}

	public void go(ControlCenter beijing) {
		Menu app = new Menu();
		app.register(app,beijing);
		app.perform();
	}


}


