package com.ftmatters.crawler;

import java.sql.*;

/**
 * SINGLETON pattern
 *
 * The MovieDB class logically represents the crawler's database.
 */
public class MovieDB {

    private final String DB_CONNECTION = "jdbc:sqlite:db/crawler.db";

    // Singleton
    private static final MovieDB INSTANCE = new MovieDB();

    private PreparedStatement insertStatement;
    private PreparedStatement selectStatement;


    /**
     * This method returns the unique instance of MovieDB (SINGLETON pattern)
     * @return an instance of MovieDB
     */
    public static MovieDB getInstance() {
        return INSTANCE;
    }

    // Constructor needs to be private (SINGLETON)
    private MovieDB() {
        try {
            Connection connection = DriverManager.getConnection(DB_CONNECTION);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(10);

            // Create the table if it doesn't exist yet
            statement.executeUpdate("create table if not exists movie (id INTEGER PRIMARY KEY, name string, image string)");

            // Prepared statement to insert a new movie
            insertStatement = connection.prepareStatement("insert or ignore into movie (id, name, image) values(?, ?, ?)");

            // Prepared statement to search a movie
            selectStatement = connection.prepareStatement("select * from movie where id = ?");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Stores a movie into the database.
     * @param movie the movie that needs to be stored into the database
     */
    public void insertMovie(Movie movie) {
        System.out.println("Storing " + movie);
        try {
            insertStatement.setInt(1, movie.id());
            insertStatement.setString(2, movie.name());
            insertStatement.setString(3, movie.imgURL());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Searches for a movieID into the database.
     * @param movieId the ID of a movie
     * @return true if the movie is present into the DB, false otherwise.
     */
    public boolean movieIsPresent(int movieId) {
        try {
            selectStatement.setInt(1, movieId);

            // If the movie is present, the cursor has a valid next() row.
            return selectStatement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}