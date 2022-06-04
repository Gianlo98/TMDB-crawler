package com.ftmatters.crawler;

import java.sql.*;

public class MovieDB {

    private final String DB_CONNECTION = "jdbc:sqlite:db/crawler.db";
    private static final MovieDB INSTANCE = new MovieDB();
    private PreparedStatement preparedInsert;

    public static MovieDB getInstance() {
        return INSTANCE;
    }

    private MovieDB() {
        try {
            Connection connection = DriverManager.getConnection(DB_CONNECTION);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(10);
            statement.executeUpdate("create table if not exists movie (id INTEGER PRIMARY KEY AUTOINCREMENT, name string, image string)");
            preparedInsert = connection.prepareStatement("insert into movie (name, image) values(?, ?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertMovie(Movie movie) {
        System.out.println("Storing " + movie);
        try {
            preparedInsert.setString(1, movie.name());
            preparedInsert.setString(2, movie.imgURL());
            preparedInsert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}