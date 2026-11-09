import java.util.ArrayList;
import java.util.Scanner;

public class StreamingService {
    private ArrayList<Medie> medias = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private User currentUser;
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

    public void loadDataUser() {
        ArrayList<String> lines = io.readData("data/userdata.csv");

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) continue;

            try {
                String[] data = line.split(";");
                String username = data[0].trim();
                String password = data[1].trim();

                ArrayList<Medie> watchedMedie = new ArrayList<>();
                ArrayList<Medie> wishlist = new ArrayList<>();

                if (data.length > 2) {
                    for (String g : data[2].split(",")) {
                        Medie m = findMedieTitle(g.trim());
                        if (m != null) watchedMedie.add(m);
                    }
                }

                if (data.length > 3) {
                    for (String g : data[3].split(",")) {
                        Medie m = findMedieTitle(g.trim());
                        if (m != null) wishlist.add(m);
                    }
                }

                createUserData(username, password, watchedMedie, wishlist);

            } catch (Exception e) {
                System.out.println("Kunne ikke indlæse linje " + (i + 1) + ": [" + line + "]");
                System.out.println("  Fejl: " + e);
            }
        }
    }

    public Medie findMedieTitle(String title) {
        for (Medie m : medias) {
            if (title.trim().equalsIgnoreCase(m.getTitle().trim())) {
                return m;
            }
        }
        return null;
    }

    public void createSeriesData(String title, String year, ArrayList<Genre> genres, double rating, ArrayList<Season> seasons) {
        Series s = new Series(title, year, genres, rating);
        for (Season season : seasons) {
            s.addSeason(season);
        }
        medias.add(s);
    }

    public void createMovieData(String title, String year, ArrayList<Genre> genres, double rating) {
        medias.add(new Movie(title, year, genres, rating));
    }

    public void createUserData(String username, String password, ArrayList<Medie> watchedMedie, ArrayList<Medie> wishlist) {
        users.add(new User(username, password, watchedMedie, wishlist));
    }

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
                    endSession();
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
                currentUser = user;
                isRunning = false;
                showOptionMenu();
            }
        }
    }

    public void showOptionMenu() {
        boolean isRunning = true;

        while (isRunning) {
            ui.displayMessage("\nWelcome back - What do you want to do?");
            ui.displayMessage("1. Search");
            ui.displayMessage("2. Show list of watched list");
            ui.displayMessage("3. Show wishlist");
            ui.displayMessage("0. Log out");

            int input = ui.promptNumeric("Choose:");

            switch (input) {
                case 1:
                    searchMedie();
                    break;
                case 2:
                    currentUser.showWatchedMedie();
                    break;
                case 3:
                    currentUser.showWishlist();
                    break;
                case 0:
                    ui.displayMessage("Logging out");
                    isRunning = false;
                    break;
                default:
                    ui.displayMessage("Please pick a choice");
                    break;
            }
        }
    }

    public void searchMovieByYear() {
        String year = ui.promptText("Enter year:");

        boolean found = false;

        for (Medie m : medias) {
            if (m instanceof Movie && m.getYear().equals(year)) {
                ui.displayMessage(m.toString());
                found = true;
            }
        }

        if (!found) {
            ui.displayMessage("No movies found from " + year);
        }
    }

    public void searchMoviesByYearRange() {
        int startYear = ui.promptNumeric("Enter start year:");
        int endYear = ui.promptNumeric("Enter end year:");

        boolean found = false;

        for (Medie m : medias) {
            if (m instanceof Movie) {
                int movieYear = Integer.parseInt(m.getYear());

                if (movieYear >= startYear && movieYear <= endYear) {
                    ui.displayMessage(m.toString());
                    found = true;
                }
            }
        }

        if (!found) {
            ui.displayMessage("No movies found in this period.");
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


    /*public void displayAllSeries() {
        for (Series s : series) {
            System.out.println(s.toString());
        }
    }

    public void displayAllMovies() {
        for (Movie m : movies) {
            System.out.println(m.toString());
        }
    }*/

    public void searchMedie() {
        ui.displayMessage("\n=== SEARCH ===");
        ui.displayMessage("1. Movie");
        ui.displayMessage("2. Series");
        ui.displayMessage("3. Movie/Series");

        int input = ui.promptNumeric("Choose:");

        String mediaType;

        switch (input) {
            case 1:
                mediaType = "Movie";
                break;
            case 2:
                mediaType = "Series";
                break;
            case 3:
                mediaType = "All";
                break;
            default:
                ui.displayMessage("Invalid choice");
                return;
        }

        searchByCriteria(mediaType);
    }

    public void searchByCriteria(String mediaType) {
        ui.displayMessage("\n=== SEARCH BY ===");
        ui.displayMessage("1. Rating");
        ui.displayMessage("2. Release year");

        int input = ui.promptNumeric("Choose:");

        switch (input) {
            case 1:
                searchByRating(mediaType);
                break;
            case 2:
                searchByYear(mediaType);
                break;
            default:
                ui.displayMessage("Invalid choice");
                break;
        }
    }

    public void searchByRating(String mediaType) {
        ui.displayMessage("\n=== RATING ===");
        ui.displayMessage("1. Rating at/above");
        ui.displayMessage("2. Rating in range of");

        int input = ui.promptNumeric("Choose:");

        switch (input) {
            case 1:
                searchRatingAbove(mediaType);
                break;
            case 2:
                searchRatingRange(mediaType);
                break;
            default:
                ui.displayMessage("Invalid choice");
                break;
        }
    }

    public void searchRatingAbove(String mediaType) {
        double rating = Double.parseDouble(
                ui.promptText("Enter minimum rating:").replace(",", ".")
        );

        boolean found = false;

        for (Medie m : medias) {
            if (matchesMediaType(m, mediaType)
                    && m.getRating() >= rating) {
                ui.displayMessage(m.toString());
                found = true;
            }
        }

        if (!found) {
            ui.displayMessage("No media found.");
        }
    }

    public void searchRatingRange(String mediaType) {
        double minRating = Double.parseDouble(
                ui.promptText("Enter minimum rating:").replace(",", ".")
        );

        double maxRating = Double.parseDouble(
                ui.promptText("Enter maximum rating:").replace(",", ".")
        );

        boolean found = false;

        for (Medie m : medias) {
            if (matchesMediaType(m, mediaType)
                    && m.getRating() >= minRating
                    && m.getRating() <= maxRating) {
                ui.displayMessage(m.toString());
                found = true;
            }
        }

        if (!found) {
            ui.displayMessage("No media found.");
        }
    }

    public void searchByYear(String mediaType) {
        ui.displayMessage("\n=== RELEASE YEAR ===");
        ui.displayMessage("1. Release year");
        ui.displayMessage("2. Release year in range of");

        int input = ui.promptNumeric("Choose:");

        switch (input) {
            case 1:
                searchYearExact(mediaType);
                break;
            case 2:
                searchYearRange(mediaType);
                break;
            default:
                ui.displayMessage("Invalid choice");
                break;
        }
    }

    public void searchYearExact(String mediaType) {
        String year = ui.promptText("Enter release year:");

        boolean found = false;

        for (Medie m : medias) {
            if (matchesMediaType(m, mediaType)
                    && m.getYear().equals(year)) {
                ui.displayMessage(m.toString());
                found = true;
            }
        }

        if (!found) {
            ui.displayMessage("No media found.");
        }
    }

    public void searchYearRange(String mediaType) {
        int minYear = Integer.parseInt(
                ui.promptText("Enter starting year:")
        );

        int maxYear = Integer.parseInt(
                ui.promptText("Enter ending year:")
        );

        boolean found = false;

        for (Medie m : medias) {
            if (matchesMediaType(m, mediaType)) {
                int year = Integer.parseInt(m.getYear());

                if (year >= minYear && year <= maxYear) {
                    ui.displayMessage(m.toString());
                    found = true;
                }
            }
        }

        if (!found) {
            ui.displayMessage("No media found.");
        }
    }

    private boolean matchesMediaType(Medie m, String mediaType) {

        switch (mediaType) {
            case "Movie":
                return m instanceof Movie;

            case "Series":
                return m instanceof Series;

            case "All":
                return true;

            default:
                return false;
        }
    }

    public void endSession() {
        ArrayList<String> data = new ArrayList<>();
        String path = "data/userdata.csv";
        String header = "Username; Password; Watchedlist; Wishlist;";
        for (User u : users) {
            data.add(u.getUsername() + "; " + u.getPassword() + "; " + u.getWatchedMedieFormatted() + "; " + u.getWishlistFormatted() + ";");
        }
        io.saveData(data, path, header);
    }
}