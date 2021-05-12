package employment.system.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class EditEmployeeProfileController {
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;

    public void cancelButtonOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            Parent openProfileTab = FXMLLoader.load(getClass().getClassLoader().getResource("employee_profile.fxml"));
            Scene scene = new Scene(openProfileTab, 780, 510);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveButtonOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) saveButton.getScene().getWindow();
            Parent openProfileTab = FXMLLoader.load(getClass().getClassLoader().getResource("employee_profile.fxml"));
            Scene scene = new Scene(openProfileTab, 780, 510);
            stage.setResizable(true);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
