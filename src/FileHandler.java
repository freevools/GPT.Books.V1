import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private static final String FILE_PATH = "src/resources/book.txt";
    public static void saveBooks(ArrayList<Book> books) {
        try(FileWriter writer = new FileWriter(FILE_PATH)){
            for (Book book : books) {
                writer.write(String.format("%s;%s;%d\n", book.getAuthor(), book.getTitle(), book.getYear()));
            }
            System.out.println("Success");
        }catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    public static ArrayList<Book> readBooks() {
        ArrayList<Book> books = new ArrayList<>();
        try(Scanner fileScanner = new Scanner(new File(FILE_PATH))){
            while (fileScanner.hasNextLine()) {
                String fileLine = fileScanner.nextLine();
                String [] line = fileLine.split(";");

                if (line.length == 3){
                    String title = line[0];
                    String author = line[1];
                    try {
                        int year = Integer.parseInt(line[2]);
                        books.add(new Book(title, author, year));

                    }catch (NumberFormatException e){
                        System.out.println("Error converting year of publication: " + e.getMessage());
                    }
                }else {
                    System.out.println("Invalid line.");
                }
            }
        }catch (Exception e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return books;
    }
}

