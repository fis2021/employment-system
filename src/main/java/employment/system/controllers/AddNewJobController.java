package employment.system.controllers;

import employment.system.exceptions.JobWithThisIdAlreadyExistsException;
import employment.system.services.JobService;
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
    private Button cancelButton;
    @FXML
    private Button addNewJobButton;
    @FXML
    private Text addNewJobMessage;
    @FXML
    private TextField jobNameField;
    @FXML
    private TextField companyField;
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
        String company = companyField.getText();
        String department = departmentField.getText();
        String program = programField.getText();
        String location = locationField.getText();
        String minimumSalary = minimumSalaryField.getText();


        if (program.isEmpty()) {
            addNewJobMessage.setText("Please enter the program!");
            return;
        }

        if (location.isEmpty()) {
            addNewJobMessage.setText("Please enter location of work!");
            return;
        }

        if (jobName.isEmpty()) {
            addNewJobMessage.setText("Please enter the name of job!");
            return;
        }


        if (company.isEmpty()) {
            addNewJobMessage.setText("Please enter the company!");
            return;
        }

        if (department.isEmpty()) {
            addNewJobMessage.setText("Please enter the department!");
            return;
        }
        if (minimumSalary.isEmpty()) {
            addNewJobMessage.setText("Please enter a minimum Salary!");
            return;
        }

        try {
            JobService.addJob(null, jobNameField.getText(), companyField.getText(), departmentField.getText());
            Stage stage = (Stage) addNewJobButton.getScene().getWindow();
            Parent openRegistrationTab = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/login.fxml"));
            Scene scene = new Scene(openRegistrationTab, 600, 400);
            stage.setResizable(false);
            stage.setScene(scene);
        }
        catch (JobWithThisIdAlreadyExistsException e) {
            // TODO: change error message later.
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void cancelButtonOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            Parent openRegistrationTab = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/login.fxml"));
            Scene scene = new Scene(openRegistrationTab, 600, 400);
            stage.setScene(scene);
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

