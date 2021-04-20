package employment.system.checkers;
/*
 * Created by Adrian Dragoș on 4/16/2021 Apr 2021.
 */


import employment.system.services.UserService;
import employment.system.user.User;

import java.util.Objects;

public class LoginChecker {

    private static int totalPCUserAttempts = 0;
    private static final int MAX_ATTEMPTS_LIMIT = 5;
    public static final int BLOCK_TIME_IN_MIN_AMOUNT = 10;
    private static long coolDownStart;
    private static boolean isCoolDownActivated = false;


    public static boolean isLoginValid(String email, String password) {
        if (!checkIfEmailAndPasswordMatch(email, UserService.encodePassword(email, password))) {
            totalPCUserAttempts += 1;
            return false;
        } else {
            totalPCUserAttempts = 0;
        }
        return true;
    }


    public static void setCoolDown() {
        coolDownStart = (long) System.currentTimeMillis();
        isCoolDownActivated = true;
    }

    public static boolean checkIfEmailAndPasswordMatch(String email, String password) {
        for (User user : UserService.getUserRepository().find()) {
            if (Objects.equals(email, user.getEmail()) && Objects.equals(password, user.getPassword())) {
                return true;
            }
        }
        return false;
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

    public static boolean maxLogInAttempts() {
        return totalPCUserAttempts >= MAX_ATTEMPTS_LIMIT;
    }


    public static void update() {
        if (passedTimeInSecondsFromFailedAttempt() >= (BLOCK_TIME_IN_MIN_AMOUNT * 60)) {
            isCoolDownActivated = false;
        }
    }
}
