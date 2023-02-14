package simple_dao.dao;

import simple_dao.entity.Track;

import java.sql.SQLException;
import java.util.List;

public interface TrackDAO {
    void add(Track track) throws SQLException;

    List<Track> getAll();

    void updateLength(double length, int trackId);

    void remove(String name);
}
