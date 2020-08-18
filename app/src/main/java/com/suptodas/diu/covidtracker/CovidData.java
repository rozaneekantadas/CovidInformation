package com.suptodas.diu.covidtracker;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CovidData {

    @SerializedName("Global")
    Global global;

    @SerializedName("Countries")
    List<Countries> countries;

    public Global getGlobal() {
        return global;
    }

    public List<Countries> getCountries() {
        return countries;
    }
}
