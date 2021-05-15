package employment.system.controllers;

import employment.system.services.JobService;
import employment.system.services.UserService;
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
            Parent openEditProfileTab = FXMLLoader.load(getClass().getClassLoader().getResource("edit_recruiter_profile.fxml"));
            Scene scene = new Scene(openEditProfileTab, 900, 510);
            stage.setScene(scene);
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewApplicantsButtonOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) viewApplicantsButton.getScene().getWindow();
            Parent openEditProfileTab = FXMLLoader.load(getClass().getClassLoader().getResource("view_who_applied.fxml"));
            Scene scene = new Scene(openEditProfileTab, 910, 620);
            stage.setScene(scene);
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void jobsAddedByMeButtonOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) jobsAddedByMeButton.getScene().getWindow();
            Parent openEditProfileTab = FXMLLoader.load(getClass().getClassLoader().getResource("jobs_added_by_recruiter.fxml"));
            Scene scene = new Scene(openEditProfileTab, 910, 620);
            stage.setScene(scene);
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addNewJobButtonOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) addNewJobButton.getScene().getWindow();
            Parent openEditProfileTab = FXMLLoader.load(getClass().getClassLoader().getResource("jobs_added_by_recruiter.fxml"));
            Scene scene = new Scene(openEditProfileTab, 910, 620);
            stage.setScene(scene);
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
