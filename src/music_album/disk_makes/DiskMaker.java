package music_album.disk_makes;

import music_album.music_classes.Disk;
import music_album.music_classes.Track;

import java.util.List;
import java.util.Scanner;

public class DiskMaker {

    private Disk disk;

    public DiskMaker() {
        disk = new Disk();
    }

    private void addTrack(Scanner sc) {

        System.out.print("Name of track: ");
        String name = sc.next();
        System.out.print("Length of track in minutes: ");
        double length = sc.nextDouble();
        System.out.print("To choose genre press POP, ROCK, INDI, ALTERNATIVE_ROCK: ");
        String genre = sc.next();

        Track track = new Track();
        track.setName(name);
        track.setLength(length);
        track.setGenre(Track.Genre.valueOf(genre));

        disk.addTrack(track);
    }

    private void findByLength(Scanner sc) {
        System.out.print("Length from minutes: ");
        double begin = sc.nextDouble();
        System.out.print("Length to minutes: ");
        double end = sc.nextDouble();
        List<Track> tracksByLength = disk.getTracksByLength(begin, end);

        System.out.println(tracksByLength);
    }

    public static void main(String[] args) {
        System.out.println("Press: \n 1 - to add Track \n 2 - count minutes length of disk \n 3 - sort tracks by genre " +
                "\n 4 - search tracks in range of minutes \n 5 - exit");

        Scanner sc = new Scanner(System.in);
        DiskMaker diskMaker = new DiskMaker();
        boolean isDoing = true;

        while (isDoing) {
            int res = sc.nextInt();

            switch (res) {
                case 1 :
                    diskMaker.addTrack(sc);
                    System.out.println(diskMaker.disk);
                    break;
                case 2 :
                    System.out.println("All lengths of album: " + diskMaker.disk.lengths());
                    break;
                case 3 :
                    diskMaker.sort();
                    System.out.println(diskMaker.disk);
                    break;

                case 4 : diskMaker.findByLength(sc);
                      break;

                case 5: isDoing = false;

            }
        }

        System.out.println("End of program!");
    }

    public void sort() {

        List<Track> tracks = disk.getTracks();

        for (int i = 0; i < tracks.size(); i++) {
            for (int j = 0; j < tracks.size(); j++) {
                if (i < tracks.size() - 1) {
                    Track.Genre currentGenre = tracks.get(i).getGenre();
                    Track.Genre nextGenre = tracks.get(i + 1).getGenre();

                    if (currentGenre.getId() > nextGenre.getId()) {
                        Track tmp = tracks.get(i);
                        tracks.set(i, tracks.get(i + 1));
                        tracks.set(i+1, tmp);
                    }
                }
            }
        }
    }
}
