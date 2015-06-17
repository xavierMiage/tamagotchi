package core;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Properties;

import plugins.ErrorPlug;


/*
 * Classe gerant les plugins
 */
public class Platform {


	private Properties plugins = new Properties();
	
	private HashMap<String, Object> plugInstanciates = new HashMap<String, Object>();
	
	private static Platform INSTANCE = null;
	
	private Platform() {}
	 
	/** Point d'acc�s pour l'instance unique du singleton */
	public static Platform getInstance()
	{			
		if (INSTANCE == null)
		{ 	
			INSTANCE = new Platform();	
		}
		return INSTANCE;
	}
	
	public Properties getPlugins() {
		
		// Lire fichier de config
		try {
			plugins.load(new FileReader("plugin.txt"));
			if(!plugins.containsKey("Error")) {
				plugins.put("Error", "plugins.ErrorPlug;interfaces.IError");
			}
			
			for(Object key : plugins.keySet()) {
				if(plugins.get(key).toString().contains(";needed")) {

					if(this.canLaunch((String) key)) {
						this.getPlugin((String) key);
					} else {
						ErrorPlug error = (ErrorPlug) this.getPlugin("Error");
						error.showError("L'application " + (String) key + " ne poss�de pas les interfaces n�cessaires � sont lancement.");
					}
				}
			}
			return plugins;
		} catch (Exception e) {
			ErrorPlug error = (ErrorPlug) this.getPlugin("Error");
			error.showError("Erreur lors du chargement du fichier plugin.txt.");
			e.printStackTrace();
		}
		return null;
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
			ErrorPlug error = new ErrorPlug();
			error.showError("Problème lors du chargement du plugin " + key);
		}
		return plugin;
	}
	
	public Object getPluginNew(String key) {
		Class<?> clazz;
		Object plugin = null;
		String name = null;
		try {
			if(this.plugins.get(key).toString().contains(";")) {
				name = (String) this.plugins.get(key).toString().substring(0, plugins.get(key).toString().indexOf(";"));
			}
			else {
				name = (String) this.plugins.get(key).toString();
			}
			clazz = Class.forName(name);
			plugin = clazz.newInstance();
			this.plugInstanciates.put(key, plugin);
		} catch (Exception e) {
			ErrorPlug error = new ErrorPlug();
			error.showError("Problème lors du chargement du plugin " + key);
		}
		return plugin;
	}
	
	private boolean canLaunch(String plug) {
		if(!plugins.containsKey(plug + ".need")) {
			return true;
		}
		
		boolean contains = true;
		String[] interfacesNeeded = ((String) plugins.get(plug + ".need")).split(";");

		for(String interf : interfacesNeeded) {
			boolean current = false;
			for(Object key : plugins.keySet()) {
				if(!key.toString().contains(".need") && plugins.get(key).toString().contains(interf)) {
					current = true;
				}
			}
			contains &= current;
		}
		return contains;
	}
}
