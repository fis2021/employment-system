package employment.system.controllers;

import employment.system.services.JobService;
import employment.system.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeProfileController {
    @FXML
    private Button cancelButton;
    @FXML
    private Button editProfileButton;

    public void cancelButtonOnAction(ActionEvent actionEvent) {
        try {
            UserService.closeDatabase();
            JobService.openJobDataBase();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClassLoader.getSystemResource("fxml/view_job_offers.fxml"));
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
            Parent openEditProfileTab = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/edit_employee_profile.fxml"));
            Scene scene = new Scene(openEditProfileTab, 900, 510);
            stage.setScene(scene);
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
