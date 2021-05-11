package employment.system.controllers;/*
 * Created by Adrian Dragoș on 5/11/2021 May 2021.
 */


import employment.system.jobs.Job;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ViewJobsController {

    public Label companyNameField;
    public Label jobPositionNameField;
    public Label jobLocationField;
    public Label jobScheduleField;
    public Label jobSalaryField;
    public Label jobDescriptionField;
    public ImageView imageField;
    public Label jobRequirementsField;
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
    }

    public void applyButtonOnAction(ActionEvent actionEvent) {
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
