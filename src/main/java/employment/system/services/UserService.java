package employment.system.services;

import employment.system.user.User;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static employment.system.services.FileSystemService.getPathToFile;
import employment.system.exceptions.UserWithThisEmailAlreadyExistsException;


public class UserService {

    private static ObjectRepository<User> userRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("registration-example.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
    }

    public static void addUser(String firstName, String lastName, String email, String password, String role) throws UserWithThisEmailAlreadyExistsException {
        checkUserDoesNotAlreadyExist(email);
        userRepository.insert(new User(firstName, lastName,email, encodePassword(firstName, password), role));
    }


    private static void checkUserDoesNotAlreadyExist(String email) throws UserWithThisEmailAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(email, user.getEmail()))
                throw new UserWithThisEmailAlreadyExistsException(email);
        }
    }


    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }

}
