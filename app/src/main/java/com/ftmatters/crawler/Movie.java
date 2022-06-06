package com.ftmatters.crawler;

/**
 * This record represents a movie with its basic information
 */
public record Movie(int id, String name, String imgURL) {

    /**
     * Returns the ID of the movie encoded in the URL
     * @param movieURL the URL of the movie
     * @return the id of the movie
     */
    public static int getMovieIDFromURL(String movieURL) {
       String movieStrId = movieURL.substring(movieURL.lastIndexOf("/") + 1);
        return Integer.parseInt(movieStrId);
    }

}
