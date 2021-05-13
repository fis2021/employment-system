package employment.system.controllers;




import employment.system.services.ApplyService;
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


public class ApplyForJobController {
    @FXML
    private TextField HWYLTBCField;
    @FXML
    private TextField CVField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button applyForJobButton;
    @FXML
    private Text applyForJobMessage;


    public void applyForJobButtonAction(ActionEvent actionEvent) {
        String HWYLTBC =  HWYLTBCField.getText();
        String CV = CVField.getText();


        if (HWYLTBC.isEmpty()) {
            applyForJobMessage.setText("Please enter your first name!");
            return;
        }

        if (CV.isEmpty()) {
            applyForJobMessage.setText("Please enter your last  name!");
            return;
        }




        try {
            ApplyService.apply(HWYLTBCField.getText(), CVField.getText());

            Stage stage = (Stage) applyForJobButton.getScene().getWindow();
            Parent openApplyingTab = FXMLLoader.load(getClass().getClassLoader().getResource("successful_applying.fxml"));
            Scene scene = new Scene(openApplyingTab, 600, 400);
            stage.setScene(scene);
            stage.setResizable(false);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void cancelButtonOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            Parent openRegistrationTab = FXMLLoader.load(getClass().getClassLoader().getResource("view_job_offers.fxml"));
            Scene scene = new Scene(openRegistrationTab, 912, 624);
            stage.setScene(scene);
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
