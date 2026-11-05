import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private ArrayList<Medie> watchedMedie;
    private ArrayList<Medie> wishlist;
    private String username;
    private String password;
    TextUI ui = new TextUI();

    public User(String username, String password){
        this.username=username;
        this.password=password;
    }


    Scanner scan = new Scanner(System.in);


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