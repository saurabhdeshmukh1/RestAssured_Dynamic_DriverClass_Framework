import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import commonFunctionPackage.utility_Common_Function;

public class DriverClass {

	public static void main(String[] args) throws IOException, NoSuchMethodException, SecurityException,
	ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//Post_tc_1.execute();

		ArrayList<String> testcaserun = utility_Common_Function.readdataexcel("Test_Runner", "testcasetoexecute");
		int count = testcaserun.size();
		System.out.println(count);

		for(int i=1;i<count;i++)
		{
			String testcasename = testcaserun.get(i);
			System.out.println(testcasename);

			Class<?> testclassname = Class.forName("testClassPackage."+testcasename);
			Method executemethod = testclassname.getDeclaredMethod("execute");
			executemethod.setAccessible(true);

			Object instanceoftestclass = testclassname.getDeclaredConstructor().newInstance();
			executemethod.invoke(instanceoftestclass);
		}
	}
} 