package music_album.music_classes;

public class Track{

    private String name;
    private double length;
    private Genre genre;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Track " +
                "name: '" + name + '\'' +
                ", Length(in minutes): " + length +
                ", Genre: " + genre +
                ')';
    }

    public enum Genre {
        POP(0), ROCK(1), INDI(2), ALTERNATIVE_ROCK(3);

        private int id;

        Genre(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

    }
}
