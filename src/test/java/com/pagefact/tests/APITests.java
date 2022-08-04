package com.pagefact.tests;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public  class APITests {
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://d.pub.network/v2/init";
        // RestAssured.port = 443;
    }

    @Test
    public void givenUrl_whenSuccessOnGetsResponseAndJsonHasRequiredKV_thenCorrect() {
        // get("https://d.pub.network/v2/init?siteId=1801&env=PROD").then().statusCode(200).assertThat().body("pubfigSettings.fsdata.pushdownZIndex", equalTo(0)).log().all();
        Response res = get("?siteId=1801&env=PROD");
        int code = res.statusCode();
        Assert.assertEquals(code, 200);
    }

    @Test
    public void givenUrl_whenSuccessOnGetsResponseAndJsonHasRequiredKV() {
        // get("https://d.pub.network/v2/init?siteId=1801&env=PROD").then().statusCode(200).assertThat().body("pubfigSettings.fsdata.pushdownZIndex", equalTo(0)).log().all();
        get("?siteId=1801&env=Test").then().statusCode(200).assertThat().extract().contentType().equals("json");

    }

    @Test
    public void deserializeTest() {
        get("https://d.pub.network/v2/init?siteId=1801&env=PROD").then().statusCode(200).assertThat().body("pubfigSettings.fsdata.pushdownZIndex", equalTo(0)).log().all();


    }
    @Test
    public void UserRegistrationSuccessful() {
        RestAssured.baseURI ="https://demoqa.com";
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("UserName", "test_rest");
        requestParams.put("Password", "rest@123");
        request.body(requestParams.toJSONString());
        Response response = request.post("/Account/v1/User");
        ResponseBody body = response.getBody();
        System.out.println(response.body().asString());
        if(response.statusCode() == 200) {

// Deserialize the Response body into JSONFailureResponse
            JSONFailureResponse responseBody = body.as(JSONFailureResponse.class);

// Use the JSONFailureResponse class instance to Assert the values of Response.
            Assert.assertEquals("User already exists", responseBody.FaultId);
            Assert.assertEquals("FAULT_USER_ALREADY_EXISTS", responseBody.fault);
        } else if (response.statusCode() == 201) {

// Deserialize the Response body into JSONSuccessResponse
            JSONSuccessResponse responseBody = body.as(JSONSuccessResponse.class);

// Use the JSONSuccessResponse class instance to Assert the values of response.
            Assert.assertEquals("OPERATION_SUCCESS", responseBody.SuccessCode);
            Assert.assertEquals("Operation completed successfully", responseBody.Message);
        }
    }
}
