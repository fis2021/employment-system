package employment.system.checkers;


import employment.system.exceptions.UserWithThisEmailAlreadyExistsException;
import employment.system.services.UserService;
import employment.system.user.User;


import java.util.Objects;

public class RegisterChecker {

    public static void checkEmailDoesNotAlreadyExist(String email) throws UserWithThisEmailAlreadyExistsException {
        for (User user : UserService.getUserRepository().find()) {
            if (Objects.equals(email, user.getEmail())) {
                throw new UserWithThisEmailAlreadyExistsException(email);
            }
        }
    }
}
