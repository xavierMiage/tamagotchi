package plugins;

import interfaces.IProducer;

import java.io.FileReader;
import java.util.Properties;

public class LoadGame implements IProducer {

	@Override
	public Properties loadData() throws Exception {
		Properties config = new Properties();
		config.load(new FileReader("save.txt"));
		return config;
	}

}