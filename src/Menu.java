import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private LibraryService libraryService;
    private ArrayList<Book> books;
    private boolean isRunning;
    private FileHandler fileHandler;
    private int maxMenuOption;

    public Menu() {
        scanner = new Scanner(System.in);
        libraryService = new LibraryService();
        fileHandler = new FileHandler();
        books = fileHandler.readBooks();
        isRunning = true;
        maxMenuOption = 5;
    }

    public void startMenu() {
        int choice = 0;

        while (isRunning) {
            System.out.println("1.Добавить книгу");
            System.out.println("2.Показать все книги");
            System.out.println("3.Удалить книгу");
            System.out.println("4.Редактировать книгу");
            System.out.println("5.Выйти");

            boolean isValid = false;

            while (!isValid) {
                try {
                    choice = scanner.nextInt();
                    if (choice > maxMenuOption || choice < 1) {
                        System.out.printf("Введите число от 1 до %d%n", maxMenuOption);
                    } else {
                        isValid = true;
                    }
                } catch (Exception e) {
                    System.out.println("Введите число");
                } finally {
                    scanner.nextLine();
                }
            }
            switch (choice) {
                case 1:
                    books = libraryService.addBook(books);
                    break;
                case 2:
                    books = libraryService.showBooks(books);
                    break;
                case 3:
                    books = libraryService.deleteBook(books);
                    break;
                case 4:
                    books = libraryService.updateBooks(books);
                    break;
                case 5:
                    System.out.println("Выход...");
                    fileHandler.saveBooks(books);
                    scanner.close();
                    isRunning = false;
            }
        }
    }
}
