package com.example.popularmovies;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//GSON - Unused
public class MainResults {
    @SerializedName("results")
    @Expose
    private List<Movies> results = null;

    public List<Movies> getResults() {
        return results;
    }

    //"results" has Movies
    public void setResults(List<Movies> results) {
        this.results = results;
    }

}