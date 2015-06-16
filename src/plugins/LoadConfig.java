package plugins;

import java.io.FileReader;
import java.util.Properties;

import interfaces.IProducer;

public class LoadConfig implements IProducer {

	@Override
	public Properties loadData() throws Exception {
		Properties config = new Properties();
		config.load(new FileReader("config.txt"));
		return config;
	}

}
