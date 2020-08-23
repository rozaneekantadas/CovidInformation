package com.suptodas.diu.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class GlobalInfoActivity extends AppCompatActivity {

    TextView todayBtn, totalBtn, affected, death, recovered;
    Integer bdTotalAffected, bdTotalDeath, bdTotalRecovered, bdTodayAffected, bdTodayDeath, bdTodayRecovered;
    List<Countries> countries = new ArrayList<>();
    String countryCode;
    CountryCodePicker countryCodePicker;
    NumberFormat numberFormat = NumberFormat.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_info);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        countries = (List<Countries>) bundle.getSerializable("globalData");

        todayBtn = findViewById(R.id.todayGlobalData);
        totalBtn = findViewById(R.id.totalGlobalData);
        affected = findViewById(R.id.affectedGlobalData);
        death = findViewById(R.id.deathGlobalData);
        recovered = findViewById(R.id.recoveredGlobalData);
        countryCodePicker = findViewById(R.id.countryCodePicker);

        countryCode = countryCodePicker.getSelectedCountryNameCode();
        getCountryData();

        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                countryCode = countryCodePicker.getSelectedCountryNameCode();
                getCountryData();
            }
        });

        todayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todayBtn.setTextColor(getColor(R.color.white));
                totalBtn.setTextColor(getColor(R.color.whitelight));

                affected.setText(numberFormat.format(bdTodayAffected));
                death.setText(numberFormat.format(bdTodayDeath));
                recovered.setText(numberFormat.format(bdTodayRecovered));
            }
        });

        totalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todayBtn.setTextColor(getColor(R.color.whitelight));
                totalBtn.setTextColor(getColor(R.color.white));

                affected.setText(numberFormat.format(bdTotalAffected));
                death.setText(numberFormat.format(bdTotalDeath));
                recovered.setText(numberFormat.format(bdTotalRecovered));
            }
        });

    }

    public void getCountryData(){

        todayBtn.setTextColor(getColor(R.color.whitelight));
        totalBtn.setTextColor(getColor(R.color.white));

        for(Countries countries1 : countries){
            if(countries1.getCountryCode().equals(countryCode)){
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

    }
}
