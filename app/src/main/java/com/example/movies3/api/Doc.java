package com.example.movies3.api;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Doc implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("year")
    private int year;
    @SerializedName("description")
    private String description;
    @SerializedName("rating")
    //@Embedded
    private Rating rating;
    @SerializedName("poster")
    //@Embedded
    private Poster poster;
    @SerializedName("videos")
    //@Embedded
    private VideoTypes videos;

    public Doc(int id, String name, int year, String description, Rating rating, Poster poster, VideoTypes videos) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.description = description;
        this.rating = rating;
        this.poster = poster;
        this.videos = videos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public Poster getPoster() {
        return poster;
    }

    public void setPoster(Poster poster) {
        this.poster = poster;
    }

    public VideoTypes getVideos() {
        return videos;
    }

    public void setVideos(VideoTypes videos) {
        this.videos = videos;
    }
}
