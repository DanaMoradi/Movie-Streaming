package com.example.bbc.model;

import java.sql.Time;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class TopMovieModel {

    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String img;
    @Getter
    @Setter
    private String director;
    @Getter
    @Setter
    private int rate_imdb;
    @Getter
    @Setter
    private Date published;
    @Getter
    @Setter
    private Time time;
    @Getter
    @Setter
    private String category_name;
    @Getter
    @Setter
    private String description;

}
