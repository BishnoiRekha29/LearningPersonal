package com.pagefact.Models;

public class Country {
    private String continent;
    private String country;
    private Integer activeCases;

    public Country(String country, String continent, Integer activeCases)
    {
        this.activeCases=activeCases;
        this.continent=continent;
        this.country=country;
    }

    public String getCountry()
    {
        return this.country;
    }
    public String getContinent()
    {
        return this.continent;
    }
        public Integer getActiveCases()
    {
        return this.activeCases;
    }
}
