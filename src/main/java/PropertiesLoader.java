import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader 
{
	private Properties prop = new Properties();

	public PropertiesLoader()
	{
		loadProperties();
	}
	
	private void loadProperties()
	{
		InputStream input = null;

		try {

			String filename = "config.properties";
			input = PropertiesLoader.class.getClassLoader().getResourceAsStream(filename);
			if(input==null){
				System.out.println("Sorry, unable to find " + filename);
				return;
			}

			//load a properties file from class path, inside static method
			prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally{
			if(input!=null){
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public String getProperty(String key)
	{
		return prop.getProperty( key ); 
	}

}
