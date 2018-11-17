/*
 * Copyright (c) 2018 - Bill Combs
 */

package com.billcombsdevelopment.paginglibraryexample.model;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.billcombsdevelopment.paginglibraryexample.BuildConfig;
import com.billcombsdevelopment.paginglibraryexample.repository.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Integer, Movie> {

    public static final int FIRST_PAGE = 1;
    public static final int PAGE_SIZE = 20;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Movie> callback) {
        /*
         * Load the initial data. This is called the first time we fetch movies.
         */
        RetrofitClient.getInstance()
                .getMovieApi()
                .getPopularMovies(BuildConfig.API_KEY, FIRST_PAGE)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body().getMovieList(),
                                    null,
                                    FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Movie> callback) {
        /*
         * Loads the previous page of data
         */
        RetrofitClient.getInstance()
                .getMovieApi()
                .getPopularMovies(BuildConfig.API_KEY, params.key)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                        if (response != null) {

                            /*
                             * If params.key is greater than 1, it means we have some previous pages
                             * so we need to decrement the current key by 1 to load the previous page.
                             * If not, there are no previous pages so set its value to null.
                             */
                            Integer key = (params.key > 1) ? params.key - 1 : null;

                            callback.onResult(response.body().getMovieList(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Movie> callback) {
        /*
         * Loads the next page of data
         */
        RetrofitClient.getInstance()
                .getMovieApi()
                .getPopularMovies(BuildConfig.API_KEY, params.key)
                .enqueue(new Callback<MovieResponse>() {
                    @Override
                    public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                        if (response != null) {

                            /*
                             * If there are more pages available, increment the key by 1. If not, set
                             * the value to null.
                             */
                            Integer key = response.body().hasMore() ? params.key + 1 : null;

                            callback.onResult(response.body().getMovieList(), params.key + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieResponse> call, Throwable t) {

                    }
                });

    }
}
