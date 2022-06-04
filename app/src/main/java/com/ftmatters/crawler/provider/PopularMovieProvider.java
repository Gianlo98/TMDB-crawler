package com.ftmatters.crawler.provider;

import com.ftmatters.crawler.MovieURLProvider;
import com.ftmatters.crawler.TMDBRequestBodyBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class PopularMovieProvider implements MovieURLProvider {
    // This queue hols a set of links that will be provided where needed
    Queue<String> movieLinks = new LinkedList<>();
    private int requestedPage = 1;

    public PopularMovieProvider() {
        retrieveMovieLinks();
    }

    @Override
    public String provideMovieURL() {
        if (movieLinks.isEmpty()) {
            retrieveMovieLinks();
        }

        return movieLinks.poll();
    }

    private void retrieveMovieLinks() {
        System.out.println("Retrieving popular movies from page " +  requestedPage);
        try {
            Document doc = Jsoup.connect("https://www.themoviedb.org/discover/movie/items")
                    .method(Connection.Method.POST)
                    .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .requestBody(TMDBRequestBodyBuilder.builder().page(requestedPage++).build().getRequestBody())
                    .execute()
                    .parse();
            Elements movies = doc.select(".wrapper");
            for (Element movie : movies) {
                String movieURL = movie.select("a").attr("href");
                movieLinks.offer("https://www.themoviedb.org" + movieURL);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Retrieved  " +  movieLinks.size() + " movies");
    }
}
