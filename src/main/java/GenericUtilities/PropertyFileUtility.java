package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author sai
 * This class is created to acess the property file
 *
 */
public class PropertyFileUtility {
	/**
	 * This is a method used to fetch the data from the property file  
	 * @param key
	 * @return
	 * @throws IOException
	 */
	
	public String fetchdatafrompropertyfile(String key) throws IOException
	{
		FileInputStream pfis = new FileInputStream("./src/test/resources/CommonData.properties");
		Properties p = new Properties();
		p.load(pfis);
	String value = p.getProperty(key);
	return value;
	}
	/**
	 * This is a method used to write back the data from the property file  
	 * @param key
	 * @param value
	 * @throws IOException
	 */

	public void Writebackdatatopropertyfile(String key, String value ) throws IOException
	{
		FileInputStream pfis = new FileInputStream("./src/test/resources/CommonData.properties");
	Properties p = new Properties();
	p.load(pfis);
	p.put(key, value);
	
	FileOutputStream pfos = new FileOutputStream("./src/test/resources/CommonData.properties");
	p.store(pfos, "Update Successfully");
	}
	
	
}
