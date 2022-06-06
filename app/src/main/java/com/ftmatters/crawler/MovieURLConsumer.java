package com.ftmatters.crawler;

/**
 * PRODUCER/CONSUMER PATTERN
 *
 * A MovieURLConsumer gets the URL of a movie, parses it and returns a Movie.
 */
public interface MovieURLConsumer {
    Movie consumeMovieURL(String movieURL);
}
