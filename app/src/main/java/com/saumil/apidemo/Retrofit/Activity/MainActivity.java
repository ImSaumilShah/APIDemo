package com.saumil.apidemo.Retrofit.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.saumil.apidemo.R;
import com.saumil.apidemo.Retrofit.Data.Model.CountryData;
import com.saumil.apidemo.Retrofit.Data.Model.CountryRespnse;
import com.saumil.apidemo.Retrofit.Data.Remote.APIService;
import com.saumil.apidemo.Retrofit.Data.Remote.APIUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    List<CountryData> countrylist;
    List<String> listitem = new ArrayList<>();
    private String countryname, code, countrylistitem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner countrycode = (Spinner) findViewById(R.id.countrycode);

        APIService apiService = APIUtils.getAPIService();
        Call<CountryRespnse> call = apiService.getCountryDataList();
        call.enqueue(new Callback<CountryRespnse>() {
            @Override
            public void onResponse(Call<CountryRespnse> call, Response<CountryRespnse> response) {
                int status = response.body().getStatus();
                if(status == 1){
                    countrylist = response.body().getData();
                    countrylistset(countrylist,listitem);
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this,R.layout.spinneritem,R.id.spinnerlistitem,listitem);
                    countrycode.setAdapter(arrayAdapter);
                    countrycode.setSelection(100);
                }
            }

            @Override
            public void onFailure(Call<CountryRespnse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "timeout", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void countrylistset(List<CountryData> countrylist, List<String> listitem) {

        int i;
        for (i = 0; i< countrylist.size(); i++){
            countryname = countrylist.get(i).getName();
            code = countrylist.get(i).getPhonecode();
            countrylistitem = countryname + "(+" + code + ")";
            listitem.add(countrylistitem);
        }


    }
}
