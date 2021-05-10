package employment.system.checkers;
/*
 * Created by Adrian Dragoș on 4/16/2021 Apr 2021.
 */


import employment.system.services.UserService;
import employment.system.user.User;


import java.util.Objects;

import static employment.system.services.UserService.verifyUserPassword;

public class LoginChecker {

    public static int totalPCUserAttempts = 0;
    private static final int MAX_ATTEMPTS_LIMIT = 5;
    public static final int BLOCK_TIME_IN_MIN_AMOUNT = 10;
    private static long coolDownStart;
    private static boolean isCoolDownActivated;


    public static boolean isLoginValid(String email, String password) {
        for (User user : UserService.getUserRepository().find()) {
            if (Objects.equals(email, user.getEmail()) && verifyUserPassword(password, user.getPassword(), email)) {
                return true;
            }
        }
        return false;
    }


    public static void setCoolDown() {
        coolDownStart = (long) System.currentTimeMillis();
        isCoolDownActivated = true;
    }

    public static long coolDownTimeRemainingInSeconds() {
        return (long) (BLOCK_TIME_IN_MIN_AMOUNT * 60) - passedTimeInSecondsFromFailedAttempt();
    }

    public static boolean hasCoolDown() {
        return isCoolDownActivated;
    }

    private static long passedTimeInSecondsFromFailedAttempt() {
        return (System.currentTimeMillis() - coolDownStart) / 1000;
    }

    public static boolean hasMaxLogInAttempts() {
        return totalPCUserAttempts >= MAX_ATTEMPTS_LIMIT;
    }


    public static void update() {
        if (passedTimeInSecondsFromFailedAttempt() >= (BLOCK_TIME_IN_MIN_AMOUNT * 60)) {
            isCoolDownActivated = false;
        }
    }

    public static boolean emailExistsInDataBase(String email) {
        for (User user : UserService.getUserRepository().find()) {
            String otherEmail = user.getEmail();
            if (Objects.equals(email, otherEmail)) {
                return false;
            }
        }
        return  true;
    }

    public static void resetAttempts() {
        totalPCUserAttempts = 0;
    }
}
