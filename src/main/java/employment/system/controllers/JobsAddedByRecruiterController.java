package employment.system.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class JobsAddedByRecruiterController {
    @FXML
    private TableView appliedTable;
    @FXML
    private TableColumn jobNameColumn;
    @FXML
    private TableColumn jobCategoryColumn;
    @FXML
    private Button applicantProfileButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addJobButton;
    @FXML
    private Button jobOfferButton;

    public void jobOfferButtonOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) jobOfferButton.getScene().getWindow();
            Parent openViewJobTab = FXMLLoader.load(getClass().getClassLoader().getResource("view_job_offers.fxml"));
            Scene scene = new Scene(openViewJobTab, 780, 624);
            stage.setResizable(true);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addJobButtonOnAction (ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) addJobButton.getScene().getWindow();
            Parent openProfileTab = FXMLLoader.load(getClass().getClassLoader().getResource("add_new_job.fxml"));
            Scene scene = new Scene(openProfileTab, 780, 510);
            stage.setResizable(false);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void applicantProfileButtonOnAction (ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) applicantProfileButton.getScene().getWindow();
            Parent openProfileTab = FXMLLoader.load(getClass().getClassLoader().getResource("employee_profile.fxml"));
            Scene scene = new Scene(openProfileTab, 780, 510);
            stage.setResizable(false);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
