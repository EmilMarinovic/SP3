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



    public void searchMedie(){

    }

    public void searchMedieInCategory(){

    }

    //GETTERE/SETTERE:
    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public double getRating() {
        return rating;
    }


}
