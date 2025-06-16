package GenericUtilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.SimpleFormatter;

public class JavaUtility {
	

	public int generateRandomNumber()
	{
		Random r = new Random();
		int num = r.nextInt(1000);
		return num;	
	}
	
	public String getcurrentsystemdate()
	{
		Date d= new  Date();
		SimpleDateFormat sim= new SimpleDateFormat("yyyy-MM-dd");
		String currentdate = sim.format(d);
		return currentdate;
	}
	
	public String getdateAfterGivendays(int days) 
	{
		Date d= new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		sim.format(d);
		Calendar c= sim.getCalendar();
		c.add(Calendar.DAY_OF_MONTH, days);
		String date = sim.format(c.getTime());
		return date;
		
		
		
	}

}
