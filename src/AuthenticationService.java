import java.util.Scanner;
//класс отвечает за регистрацию и аутентификацию пользователя в программе
public class AuthenticationService {
    private User currentUser;
    private final Scanner scanner;
    private final FileHandler fileHandler;
    public AuthenticationService() {
        scanner = new Scanner(System.in);
        fileHandler = new FileHandler();
    }
    //при регистрации записывает в файл user.txt нового пользователя
    public void registerUser(){
        String username = null;
        String password = null;

        boolean isValidName = false;
        while (!isValidName){
            System.out.println("Введите логин: ");
            username = scanner.nextLine();

            if (UserService.userExists(username)){
                System.out.println("Пользователь с таким именем существует");
            }else {
                isValidName = true;
            }
        }

        boolean isValidPassword = false;
        while (!isValidPassword){
            System.out.println("Введите пароль: ");
            String rawPassword = scanner.nextLine();
            System.out.println("Повторите пароль: ");
            String confirmPassword = scanner.nextLine();
            if (rawPassword.equals(confirmPassword)){
                password = PasswordUtil.hashPassword(rawPassword);
                isValidPassword = true;
            }else {
                System.out.println("Пароли отличаются.");
            }
        }
        fileHandler.saveUser(new User(username, password, false));
    }
    //выводит меню аутентификации пользователя и назначает currentUser
    public void loginUser() {
        System.out.println("Введите логин: ");
        String username = scanner.nextLine();
        System.out.println("Введите пароль: ");
        String password = PasswordUtil.hashPassword(scanner.nextLine());

        if (UserService.validateUser(username, password)) {
            System.out.println("Аутентификация успешна!");
            currentUser = UserService.currentUser;
        } else {
            System.out.println("Неверный логин или пароль.");
        }
    }
    public boolean isAuthenticated() {
        return currentUser != null;
    }

    public boolean isAdmin() {
        return currentUser != null && currentUser.getAdmission();
    }
}
