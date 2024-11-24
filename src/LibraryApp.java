import java.util.ArrayList;
import java.util.Scanner;

public class LibraryApp {
    private static final ArrayList<Book> books = FileHandler.readBooks();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isTrue = true;
        while(isTrue){
            System.out.println("1.Добавить книгу");
            System.out.println("2.Показать все книги");
            System.out.println("3.Выйти");

            int choice = 0;
            boolean isValid = false;

            while (!isValid) {
                try {
                    choice = scanner.nextInt();
                    if (choice > 3 || choice < 1){
                        System.out.println("Введите число от 1 до 3");
                    }else {
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
                    addBook();
                    break;
                case 2:
                    showBooks();
                    break;
                case 3:
                    System.out.println("Выход...");
                    scanner.close();
                    isTrue = false;
            }
        }
        if (!books.isEmpty()){
            FileHandler.saveBooks(books);
        }
    }
    public static void addBook(){
        System.out.println("Введите имя автора: ");
        String author = scanner.nextLine();
        System.out.println("Введите название книги: ");
        String title = scanner.nextLine();
        System.out.println("Введите год издания: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        Book book = new Book(title, author, year);
        books.add(book);
    }
    public static void showBooks(){
        if (books.isEmpty()){
            System.out.println("Книг нет.");
        }else{
            System.out.println("Список всех книг:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
}
