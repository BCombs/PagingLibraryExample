/*
 * Copyright (c) 2018 - Bill Combs
 */

package com.billcombsdevelopment.paginglibraryexample.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static RetrofitClient mClient;
    private Retrofit retrofit;

    private RetrofitClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (mClient == null) {
            mClient = new RetrofitClient();
        }
        return mClient;
    }

    public MovieApi getMovieApi() {
        return retrofit.create(MovieApi.class);
    }
}
