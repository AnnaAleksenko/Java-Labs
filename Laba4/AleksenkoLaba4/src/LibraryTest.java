import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class LibraryTest {
    public static void main(String[] args) {
        Library library = new Library();

        // Создаем книги
        Book book1 = new Book("Война и мир", "Лев Толстой", 1869);
        Book book2 = new Book("Анна Каренина", "Лев Толстой", 1877);
        Book book3 = new Book("Преступление и наказание", "Фёдор Достоевский", 1866);
        Book book4 = new Book("Идиот", "Фёдор Достоевский", 1869);
        Book book5 = new Book("1984", "Джордж Оруэлл", 1949);

        // Добавляем книги в библиотеку
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);

        // Тестируем методы
        System.out.println("=== Тестирование системы управления библиотекой ===");

        // 1. Вывод всех книг
        library.printAllBooks();
        System.out.println();

        // 2. Вывод уникальных авторов
        library.printUniqueAuthors();
        System.out.println();

        // 3. Вывод статистики по авторам
        library.printAuthorStatistics();
        System.out.println();

        // 4. Поиск книг по автору
        System.out.println("Книги Льва Толстого:");
        List<Book> tolstoyBooks = library.findBooksByAuthor("Лев Толстой");
        for (Book book : tolstoyBooks) {
            System.out.println(book);
        }
        System.out.println();

        // 5. Поиск книг по году
        System.out.println("Книги 1869 года:");
        List<Book> books1869 = library.findBooksByYear(1869);
        for (Book book : books1869) {
            System.out.println(book);
        }
        System.out.println();

        // 6. Удаление книги
        System.out.println("Удаляем книгу '1984'...");
        library.removeBook(book5);
        library.printAllBooks();
    }
}