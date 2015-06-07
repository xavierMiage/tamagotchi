package plugins;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import core.NombreNonValideException;
import core.Platform;

public class Appli {
	
	public Appli() {
		System.out.println("Appli loadée");
		LoadConfig confPlug = (LoadConfig) Platform.getInstance().getPlugin("LoadConfig");
		try {
			Properties conf = confPlug.loadData();
			LoadUI uiPlug = (LoadUI) Platform.getInstance().getPlugin("LoadUI");
			uiPlug.loadUI(conf);
		} catch (Exception e) {
			System.out.println("Erreur lors du chargement de la configuration.");
		}
	}

	public void soccuper(String action) {
		Soccuper soccuper = (Soccuper) Platform.getInstance().getPlugin("Soccuper");
		Tamagotchi tama = (Tamagotchi) Platform.getInstance().getPlugin("Tamagotchi");
		try {
			soccuper.soccuper(action, tama);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NombreNonValideException e) {
			// TODO Auto-generated catch block
			ErrorPlug error = (ErrorPlug) Platform.getInstance().getPlugin("Error");
			error.showError(e.getMessage());
		}
	}
}
