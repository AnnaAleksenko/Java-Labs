import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private String name;
    private List<Hall> halls;
    private List<Session> sessions;

    public Cinema(String name) {
        this.name = name;
        this.halls = new ArrayList<>();
        this.sessions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addHall(Hall hall) {
        halls.add(hall);
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public List<Session> getSessions() {
        return sessions;
    }

    // Добавленный метод
    public List<Hall> getHalls() {
        return halls;
    }

    public void printSessions() {
        for (Session session : sessions) {
            session.printSessionInfo();
            System.out.println();
        }
    }
}