package employment.system.controllers;


import employment.system.checkers.EmailChecker;
import employment.system.checkers.LoginChecker;
import employment.system.services.JobService;
import employment.system.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.IOException;


public class LoginAndRegisterController {


    @FXML
    private Text loginMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField emailField;


    public void loginButtonOnAction(ActionEvent actionEvent) throws Exception {
        String email = emailField.getText();
        String password = passwordField.getText();

        LoginChecker.update();

        if (LoginChecker.hasCoolDown()) {
            long seconds = LoginChecker.coolDownTimeRemainingInSeconds();
            loginMessage.setText("Incorrect attempt. Try again in " + ((int) (seconds / 60))
                    + " minutes and " + (seconds % 60) + " seconds.");
            return;
        }

        if (email.isEmpty()) {
            loginMessage.setText("Please type in your email!");
            return;
        }

        if (password.isEmpty()) {
            loginMessage.setText("Please type in your password!");
            return;
        }

        if (!EmailChecker.validate(email)) {
            loginMessage.setText("The email is not valid!");
            return;
        }

        if (LoginChecker.emailExistsInDataBase(email)) {
            loginMessage.setText("This email has no account associated to it!");
            return;
        }

        if (LoginChecker.isLoginValid(email, password)) {
            try {
                JobService.openJobDataBase();
                FXMLLoader loader = new FXMLLoader();
                UserService.storeCurrentPersonData(email);
                loader.setLocation(ClassLoader.getSystemResource("fxml/view_job_offers.fxml"));
                Parent openViewJobsTab = loader.load();
                ViewJobsController viewJobsController = loader.getController();
                viewJobsController.init();
                Stage stage = (Stage) loginMessage.getScene().getWindow();
                //Scene scene = new Scene(openViewJobsTab, 780, 510);
                Scene scene = new Scene(openViewJobsTab);
                stage.setResizable(true);
                stage.setScene(scene);
                stage.setMaximized(true);
                LoginChecker.resetAttempts();
                UserService.closeDatabase();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            LoginChecker.totalPCUserAttempts += 1;
            if (LoginChecker.hasMaxLogInAttempts()) {
                loginMessage.setText("Too many incorrect attempts. You can try again in " + LoginChecker.BLOCK_TIME_IN_MIN_AMOUNT + " minutes");
                LoginChecker.setCoolDown();
            } else {
                loginMessage.setText("Incorrect login!");
            }
        }
    }


    public void registerButtonOnAction(ActionEvent actionEvent) throws Exception {
        try {
            Stage stage = (Stage) loginMessage.getScene().getWindow();
            Parent openRegistrationTab = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/register.fxml"));
            Scene scene = new Scene(openRegistrationTab, 600, 400);
            stage.setScene(scene);
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
