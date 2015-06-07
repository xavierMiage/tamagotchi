package plugins;

import java.awt.EventQueue;

import ui.Window;

public class Load {

	public Load() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Création de la vue
					Window frame = new Window();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
