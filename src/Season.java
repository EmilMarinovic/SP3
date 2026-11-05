public class Season implements Playable{
    private int seasonNumber;
    private int numberOfEpisodes;
    private int episode;
    // private int reachedEpisode;

    public Season(int seasonNumber, int numberOfEpisodes) {
        this.seasonNumber = seasonNumber;
        this.numberOfEpisodes = numberOfEpisodes;
    }

    @Override
    public void play(){
        System.out.println("Season " + seasonNumber + ", episode 1 is playing...");
        //(for loop()? for watched episodes. eller manual chosen episode to watch.)->køres inde i main?
    }

    public void playEpisode(int episode) {
        System.out.println("Season " + seasonNumber + ", episode " + episode + " is playing...");
    }

    @Override
    public String toString() {
        return seasonNumber + "-" + numberOfEpisodes;
    }



}
