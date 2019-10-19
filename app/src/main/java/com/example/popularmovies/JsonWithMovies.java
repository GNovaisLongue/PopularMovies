package com.example.popularmovies;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JsonWithMovies {
    @SerializedName("results")
    private List<Movies> mMovies;

    public JsonWithMovies() {
    }

    public JsonWithMovies(List<Movies> mMovies) {
        this.mMovies = mMovies;
    }

    public List<Movies> getmMovies() {
        return mMovies;
    }

    public void setmMovies(List<Movies> mMovies) {
        this.mMovies = mMovies;
    }
}
