import java.util.ArrayList;
import java.util.List;

public class Hall {
    private String name;
    private List<Seat> seats;

    public Hall(String name, int rows, int seatsPerRow) {
        this.name = name;
        this.seats = new ArrayList<>();
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= seatsPerRow; j++) {
                seats.add(new Seat(i, j));
            }
        }
    }

    public String getName() {
        return name;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void printSeats() {
        for (Seat seat : seats) {
            System.out.println(seat);
        }
    }

    public boolean bookSeat(int row, int number) {
        for (Seat seat : seats) {
            if (seat.getRow() == row && seat.getNumber() == number && !seat.isBooked()) {
                seat.book();
                return true;
            }
        }
        return false;
    }
}