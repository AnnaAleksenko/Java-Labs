import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Cinema> cinemas = new ArrayList<>();
    private static List<User> users = new ArrayList<>();
    private static User currentUser = null;

    public static void main(String[] args) {
        // Инициализация тестовых данных
        initializeTestData();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (currentUser == null) {
                System.out.println("1. Login");
                System.out.println("2. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    for (User user : users) {
                        if (user.getUsername().equals(username) && user.authenticate(password)) {
                            currentUser = user;
                            break;
                        }
                    }

                    if (currentUser == null) {
                        System.out.println("Invalid username or password.");
                    }
                } else if (choice == 2) {
                    break;
                }
            } else {
                if (currentUser.isAdmin()) {
                    adminMenu(scanner);
                } else {
                    userMenu(scanner);
                }
            }
        }
    }

    private static void adminMenu(Scanner scanner) {
        while (true) {
            System.out.println("1. Add Cinema");
            System.out.println("2. Add Hall to Cinema");
            System.out.println("3. Add Session to Cinema");
            System.out.println("4. View All Sessions");
            System.out.println("5. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter cinema name: ");
                String name = scanner.nextLine();
                cinemas.add(new Cinema(name));
                System.out.println("Cinema added.");
            } else if (choice == 2) {
                System.out.print("Enter cinema name: ");
                String cinemaName = scanner.nextLine();
                Cinema cinema = findCinema(cinemaName);
                if (cinema != null) {
                    System.out.print("Enter hall name: ");
                    String hallName = scanner.nextLine();
                    System.out.print("Enter number of rows: ");
                    int rows = scanner.nextInt();
                    System.out.print("Enter number of seats per row: ");
                    int seatsPerRow = scanner.nextInt();
                    scanner.nextLine();
                    cinema.addHall(new Hall(hallName, rows, seatsPerRow));
                    System.out.println("Hall added.");
                } else {
                    System.out.println("Cinema not found.");
                }
            } else if (choice == 3) {
                System.out.print("Enter cinema name: ");
                String cinemaName = scanner.nextLine();
                Cinema cinema = findCinema(cinemaName);
                if (cinema != null) {
                    System.out.print("Enter movie name: ");
                    String movieName = scanner.nextLine();
                    System.out.print("Enter start time (yyyy-MM-dd HH:mm): ");
                    String startTimeStr = scanner.nextLine();
                    Date startTime = new Date();
                    System.out.print("Enter duration (minutes): ");
                    int duration = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter hall name: ");
                    String hallName = scanner.nextLine();
                    Hall hall = findHall(cinema, hallName);
                    if (hall != null) {
                        cinema.addSession(new Session(movieName, startTime, duration, hall));
                        System.out.println("Session added.");
                    } else {
                        System.out.println("Hall not found.");
                    }
                } else {
                    System.out.println("Cinema not found.");
                }
            } else if (choice == 4) {
                for (Cinema cinema : cinemas) {
                    System.out.println("Cinema: " + cinema.getName());
                    cinema.printSessions();
                }
            } else if (choice == 5) {
                currentUser = null;
                break;
            }
        }
    }

    private static void userMenu(Scanner scanner) {
        while (true) {
            System.out.println("1. Find Nearest Session");
            System.out.println("2. Book Ticket");
            System.out.println("3. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter movie name: ");
                String movieName = scanner.nextLine();
                Session nearestSession = findNearestSession(movieName);
                if (nearestSession != null) {
                    nearestSession.printSessionInfo();
                } else {
                    System.out.println("No sessions found for the specified movie.");
                }
            } else if (choice == 2) {
                System.out.print("Enter cinema name: ");
                String cinemaName = scanner.nextLine();
                Cinema cinema = findCinema(cinemaName);
                if (cinema != null) {
                    System.out.print("Enter movie name: ");
                    String movieName = scanner.nextLine();
                    Session session = findSession(cinema, movieName);
                    if (session != null) {
                        System.out.println("Available seats:");
                        session.getHall().printSeats();
                        System.out.print("Enter row number: ");
                        int row = scanner.nextInt();
                        System.out.print("Enter seat number: ");
                        int seatNumber = scanner.nextInt();
                        scanner.nextLine();
                        if (session.getHall().bookSeat(row, seatNumber)) {
                            System.out.println("Ticket booked successfully!");
                        } else {
                            System.out.println("Seat is already booked or does not exist.");
                        }
                    } else {
                        System.out.println("Session not found.");
                    }
                } else {
                    System.out.println("Cinema not found.");
                }
            } else if (choice == 3) {
                currentUser = null;
                break;
            }
        }
    }

    private static Cinema findCinema(String name) {
        for (Cinema cinema : cinemas) {
            if (cinema.getName().equals(name)) {
                return cinema;
            }
        }
        return null;
    }

    private static Hall findHall(Cinema cinema, String name) {
        for (Hall hall : cinema.getHalls()) {
            if (hall.getName().equals(name)) {
                return hall;
            }
        }
        return null;
    }

    private static Session findSession(Cinema cinema, String movieName) {
        for (Session session : cinema.getSessions()) {
            if (session.getMovieName().trim().equalsIgnoreCase(movieName.trim())) {
                return session;
            }
        }
        return null;
    }

    private static Session findNearestSession(String movieName) {
        Session nearestSession = null;
        Date now = new Date();
        for (Cinema cinema : cinemas) {
            for (Session session : cinema.getSessions()) {
                if (session.getMovieName().trim().equalsIgnoreCase(movieName.trim()) && session.getStartTime().after(now)) {
                    if (nearestSession == null || session.getStartTime().before(nearestSession.getStartTime())) {
                        nearestSession = session;
                    }
                }
            }
        }
        return nearestSession;
    }

    private static void initializeTestData() {
        // Добавляем тестовых пользователей
        users.add(new User("admin", "admin", true));
        users.add(new User("Alexenko", "user", false));

        // Добавляем тестовый кинотеатр, зал и сеансы
        Cinema cinema = new Cinema("Cinema City");
        Hall hall1 = new Hall("Wayne 1", 9, 10);
        Hall hall2 = new Hall("Wayne 2", 6, 8);
        cinema.addHall(hall1);
        cinema.addHall(hall2);

        // Устанавливаем время сеансов на 1 и 2 часа вперед от текущего времени
        Date futureTime1 = new Date(System.currentTimeMillis() + 3600 * 1000);
        Date futureTime2 = new Date(System.currentTimeMillis() + 7200 * 1000);
        cinema.addSession(new Session("Titanic", futureTime1, 120, hall1));
        cinema.addSession(new Session("The Matrix", futureTime2, 150, hall2));
        cinemas.add(cinema);
    }
}