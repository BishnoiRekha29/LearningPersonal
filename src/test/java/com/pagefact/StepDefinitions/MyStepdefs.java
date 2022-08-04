package com.pagefact.StepDefinitions;

import Cucumber.ScenarioContext;
import com.pagefact.pages.BasePage;
import enums.Context;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class MyStepdefs  {
    TestContext testContext;
    BasePage basePage;
    public MyStepdefs(TestContext context)
    {
        this.testContext=context;
        this.basePage=testContext.getBasePage();

    }
    @Given("I send a {string} request at endpoint {string}")
    public void iSendARequestAtEndpoint(String arg0, String arg1) {
        RestAssured.baseURI = "https://covid-193.p.rapidapi.com/";
        RequestSpecification req = RestAssured.given();
        Map<String, String> headers = new HashMap<>();
        headers.put("X-RapidAPI-Key", "1cb97fbf67msh8b65881708e827dp18b85fjsn76d85232c1c3");
        req.header("X-RapidAPI-Host", "covid-193.p.rapidapi.com");
        Response response = req.headers(headers).get("statistics");

    }

    @Given("I have opened lambdatest Home Page")
    public void iHaveOpenedLambdatestHomePage() {
        basePage.HomePage().loadPage();
        testContext.scenarioContext.setContext(Context.NAME,"Rekha");
    }

    @Then("print my Name")
    public void printMyName() {
        System.out.println(testContext.getScenarioContext().getContext(Context.NAME));
    }
}
