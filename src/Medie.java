import java.util.ArrayList;

public abstract class Medie {
    private String title;
    private String year;
    private ArrayList<Genre> genres;
    private double rating;

    public Medie(String title, String year, ArrayList<Genre> genres, double rating) {
        this.title = title;
        this.year = year;
        this.genres = genres;
        this.rating = rating;
    }

    //GETTERE/SETTERE:
    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getGenresFormatted() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < genres.size(); i++) {
            String name = genres.get(i).name();   // f.eks. "CRIME"
            sb.append(name.charAt(0)).append(name.substring(1).toLowerCase());
            if (i < genres.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public double getRating() {
        return rating;
    }


}
