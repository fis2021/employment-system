package employment.system.controllers;


import employment.system.job.Job;
import employment.system.services.ApplicantService;
import employment.system.services.JobService;
import employment.system.services.UserService;
import employment.system.user.Applicant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;
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
            JobService.closeJobDataBase();
            UserService.openUserDatabase();
            Stage stage = (Stage) profileButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClassLoader.getSystemResource("fxml/employee_profile.fxml"));
            Parent employeeProfileTab = loader.load();
            EmployeeProfileController employeeProfileController = loader.getController();
            employeeProfileController.initiate();
            Scene scene = new Scene(employeeProfileTab, 900, 510);
            stage.setResizable(false);
            stage.setScene(scene);
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void applyButtonOnAction(ActionEvent actionEvent) {
        try {
            JobService.closeJobDataBase();
            UserService.openUserDatabase();
            Stage stage = (Stage) profileButton.getScene().getWindow();
            Parent openProfileTab = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/apply_for_job.fxml"));
            Scene scene = new Scene(openProfileTab, 796, 571);
            stage.setResizable(false);
            stage.setScene(scene);
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        initJobData();
        jobTable.setItems(jobData);
    }


    private void initJobData() {
        // Add some sample data
        jobData = FXCollections.observableArrayList(JobService.extractJobsFromDataBase());

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
