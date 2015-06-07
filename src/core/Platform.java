package core;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import plugins.ErrorPlug;
import ui.Window;

/*
 * Classe gérant les plugins
 */
public class Platform {


	private Properties plugins = new Properties();
	
	private HashMap<String, Object> plugInstanciates = new HashMap<String, Object>();
	
	private static Platform INSTANCE = null;
	
	private Platform() {}
	 
	/** Point d'accès pour l'instance unique du singleton */
	public static Platform getInstance()
	{			
		if (INSTANCE == null)
		{ 	
			INSTANCE = new Platform();	
		}
		return INSTANCE;
	}
	// Constructeur
	/*public Platform() {
		try {
			// Chargement de la configuration
			LoadData conf = (LoadData) this.getPlugin(ILoad.class);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						// Création de la vue
						Window frame = new Window(conf.doSomething("config.txt"), Platform.this);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}*/
	
	public Properties getPlugins() {
		
		// Lire fichier de config
		Properties config = new Properties();
		try {
			plugins.load(new FileReader("plugin.txt"));
			Class<?> clazz;
			Object o;
			String tempKey;
			String tempClass;
			for(Object key : plugins.keySet()) {
				if(plugins.get(key).toString().contains(";needed")) {
					tempKey = (String) key;
					tempClass=plugins.get(key).toString().substring(0, plugins.get(key).toString().indexOf(";"));
					o = this.getPlugin(tempKey);
				}
			}
		} catch (Exception e) {
			ErrorPlug error = (ErrorPlug) this.getPlugin("Error");
			error.showError("Erreur lors du chargement du fichier plugin.txt.");
		}
		return plugins;
	}
	
	public Object getPlugin(String key) {
		Class<?> clazz;
		Object plugin = null;
		String name = null;
		try {
			if(!this.plugInstanciates.containsKey(key)) {
				if(this.plugins.get(key).toString().contains(";")) {
					name = (String) this.plugins.get(key).toString().substring(0, plugins.get(key).toString().indexOf(";"));
				}
				else {
					name = (String) this.plugins.get(key).toString();
				}
				clazz = Class.forName(name);
				plugin = clazz.newInstance();
				this.plugInstanciates.put(key, plugin);
			}
			else {
				plugin = this.plugInstanciates.get(key);
			}
		} catch (Exception e) {
			ErrorPlug error = (ErrorPlug) this.getPlugin("Error");
			error.showError("Problème lors du chargement du plugin " + key);
		}
		return plugin;
	}
}
