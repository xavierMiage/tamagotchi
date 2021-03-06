package plugins;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Timer;

import javax.swing.JDialog;

import ui.ChoixEspece;
import ui.Window;
import core.NombreNonValideException;
import core.Platform;

public class Appli {
	
	private Window gameUi;
	
	public Appli() {
		System.out.println("Appli load�e");
		LoadConfig confPlug = (LoadConfig) Platform.getInstance().getPlugin("LoadConfig");
		try {
			Properties conf = confPlug.loadData();
			//LoadUI uiPlug = (LoadUI) Platform.getInstance().getPlugin("LoadUI");
			//uiPlug.loadUI(conf);
			this.gameUi = new Window(conf, this);
			this.gameUi.setVisible(true);
		} catch (Exception e) {
			System.out.println("Erreur lors du chargement de la configuration.");
		}
	};

	public void soccuper(String action) {
		Soccuper soccuper = (Soccuper) Platform.getInstance().getPlugin("Soccuper");
		Tamagotchi tama = (Tamagotchi) Platform.getInstance().getPlugin("Tamagotchi");
		try {
			soccuper.soccuper(action, tama);
			this.gameUi.showGame(tama);
		} catch (SecurityException e) {
			ErrorPlug error = (ErrorPlug) Platform.getInstance().getPlugin("Error");
			error.showError(e.getMessage());
		} catch (IllegalAccessException e) {
			ErrorPlug error = (ErrorPlug) Platform.getInstance().getPlugin("Error");
			error.showError(e.getMessage());
		} catch (IllegalArgumentException e) {
			ErrorPlug error = (ErrorPlug) Platform.getInstance().getPlugin("Error");
			error.showError(e.getMessage());
		} catch (InvocationTargetException e) {
			ErrorPlug error = (ErrorPlug) Platform.getInstance().getPlugin("Error");
			error.showError(e.getMessage());
		} catch (NombreNonValideException e ) {
			ErrorPlug error = (ErrorPlug) Platform.getInstance().getPlugin("Error");
			error.showError(e.getMessage());
		} catch (NoSuchMethodException e) {
			ErrorPlug error = (ErrorPlug) Platform.getInstance().getPlugin("Error");
			error.showError(e.getMessage());
		}
	}
	
	public void save() {
		Tamagotchi tama = (Tamagotchi) Platform.getInstance().getPlugin("Tamagotchi");
		
		Map<String, Object> objectAsMap = new HashMap<String, Object>();
	    BeanInfo info;
		try {
			info = Introspector.getBeanInfo(tama.getClass());
			for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
		        Method reader = pd.getReadMethod();
		        if (reader != null && !pd.getName().equals("class"))
		            objectAsMap.put(pd.getName(),reader.invoke(tama));
		    }
		    
		    System.out.println(objectAsMap.toString());
		    
		    Properties properties = new Properties();

		    for (Entry<String, Object> entry : objectAsMap.entrySet()) {
		        properties.put(entry.getKey(), entry.getValue().toString());
		    }
		    
		    ((SaveGame)Platform.getInstance().getPlugin("SaveGame")).exportData(properties);
		} catch (Exception e) {
			ErrorPlug error = (ErrorPlug) Platform.getInstance().getPlugin("Error");
			error.showError(e.getMessage());
		}
	    
	}
	
	// TODO : Fen�tre de d�marrage
	public void newGame(int espece) {
		Tamagotchi tama = (Tamagotchi) Platform.getInstance().getPluginNew("Tamagotchi");
		tama.setEspece(espece);
		
		try {
			this.gameUi.showGame(tama);
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			((ErrorPlug)Platform.getInstance().getPlugin("Error")).showError(e.getMessage());
		}
	}
	
	public void choixEspece() {
		try {
			ChoixEspece dialog = new ChoixEspece(this);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			((ErrorPlug)Platform.getInstance().getPlugin("Error")).showError(e.getMessage());
		}
	}
	
	public void timer(String attr, String val) {
		Tamagotchi tama = (Tamagotchi) Platform.getInstance().getPlugin("Tamagotchi");
		Method m;
		
		Timer t = new Timer();
		TimerTamagotchi tt = new TimerTamagotchi();
		tt.setTama(tama);
		tt.setVal(Integer.parseInt(val));
		tt.setApp(this);
		tt.setAttr(attr);
		t.schedule(tt, 1000);
	}
	
	public void loadGame(){
		try {
			Properties data = ((LoadGame) Platform.getInstance().getPlugin("LoadGame")).loadData();
			Tamagotchi tama = (Tamagotchi) Platform.getInstance().getPlugin("Tamagotchi");
	        
			for (Object key : data.keySet()) {
				Method mG = tama.getClass().getMethod("get"+((String) key).replaceFirst(".",(((String) key).charAt(0)+"").toUpperCase()));
		        Method m = tama.getClass().getMethod("set"+((String) key).replaceFirst(".",(((String) key).charAt(0)+"").toUpperCase()), mG.getReturnType());
		    
		        String mesTypes = mG.getReturnType().getTypeName();
		        switch (mesTypes) {
		        	case "int": 
		        		m.invoke(tama, Integer.parseInt(data.get(key).toString()));
		        		break;
		        	case "boolean":
		        		m.invoke(tama, Boolean.parseBoolean(data.get(key).toString()));
		        		break;
		        	default :
		        		m.invoke(tama, data.get(key).toString());
		        }
		    }
			this.gameUi.showGame(tama);
		} catch (Exception e) {
			e.printStackTrace();
			((ErrorPlug)Platform.getInstance().getPlugin("Error")).showError(e.getMessage());
		}
	}
}
