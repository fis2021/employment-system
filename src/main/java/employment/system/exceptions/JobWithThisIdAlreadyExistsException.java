package employment.system.exceptions;

public class JobWithThisIdAlreadyExistsException extends Exception {
        private String job;
        public JobWithThisIdAlreadyExistsException(String job) {
                super(String.format("An account with the email %s is already created!", job));
                this.job = job;
        }
        public String getEmail() {
                return job;
        }
}
