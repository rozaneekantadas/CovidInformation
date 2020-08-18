package com.suptodas.diu.covidtracker;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidDataApi {

    @GET("summary")
    Call<CovidData> getData();

}
