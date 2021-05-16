package employment.system.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


public class AddNewJobController {
    @FXML
    private Button addJobButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Text addNewJobMessage;
    @FXML
    private TextField jobNameField;
    @FXML
    private TextField departmentField;
    @FXML
    private TextField programField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField minimumSalaryField;

    public void addNewJobButtonAction(ActionEvent actionEvent) {
        String jobName =  jobNameField.getText();
        String department = departmentField.getText();
        String program = programField.getText();
        String location = locationField.getText();
        String minimumSalary = minimumSalaryField.getText();

        if (jobName.isEmpty()) {
            addNewJobMessage.setText("Please enter the job name!");
            return;
        }

        if (department.isEmpty()) {
            addNewJobMessage.setText("Please enter the department!");
            return;
        }

        if (program.isEmpty()) {
            addNewJobMessage.setText("Please enter the schedule!");
            return;
        }

        if (location.isEmpty()) {
            addNewJobMessage.setText("Please enter location of work!");
            return;
        }

        if (minimumSalary.isEmpty()) {
            addNewJobMessage.setText("Please enter a minimum Salary!");
            return;
        }

        try {
            Stage stage = (Stage) addJobButton.getScene().getWindow();
            Parent openRegistrationTab = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/jobs_added_by_recruiter.fxml"));
            Scene scene = new Scene(openRegistrationTab, 900, 620);
            stage.setResizable(false);
            stage.setScene(scene);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void cancelButtonOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            Parent openRegistrationTab = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/jobs_added_by_recruiter.fxml"));
            Scene scene = new Scene(openRegistrationTab, 900, 620);
            stage.setScene(scene);
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

