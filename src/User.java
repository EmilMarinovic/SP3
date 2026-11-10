import java.util.ArrayList;

public class User {
    private final ArrayList<Medie> watchedMedie;
    private final ArrayList<Medie> wishlist;
    private final String username;
    private final String password;
    TextUI ui = new TextUI();

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
            if (m instanceof Series) {
                System.out.print("Series: ");
            } else {
                System.out.print("Movie: ");
            }
            System.out.print(m.getTitle() + " -- Release year: " + m.getYear() + " -- Genres: " +
                    m.getGenresFormatted() + " -- Rating: " + m.getRating());
            if (m instanceof Series s) {
                System.out.println(" -- Seasons: " + s.getSeasons().size());
            } else System.out.println();
        }
    }

    public ArrayList<Medie> getWishlist() {
        return wishlist;
    }

    public void addToWatched(Medie m) {
        if (watchedMedie.contains(m)) {
            System.out.println("Already in watched list");
            return;
        }
        watchedMedie.add(m);
        wishlist.remove(m);
    }

    public void addToWishlist(Medie m) {
        if (watchedMedie.contains(m) || wishlist.contains(m)) {
            System.out.println("Already in one of your lists");
            return;
        }
        ui.displayMessage(m.getTitle() + " is added to wishlist");
        wishlist.add(m);
    }

    public void removeFromWishlist(Medie medie) {
        wishlist.remove(medie);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getWatchedMedieFormatted() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < watchedMedie.size(); i++) {
            sb.append(watchedMedie.get(i).getTitle());
            if (i < watchedMedie.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public String getWishlistFormatted() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wishlist.size(); i++) {
            sb.append(wishlist.get(i).getTitle());
            if (i < wishlist.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}