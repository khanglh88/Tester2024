package com.api.auto.testcase;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.auto.utils.PropertiesFileUtils;

public class TC_API_CreateWork {

    private String token;
    private Response response;
    private ResponseBody responseBody;
    private JsonPath jsonBody;

    private String myWork = "Software Testing";
    private String myExperience = "1 năm";
    private String myEducation = "FUNIX";

    @BeforeClass
    public void init() {
				
        String baseUrl = PropertiesFileUtils.getProperty("baseurl");
        String createWorkPath = PropertiesFileUtils.getProperty("createWorkPath");
        token = PropertiesFileUtils.getToken("token");

        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = createWorkPath;
        
        File body = new File("./dataJSON/info.json");
//        Map<String, Object> body = new HashMap<>();
//        body.put("nameWork", myWork);
//        body.put("experience", myExperience);
//        body.put("education", myEducation);

        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("token",token)
                .body(body);

        response = request.post();
        responseBody = response.body();
        jsonBody = responseBody.jsonPath();

        System.out.println("Response Body: " + responseBody.asPrettyString());
    }

    @Test(priority = 0)
    public void TC01_Validate201Created() {
        // Kiểm chứng status code 201
    	assertEquals(201, response.getStatusCode(), "Status Check Failed");
    }

    @Test(priority = 1)
    public void TC02_ValidateWorkId() {
        // Kiểm chứng id
    	assertNotNull(jsonBody.getString("id"), "Id Check Failed");
    }

    @Test(priority = 2)
    public void TC03_ValidateNameOfWorkMatched() {
        // Kiểm chứng tên công việc nhận được có giống lúc tạo
    	String ResponseWork = jsonBody.getString("nameWork");
    	assertEquals(myWork, ResponseWork, "Name Of Work Not Matched!");
    }

    @Test(priority = 3)
    public void TC04_ValidateExperienceMatched() {
        // Kiểm chứng kinh nghiệm nhận được có giống lúc tạo
    	String ResExperience = jsonBody.getString("experience");
    	assertEquals(myExperience, ResExperience, "Name Of Experience Not Match");
    }

    @Test(priority = 4)
    public void TC05_ValidateEducationMatched() {
        // Kiểm chứng học vấn nhận được có giống lúc tạo
    	String ResEducation = jsonBody.getString("education");
    	assertEquals(myEducation, ResEducation, "Name Of Education Not Match");
    }
}