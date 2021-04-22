package employment.system.controllers;


import employment.system.checkers.EmailValidation;
import employment.system.services.ApplyService;
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


public class ApplyForJobController {
    @FXML
    public Button cancelButton;
    public Button applyForJobButton;
    public Text applyForJobMessage;
    public TextField firstNameField;
    public TextField lastNameField;
    public TextField emailField;
    public TextField ageField;
    public TextField nativeLanguageField;
    public TextField otherLanguagesField;
    public TextField addressField;

    public void applyForJobButtonAction(ActionEvent actionEvent) {
        String firstName =  firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String age = ageField.getText();
        String address = addressField.getText();
        String nativeLanguage = nativeLanguageField.getText();
        String otherLanguages = otherLanguagesField.getText();


        if (firstName.isEmpty()) {
            applyForJobMessage.setText("Please enter your first name!");
            return;
        }

        if(lastName.isEmpty()) {
            applyForJobMessage.setText("Please enter your last  name!");
            return;
        }

        if (email.isEmpty()) {
            applyForJobMessage.setText("Please enter a email!");
            return;
        }

        if (!EmailValidation.validate(email)){
            applyForJobMessage.setText("Please enter a valid email!");
            return;
        }

        if (age.isEmpty()) {
            applyForJobMessage.setText("Please enter your age!");
            return;
        }

        if (address.isEmpty()) {
            applyForJobMessage.setText("Please enter your address!");
            return;
        }



        try {
            ApplyService.apply(firstNameField.getText(), lastNameField.getText(), emailField.getText(), ageField.getText(), addressField.getText(), nativeLanguageField.getText(), otherLanguagesField.getText());

            Stage stage = (Stage) applyForJobButton.getScene().getWindow();
            Parent openRegistrationTab = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
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