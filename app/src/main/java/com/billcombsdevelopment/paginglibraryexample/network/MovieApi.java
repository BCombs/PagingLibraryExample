/*
 * Copyright (c) 2018 - Bill Combs
 */

package com.billcombsdevelopment.paginglibraryexample.network;

import com.billcombsdevelopment.paginglibraryexample.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {

    String POPULAR_MOVIES = "movie/popular";

    @GET(POPULAR_MOVIES)
    Call<MovieResponse> getPopularMovies(@Query("api_key") String apiKey,
                                         @Query("page") int pageNumber);
}
