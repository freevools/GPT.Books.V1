import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private static final int AUTH_MENU_MAX_OPTION = 2;
    private static final int USER_MAIN_MENU_MAX_OPTION = 3;
    private static final int ADMIN_MAIN_MENU_MAX_OPTION = 5;
    private static AuthenticationService authService;
    private final Scanner scanner;
    private final LibraryService libraryService;
    private ArrayList<Book> books;
    private boolean isRunning;
    private final FileHandler fileHandler;

    public Menu() {
        authService = new AuthenticationService();
        scanner = new Scanner(System.in);
        libraryService = new LibraryService();
        fileHandler = new FileHandler();
        books = fileHandler.readBooks();
        isRunning = true;
    }
    public void startMenu() {
        int userChoice;
        while (isRunning) {
            if (!authService.isAuthenticated()) {
                showAuthenticationMenu();
                userChoice = getUserChoice(AUTH_MENU_MAX_OPTION);
                handleAuthentication(userChoice);
            }else{
                if (authService.isAdmin()){
                    showAdminMainMenu();
                    userChoice = getUserChoice(ADMIN_MAIN_MENU_MAX_OPTION);
                    handleAdminMainMenuChoice(userChoice);
                }else{
                    showUserMainMenu();
                    userChoice = getUserChoice(USER_MAIN_MENU_MAX_OPTION);
                    handleUserMainMenuChoice(userChoice);
                }
            }
        }
    }
    //проверка, чтобы пользователь ввёл валидные данные
    private int getUserChoice(int maxMenuOption){
        boolean isValid = false;
        int selectedMenuOption = 0;

        while (!isValid) {
            try {
                selectedMenuOption = scanner.nextInt();
                if (selectedMenuOption > maxMenuOption || selectedMenuOption < 1) {
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
        return selectedMenuOption;
    }
    //отображение меню аутентификации
    private void showAuthenticationMenu(){
        System.out.println("1.Регистрация");
        System.out.println("2.Вход");
    }
    //отображение меню администратора
    private void showAdminMainMenu() {
        System.out.println("1.Добавить книгу");
        System.out.println("2.Показать все книги");
        System.out.println("3.Удалить книгу");
        System.out.println("4.Редактировать книгу");
        System.out.println("5.Выйти");
    }
    //отображение меню пользователя
    private void showUserMainMenu() {
        System.out.println("1.Добавить книгу");
        System.out.println("2.Показать все книги");
        System.out.println("3.Выйти");
    }
    //обработка выбора пункта меню аутентификации и регистрации
    private void handleAuthentication(int selectedMenuOption) {
        switch (selectedMenuOption) {
            case 1:
                authService.registerUser();
                break;
            case 2:
                authService.loginUser();
                break;
            default:
                System.out.println("Некорректный пункт меню.");
        }
    }
    //обработка выбора пункта меню администратора
    private void handleAdminMainMenuChoice(int selectedMenuOption){
        switch (selectedMenuOption) {
            case 1:
                books = libraryService.addBook(books);
                fileHandler.saveBooks(books);
                break;
            case 2:
                books = libraryService.showBooks(books);
                break;
            case 3:
                books = libraryService.deleteBook(books);
                fileHandler.saveBooks(books);
                break;
            case 4:
                books = libraryService.updateBooks(books);
                fileHandler.saveBooks(books);
                break;
            case 5:
                System.out.println("Выход...");
                scanner.close();
                isRunning = false;
        }
    }
    //обработка выбора пункта меню пользователя
    private void handleUserMainMenuChoice(int selectedMenuOption){
        switch (selectedMenuOption) {
            case 1:
                books = libraryService.addBook(books);
                fileHandler.saveBooks(books);
                break;
            case 2:
                books = libraryService.showBooks(books);
                break;
            case 3:
                System.out.println("Выход...");
                scanner.close();
                isRunning = false;
        }
    }
}
