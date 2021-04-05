package employment.system.controllers;


import employment.system.checkers.EmailValidation;
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
    public Text loginMessage;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField emailField;


    public void loginButtonOnAction(ActionEvent actionEvent) throws Exception {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty()) {
            loginMessage.setText("Please type in your email!");
            return;
        }
        if (!EmailValidation.validate(email)) {
            loginMessage.setText("The email is not valid!");
            return;
        }

        if (password.isEmpty()) {
            loginMessage.setText("Password cannot be empty");
            return;
        }

        loginMessage.setText("Incorrect login!");
    }


    public void registerButtonOnAction(ActionEvent actionEvent) throws Exception {
        try {
            Stage stage = (Stage) loginMessage.getScene().getWindow();
            // TODO: login.fxml file
            Parent openRegistrationTab = FXMLLoader.load(getClass().getResource("../fxml/register.fxml"));
            Scene scene = new Scene(openRegistrationTab, 600, 400);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
