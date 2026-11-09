import java.util.ArrayList;

public class Series extends Medie {
    private final ArrayList<Season> seasons;

    public Series(String title, String year, ArrayList<Genre> genres, double rating) {
        super(title, year, genres, rating);
        this.seasons = new ArrayList<>();
    }

    public void addSeason(Season season) {
        seasons.add(season);
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    @Override
    public String toString() {
        return getTitle() + " -- Release year: " + getYear() + " -- Genres: " +
                getGenresFormatted() + " -- Rating: " + getRating() + " -- Seasons: " + getSeasons().size();
    }
}