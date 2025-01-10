import java.util.ArrayList;

public class UserService {
    static User currentUser;
    static FileHandler fileHandler = new FileHandler();
    // сравнивает имя пользователя и пароль в файле users.txt и введённми пользователем
    public static boolean validateUser(String username, String password){
        ArrayList<User> users = fileHandler.readUsers();
        for(User user : users){
            if (user.getName().equals(username) && user.getPassword().equals(password)){
                currentUser = user;
                return true;
            }
        }
        return false;
    }
    //Если пользователь существует, возвращает true
    public static boolean userExists(String username){
        boolean exists = false;
        ArrayList<User> users = fileHandler.readUsers();
        for(User user : users){
            if (user.getName().equals(username)){
                exists = true;
                break;
            }
        }
        return exists;
    }
}
