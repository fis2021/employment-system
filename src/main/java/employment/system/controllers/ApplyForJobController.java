package employment.system.controllers;


import employment.system.checkers.EmailChecker;
import employment.system.services.ApplyService;
import employment.system.services.JobService;
import employment.system.services.UserService;
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
    private Button cancelButton;
    @FXML
    private Button applyForJobButton;
    @FXML
    private Text applyForJobMessage;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField nativeLanguageField;
    @FXML
    private TextField otherLanguagesField;
    @FXML
    private TextField addressField;

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

        if (lastName.isEmpty()) {
            applyForJobMessage.setText("Please enter your last  name!");
            return;
        }

        if (email.isEmpty()) {
            applyForJobMessage.setText("Please enter a email!");
            return;
        }

        if (!EmailChecker.validate(email)) {
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
            Parent openApplyingTab = FXMLLoader.load(getClass().getClassLoader().getResource("successful_applying.fxml"));
            Scene scene = new Scene(openApplyingTab, 600, 400);
            stage.setScene(scene);
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void cancelButtonOnAction(ActionEvent actionEvent) {
        try {
            UserService.closeDatabase();
            JobService.openJobDataBase();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClassLoader.getSystemResource("view_job_offers.fxml"));
            Parent openViewJobsTab = loader.load();
            ViewJobsController viewJobsController = loader.getController();
            viewJobsController.createTable();
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            Scene scene = new Scene(openViewJobsTab, 912, 624);
            stage.setResizable(true);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
