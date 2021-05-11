package employment.system.controllers;/*
 * Created by Adrian Dragoș on 5/11/2021 May 2021.
 */


import employment.system.jobs.Job;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;


public class ViewJobsController {
    @FXML
    private Button applyButton;
    @FXML
    private Label companyNameField;
    @FXML
    private Label jobPositionNameField;
    @FXML
    private Label jobLocationField;
    @FXML
    private Label jobScheduleField;
    @FXML
    private Label jobSalaryField;
    @FXML
    private Label jobDescriptionField;
    @FXML
    private ImageView imageField;
    @FXML
    private Label jobRequirementsField;
    @FXML
    private Button profileButton;
    @FXML
    private TableView<Job> jobTable;
    @FXML
    private TableColumn<Job, String> jobNameColumn;
    @FXML
    private TableColumn<Job, String> jobCategoryColumn;
    @FXML
    private TableColumn<Job, String> companyNameColumn;


    private ObservableList<Job> jobData = FXCollections.observableArrayList();


    public void profileButtonOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) profileButton.getScene().getWindow();
            Parent openProfileTab = FXMLLoader.load(getClass().getClassLoader().getResource("employee_profile.fxml"));
            Scene scene = new Scene(openProfileTab, 600, 400);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void applyButtonOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) applyButton.getScene().getWindow();
            Parent openApplicationTab = FXMLLoader.load(getClass().getClassLoader().getResource("apply_for_job.fxml"));
            Scene scene = new Scene(openApplicationTab, 600, 400);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        initJobData();
        jobTable.setItems(jobData);
    }


    private void initJobData()
    {
        // Add some sample data
        jobData.add(new Job("Full Stack Engineer", "Full Stack", "Google", new Image("google.png")));
        jobData.add(new Job("Full Stack Engineer", "Full Stack", "Amazon", new Image("amazon.png")));
        jobData.add(new Job("Full Stack Engineer", "Full Stack", "Facebook", new Image("facebook.png")));
        jobData.add(new Job("Web Designer", "Web Designer", "Advanced Supreme", null));
        jobData.add(new Job("ASAP.Net Developer", "Back End", "ASsOQ", null));
        jobData.add(new Job("PHP programmer", "Front End", "BPay", null));
        jobData.add(new Job("Java Developer", "Back End", "Google", new Image("google.png")));
        jobData.add(new Job("C/C++", "Embedded", "Mantis", null));
        jobData.add(new Job("Android Developer", "Mobile", "Amazon", new Image("amazon.png")));

        // Initialize the person table with the two columns.
        jobNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().jobNameProperty());
        jobCategoryColumn.setCellValueFactory(
                cellData -> cellData.getValue().jobCategoryProperty());
        companyNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().companyNameProperty());


        // Clear person details.
        showJobsDetails(null);

        // Listen for selection changes and show the person details when changed.
        jobTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showJobsDetails(newValue));
    }


    private void showJobsDetails(Job job) {
        if (job != null) {
            // Fill the labels with info from the person object.
            companyNameField.setText(job.getCompanyName());
            jobPositionNameField.setText(job.getJobName());
            jobLocationField.setText(job.getJobLocation());
            jobScheduleField.setText(job.getJobSchedule());
            jobSalaryField.setText(job.getJobSalary());
            jobDescriptionField.setText(job.getJobDescription());
            jobRequirementsField.setText(job.getJobRequirements());
            imageField.setImage(job.getImage());
        } else {
            // Person is null, remove all the text.
            companyNameField.setText("");
            jobPositionNameField.setText("");
            jobLocationField.setText("");
            jobScheduleField.setText("");
            jobSalaryField.setText("");
            jobDescriptionField.setText("");
            jobRequirementsField.setText("");
        }
    }
}
