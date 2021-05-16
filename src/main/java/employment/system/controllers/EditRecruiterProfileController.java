package employment.system.controllers;

import employment.system.services.RecruiterService;
import employment.system.services.UserService;
import employment.system.user.Recruiter;
import employment.system.user.User;
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

public class EditRecruiterProfileController {
    @FXML
    private Text recruiterEmailField;
    @FXML
    private Text recruiterNameField;
    @FXML
    private TextField companyField;
    @FXML
    private TextField telephoneNumberField;
    @FXML
    private TextField addressField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;

    public void cancelButtonOnAction(ActionEvent actionEvent) {
        RecruiterService.closeDatabase();
        UserService.openUserDatabase();
        try {
            Stage stage = (Stage) saveButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClassLoader.getSystemResource("fxml/recruiter_profile.fxml"));
            Parent recruiterTab = loader.load();
            RecruiterProfileController recruiterProfileController = loader.getController();
            recruiterProfileController.initiate();
            Scene scene = new Scene(recruiterTab, 780, 510);
            stage.setResizable(false);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveButtonOnAction(ActionEvent actionEvent) {
        // Save to recruiter Database
        Recruiter recruiter = RecruiterService.getCurrentRecruiter();

        recruiter.setCompanyAddress(addressField.getText());
        recruiter.setTelephone(telephoneNumberField.getText());
        recruiter.setCompanyName(companyField.getText());


        RecruiterService.update(recruiter);
        RecruiterService.closeDatabase();
        UserService.openUserDatabase();
        try {
            Stage stage = (Stage) saveButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClassLoader.getSystemResource("fxml/recruiter_profile.fxml"));
            Parent recruiterProfileTab = loader.load();
            RecruiterProfileController recruiterProfileController = loader.getController();
            recruiterProfileController.initiate();
            Scene scene = new Scene(recruiterProfileTab, 780, 510);
            stage.setResizable(false);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initiate() {
        User currentUser = UserService.getCurrentUser();
        recruiterNameField.setText(currentUser.getEmail());
        recruiterEmailField.setText(currentUser.getFirstName() + " " + currentUser.getLastName());

        Recruiter recruiter = RecruiterService.getCurrentRecruiter();
        companyField.setText(recruiter.getCompanyName());
        telephoneNumberField.setText(recruiter.getTelephone());
        addressField.setText(recruiter.getCompanyAddress());
    }
}
