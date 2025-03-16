public class Ticket {
    private Session session;
    private Seat seat;

    public Ticket(Session session, Seat seat) {
        this.session = session;
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Ticket for " + session.getMovieName() + " at " + session.getStartTime() + " in " + session.getHall().getName() + ", " + seat;
    }
}