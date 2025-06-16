package GenericUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JasonFileUtility {
	
	
	public String fetchdatafromdatafile( String key ) throws Exception 
	{
		JSONParser  parse= new JSONParser(); 
		Object obj = parse.parse( new FileReader("./src/test/resources/V-tigerCommondata.json"));
		
		JSONObject json = (JSONObject) obj;
	String data = (String)json.get(key);
		
		return data;
	}
	

}
