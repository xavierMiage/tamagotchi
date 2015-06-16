package plugins;

import interfaces.IProducer;

import java.io.FileReader;
import java.util.Properties;

public class LoadTimer implements IProducer {
	@Override
	public Properties loadData() throws Exception {
		Properties config = new Properties();
		config.load(new FileReader("timer.txt"));
		return config;
	}
}
