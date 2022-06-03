package com.ftmatters.crawler;

import java.sql.*;

public class MovieDB {

    private final String DB_CONNECTION = "jdbc:sqlite:crawler.db";
    private static final MovieDB INSTANCE = new MovieDB();
    private PreparedStatement preparedInsert;


    private MovieDB() {
        try {
            Connection connection = DriverManager.getConnection(DB_CONNECTION);
            preparedInsert = connection.prepareStatement("insert into movie (name, image) values(?, ?)");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            statement.executeUpdate("create table if not exists movie (id INTEGER PRIMARY KEY AUTOINCREMENT, name string, image string)");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertMovie(Movie movie) {
        try {
            preparedInsert.setString(1, movie.name());
            preparedInsert.setString(2, movie.imgURL());
            preparedInsert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}