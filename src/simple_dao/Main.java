package simple_dao;

import simple_dao.dao.DAOFactory;
import simple_dao.dao.IDAOFactory;
import simple_dao.dao.TrackDAO;
import simple_dao.entity.Track;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        IDAOFactory factory = DAOFactory.getInstance();
        TrackDAO trackDAO = factory.getTrackDAO();

        Track track = new Track();

        track.setId(2);
        track.setName("Bob Marley - Relax");
        track.setGenre("Rasta");
        track.setLength(2.15);
        trackDAO.add(track);


        List<Track> tracks = trackDAO.getAll();
//
//        for (Track track : tracks) {
//            System.out.println(track.getId() + " " + track.getLength() + " "
//                    + track.getName() + " " + track.getGenre() + " ");
//        }


//        trackDAO.updateLength(3.59, 2);

//        trackDAO.remove("Jimmy Hendrix - Voodoo Child");
    }
}
