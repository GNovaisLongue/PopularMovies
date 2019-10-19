package com.example.popularmovies;

import com.google.gson.annotations.SerializedName;

public class Movies {

    @SerializedName("original_title")
    private String movie_title; //title
    @SerializedName("overview")
    private String movie_description; //movies's movie_description
    @SerializedName("poster_path")
    private String image_url; //img url  https://image.tmdb.org/t/p/w500/ + .jpg

//    private String categories;
//    private String rating;
//    private String date;

    public Movies() {
    }

    public Movies(String movie_title,
                  String movie_description,
//                  String categories,
//                  String rating,
//                  String date,
                  String poster_path
    ) {
        this.movie_title = movie_title;
        this.movie_description = movie_description;
        this.image_url = ("https://image.tmdb.org/t/p/w500" + poster_path);
//        this.categories = categories;
//        this.rating = rating;
//        this.date = date;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public String getMovie_description() {
        return movie_description;
    }

    public void setMovie_description(String movie_description) {
        this.movie_description = movie_description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }


    @Override
    public String toString() {
        return "Results{" +
                "title='" + movie_title + '\'' +
                ", overview='" + movie_description + '\'' +
                ", poster_path='" + image_url + '\'' +
                '}';
    }

}
