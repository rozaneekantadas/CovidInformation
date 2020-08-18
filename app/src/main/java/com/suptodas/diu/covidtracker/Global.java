package com.suptodas.diu.covidtracker;

import com.google.gson.annotations.SerializedName;

public class Global {

    @SerializedName("NewConfirmed")
    int NewConfirmed;

    @SerializedName("TotalConfirmed")
    int TotalConfirmed;

    @SerializedName("NewDeaths")
    int NewDeaths;

    @SerializedName("TotalDeaths")
    int TotalDeaths;

    @SerializedName("NewRecovered")
    int NewRecovered;

    @SerializedName("TotalRecovered")
    int TotalRecovered;


    public int getNewConfirmed() {
        return NewConfirmed;
    }

    public int getTotalConfirmed() {
        return TotalConfirmed;
    }

    public int getNewDeaths() {
        return NewDeaths;
    }

    public int getTotalDeaths() {
        return TotalDeaths;
    }

    public int getNewRecovered() {
        return NewRecovered;
    }

    public int getTotalRecovered() {
        return TotalRecovered;
    }
}
