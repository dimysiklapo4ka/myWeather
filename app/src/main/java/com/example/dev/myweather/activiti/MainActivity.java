package com.example.dev.myweather.activiti;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.example.dev.myweather.R;
import com.example.dev.myweather.modelsWeather.Example;
import com.example.dev.myweather.retrofit.RetrofitClient;
import com.example.dev.myweather.retrofit.WeatherInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private TextView mLocalTime;
    private TextView mTempC;
    private TextView mTextWeather;

    private AutoCompleteTextView mAutoCompleteTextView;

    private String[] cityUkraine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityUkraine = new String[] {"Vinnytsia", "Kirovograd", "Poltava", "Kharkov",
                "Dnepropetrovsk", "Lugansk","Exactly", "Kherson", "Donetsk", "Lutsk", "Simferopol",
                "Khmelnitsky", "Zhitomir", "Lviv","Sumy", "Cherkasy", "Zaporozhye", "Nikolaev",
                "Ternopil", "Chernigov", "Ivano-Frankivsk","Odessa", "Uzhhorod", "Chernivtsi", "Kiev"};

        mLocalTime = (TextView)findViewById(R.id.tv_local_time);
        mTempC = (TextView)findViewById(R.id.tv_temp_c);
        mTextWeather = (TextView)findViewById(R.id.tv_text_weather);
        mAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        mAutoCompleteTextView.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, cityUkraine));
        mAutoCompleteTextView.setOnItemClickListener(this);

    }


    public void currentWeather() {

        WeatherInterface api = RetrofitClient.getWeatherInterface();

        Call<Example> call = api.getAllData("3badc9398a6d4a38b47120442170908", mAutoCompleteTextView.getText().toString());

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                Log.e("@@@@", "onResponse: " + response.body());

                if (response.isSuccessful() & response != null) {

                    mLocalTime.setText(response.body().getLocation().getLocaltime());
                    mTempC.setText(response.body().getCurrent().getTempC().toString());
                    mTextWeather.setText(response.body().getCurrent().getCondition().getText());

                }

            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {



            }
        });

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        currentWeather();
    }
}
