import java.util.ArrayList;

public class User {
    private ArrayList<Medie> watchedMedie;
    private ArrayList<Medie> wishlist;
    private String username;
    private String password;

    public User(String username, String password, ArrayList<Medie> watchedMedie, ArrayList<Medie> wishlist){
        this.username=username;
        this.password=password;
        this.watchedMedie = watchedMedie;
        this.wishlist = wishlist;
    } //Konstruktør til når gemte users data skal loades ind

    public User(String username, String password){
        this.username=username;
        this.password=password;
        this.watchedMedie = new ArrayList<>();
        this.wishlist = new ArrayList<>();
    } //Konstruktør til når bruger skal oprette en user

    public void showWatchedMedie(){
        for (Medie m : watchedMedie) {
            System.out.println(m.getTitle() + " -- Release year: " + m.getYear() + " -- Genres: " +
                    m.getGenresFormatted() + " -- Rating: " + m.getRating());
        }
    }

    public void showWishlist(){
        for (Medie m : wishlist) {
            System.out.println(m.getTitle() + " -- Release year: " + m.getYear() + " -- Genres: " +
                    m.getGenresFormatted() + " -- Rating: " + m.getRating());
        }
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Medie> getWatchedMedie() {
        return watchedMedie;
    }

    public ArrayList<Medie> getWishlist() {
        return wishlist;
    }
}