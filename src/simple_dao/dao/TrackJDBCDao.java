package simple_dao.dao;

import simple_dao.entity.Track;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrackJDBCDao implements TrackDAO{
    @Override
    public void add(Track track) {
        Connection connection = null;

        connection = getConnection();
        PreparedStatement statement;

        try {
            statement = connection.prepareStatement("INSERT INTO tracks(id, name, genre, length) VALUES (?, ?, ?, ?)");

            statement.setInt(1, track.getId());
            statement.setString(2, track.getName());
            statement.setString(3, track.getGenre());
            statement.setDouble(4, track.getLength());

            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getTrackId(String trackName, Connection connection) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM tracks WHERE name = ? ");
            preparedStatement.setString(1, trackName);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Track> getAll() {
        List<Track> allTracks = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT id, name, genre, length FROM tracks");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String genre = rs.getString(3);
                double length = rs.getDouble(4);
                Track track = new Track();
                track.setId(id);
                track.setName(name);
                track.setGenre(genre);
                track.setLength(length);
                allTracks.add(track);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allTracks;
    }

    @Override
    public void updateLength(double length, int trackId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        connection = getConnection();

        try {
            preparedStatement = connection.prepareStatement("UPDATE tracks SET length = ? WHERE id = ?");

            preparedStatement.setDouble(1, length);
            preparedStatement.setInt(2, trackId);

            int updatedValues = preparedStatement.executeUpdate();

            System.out.println("Values updated: " + updatedValues);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && preparedStatement != null) {

                try {
                    connection.close();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void remove(String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        connection = getConnection();

        try {

            int trackId = getTrackId(name, connection);
            preparedStatement = connection.prepareStatement("DELETE FROM tracks WHERE id = ? ");

            preparedStatement.setInt(1, trackId);

            int deletedValues = preparedStatement.executeUpdate();

            System.out.println("Values deleted: " + deletedValues);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && preparedStatement != null) {

                try {
                    connection.close();
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tracks", "root", "root");
            return connection;
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
