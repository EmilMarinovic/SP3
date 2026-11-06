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
        if (watchedMedie.isEmpty()) {
            System.out.println("The list is empty...");
        }
        for (Medie m : watchedMedie) {
            System.out.println(m.getTitle() + " -- Release year: " + m.getYear() + " -- Genres: " +
                    m.getGenresFormatted() + " -- Rating: " + m.getRating());
        }
    }

    public void showWishlist(){
        if (wishlist.isEmpty()) {
            System.out.println("The list is empty...");
        }
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

    public String getWatchedMedieFormatted() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < watchedMedie.size(); i++) {
            String name = watchedMedie.get(i).getTitle();
            sb.append(name.charAt(0)).append(name.substring(1).toLowerCase());
            if (i < watchedMedie.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public ArrayList<Medie> getWishlist() {
        return wishlist;
    }

    public String getWishlistFormatted() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wishlist.size(); i++) {
            String name = wishlist.get(i).getTitle();
            sb.append(name.charAt(0)).append(name.substring(1).toLowerCase());
            if (i < wishlist.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}