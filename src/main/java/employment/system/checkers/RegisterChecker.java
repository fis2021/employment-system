package employment.system.checkers;
/*
 * Created by Adrian Dragoș on 4/16/2021 Apr 2021.
 */

import employment.system.exceptions.UserWithThisEmailAlreadyExistsException;
import employment.system.services.UserService;
import employment.system.user.User;


import java.util.Objects;

public class RegisterChecker {

    public static void checkEmailDoesNotAlreadyExist(String email) throws UserWithThisEmailAlreadyExistsException {
        for (User user : UserService.getUserRepository().find()) {
            String otherEmail = user.getEmail();
            if (Objects.equals(email, otherEmail)) {
                throw new UserWithThisEmailAlreadyExistsException(email);
            }
        }
    }
}
