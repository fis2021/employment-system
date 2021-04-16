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

    public static int blockPCUserTime() {
        int amountOfTime = 0;

        if (totalPCUserAttempts >= MAX_ATTEMPTS_LIMIT) {
            amountOfTime = BLOCK_TIME_IN_MIN_AMOUNT;
            if (!isCoolDownActivated || !hasCoolDown()) {
                setCoolDown();
                isCoolDownActivated = true;
            }
        }

        return amountOfTime;
    }

    private static void setCoolDown() {
        coolDownStart = (long) System.currentTimeMillis();
    }

    public static boolean checkIfEmailAndPasswordMatch(String email, String password) {
        for (User user : UserService.getUserRepository().find()) {
            if (Objects.equals(email, user.getEmail()) && Objects.equals(password, user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public static long timeRemainingSeconds() {
        System.out.println(System.currentTimeMillis() - coolDownStart);
        long output = (long)(BLOCK_TIME_IN_MIN_AMOUNT * 60) - (long) (System.currentTimeMillis() - coolDownStart) / 1000;
        //System.out.println(output);
        return output;
    }


    public static boolean hasCoolDown() {
        if (!isCoolDownActivated) {
            return false;
        }

        long timePassed = System.currentTimeMillis() - coolDownStart;
        //System.out.println(timePassed);
        return timePassed >= (BLOCK_TIME_IN_MIN_AMOUNT * 60000) ? false : true;
    }
}
