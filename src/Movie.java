import java.util.ArrayList;

public class Movie extends Medie implements Playable{

    public Movie(String title, String year, ArrayList<Genre> genres, double rating) {
        super(title, year, genres, rating);
    }

    public void play() {
        System.out.println(getTitle() + " is playing...");
    }

    @Override
    public String toString() {
        return getTitle() + " -- Release year: " + getYear() + " -- Genres: " +
                getGenresFormatted() + " -- Rating: " + getRating();
    }
}
