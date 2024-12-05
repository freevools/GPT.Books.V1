import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private final String FILE_PATH = "src/resources/book.txt";
    public void saveBooks(ArrayList<Book> books) {
        try(FileWriter writer = new FileWriter(FILE_PATH)){
            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);
                writer.write(String.format("%d;%s;%s;%d\n", i, book.getTitle(), book.getAuthor(), book.getYear()));
            }
            System.out.println("Success");
        }catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    public ArrayList<Book> readBooks() {
        ArrayList<Book> books = new ArrayList<>();
        try(Scanner fileScanner = new Scanner(new File(FILE_PATH))){
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

