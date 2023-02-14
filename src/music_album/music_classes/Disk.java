package music_album.music_classes;

import java.util.ArrayList;
import java.util.List;

public class Disk {

    private List<Track> tracks;
    private double length;

    public Disk() {
        tracks = new ArrayList<>();
    }

    public void addTrack(Track track) {
        tracks.add(track);
        length += track.getLength();
    }

    public double lengths() {
        return length;
    }

    @Override
    public String toString() {
        return tracks.toString();
    }

    public List<Track> getTracksByLength(double begin, double end){
        List<Track> result = new ArrayList<>();

        for (Track track : tracks) {
            double lengthTrack = track.getLength();
            if (lengthTrack >= begin && lengthTrack <= end) {
                result.add(track);
            }
        }

        return result;
    }

    public List<Track> getTracks() {
        return tracks;
    }
}
