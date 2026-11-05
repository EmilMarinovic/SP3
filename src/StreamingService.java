import java.util.ArrayList;
import java.util.Scanner;

public class StreamingService {
    private ArrayList<Series> series = new ArrayList<>();
    private ArrayList<Movie> movies = new ArrayList<>();
    FileIO io = new FileIO();

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

    public void createUserData(String title, String password);

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
}