package plugins;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import interfaces.IExport;

public class SaveGame implements IExport {

	@Override
	public void exportData(Properties data) throws Exception {
		File fProp = new File("save.txt") ;
		 
		// Charge le contenu de ton objet Properties dans ton fichier properties
		FileOutputStream oStream = new FileOutputStream(fProp) ;
		data.store(oStream,"Jeux tamagotchi") ;
	}

}
