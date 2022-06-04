package com.ftmatters.crawler;

import com.ftmatters.crawler.consumer.TMDBMovieConsumer;
import com.ftmatters.crawler.provider.PopularMovieProvider;

import java.util.concurrent.*;

public class Crawler {

    private static int requestsPerSecond = 1;

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
        MovieURLProvider movieURLProvider = new PopularMovieProvider();
        MovieURLConsumer movieURLConsumer = new TMDBMovieConsumer();
        scheduler.scheduleAtFixedRate(() -> {
            String movieURL = movieURLProvider.provideMovieURL();
            Movie movie = movieURLConsumer.consumeMovieURL(movieURL);
            MovieDB.getInstance().insertMovie(movie);
        }, 0, 1000 / requestsPerSecond, TimeUnit.MILLISECONDS);
    }
}
