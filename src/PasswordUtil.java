import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
//класс отвечает за шифрование паролей пользователей в SHA-256
public class PasswordUtil {
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA256");
            byte[] hashedBytes = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        }catch (NoSuchAlgorithmException e) {
            System.err.println("Error creating SHA-256 digest: " + e.getMessage());
            return null;
        }
    }
}
