import java.util.ArrayList;
import java.util.Scanner;

public class StreamingService {
    private ArrayList<Series> series = new ArrayList<>();
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    FileIO io = new FileIO();
    TextUI ui = new TextUI();

    Scanner scan = new Scanner(System.in);

    public void loadDataSeries() {
        ArrayList<String> lines = io.readData("data/seriesdata.csv");

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) continue;

            try {
                String[] data = line.split(";");
                String title = data[0].trim();
                String year = data[1].trim();

                ArrayList<Genre> genres = new ArrayList<>();
                for (String g : data[2].split(",")) {
                    genres.add(Genre.valueOf(g.trim().toUpperCase()));
                }

                double rating = Double.parseDouble(data[3].trim().replace(",", "."));

                // Saml alle resterende felter, så både "," og ";" mellem sæsoner virker
                StringBuilder seasonText = new StringBuilder();
                for (int j = 4; j < data.length; j++) {
                    seasonText.append(data[j]).append(",");
                }

                ArrayList<Season> seasonList = new ArrayList<>();
                for (String season : seasonText.toString().split(",")) {
                    season = season.trim();
                    if (season.isEmpty()) continue;
                    String[] seasonInfo = season.split("-");
                    int seasonNumber = Integer.parseInt(seasonInfo[0].trim());
                    int numberOfEpisodes = Integer.parseInt(seasonInfo[1].trim());
                    seasonList.add(new Season(seasonNumber, numberOfEpisodes));
                }

                createSeriesData(title, year, genres, rating, seasonList);

            } catch (Exception e) {
                System.out.println("Kunne ikke indlæse linje " + (i + 1) + ": [" + line + "]");
                System.out.println("  Fejl: " + e);
            }
        }
    }

    public void loadDataMovies() {
        ArrayList<String> lines = io.readData("data/moviesdata.csv");

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) continue;

            try {
                String[] data = line.split(";");
                String title = data[0].trim();
                String year = data[1].trim();

                ArrayList<Genre> genres = new ArrayList<>();
                for (String g : data[2].split(",")) {
                    genres.add(Genre.valueOf(g.trim().toUpperCase()));
                }

                double rating = Double.parseDouble(data[3].trim().replace(",", "."));


                createMovieData(title, year, genres, rating);

            } catch (Exception e) {
                System.out.println("Kunne ikke indlæse linje " + (i + 1) + ": [" + line + "]");
                System.out.println("  Fejl: " + e);
            }
        }
    }

    public void createSeriesData(String title, String year, ArrayList<Genre> genres, double rating, ArrayList<Season> seasons) {
        Series s = new Series(title, year, genres, rating);
        for (Season season : seasons) {
            s.addSeason(season);
        }
        series.add(s);
    }

    public void createMovieData(String title, String year, ArrayList<Genre> genres, double rating) {
        movies.add(new Movie(title, year, genres, rating));
    }

    public void createUserData(String title, String password) {}

    public void showStartMenu(){
        boolean isRunning = true;
        while (isRunning) {
            ui.displayMessage("===MENU===");
            ui.displayMessage("\n1. Create user");
            ui.displayMessage("2. Log in");
            ui.displayMessage("0. Close");

            int input = ui.promptNumeric("Choose:");

            switch (input){
                case 1:
                    createUser();
                    break;
                case 2:
                    logIn();
                    break;
                case 0:
                    ui.displayMessage("Closing...");
                    isRunning = false;
                    break;
                default:
                    ui.displayMessage("Please pick a choice");
                    break;
            }
        }
    }

    public void createUser(){
        String username = ui.promptText("Type username:");
        String password = ui.promptText("Type password:");
        users.add(new User(username, password));
        ui.displayMessage("\nUser created\n");

        //Vi skal have en håndtering for at man ikke kan oprette en user med et username der allerede findes
    }

    public void logIn() {
        boolean isRunning = true;
        while (isRunning) {
            String username = ui.promptText("Type username");

            User user = findUser(username);

            if (user == null) {
                ui.displayMessage("User does not exist!");
                break;
            }

            String password = ui.promptText("Type password");

            if (!user.getPassword().equals(password)) {
                ui.displayMessage("Wrong password!. Try again");
            } else {
                ui.displayMessage("You are now logged in");
                isRunning = false;
                showOptionMenu();
            }
        }
    }

    public void showOptionMenu() {
        boolean isRunning = true;

        while (isRunning) {
            ui.displayMessage("\nWelcome back - What do you want to do?");
            ui.displayMessage("1. Search after a title");
            ui.displayMessage("2. Search after a genre");
            ui.displayMessage("3. Show list of watched list");
            ui.displayMessage("4. Show wishlist");
            ui.displayMessage("0. Close");

            int input = ui.promptNumeric("Choose:");

            switch (input) {
                case 1:
                    //searchAfterTitle();
                    break;
                case 2:
                    //searchAfterGenre();
                    break;
                case 3:
                    //showWatchedList();
                    break;
                case 4:
                    //showWishlist();
                    break;
                case 0:
                    ui.displayMessage("Closing...");
                    isRunning = false;
                default:
                    ui.displayMessage("Please pick a choice");
            }
        }
    }

    private User findUser(String username) {
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) {
                return u;
            }
        }
        return null;   // ingen fundet
    }


    public void displayAllSeries() {
        for (Series s : series) {
            System.out.println(s.toString());
        }
    }

    public void displayAllMovies() {
        for (Movie m : movies) {
            System.out.println(m.toString());
        }
    }

    public void searchMedie(){

    }

    public void searchMedieInCategory(){

    }
}