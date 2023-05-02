package com.example.bbc.db.common;

public class ApiConstant {

    //Base url
    public static final String BASE_URL = "http://bbc.mywebcommunity.org/";

    //Slider url
    public static final String SLIDER_URL = BASE_URL + "getSlider.php";
    public static final String GENRE_URL = BASE_URL + "getGenre.php";
    public static final String TOP_MOVIE_URL = BASE_URL + "getMovieInformation.php?" + ApiConstant.CATEGORY + "=" + ApiConstant.CATEGORY_TOP_MOVIE;
    public static final String TOP_MOVIE_ALL_URL = BASE_URL + "getAllMovieInformation.php";
    public static final String SERIES_URL = BASE_URL + "getSeriesInformation.php";
    public static final String SERIES_ALL_URL = BASE_URL + "getAllSeriesInformation.php";
    public static final String CAST_URL = BASE_URL + "getCast.php?id_item=";
    public static final String RELATED_MOVIE_URL = BASE_URL + "getRelated.php?genre=";
    public static final String FIND_BY_ID_URL = BASE_URL + "findById.php?id=";


    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String IMG = "img";
    public static final String DIRECTOR = "director";
    public static final String RATE_IMDB = "rate_imdb";
    public static final String PUBLISHED = "published";
    public static final String TIME = "time";
    public static final String GENRE = "genre_name";
    public static final String CATEGORY = "category_name";
    public static final String DESCRIPTION = "description";
    public static final String CATEGORY_TOP_MOVIE = "top_movie_new";


}
