import java.util.ArrayList;

public class User {
    private ArrayList<Medie> watchedMedie;
    private ArrayList<Medie> wishlist;
    private String username;
    private String password;

    public User(String username, String password){
        this.username=username;
        this.password=password;
        this.watchedMedie = new ArrayList<>();
        this.wishlist = new ArrayList<>();
    }

    public void showWatchedList(){

    }

    public void showWishList(){

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}