public class Season implements Playable{
    private final int seasonNumber;
    private final int numberOfEpisodes;

    public Season(int seasonNumber, int numberOfEpisodes) {
        this.seasonNumber = seasonNumber;
        this.numberOfEpisodes = numberOfEpisodes;
    }

    @Override
    public void play() {
        System.out.println("Season " + seasonNumber + ", episode 1 is playing...");
    }

    @Override
    public String toString() {
        return seasonNumber + "-" + numberOfEpisodes;
    }



}
