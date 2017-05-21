package Cliente.control;

import java.awt.EventQueue;


public class Launcher {
	/**
	 * Launch the application.
	 */
    private final static String DEFAULT_HOSTNAME = "localhost";
    private final static int DEFAULT_PORT = 5025;
    private static boolean session = true;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteSimplesTCP clienteTCP = new ClienteSimplesTCP();
					clienteTCP.openSocket(DEFAULT_HOSTNAME, DEFAULT_PORT);
					GuiControl login= new GuiControl(clienteTCP);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
