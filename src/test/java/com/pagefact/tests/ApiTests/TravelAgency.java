package com.pagefact.tests.ApiTests;
import com.pagefact.Models.Country;
import com.pagefact.pages.HelperClass;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class TravelAgency {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void getTravelSafeCountries() {
        RestAssured.baseURI = "https://covid-193.p.rapidapi.com/";
        RequestSpecification req = RestAssured.given();
        Map<String, String> headers = new HashMap<>();
        headers.put("X-RapidAPI-Key", "1cb97fbf67msh8b65881708e827dp18b85fjsn76d85232c1c3");
        req.header("X-RapidAPI-Host", "covid-193.p.rapidapi.com");
        Response response = req.headers(headers).get("statistics");
        JsonPath jsonPath = response.body().jsonPath();
        int size = jsonPath.getInt("response.size()");
        List<Country> data = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String country = HelperClass.getStringValUsingJsonPath(jsonPath, "response[" + i + "].country");
            String continent = HelperClass.getStringValUsingJsonPath(jsonPath, "response[" + i + "].continent");
            String activeCases = HelperClass.getStringValUsingJsonPath(jsonPath, "response[" + i + "].cases.active");
            if (!(continent.equals("") || country.equals("") || activeCases.equals(""))) {
                Country countryObj = new Country(country, continent, Integer.parseInt(activeCases));
                data.add(countryObj);
            }
        }
        List<String> safeContinents=HelperClass.getSafeContinents(data);
       // System.out.println(safeContinents.get(0)+" "+safeContinents.get(1)+" "+safeContinents.get(2));
        List<Map<String,String>> safeCountries=new ArrayList<>();
        for(int i=0;i<safeContinents.size();i++)
        {
            Map<String,String> myMap=new HashMap<>();
            myMap.putAll(HelperClass.getSafeCountries(safeContinents.get(i),data,3));
            safeCountries.add(myMap);
        }
        System.out.println(safeCountries.size());
        for(int i=0;i<safeCountries.size();i++)
        {
            Map<String,String> countryContinentMap=safeCountries.get(i);
            for(Map.Entry<String,String> entry:countryContinentMap.entrySet())
            {
                System.out.println(entry.getValue()+" "+ entry.getKey());
            }
            System.out.println("----*************--------------------**************");
        }

    }
}
