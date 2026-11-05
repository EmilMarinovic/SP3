import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private ArrayList<Medie> watchedMedie;
    private ArrayList<Medie> wishlist;
    private String username;
    private String password;

    public User(String username, String password){
        this.username=username;
        this.password=password;
    }


    Scanner scan = new Scanner(System.in);


    public void showWatchedList(){

    }

    public void showWishList(){

    }

    public void createUser(){
        System.out.println("=== OPRET BRUGER ===");

        System.out.println("Indtast brugernavn: ");
        String newUserName = scan.nextLine();

        System.out.println("Indtast adgangskode: ");
        String newPassword = scan.nextLine();

        System.out.println("\n Bruger er nu oprettet");
        System.out.println("Brugernavn: " + newUserName);
    }

    public void logIn(){

        if (username.isEmpty() || password.isEmpty()){
            System.out.println("Brugernavn/adgangskode kan ikke være tomme. Venligst indtast brugernavn og adgangskode");
        }

        if (username.equals("") && password.equals("")){
            System.out.println("Du er nu logget ind");
        }

        else {
            System.out.println("Du kan ikke logge ind");
        }
    }

    public void createStartMenu(){
        System.out.println("===MENU===");
        System.out.println();
        System.out.println("Vælg mulighed: ");
        System.out.println("1) Opret bruger ");
        System.out.println("2) Log ind");
        System.out.println("3) Afslut");
        
        int valgStart = scan.nextInt();
        
        switch (valgStart){
            case 1:
                System.out.println("Opret ny bruger med password");
                createUser();
                break;
            case 2:
                System.out.println("indtast bruger og password");
                logIn();
                break;
            case 3:
                System.out.println("Luk menu");
                scan.close();
                break;
            default:
                System.out.println("ERROR: indtast gyldigt valg");
        }
    }

    public void createMainMenu(){
        System.out.println("===MENU===");
        System.out.println();
        System.out.println("Vælg mulighed: ");
        System.out.println("1) Søg efter bestemt film");
        System.out.println("2) Søg alle film i en kategori");
        System.out.println("3) Se liste over sete film");
        System.out.println("4) Se liste over gemte film");
        System.out.println("5) afslut");

        int valgMain = scan.nextInt();

        switch(valgMain){
            case 1:
                System.out.println("Indtast navn på film");
                break;
            case 2:
                System.out.println("indtast navn på film i kategori");
                break;
            case 3:
                System.out.println("Liste over sete film: ");
                break;
            case 4:
                System.out.println("liste over gemte film: ");
                break;
            case 5:
                System.out.println("Luk menu");
                scan.close();
            default:
                System.out.println("ERROR: indtast gyldigt valg");
        }
    }
}