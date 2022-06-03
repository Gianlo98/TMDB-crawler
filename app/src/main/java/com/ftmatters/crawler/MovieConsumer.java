package com.ftmatters.crawler;

/**
 * PRODUCER/CONSUMER PATTERN
 *
 * A MovieConsumer gets the URL of a movie, parses it and returns a Movie.
 */
public interface MovieConsumer {
    public Movie consumeMovieURL(String movieURL);
}
