package com.ftmatters.crawler.consumer;

import com.ftmatters.crawler.Movie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieConsumerTest {

    @Test void testConsumeMovie() {
        TMDBMovieConsumer tmdbMovieConsumer = new TMDBMovieConsumer();
        Movie movie = tmdbMovieConsumer.consumeMovieURL("https://www.themoviedb.org/movie/453395-doctor-strange-in-the-multiverse-of-madness");
        assertEquals("Doctor Strange in the Multiverse of Madness", movie.name());
        assertEquals("https://www.themoviedb.org/t/p/w300_and_h450_bestv2/9Gtg2DzBhmYamXBS1hKAhiwbBKS.jpg", movie.imgURL());
    }
}
