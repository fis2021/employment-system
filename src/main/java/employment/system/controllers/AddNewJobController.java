package employment.system.controllers;

import employment.system.services.JobService;
import employment.system.services.UserService;
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
    public Button cancelButton;
    public Button addNewJobButton;
    public Text addNewJobMessage;
    public TextField jobNameField;
    public TextField companyField;
    public TextField departmentField;
    public TextField programField;
    public TextField locationField;
    public TextField minimumSalaryField;

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

        if(location.isEmpty()) {
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
            JobService.addJob(jobNameField.getText(), companyField.getText(), departmentField.getText(), programField.getText(), locationField.getText(), minimumSalaryField.getText());

            Stage stage = (Stage) addNewJobButton.getScene().getWindow();
            Parent openRegistrationTab = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Scene scene = new Scene(openRegistrationTab, 600, 400);
            stage.setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void cancelButtonOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            Parent openRegistrationTab = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Scene scene = new Scene(openRegistrationTab, 600, 400);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

