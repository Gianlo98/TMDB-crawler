package com.ftmatters.crawler.consumer;

import com.ftmatters.crawler.Movie;
import com.ftmatters.crawler.MovieURLConsumer;
import com.ftmatters.crawler.TMDBRequestBodyBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class TMDBMovieConsumer implements MovieURLConsumer {

    @Override
    public Movie consumeMovieURL(String movieURL) {
        try {
            Document doc = Jsoup.connect(movieURL)
                    .get();
            String movieTitle = doc.select(".title").select("a").get(0).text();
            String imgURL = "https://www.themoviedb.org" + doc.select("img.lazyload").attr("data-src");
            return new Movie(movieTitle, imgURL);
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
