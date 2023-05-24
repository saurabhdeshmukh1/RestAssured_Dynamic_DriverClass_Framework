package testClassPackage;

import java.io.IOException;
import java.time.LocalDate;

import org.testng.Assert;

import commonFunctionPackage.API_Common_Function;
import commonFunctionPackage.utility_Common_Function;
import io.restassured.path.json.JsonPath;
import requestRepositoryPackage.Post_req_repository;

public class Post_tc_3 {
	public static void execute() throws IOException { 
		for(int i=0;i<5;i++)  
		{

			int res_status_Code=API_Common_Function.response_statusCode(Post_req_repository.base_URI(),
					Post_req_repository.post_resource(), Post_req_repository.Post_tc_3()); 
			System.out.println(res_status_Code);
			if (res_status_Code == 201) 

			{
				String responseBody = API_Common_Function.response_body(Post_req_repository.base_URI(), 
						Post_req_repository.post_resource(), Post_req_repository.Post_tc_3());
				System.out.println(responseBody);
				Post_tc_3.validator(responseBody, res_status_Code);
				utility_Common_Function.evidencefilecreator("Post_tc_3", Post_req_repository.Post_tc_3(), responseBody);
				break; 
			} 

			else   
			{
				System.out.println("Correct status code is not found, hence retrying the API");
			}
		}
	}

	public static void validator(String responseBody, int res_status_Code) {
		//parse the response body
		JsonPath jsp = new JsonPath(responseBody); 
		String res_name = jsp.getString("name"); 
		String res_job = jsp.getString("job");
		String res_id = jsp.getString("id");
		String res_createdAt = jsp.getString("createdAt");


		//validate response body parameters
		Assert.assertEquals(res_name, "sam");
		Assert.assertEquals(res_job, "tester");
		Assert.assertNotNull(res_id); 
		String currentdate = LocalDate.now().toString();
		Assert.assertEquals(res_createdAt.substring(0,10), currentdate);
		}
	}

