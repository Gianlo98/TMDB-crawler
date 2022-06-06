package com.ftmatters.crawler;

import com.ftmatters.crawler.consumer.TMDBMovieConsumer;
import com.ftmatters.crawler.provider.NoAvailableMovieException;
import com.ftmatters.crawler.provider.PopularMovieProvider;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Crawler {

    private static int requestsPerSecond = 1;

    public static void main(String[] args) {

        if (System.getenv("CRAWL_RATIO") != null) {
            try {
                requestsPerSecond = Integer.parseInt(System.getenv("CRAWL_RATIO"));
            } catch (NumberFormatException exception) {
                System.err.println("Invalid value of CRAWL_RATIO");
            }
        }

        System.out.println("CRAWL_RATIO: " + requestsPerSecond);

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(requestsPerSecond);
        try {
            MovieURLProvider movieURLProvider = new PopularMovieProvider();

            MovieURLConsumer movieURLConsumer = new TMDBMovieConsumer();
            MovieDB movieDB = MovieDB.getInstance();

            scheduler.scheduleAtFixedRate(() -> {
                try {
                    String movieURL = movieURLProvider.provideMovieURL();
                    if (movieURL != null) {
                        Movie movie = movieURLConsumer.consumeMovieURL(movieURL);
                        movieDB.insertMovie(movie);
                    }
                } catch (NoAvailableMovieException e) {
                    System.out.println("There aren't any available movies");
                    System.exit(0);
                }
            }, 0, 1000 / requestsPerSecond, TimeUnit.MILLISECONDS);

        } catch (NoAvailableMovieException e) {
            System.out.println("There aren't any available movies");
            System.exit(1);
        }
    }
}
