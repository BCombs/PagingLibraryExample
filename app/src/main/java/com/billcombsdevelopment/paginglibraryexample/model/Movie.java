/*
 * Copyright (c) 2018 - Bill Combs
 */

package com.billcombsdevelopment.paginglibraryexample.model;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("id")
    private long mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("poster_path")
    private String mPosterPath;

    public Movie(long id, String title, String posterPath) {
        this.mId = id;
        this.mTitle = title;
        this.mPosterPath = posterPath;
    }

    public long getmId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        this.mPosterPath = posterPath;
    }
}
