
package com.api.auto.testcase;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.auto.utils.PropertiesFileUtils;

public class TC_API_Login {

	private String account;
	private String password;
	private Response response;
	private ResponseBody resBody;
	private JsonPath jsonBody;
	
	@BeforeClass
	public void init() {
		
		
		String baseUrl = PropertiesFileUtils.getProperty("baseurl");
		String loginPath = PropertiesFileUtils.getProperty("loginPath");
		account = PropertiesFileUtils.getProperty("account");
		password = PropertiesFileUtils.getProperty("password");
		
		RestAssured.baseURI = baseUrl;
		RestAssured.basePath = loginPath;

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("account", account);
		body.put("password", password);

		RequestSpecification request = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(body);

		response = request.post();
		resBody = response.getBody();
		jsonBody = resBody.jsonPath();

		System.out.println("Response Body: " + resBody.asPrettyString());	
	}	
	

	@Test(priority = 0)
	public void TC01_Validate200Ok() {
		// Kiểm chứng status code

		assertEquals(200, response.getStatusCode(), "Status Check Failed!");
	}

	@Test(priority = 1)
	public void TC02_ValidateMessage() {
		// Kiểm chứng response body có chứa trường message và giá trị "Đăng nhập thành công"

		assertTrue(jsonBody.getString("message").contains("Đăng nhập thành công"), "Message Check Failed");
	}

	@Test(priority = 2)
	public void TC03_ValidateToken() {
		// Kiểm chứng response body có chứa trường token hay không
		// Lưu lại token
		assertNotNull(jsonBody.getString("token"), "Check Token Failed");
		String token = jsonBody.getString("token");
		PropertiesFileUtils.saveToken(token);
	}

	@Test(priority = 3)
	public void TC05_ValidateUserType() {
		// Kiểm chứng response body có chứa thông tin user và trường type hay không
		// Kiểm chứng trường type có phải là "UNGVIEN"

		assertNotNull(resBody.asString().contains("user") && resBody.asString().contains("type"), "Check Type Failed");

		String ResponseType = jsonBody.getString("user.type");
		assertEquals("UNGVIEN", ResponseType, "User and Type not match!");
	}

	@Test(priority = 4)
	public void TC06_ValidateAccount() {
		// Kiểm chứng response chứa thông tin user và trường account hay không
		// Kiểm chứng trường account có khớp với account đăng nhập
		// Kiểm chứng response chứa thông tin user và trường password hay không
		// Kiểm chứng trường password có khớp với password đăng nhập

		assertNotNull(jsonBody.getString("user").contains("account"), "Check Account Failed");

		String bodyAccount = PropertiesFileUtils.getProperty("account");
		String ResponseAccount = jsonBody.getString("user.account");
		assertEquals(bodyAccount, ResponseAccount, "User and Account not match");

		assertNotNull(jsonBody.getString("user").contains("password"), "Check Password Failed");

		String bodyPassword = PropertiesFileUtils.getProperty("password");
		String ResponsePassword = jsonBody.getString("user.password");
		assertEquals(bodyPassword, ResponsePassword, "User and Password not match");
	}

}