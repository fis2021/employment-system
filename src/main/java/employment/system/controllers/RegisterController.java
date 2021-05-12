package employment.system.controllers;


import employment.system.checkers.EmailChecker;
import employment.system.exceptions.UserWithThisEmailAlreadyExistsException;
import employment.system.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class RegisterController {
    @FXML
    private ChoiceBox roleChoiceBox;
    @FXML
    private Button cancelButton;
    @FXML
    private Button registerButton;
    @FXML
    private TextField emailField;
    @FXML
    private Text registrationMessage;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField reenteredPasswordField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;

    public void registerButtonAction(ActionEvent actionEvent) {
        String email =  emailField.getText();
        String password = passwordField.getText();
        String reenteredPassword = reenteredPasswordField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();


        if (firstName.isEmpty()) {
            registrationMessage.setText("Please enter your first name!");
        }

        if (lastName.isEmpty()) {
            registrationMessage.setText("Please enter your last  name!");
            return;
        }

        if (email.isEmpty()) {
            registrationMessage.setText("Please enter a email!");
            return;
        }

        if (!EmailChecker.validate(email)) {
            registrationMessage.setText("Please enter a valid email!");
            return;
        }

        if (password.isEmpty()) {
            registrationMessage.setText("Please enter a password!");
            return;
        }

        if (reenteredPassword.isEmpty()) {
            registrationMessage.setText("Please re-enter the password!");
            return;
        }

        if (!password.equals(reenteredPassword)) {
            registrationMessage.setText("Passwords do not match!");
            return;
        }

        try {
            UserService.addUser(emailField.getText(), firstNameField.getText(), lastNameField.getText(), passwordField.getText(), null);

            Stage stage = (Stage) registerButton.getScene().getWindow();
            Parent openRegistrationTab = FXMLLoader.load(getClass().getClassLoader().getResource("successful_registration.fxml"));
            Scene scene = new Scene(openRegistrationTab, 600, 400);
            stage.setResizable(false);
            stage.setScene(scene);
        } catch (UserWithThisEmailAlreadyExistsException e) {
            registrationMessage.setText("This email is already used by another account.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void cancelButtonOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            Parent openRegistrationTab = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Scene scene = new Scene(openRegistrationTab, 600, 400);
            stage.setResizable(false);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
