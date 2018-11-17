/*
 * Copyright (c) 2018 - Bill Combs
 */

package com.billcombsdevelopment.paginglibraryexample;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.billcombsdevelopment.paginglibraryexample.model.Movie;
import com.squareup.picasso.Picasso;

public class MovieAdapter extends PagedListAdapter<Movie, MovieAdapter.ViewHolder> {

    private static DiffUtil.ItemCallback<Movie> sDiffCallback =
            new DiffUtil.ItemCallback<Movie>() {
                @Override
                public boolean areItemsTheSame(@NonNull Movie oldMovie, @NonNull Movie newMovie) {
                    // Determine whether the two movies are the same
                    return oldMovie.getmId() == newMovie.getmId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Movie oldMovie, @NonNull Movie newMovie) {
                    // Checks to see if the two items have the same data
                    return oldMovie.equals(newMovie);
                }
            };
    private final String BASE_POSTER_URL = "http://image.tmdb.org/t/p/w185/";
    private Context mContext;

    protected MovieAdapter(@NonNull Context context) {
        super(sDiffCallback);
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.movie_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Movie movie = getItem(position);
        if (movie != null) {
            // Form the complete poster path
            String posterPath = BASE_POSTER_URL + movie.getPosterPath();
            // Load the image into the image view
            Picasso.get().load(posterPath).into(viewHolder.posterImageView);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView posterImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            posterImageView = itemView.findViewById(R.id.list_item_poster_image_view);
        }
    }
}
