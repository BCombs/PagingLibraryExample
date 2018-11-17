/*
 * Copyright (c) 2018 - Bill Combs
 */

package com.billcombsdevelopment.paginglibraryexample;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

import com.billcombsdevelopment.paginglibraryexample.model.Movie;
import com.billcombsdevelopment.paginglibraryexample.viewmodel.MovieViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, getSpanCount()));
        mRecyclerView.setHasFixedSize(true);

        MovieViewModel movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        final MovieAdapter movieAdapter = new MovieAdapter(this);

        // Begin observing the data
        movieViewModel.moviePagedList.observe(this, new Observer<PagedList<Movie>>() {
            @Override
            public void onChanged(@Nullable PagedList<Movie> movies) {
                movieAdapter.submitList(movies);
            }
        });

        // Set the adapter
        mRecyclerView.setAdapter(movieAdapter);
    }

    /**
     * Determines how many columns the GridLayout should have based poster width and screen size
     * @return int - number of columns
     */
    private int getSpanCount() {
        // Width of movie poster in pixels
        int posterWidth = 500;
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        // Get the screen width in pixels
        float screenWidth = metrics.widthPixels;

        return Math.round(screenWidth / posterWidth);
    }
}
