package employment.system.exceptions;

public class UserWithThisEmailAlreadyExistsException extends Exception {
    private String email;
    public UserWithThisEmailAlreadyExistsException(String email) {
        super(String.format("An account with the email %s is already created!", email));
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
}
