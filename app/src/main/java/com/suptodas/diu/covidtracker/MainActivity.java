package com.suptodas.diu.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    CovidDataApi covidDataApi;
    CovidData covidData;
    Global global;
    List<Countries> countries;
    TextView myCountryBtn, globalBtn, todayBtn, totalBtn, affected, death, recovered;
    Integer bdTotalAffected, bdTotalDeath, bdTotalRecovered, bdTodayAffected, bdTodayDeath, bdTodayRecovered;
    Integer TotalAffected, TotalDeath, TotalRecovered, TodayAffected, TodayDeath, TodayRecovered;
    boolean status = false;
    Button worldData;
    NumberFormat numberFormat = NumberFormat.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myCountryBtn = findViewById(R.id.myCountryButton);
        globalBtn = findViewById(R.id.globalButton);
        todayBtn = findViewById(R.id.todayData);
        totalBtn = findViewById(R.id.totalData);
        affected = findViewById(R.id.affectedData);
        death = findViewById(R.id.deathData);
        recovered = findViewById(R.id.recoveredData);
        worldData = findViewById(R.id.worldData);

        myCountryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCountryBtn.setBackground(getDrawable(R.drawable.layout_bg2));
                globalBtn.setBackground(null);
                myCountryBtn.setTextColor(getColor(R.color.black));
                globalBtn.setTextColor(getColor(R.color.white));
                totalBtn.setTextColor(getColor(R.color.white));
                todayBtn.setTextColor(getColor(R.color.whitelight));

                status = false;

                affected.setText(numberFormat.format(bdTotalAffected));
                death.setText(numberFormat.format(bdTotalDeath));
                recovered.setText(numberFormat.format(bdTotalRecovered));
            }
        });

        globalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myCountryBtn.setBackground(null);
                globalBtn.setBackground(getDrawable(R.drawable.layout_bg2));
                myCountryBtn.setTextColor(getColor(R.color.white));
                globalBtn.setTextColor(getColor(R.color.black));
                totalBtn.setTextColor(getColor(R.color.white));
                todayBtn.setTextColor(getColor(R.color.whitelight));
                status = true;

                affected.setText(numberFormat.format(TotalAffected));
                death.setText(numberFormat.format(TotalDeath));
                recovered.setText(numberFormat.format(TotalRecovered));
            }
        });

        todayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todayBtn.setTextColor(getColor(R.color.white));
                totalBtn.setTextColor(getColor(R.color.whitelight));

                if(status){
                    affected.setText(numberFormat.format(TodayAffected));
                    death.setText(numberFormat.format(TodayDeath));
                    recovered.setText(numberFormat.format(TodayRecovered));
                }else {
                    affected.setText(numberFormat.format(bdTodayAffected));
                    death.setText(numberFormat.format(bdTodayDeath));
                    recovered.setText(numberFormat.format(bdTodayRecovered));
                }
            }
        });

        totalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todayBtn.setTextColor(getColor(R.color.whitelight));
                totalBtn.setTextColor(getColor(R.color.white));

                if(status){
                    affected.setText(numberFormat.format(TotalAffected));
                    death.setText(numberFormat.format(TotalDeath));
                    recovered.setText(numberFormat.format(TotalRecovered));
                }else {
                    affected.setText(numberFormat.format(bdTotalAffected));
                    death.setText(numberFormat.format(bdTotalDeath));
                    recovered.setText(numberFormat.format(bdTotalRecovered));
                }
            }
        });

        worldData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), GlobalInfoActivity.class);
                intent.putExtra("globalData", (Serializable) countries);
                startActivity(intent);
            }
        });

    }

    private void getData() {

        Call<CovidData> call = covidDataApi.getData();

        call.enqueue(new Callback<CovidData>() {
            @Override
            public void onResponse(Call<CovidData> call, Response<CovidData> response) {

                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Error Code: "+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                covidData = response.body();

                global = covidData.getGlobal();
                countries = covidData.getCountries();

                for(Countries countries1 : countries){
                    if (countries1.getCountryCode().equals("BD")){
                        bdTodayAffected = countries1.getNewConfirmed();
                        bdTodayDeath = countries1.getNewDeaths();
                        bdTodayRecovered = countries1.getNewRecovered();
                        bdTotalAffected = countries1.getTotalConfirmed();
                        bdTotalDeath = countries1.getTotalDeaths();
                        bdTotalRecovered = countries1.getTotalRecovered();
                    }
                }

                affected.setText(numberFormat.format(bdTotalAffected));
                death.setText(numberFormat.format(bdTotalDeath));
                recovered.setText(numberFormat.format(bdTotalRecovered));

                worldData.setEnabled(true);

                TodayAffected = global.getNewConfirmed();
                TodayDeath = global.getNewDeaths();
                TodayRecovered = global.getNewRecovered();
                TotalAffected = global.getTotalConfirmed();
                TotalDeath = global.getTotalDeaths();
                TotalRecovered = global.getTotalRecovered();
            }

            @Override
            public void onFailure(Call<CovidData> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Server is busy! Try again later", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19api.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        covidDataApi = retrofit.create(CovidDataApi.class);

        getData();
    }
}
