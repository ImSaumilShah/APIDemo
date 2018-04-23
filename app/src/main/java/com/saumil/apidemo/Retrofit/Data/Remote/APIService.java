package com.saumil.apidemo.Retrofit.Data.Remote;


import com.saumil.apidemo.Retrofit.Data.Model.CountryRespnse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Saumil on 1/11/2018.
 */

public interface APIService {

    @GET("END_POINT_URL")
    Call<CountryRespnse> getCountryDataList();

}
