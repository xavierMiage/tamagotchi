package interfaces;

import java.util.Properties;

public interface IProducer {

	public Properties loadData() throws Exception;
	
	public void loadUI(Properties p);
}
