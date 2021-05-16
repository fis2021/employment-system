package employment.system.controllers;

import employment.system.services.ApplicantService;
import employment.system.services.JobService;
import employment.system.services.UserService;
import employment.system.user.Applicant;
import employment.system.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class EmployeeProfileController {
    @FXML
    private Label messageField;
    @FXML
    private Label experienceInITLabel;
    @FXML
    private Label birthdayLabel;
    @FXML
    private Label countryLabel;
    @FXML
    private Label citizenshipLabel;
    @FXML
    private Label nativeLanguageLabel;
    @FXML
    private Label otherLanguageLabel;
    @FXML
    private Label telephoneLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Text expInItField;
    @FXML
    private Text birthDayField;
    @FXML
    private Text otherLanguagesField;
    @FXML
    private Text citizenshipField;
    @FXML
    private Text nativeLanguageField;
    @FXML
    private Text cityField;
    @FXML
    private Text telephoneNumberField;
    @FXML
    private Text countryField;
    @FXML
    private Text employeeName;
    @FXML
    private Text emailField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button editProfileButton;

    private static final String NOT_SET_YET_MESSAGE = "not set yet";

    public void cancelButtonOnAction(ActionEvent actionEvent) {
        try {
            UserService.closeDatabase();
            ApplicantService.closeDatabase();
            JobService.openJobDataBase();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClassLoader.getSystemResource("fxml/view_job_offers.fxml"));
            Parent openViewJobsTab = loader.load();
            ViewJobsController viewJobsController = loader.getController();
            viewJobsController.init();
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            Scene scene = new Scene(openViewJobsTab, 780, 510);
            stage.setResizable(true);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editProfileButtonOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) editProfileButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClassLoader.getSystemResource("fxml/edit_employee_profile.fxml"));
            Parent employeeProfileTab = loader.load();
            EditEmployeeProfileController employeeProfileController = loader.getController();
            employeeProfileController.initiate();
            Scene scene = new Scene(employeeProfileTab, 780, 510);
            stage.setResizable(false);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initiate() {

        User currentUser = UserService.getCurrentUser();
        emailField.setText(currentUser.getEmail());
        employeeName.setText(currentUser.getFirstName() + " " + currentUser.getLastName());

        UserService.closeDatabase();
        ApplicantService.openDatabase();
        ApplicantService.setCurrentApplicant(currentUser.getEmail());
        Applicant applicant = ApplicantService.getCurrentApplicant();


        if (applicant == null || applicant.getExperienceInItDomain() == null || applicant.getExperienceInItDomain().isEmpty()) {
            experienceInITLabel.setText(NOT_SET_YET_MESSAGE);
        } else {
            experienceInITLabel.setText(applicant.getExperienceInItDomain());
        }

        if (applicant != null && (!applicant.applicantHasNullFields() || !applicant.hasEmptyFields())) {
            messageField.setText("Please complete your profile!");
        } else {
            messageField.setVisible(false);
        }

        if (applicant == null || applicant.getBirthday() == null) {
            birthdayLabel.setText(NOT_SET_YET_MESSAGE);
        } else {
            DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
            birthdayLabel.setText(dateFormat.format(applicant.getBirthday()));
        }

        if (applicant == null || applicant.getTelephoneNumber() == null || applicant.getTelephoneNumber().isEmpty()) {
            telephoneLabel.setText(NOT_SET_YET_MESSAGE);
        } else {
            telephoneLabel.setText(applicant.getTelephoneNumber());
        }

        if (applicant == null || applicant.getCountry() == null || applicant.getCountry().isEmpty()) {
            countryLabel.setText(NOT_SET_YET_MESSAGE);
        } else {
            countryLabel.setText(applicant.getCountry());
        }

        if (applicant == null || applicant.getCity() == null || applicant.getCity().isEmpty()) {
            cityLabel.setText(NOT_SET_YET_MESSAGE);
        } else {
            cityLabel.setText(applicant.getCity());
        }

        if (applicant == null || applicant.getCitizenship() == null || applicant.getCitizenship().isEmpty()) {
            citizenshipLabel.setText(NOT_SET_YET_MESSAGE);
        } else {
            citizenshipLabel.setText(applicant.getCitizenship());
        }

        if (applicant == null || applicant.getNativeLanguage() == null || applicant.getNativeLanguage().isEmpty()) {
            nativeLanguageLabel.setText(NOT_SET_YET_MESSAGE);
        } else {
            nativeLanguageLabel.setText(applicant.getNativeLanguage());
        }

        if (applicant == null || applicant.getOtherLanguages() == null || applicant.getOtherLanguages().isEmpty()) {
            otherLanguageLabel.setText(NOT_SET_YET_MESSAGE);
        } else {
            otherLanguageLabel.setText(applicant.getOtherLanguages());
        }
    }
}
