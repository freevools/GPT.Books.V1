import java.util.ArrayList;
import java.util.Scanner;

public class LibraryService {
    FileHandler fileHandler;
    private final Scanner scanner;
    public LibraryService(){
        fileHandler = new FileHandler();
        scanner = new Scanner(System.in);
    }

    public ArrayList<Book> addBook(ArrayList<Book> books){
        System.out.println("Введите имя автора: ");
        String author = scanner.nextLine();
        System.out.println("Введите название книги: ");
        String title = scanner.nextLine();
        System.out.println("Введите год издания: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        Book book = new Book(title, author, year);
        books.add(book);
        return books;
    }
    public ArrayList<Book> showBooks(ArrayList<Book> books) {
        if (books.isEmpty()){
            System.out.println("Книг нет.");
        }else{
            System.out.println("Список всех книг:");
            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);
                System.out.println(i + ")" + book);
            }
        }
        return books;
    }
    public ArrayList<Book> deleteBook(ArrayList<Book> books){
        showBooks(books);
        System.out.println("Введите номер книги для удаления: ");
        try{
            int index = scanner.nextInt();
            scanner.nextLine();
            if (index >= 0 && index < books.size()){
                books.remove(index);
                System.out.println("Книга удалена.");
            }else {
                System.out.println("Неверный номер.");
            }
        }catch (Exception e){
            System.out.println("Введите число.");
            scanner.nextLine();
        }
        return books;
    }
    public ArrayList<Book> updateBooks(ArrayList<Book> books){
        showBooks(books);
        System.out.println("Введите номер книги для изменения: ");
        int numberOfBook = scanner.nextInt();
        System.out.println("Какой пункт хотите изменить?");
        System.out.println("1.Название книги");
        System.out.println("2.Автора");
        System.out.println("3.Год издания");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("Введите новое название книги: ");
                String newTitle = scanner.nextLine();
                if (numberOfBook >= 0 && numberOfBook < books.size()){
                    books.get(numberOfBook).setTitle(newTitle);
                    System.out.println("Название книги изменено.");
                } else {
                    System.out.println("Неверный номер.");
                }
                break;
            case 2:
                System.out.println("Введите нового автора: ");
                String newAuthor = scanner.nextLine();
                if (numberOfBook >= 0 && numberOfBook < books.size()){
                    books.get(numberOfBook).setAuthor(newAuthor);
                    System.out.println("Автор изменен.");
                } else {
                    System.out.println("Неверный номер.");
                }
                break;
            case 3:
                System.out.println("Введите новый год издания: ");
                int newYear = scanner.nextInt();
                scanner.nextLine();
                if (numberOfBook >= 0 && numberOfBook < books.size()){
                    books.get(numberOfBook).setYear(newYear);
                    System.out.println("Год издания изменен.");
                } else {
                    System.out.println("Неверный номер.");
                }
                break;
        }
        return books;
    }
}
