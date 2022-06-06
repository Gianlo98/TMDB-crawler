package com.ftmatters.crawler.provider;

import com.ftmatters.crawler.Movie;
import com.ftmatters.crawler.MovieDB;
import com.ftmatters.crawler.MovieURLProvider;
import com.ftmatters.crawler.TMDBRequestBody;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

/**
 * The PopularMovieProvider is a provider of movies inside the section "Popular" on TMDB
 * https://www.themoviedb.org/tv
 *
 * It holds a smart queue of movie URL, that is filled every time is empty.
 * Moreover, it also provides a guard CHECK_FOR_DUPLICATES to avoid putting movies arleady stored into the DB in the queue.
 *
 */
public class PopularMovieProvider implements MovieURLProvider {

    // Guard to avoid putting on the queue movies already stored into the DB
    private final boolean CHECK_FOR_DUPLICATES = System.getenv("CHECK_FOR_DUPLICATES") == null || Boolean.parseBoolean(System.getenv("CHECK_FOR_DUPLICATES"));

    // Queue that contains all the movies that will be returned by the provider.
    Queue<String> movieLinks = new LinkedList<>();

    // Integer that represent the requested page on TMDB to lazily retrieve movie URLs.
    private int requestedPage = 0;

    public PopularMovieProvider() throws NoAvailableMovieException {
        System.out.println("CHECK_FOR_DUPLICATES: " + CHECK_FOR_DUPLICATES);
        retrieveMovieLinks();
    }

    /**
     * Returns the ULR of the next movie in the "movieLinks" queue.
     * @return The URL of a movie
     * @throws NoAvailableMovieException if there are no more movies.
     */
    @Override
    public String provideMovieURL() throws NoAvailableMovieException {
        if (movieLinks.isEmpty()) {
            retrieveMovieLinks();
        }

        return movieLinks.poll();
    }

    /**
     * Gets the next "popular movies page" from TMDB and puts all the movie's URLs in the "movieLinks" queue.
     * @throws NoAvailableMovieException if there are no more pages to be retrieved.
     */
    private void retrieveMovieLinks() throws NoAvailableMovieException {
        System.out.println("Retrieving popular movies from page " +  requestedPage);
        try {
            Document doc = Jsoup.connect("https://www.themoviedb.org/discover/movie/items")
                    .method(Connection.Method.POST)
                    .header("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .requestBody(TMDBRequestBody.builder().page(requestedPage++).build().getRequestBody())
                    .execute()
                    .parse();

            // In the response, each movie is wrapped inside a <div class="wrapper"
            Elements movies = doc.select(".wrapper");
            for (Element movie : movies) {

                // The first child of the <div> is an <a href="/movie/646380"> that holds the id of the movie,
                // needed to reconstruct the URL
                String movieURLResource = movie.select("a").attr("href");
                String movieURL = "https://www.themoviedb.org" + movieURLResource;

                if (shouldURLBeInserted(movieURL)) {
                    movieLinks.offer(movieURL);
                }
            }
        } catch (HttpStatusException e) {

            // If the request returns an error code different than 200, it means that there are no more pages available.
            throw new NoAvailableMovieException();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Retrieved " +  movieLinks.size() + " new movies");
    }


    /**
     * This function returns false if the CHECK_FOR_DUPLICATES guard is set to true and the movie id is already present into the DB.
     * @param movieURL the URL of a movie
     * @return true if the movie URL should be inserted into the queue false otherwise
     */
    private boolean shouldURLBeInserted(String movieURL) {
        // This boolean condition represents the implication CHECK_FOR_DUPLICATES -> Movie is not present into the DB
        return !CHECK_FOR_DUPLICATES || !MovieDB.getInstance().movieIsPresent(Movie.getMovieIDFromURL(movieURL));
    }
}
