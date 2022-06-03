package com.ftmatters.crawler;

/**
 * PRODUCER/CONSUMER PATTERN
 *
 * A movie Provider provides movie's URL.
 */
public interface MovieURLProvider {

    /**
     * This method provides a new movieURL each time is invoked.
     * Ideally, it should ensure that the URL wasn't returned yet before.
     * @return A String that contains the URL of a movie.
     */
    public String provideMovieURL();
}
