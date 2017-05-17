package Cliente.control;

import java.awt.EventQueue;


public class Launcher {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiControl login= new GuiControl();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
