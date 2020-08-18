package com.suptodas.diu.covidtracker;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Countries implements Serializable {

    @SerializedName("Country")
    String country;

    @SerializedName("CountryCode")
    String countryCode;

    @SerializedName("NewConfirmed")
    Integer newConfirmed;

    @SerializedName("TotalConfirmed")
    Integer totalConfirmed;

    @SerializedName("NewDeaths")
    Integer newDeaths;

    @SerializedName("TotalDeaths")
    Integer totalDeaths;

    @SerializedName("NewRecovered")
    Integer newRecovered;

    @SerializedName("TotalRecovered")
    Integer totalRecovered;

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Integer getNewConfirmed() {
        return newConfirmed;
    }

    public Integer getTotalConfirmed() {
        return totalConfirmed;
    }

    public Integer getNewDeaths() {
        return newDeaths;
    }

    public Integer getTotalDeaths() {
        return totalDeaths;
    }

    public Integer getNewRecovered() {
        return newRecovered;
    }

    public Integer getTotalRecovered() {
        return totalRecovered;
    }
}
