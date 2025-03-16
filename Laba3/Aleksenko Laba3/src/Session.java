import java.util.Date;

public class Session {
    private String movieName;
    private Date startTime;
    private int duration; // в минутах!!
    private Hall hall;

    public Session(String movieName, Date startTime, int duration, Hall hall) {
        this.movieName = movieName;
        this.startTime = startTime;
        this.duration = duration;
        this.hall = hall;
    }

    public String getMovieName() {
        return movieName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public int getDuration() {
        return duration;
    }

    public Hall getHall() {
        return hall;
    }

    public void printSessionInfo() {
        System.out.println("Movie: " + movieName);
        System.out.println("Start Time: " + startTime);
        System.out.println("Duration: " + duration + " minutes");
        System.out.println("Hall: " + hall.getName());
    }
}