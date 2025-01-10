import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//класс для чтения данных из файла и записью данных в файлы

public class FileHandler {
    private final String BOOKS_FILE = "src/resources/book.txt";
    private final String USERS_FILE = "src/resources/users.txt";

    //записывает данные в файл book.txt
    public void saveBooks(ArrayList<Book> books) {
        try(FileWriter writer = new FileWriter(BOOKS_FILE)){
            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);
                writer.write(String.format("%d;%s;%s;%d\n", i, book.getTitle(), book.getAuthor(), book.getYear()));
            }
            System.out.println("Success");
        }catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    //считывает данные из book.txt и возвращает список книг
    public ArrayList<Book> readBooks() {
        ArrayList<Book> books = new ArrayList<>();
        try(Scanner fileScanner = new Scanner(new File(BOOKS_FILE))){
            while (fileScanner.hasNextLine()) {
                int countOfColumns = 4;
                String fileLine = fileScanner.nextLine();
                String [] line = fileLine.split(";");

                if (line.length == countOfColumns){
                    String title = line[1];
                    String author = line[2];
                    try {
                        int year = Integer.parseInt(line[3]);
                        books.add(new Book(title, author, year));
                    }catch (NumberFormatException e){
                        System.out.println("Неверный год издания книги: " + e.getMessage());
                    }
                }else {
                    System.out.println("Недопустимая строка");
                }
            }
        }catch (Exception e) {
            System.err.println("Ошибка при чтении из файла: " + e.getMessage());
        }
        return books;
    }
    //записывает пользовательские данные в файл users.txt
    public void saveUser(User user){
        try(FileWriter writer = new FileWriter(USERS_FILE, true)){
            writer.write(user.toString() + "\n");
            System.out.println("Регистрация успешно пройдена!\n");
        }catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
    //считывает данные из файла users.txt и возвращает список пользователей
    public ArrayList<User> readUsers() {
        ArrayList<User> users = new ArrayList<>();
        try(Scanner fileScanner = new Scanner(new File(USERS_FILE))){
            while (fileScanner.hasNextLine()) {
                String fileLine = fileScanner.nextLine();
                String [] line = fileLine.split(";");

                int countOfColumns = 3;
                if (line.length == countOfColumns){
                    String username = line[0];
                    String admission = line[1];
                    boolean password = Boolean.parseBoolean(line[2]);
                    users.add(new User(username, admission, password));
                }else {
                    System.out.println("Некорректная строчка в users.txt.");
                }
            }
        }catch (Exception e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
        return users;
    }
}

