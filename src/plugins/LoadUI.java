package plugins;

import java.awt.EventQueue;
import java.util.Properties;

import ui.Window;
import interfaces.IProducer;

public class LoadUI implements IProducer{

	private Window frame;
	
	@Override
	public Properties loadData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadUI(Properties conf) {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Création de la vue*/
					this.frame = new Window(conf);
					frame.setVisible(true);
				/*} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});*/
		
	}
	
	public void updUI() {
		this.frame.showGame();
	}

}
