package com.ftmatters.crawler;

import com.ftmatters.crawler.provider.NoAvailableMovieException;

/**
 * PRODUCER/CONSUMER PATTERN
 *
 * A MovieURLProvider provides movie's URL.
 */
public interface MovieURLProvider {

    /**
     * This method provides a new movieURL each time is invoked.
     * Ideally, it should ensure that the URL wasn't returned yet before.
     * @return A String that contains the URL of a movie.
     */
    public String provideMovieURL() throws NoAvailableMovieException;
}
