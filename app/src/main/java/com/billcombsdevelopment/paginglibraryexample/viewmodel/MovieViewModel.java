/*
 * Copyright (c) 2018 - Bill Combs
 */

package com.billcombsdevelopment.paginglibraryexample.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.billcombsdevelopment.paginglibraryexample.model.Movie;
import com.billcombsdevelopment.paginglibraryexample.model.MovieDataSource;
import com.billcombsdevelopment.paginglibraryexample.model.MovieDataSourceFactory;

public class MovieViewModel extends ViewModel {
    public LiveData<PagedList<Movie>> moviePagedList;
    public LiveData<PageKeyedDataSource<Integer, Movie>> liveDataSource;

    public MovieViewModel() {
        // Create the date source factory
        MovieDataSourceFactory movieDataSourceFactory = new MovieDataSourceFactory();

        // Using the factory we can get the liveDataSource
        movieDataSourceFactory.getMovieLiveDataSource();

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(MovieDataSource.PAGE_SIZE)
                .build();

        moviePagedList = new LivePagedListBuilder(movieDataSourceFactory, config).build();
    }
}
