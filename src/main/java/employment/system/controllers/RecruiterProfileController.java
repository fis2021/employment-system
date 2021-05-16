package employment.system.controllers;

import employment.system.services.RecruiterService;
import employment.system.services.JobService;
import employment.system.services.UserService;
import employment.system.user.Recruiter;
import employment.system.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class RecruiterProfileController {
    @FXML
    private Label messageField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button editProfileButton;
    @FXML
    private Button viewApplicantsButton;
    @FXML
    private Button jobsAddedByMeButton;
    @FXML
    private Button addNewJobButton;
    @FXML
    private Text recruiterNameText;
    @FXML
    private Text recruiterEmailText;
    @FXML
    private Label companyNameLabel;
    @FXML
    private Label telephoneNumberLabel;
    @FXML
    private Label addressLabel;

    private final String NOT_SET_YET_MESSAGE = "not set yet";

    public void cancelButtonOnAction(ActionEvent actionEvent) {
        try {
            UserService.closeDatabase();
            RecruiterService.closeDatabase();
            JobService.openJobDataBase();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClassLoader.getSystemResource("fxml/view_job_offers.fxml"));
            Parent openViewJobsTab = loader.load();
            ViewJobsController viewJobsController = loader.getController();
            viewJobsController.init();
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            Scene scene = new Scene(openViewJobsTab, 780, 510);
            stage.setResizable(true);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editProfileButtonOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) editProfileButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClassLoader.getSystemResource("fxml/edit_recruiter_profile.fxml"));
            Parent editTabRecruiter = loader.load();
            EditRecruiterProfileController editRecruiterProfileController = loader.getController();
            editRecruiterProfileController.initiate();
            Scene scene = new Scene(editTabRecruiter, 900, 510);
            stage.setResizable(false);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewApplicantsButtonOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) viewApplicantsButton.getScene().getWindow();
            Parent openEditProfileTab = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/view_who_applied.fxml"));
            Scene scene = new Scene(openEditProfileTab, 780, 620);
            stage.setScene(scene);
            stage.setResizable(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void jobsAddedByMeButtonOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) jobsAddedByMeButton.getScene().getWindow();
            Parent openEditProfileTab = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/jobs_added_by_recruiter.fxml"));
            Scene scene = new Scene(openEditProfileTab, 780, 620);
            stage.setScene(scene);
            stage.setResizable(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void initiate() {
        User currentUser = UserService.getCurrentUser();
        recruiterEmailText.setText(currentUser.getEmail());
        recruiterNameText.setText(currentUser.getFirstName() + " " + currentUser.getLastName());

        UserService.closeDatabase();
        if (RecruiterService.isClosed()) {
            RecruiterService.openDatabase();
        }
        RecruiterService.setCurrentRecruiter(currentUser.getEmail());
        Recruiter recruiter = RecruiterService.getCurrentRecruiter();

        if (recruiter == null || recruiter.getCompanyName() == null || recruiter.getCompanyName().isEmpty()) {
            companyNameLabel.setText(NOT_SET_YET_MESSAGE);
        } else {
            companyNameLabel.setText(recruiter.getCompanyName());
        }

        if (!recruiter.hasNullFields() && !recruiter.hasEmptyFields()) {
            messageField.setVisible(false);
        } else {
            messageField.setVisible(true);
            messageField.setText("Please complete your profile!");
        }

        if (recruiter == null || recruiter.getTelephone() == null || recruiter.getTelephone().isEmpty()) {
            telephoneNumberLabel.setText(NOT_SET_YET_MESSAGE);
        } else {
            telephoneNumberLabel.setText(recruiter.getTelephone());
        }

        if (recruiter == null || recruiter.getCompanyAddress() == null || recruiter.getCompanyAddress().isEmpty()) {
            addressLabel.setText(NOT_SET_YET_MESSAGE);
        } else {
            addressLabel.setText(recruiter.getCompanyAddress());
        }
    }
}
