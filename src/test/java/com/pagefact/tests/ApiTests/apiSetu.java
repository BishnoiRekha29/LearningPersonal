package com.pagefact.tests.ApiTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pagefact.Models.*;
import io.restassured.RestAssured;
import io.restassured.authentication.OAuthSignature;
import io.restassured.config.HttpClientConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class apiSetu {
    @BeforeClass
    public void SetUp()
    {
       RestAssured.baseURI="https://cdn-api.co-vin.in/";
    }

    @Test
    public void findSlotsTest()
    {
        RequestSpecification req=RestAssured.given();
        Response response=req.when()
                .formParam("district_id","512")
                .formParam("date","23-07-2022")
                .get("api/v2/appointment/sessions/public/findByDistrict");
        int size=response.body().jsonPath().getInt("sessions.size()");
        int totalCounts=0;
        for(int i=0;i<size;i++)
        {
            if(response.body().jsonPath().get(String.format("sessions[%s].fee_type",i)).equals("Free"))
                totalCounts+=response.body().jsonPath().getInt(String.format("sessions[%s].fee_type.size()",i));
        }
        System.out.println(totalCounts);

    }

    @Test
    public void validate_Response_GetCentreUsingDataAndPincode() {
        RequestSpecification request = RestAssured.given();
        Response response = request.when().formParam("pincode", "152116")
                .formParam("date", "24-07-2022")
                //  .header("Connection","keep-alive")
                .get("api/v2/appointment/sessions/public/calendarByPin");

        // Center center=new Center(624154,"For Army Personal Dependent","Abohar", "Punjab","Fazilka","Abohar",152116,30,74,"09:00:00","18:00:00","Free",sessions);
         String jsonBody="";
        Root rootExpected = null;
        Root rootActual=null;
        try {
            jsonBody = new String(Files.readAllBytes(Paths.get("C:\\Users\\rebishno\\IdeaProjects\\LearningPersonal\\src\\test\\java\\com\\pagefact\\Data\\root.json")));
            rootExpected = new ObjectMapper().readValue(jsonBody, Root.class);
            rootActual=new ObjectMapper().readValue(response.body().asString(), Root.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArrayList<Center> centerList = rootExpected.centers;
        int size= response.body().jsonPath().getInt("centers.size()");
        ArrayList<Center> actualCenterList=rootActual.centers;
        ArrayList<Center> expectedCenterList= rootExpected.centers;
        for(int i=0;i<expectedCenterList.size();i++)
        {
           Center expectedCenter=expectedCenterList.get(0);
            Center actualCenter=actualCenterList.get(0);
            Assert.assertEquals(expectedCenter.center_id,actualCenter.center_id);
            Assert.assertEquals(expectedCenter.district_name,actualCenter.district_name);
            Assert.assertTrue(expectedCenter==actualCenter);
        }
    }
        @Test
           public void getSlot()
        {
            RequestSpecification request=RestAssured.given();
            Response response=request.when().formParam("pincode","152116")
                    .formParam("date","24-07-2022")
                    //  .header("Connection","keep-alive")
                    .get("api/v2/appointment/sessions/public/calendarByPin");

            int size= response.body().jsonPath().getInt("centers.size()");
            System.out.println(size);
            String jsonBody="";
            Slot slot = null;
            try {
                jsonBody = new String(Files.readAllBytes(Paths.get("C:\\Users\\rebishno\\IdeaProjects\\LearningPersonal\\src\\test\\java\\com\\pagefact\\Data\\slot.json")));
                slot = new ObjectMapper().readValue(jsonBody, Slot.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.out.println(slot.time);
        }

        @Test
        public void getMinAgeLimit()
        {

            RequestSpecification request = RestAssured.given();
            Response response = request.when().formParam("pincode", "152116")
                    .formParam("date", "24-07-2022")
                    //  .header("Connection","keep-alive")
                    .get("api/v2/appointment/sessions/public/calendarByPin");

            String jsonBody="";
            Session session = null;
            try {
                jsonBody = new String(Files.readAllBytes(Paths.get("C:\\Users\\rebishno\\IdeaProjects\\LearningPersonal\\src\\test\\java\\com\\pagefact\\Data\\sessions.json")));
                session = new ObjectMapper().readValue(jsonBody, Session.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            ArrayList<String> sessions = new ArrayList<String>();
            sessions.add(String.valueOf(session.min_age_limit));

    }

    @Test
    public void post_getMobileOtp()
    {
        RequestSpecification req=RestAssured.given();
        String jsonBody="";
        Mobile mobileObj=new Mobile();
        mobileObj.mobile="9566467690";
        try {
            jsonBody=new ObjectMapper().writeValueAsString(mobileObj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(jsonBody);
        Response res=req.header("Content-Type","application/json")
                .header("accept","application/json")
                .body(jsonBody)
                .post("api/v2/auth/public/generateOTP").andReturn();
        System.out.println(res.body().jsonPath().get("txnId").toString());
        System.out.println(res.getStatusCode());
        System.out.println(res.then().extract().contentType());
        System.out.println(res.then().extract().headers());

    }

    @Test
    public void PostWithoutMobileNumber()
    {
        RequestSpecification req=RestAssured.given();
        String jsonBody="";
        Mobile mobileObj=new Mobile();
        mobileObj.mobile="";
        try {
            jsonBody=new ObjectMapper().writeValueAsString(mobileObj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(jsonBody);
        Response res=req.header("Content-Type","application/json")
                .header("accept","application/json")
                .body(jsonBody)
                .post("api/v2/auth/public/generateOTP").andReturn();
        int code=res.getStatusCode();
        System.out.println("code is "+code);
        System.out.println(code==HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Test
    public void PostWithoutBody()
    {
        RequestSpecification req=RestAssured.given();
        String jsonBody="";
        Mobile mobileObj=new Mobile();
        mobileObj.mobile="";
        try {
            jsonBody=new ObjectMapper().writeValueAsString(mobileObj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        System.out.println(jsonBody);
        Response res=req.header("Content-Type","application/json")
                .header("accept","application/json")
                .post("api/v2/auth/public/generateOTP").andReturn();
        int code=res.getStatusCode();
        System.out.println("code is "+code);
        System.out.println(code==HttpStatus.SC_BAD_REQUEST);
        System.out.println( res.getBody().jsonPath().get("error").toString());
    }

    @Test
    public void checkTimeOutScenario()
    {  RestAssured.baseURI="https://api-dt.bdo.global/gpl-prvapi-tst2-eur/v2/User/GetPortalUsers?memberFirmID=103&userRoleTypes=All&clientID=36263";

//        RequestConfig requestConfig=RequestConfig.custom()
//                .setConnectTimeout(5000)
//                .setConnectionRequestTimeout(5000)
//                .setSocketTimeout(5000)
//                .build();
//
//        HttpClientConfig httpClientFactory = HttpClientConfig.httpClientConfig()
//                .httpClientFactory(() -> HttpClientBuilder.create()
//                        .setDefaultRequestConfig(requestConfig)
//                        .build());
//
//        RestAssured.config = RestAssured
//                .config()
//                .httpClient(httpClientFactory);
//
        RequestSpecification req=RestAssured.given();

       Response response= //req.auth().oauth2("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IlRpb0d5d3dsaHZkRmJYWjgxM1dwUGF5OUFsVSIsImtpZCI6IlRpb0d5d3dsaHZkRmJYWjgxM1dwUGF5OUFsVSJ9.eyJhdWQiOiJodHRwczovL2Jkb2FhZGR0Lm9ubWljcm9zb2Z0LmNvbS9iZG8td2ViLXBydmFwaS10c3QiLCJpc3MiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC84OTcyMWZhZi0yZjM4LTRkZDQtOWU2Ni02YzUyZTI2YWY3MTcvIiwiaWF0IjoxNTMxMTIwNDU3LCJuYmYiOjE1MzExMjA0NTcsImV4cCI6MTUzMTEyNDM1NywiYWlvIjoiWTJkZ1lIamdtYnRreHpiM3J6Mm5WeG5XeVRXWEF3QT0iLCJhcHBpZCI6IjYwZThkMTQzLTU4MjEtNDg3ZS1hZDIxLTUzMzU0MTUzNWU1YyIsImFwcGlkYWNyIjoiMSIsImVfZXhwIjoyNjI4MDAsImlkcCI6Imh0dHBzOi8vc3RzLndpbmRvd3MubmV0Lzg5NzIxZmFmLTJmMzgtNGRkNC05ZTY2LTZjNTJlMjZhZjcxNy8iLCJvaWQiOiI1MjMwYjMxZi0yYzFmLTRkZTAtOWQ1Mi02ZjllNTgwZmNmYzEiLCJzdWIiOiI1MjMwYjMxZi0yYzFmLTRkZTAtOWQ1Mi02ZjllNTgwZmNmYzEiLCJ0aWQiOiI4OTcyMWZhZi0yZjM4LTRkZDQtOWU2Ni02YzUyZTI2YWY3MTciLCJ1dGkiOiI2bGlVYmdVQnlVMjQ1QVRfV1VvTUFBIiwidmVyIjoiMS4wIn0.s2fEZBrOswBC0vmkEbvGVB_1jkirrI25B29AtvMEq2lozLEJzkRnc5dClny8kFYUz4LDuixbli3R6w023tVoxHBgaGcllXzzET5C7bHUW5viJLmYF8xWYlY9YpsHObzkL4l_zEK58xgg7Py39VK-NKDwwZcmNF3i6sYZ3kjNGfM3km57pZwp1QE9QGD-taqFbfN5m_Ugze1ps8eIA9CopDRq1UJ6UiJ_0kXArVToPb13-mcr07mlHJQfqorogTyE2bgP25hT6XYQxXxVqGpo1mNPg9pO4VIUwZRp9E9cglMtjFYNnkKOaPc75S3VlpVLdZLrQV_mTSiSgHBmi48rCw")
        req.header("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IlRpb0d5d3dsaHZkRmJYWjgxM1dwUGF5OUFsVSIsImtpZCI6IlRpb0d5d3dsaHZkRmJYWjgxM1dwUGF5OUFsVSJ9.eyJhdWQiOiJodHRwczovL2Jkb2FhZGR0Lm9ubWljcm9zb2Z0LmNvbS9iZG8td2ViLXBydmFwaS10c3QiLCJpc3MiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC84OTcyMWZhZi0yZjM4LTRkZDQtOWU2Ni02YzUyZTI2YWY3MTcvIiwiaWF0IjoxNTMxMTIwNDU3LCJuYmYiOjE1MzExMjA0NTcsImV4cCI6MTUzMTEyNDM1NywiYWlvIjoiWTJkZ1lIamdtYnRreHpiM3J6Mm5WeG5XeVRXWEF3QT0iLCJhcHBpZCI6IjYwZThkMTQzLTU4MjEtNDg3ZS1hZDIxLTUzMzU0MTUzNWU1YyIsImFwcGlkYWNyIjoiMSIsImVfZXhwIjoyNjI4MDAsImlkcCI6Imh0dHBzOi8vc3RzLndpbmRvd3MubmV0Lzg5NzIxZmFmLTJmMzgtNGRkNC05ZTY2LTZjNTJlMjZhZjcxNy8iLCJvaWQiOiI1MjMwYjMxZi0yYzFmLTRkZTAtOWQ1Mi02ZjllNTgwZmNmYzEiLCJzdWIiOiI1MjMwYjMxZi0yYzFmLTRkZTAtOWQ1Mi02ZjllNTgwZmNmYzEiLCJ0aWQiOiI4OTcyMWZhZi0yZjM4LTRkZDQtOWU2Ni02YzUyZTI2YWY3MTciLCJ1dGkiOiI2bGlVYmdVQnlVMjQ1QVRfV1VvTUFBIiwidmVyIjoiMS4wIn0.s2fEZBrOswBC0vmkEbvGVB_1jkirrI25B29AtvMEq2lozLEJzkRnc5dClny8kFYUz4LDuixbli3R6w023tVoxHBgaGcllXzzET5C7bHUW5viJLmYF8xWYlY9YpsHObzkL4l_zEK58xgg7Py39VK-NKDwwZcmNF3i6sYZ3kjNGfM3km57pZwp1QE9QGD-taqFbfN5m_Ugze1ps8eIA9CopDRq1UJ6UiJ_0kXArVToPb13-mcr07mlHJQfqorogTyE2bgP25hT6XYQxXxVqGpo1mNPg9pO4VIUwZRp9E9cglMtjFYNnkKOaPc75S3VlpVLdZLrQV_mTSiSgHBmi48rCw")
        .header("Ocp-Apim-Subscription-Key","0b5b8386e9f44f71af858f88307dde2a")
                .get();
        System.out.println(response.getTime());
       response.then().time(Matchers.equalTo(2L),TimeUnit.MICROSECONDS);

//       int code=response.getStatusCode();
//        System.out.println(response.then().log().all());
//        System.out.println(code);
    }

    @Test
    public void getOuthToken(ITestContext TestContext)
    {
        RestAssured.baseURI="https://login.microsoftonline.com/89721faf-2f38-4dd4-9e66-6c52e26af717/oauth2/token";
        RequestSpecification req=RestAssured.given();
        Response response=req.contentType("multipart/form-data").multiPart("grant_type","client_credentials")
                .multiPart("client_id","4783cc86-4473-4050-868e-eb8b1c0d91b7")
                .multiPart("client_secret","/Kr7a0oH9j9PtXcqKG0j/tvKuuoPLMijzM219nTb06o=")
                .multiPart("resource","https://bdoaaddt.onmicrosoft.com/bdo-web-prvapi-tst2")
                .post();
        System.out.println(response.then().log().all());
        System.out.println("--------access token is -----"+response.body().jsonPath().get("access_token").toString());
       // TestContext.setAttribute("accessToken");
    }


}
