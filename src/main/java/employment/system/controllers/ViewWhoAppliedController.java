package employment.system.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class ViewWhoAppliedController {
    @FXML
    public Button backButton;



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

