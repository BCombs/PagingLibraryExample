/*
 * Copyright (c) 2018 - Bill Combs
 */

package com.billcombsdevelopment.paginglibraryexample.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName("page")
    private int mPageNumber;
    @SerializedName("total_pages")
    private int mTotalPages;
    @SerializedName("results")
    private List<Movie> mMovieList;

    public MovieResponse(int pageNumber, int totalPages, List<Movie> movieList) {
        this.mPageNumber = pageNumber;
        this.mTotalPages = totalPages;
        this.mMovieList = movieList;
    }

    public int getPageNumber() {
        return mPageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.mPageNumber = pageNumber;
    }

    public int getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(int totalPages) {
        this.mTotalPages = totalPages;
    }

    public List<Movie> getMovieList() {
        return mMovieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.mMovieList = movieList;
    }

    public boolean hasMore() {
        return (mPageNumber < mTotalPages);
    }
}

