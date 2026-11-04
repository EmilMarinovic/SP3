import java.util.ArrayList;

public class StreamingService {
    private ArrayList<Series> series = new ArrayList<>();
    FileIO io = new FileIO();

    public void loadData() { // Til at få Serier og Film data ind
        ArrayList<String> lines = io.readData("data/seriesdata.csv");
        for (String line : lines) {
            String[] data = line.split(";");
            String title = data[0];
            String year = data[1];
            String[] genres = data[2].split(", ");
            double rating = Double.parseDouble(data[3].replace(",", "."));
            String[] seasons = data[4].split(", ");
            for (String season : seasons) {
                String[] seasonInfo = season.split("-");
                int seasonNumber = Integer.parseInt(seasonInfo[0]);
                int numberOfEpisodes = Integer.parseInt(seasonInfo[1]);
            }
            createSeries(title, year, genres, rating);
        }
    }

    public void createSeries(String title, String year, String[] genres,
                             double rating) {
        series.add(new Series(title, year, genres, rating));
    }
}
