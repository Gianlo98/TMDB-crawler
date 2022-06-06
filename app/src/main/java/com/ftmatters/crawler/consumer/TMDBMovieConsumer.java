package com.ftmatters.crawler.consumer;

import com.ftmatters.crawler.Movie;
import com.ftmatters.crawler.MovieURLConsumer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

/**
 * The TMDBMovieConsumer represents a consumer of movie URLs.
 * The main goal of this consumer is to retrieve all the needed information about a movie from the consumed URL.
 */
public class TMDBMovieConsumer implements MovieURLConsumer {

    @Override
    public Movie consumeMovieURL(String movieURL) {
        int movieId = Movie.getMovieIDFromURL(movieURL);
        try {
            Document doc = Jsoup.connect(movieURL).get();

            // The title of a movie is wrapped inside the first <a> tag of the <div class="tile"> element.
            String movieTitle = doc.select(".title").select("a").get(0).text();

            // The image URL is an attribute of the <img class="lazyload" data-src="IMG_URL" /> element
            String imgURL = "https://www.themoviedb.org" + doc.select("img.lazyload").attr("data-src");

            return new Movie(movieId, movieTitle, imgURL);
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }

    }
}
