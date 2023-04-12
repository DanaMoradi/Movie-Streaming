package com.example.bbc.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SeriesModel {

    private Long id;
    private String name;
    private String img;
    private String director;
    private String rate_imdb;
    private String seasonsAndEpisodes;
    private Date published;
    private String category_name;
}
