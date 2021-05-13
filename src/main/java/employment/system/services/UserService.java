package employment.system.services;

import employment.system.checkers.RegisterChecker;
import employment.system.jobs.Job;
import employment.system.user.AccountType;
import employment.system.user.User;
import static employment.system.services.FileSystemService.getPathToFile;
import employment.system.exceptions.UserWithThisEmailAlreadyExistsException;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


public class UserService {

    private static final int KEY_LENGTH = 256;
    private static final int ITERATIONS = 10000;
    private static ObjectRepository<User> userRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("users.db").toFile())
                .openOrCreate("admin", "admin");

        userRepository = database.getRepository(User.class);
    }

    public static void addUser(String email, String firstName, String lastName, String password, AccountType accountType) throws UserWithThisEmailAlreadyExistsException {
        RegisterChecker.checkEmailDoesNotAlreadyExist(email);
        userRepository.insert(new User(email, firstName, lastName, encodePassword(password, email), accountType, new ArrayList<Job>()) );
    }

    public static String encodePassword(String password, String salt) {
        String returnValue = null;
        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());
        returnValue = Base64.getEncoder().encodeToString(securePassword);
        return returnValue;
    }

    public static byte[] hash(char[] password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError("Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    public static ObjectRepository<User> getUserRepository() {
        return userRepository;
    }

    public static void setUserRepository(ObjectRepository<User> userRepository) {
        UserService.userRepository = userRepository;
    }


    public static boolean verifyUserPassword(String providedPassword, String securedPassword, String salt) // salt is the user email's
    {
        boolean returnValue = false;
        String newSecurePassword = encodePassword(providedPassword, salt);
        returnValue = newSecurePassword.equalsIgnoreCase(securedPassword);
        return returnValue;
    }
}
