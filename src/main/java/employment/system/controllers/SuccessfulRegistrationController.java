package employment.system.controllers;

import employment.system.services.JobService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SuccessfulRegistrationController {
    @FXML
    private Button okButton;

    public void okButtonOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) okButton.getScene().getWindow();
            Parent openRegistrationTab = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/login.fxml"));
            Scene scene = new Scene(openRegistrationTab, 600, 400);
            JobService.setSelectedJob(null);
            stage.setResizable(false);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
