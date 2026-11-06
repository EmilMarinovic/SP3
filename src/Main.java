public class Main {

    public static void main(String[] args) {
        StreamingService s = new StreamingService();
        s.loadDataMovies();
        s.loadDataSeries();
        s.loadDataUser();
        s.showStartMenu();

    }
}
