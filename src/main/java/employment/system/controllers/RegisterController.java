package employment.system.controllers;/*
 ~ Created by Anca Esanu on 6 Aprilie 2021 ~
 */

import employment.system.checkers.EmailValidation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;




public class RegisterController {
    @FXML
    public Button cancelButton;
    public Button registerButton;
    public TextField emailField;
    public Text errorMessage;
    public TextField passwordField;
    public TextField reenteredPasswordField;
    public TextField firstNameField;
    public TextField lastNameField;

    public void registerButtonAction(ActionEvent actionEvent) {
        String email =  emailField.getText();
        String password = passwordField.getText();
        String reenteredPassword = reenteredPasswordField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();


        if (firstName.isEmpty()) {
            errorMessage.setText("Please enter your first name!");
        }

        if(lastName.isEmpty()) {
            errorMessage.setText("Please enter your last  name!");
            return;
        }

        if (email.isEmpty()) {
            errorMessage.setText("Please enter a email!");
            return;
        }

        if (!EmailValidation.validate(email)){
            errorMessage.setText("Please enter a valid email!");
            return;
        }

        if (password.isEmpty()) {
            errorMessage.setText("Please enter a password!");
            return;
        }

        if (reenteredPassword.isEmpty()) {
            errorMessage.setText("Please re-enter the password!");
            return;
        }

        if (!password.equals(reenteredPassword)) {
            errorMessage.setText("Passwords do not match!");
            return;
        }

        // replace with a new window where it will be shown that registration was successful
        try {
            Stage stage = (Stage) registerButton.getScene().getWindow();
            Parent openRegistrationTab = FXMLLoader.load(getClass().getClassLoader().getResource("successfulRegistration.fxml"));
            Scene scene = new Scene(openRegistrationTab, 600, 400);
            stage.setScene(scene);



        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void cancelButtonOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            Parent openRegistrationTab = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Scene scene = new Scene(openRegistrationTab, 600, 400);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
