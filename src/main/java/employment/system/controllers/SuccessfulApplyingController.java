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

public class SuccessfulApplyingController {
    @FXML
    private Button okButton;


    public void okButtonOnAction(ActionEvent actionEvent) {
        try {
            UserService.closeDatabase();
            JobService.openJobDataBase();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClassLoader.getSystemResource("view_job_offers.fxml"));
            Parent openViewJobsTab = loader.load();
            ViewJobsController viewJobsController = loader.getController();
            viewJobsController.createTable();
            Stage stage = (Stage) okButton.getScene().getWindow();
            Scene scene = new Scene(openViewJobsTab, 912, 624);
            stage.setResizable(true);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
