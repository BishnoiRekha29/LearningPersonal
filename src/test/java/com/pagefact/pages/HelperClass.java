package com.pagefact.pages;

import com.Utilities.MapUtil;
import com.pagefact.Models.Country;
import io.restassured.path.json.JsonPath;

import java.util.*;

/**
 * Hello world!
 *
 */
public class HelperClass
{

    public static String getStringValUsingJsonPath(JsonPath jpathObj,String path)
    {
        return (jpathObj.get(path)==null)?"":jpathObj.get(path).toString();
    }

    public static List<String> getSafeContinents(List<Country> data) {
        List<String> safeContinents=new ArrayList<>();
        Map<String,Integer> continentCasesMap=new HashMap<>();
        for(int i=0;i<data.size();i++) {
            Country country = data.get(i);
            if (continentCasesMap.containsKey(country.getContinent())) {
                Integer existingCases = continentCasesMap.get(country.getContinent());
                continentCasesMap.replace(country.getContinent(), existingCases, country.getActiveCases() + existingCases);
            }
            else {
                continentCasesMap.put(country.getContinent(), country.getActiveCases());
            }
        }
        MapUtil comparator=new MapUtil(continentCasesMap);
        Map<String,Integer> sortedMap=new TreeMap<>(comparator.reversed());
        sortedMap.putAll(continentCasesMap);
        int continentCount=0;
        for(Map.Entry<String,Integer> entry:sortedMap.entrySet())
        {
            safeContinents.add(entry.getKey());
            continentCount++;
            if(continentCount==3)
            {
                break;
            }
        }
        return safeContinents;
    }


    public static Map<String,String> getSafeCountries(String continent, List<Country> data, int noOfCountries) {
        Map<String,Integer> safeCountries=new HashMap<>();
        Map<String,String> safeCountryMap=new HashMap<>();
        for(int i=0;i<data.size();i++)
        {
            Country country=data.get(i);
            if(country.getContinent().equals(continent))
            {
                safeCountries.put(country.getCountry(),country.getActiveCases());
            }
        }
        MapUtil comparator=new MapUtil(safeCountries);
        Map<String,Integer> sortedMap=new TreeMap<>(comparator.reversed());
        sortedMap.putAll(safeCountries);
        int count=0;
        for(Map.Entry<String,Integer> entry:sortedMap.entrySet()) {
            safeCountryMap.put(entry.getKey(),continent);
            count++;
            if (noOfCountries == count) {
                break;
            }
        }
        return safeCountryMap;
    }
}
