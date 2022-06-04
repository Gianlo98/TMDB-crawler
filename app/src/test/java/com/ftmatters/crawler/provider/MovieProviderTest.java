package com.ftmatters.crawler.provider;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MovieProviderTest {

    @Test
    void testPopularMovieProvider() {
        PopularMovieProvider popularMovieProvider = new PopularMovieProvider();
        for (int i = 0; i < 100; i++) {
            String movieURL = popularMovieProvider.provideMovieURL();
            System.out.println(i + ": " + movieURL);
            assertNotNull(movieURL);
        }
    }

}
