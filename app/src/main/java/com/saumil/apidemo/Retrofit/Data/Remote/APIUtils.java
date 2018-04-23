package com.saumil.apidemo.Retrofit.Data.Remote;


/**
 * Created by Saumil on 1/11/2018.
 */

public class APIUtils {

    public static final String BASE_URL = "Your_Base_URL";

    public static APIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

}
