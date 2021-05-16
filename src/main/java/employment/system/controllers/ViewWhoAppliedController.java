package employment.system.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;


public class ViewWhoAppliedController {
    @FXML
    public Button backButton;
    @FXML
    private Button rejectButton;
    @FXML
    private Label expInItField;
    @FXML
    private Label ageField;
    @FXML
    private Label telephoneField;
    @FXML
    private Label countryField;
    @FXML
    private Label cityField;
    @FXML
    private Label citizenshipField;
    @FXML
    private Label otherLanguagesField;
    @FXML
    private Label nativeLanguageField;
    @FXML
    private Button cvButton;
    @FXML
    private Button applicantProfileButton;
    @FXML
    private Button downLoadButton;
    @FXML
    private Button acceptButton;
    @FXML
    private Button inProgressButton;
    @FXML
    private Button RejectButton;
    @FXML
    private TableColumn applicantNameColumn;
    @FXML
    private TableColumn positionColumn;
    @FXML
    private TableColumn statusColumn;
    @FXML
    private TableView appliedTable;



    public void backButtonOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            Parent openViewJobTab = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/recruiter_profile.fxml"));
            Scene scene = new Scene(openViewJobTab, 780, 624);
            stage.setResizable(true);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

