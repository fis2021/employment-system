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
    private Button backButton;
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


    public void addJobButtonOnAction (ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) addJobButton.getScene().getWindow();
            Parent openProfileTab = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/add_new_job.fxml"));
            Scene scene = new Scene(openProfileTab, 780, 510);
            stage.setResizable(false);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backButtonOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClassLoader.getSystemResource("fxml/recruiter_profile.fxml"));
            Parent recruiterProfileTab = loader.load();
            RecruiterProfileController recruiterProfileController = loader.getController();
            recruiterProfileController.initiate();
            Scene scene = new Scene(recruiterProfileTab, 900, 510);
            stage.setResizable(false);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
