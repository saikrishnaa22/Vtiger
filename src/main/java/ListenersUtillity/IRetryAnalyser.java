package ListenersUtillity;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class IRetryAnalyser implements IRetryAnalyzer{

	// when testscript  is failed automatically retrying for success on a count .
	int maxrertrys= 5;
	int count= 0;
	@Override
	public boolean retry(ITestResult result) {
		if (count<maxrertrys)
		{
			count++;
			return true;
		}
		return false;
	}

}
