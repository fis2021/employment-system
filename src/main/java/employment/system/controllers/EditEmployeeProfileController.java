package employment.system.controllers;

import employment.system.services.ApplicantService;
import employment.system.services.UserService;
import employment.system.user.Applicant;
import employment.system.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;


public class EditEmployeeProfileController {
    @FXML
    private TextField citizenshipField;
    @FXML
    private TextField otherLanguageField;
    @FXML
    private DatePicker birthdayField;
    @FXML
    private TextField telephoneNumberField;
    @FXML
    private TextField expInITField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField countryField;
    @FXML
    private TextField nativeLanguageField;
    @FXML
    private Text employeeName;
    @FXML
    private Text emailField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;

    public void cancelButtonOnAction(ActionEvent actionEvent) {
        ApplicantService.closeDatabase();
        UserService.openUserDatabase();
        try {
            Stage stage = (Stage) saveButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClassLoader.getSystemResource("fxml/employee_profile.fxml"));
            Parent employeeProfileTab = loader.load();
            EmployeeProfileController employeeProfileController = loader.getController();
            employeeProfileController.initiate();
            Scene scene = new Scene(employeeProfileTab, 780, 510);
            stage.setResizable(false);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveButtonOnAction(ActionEvent actionEvent) {
        // Save to applicant Database
        Applicant applicant = ApplicantService.getCurrentApplicant();

        applicant.setExperienceInItDomain(expInITField.getText());
        if (birthdayField.getValue() != null) {
            LocalDate ld = birthdayField.getValue();
            Calendar c = Calendar.getInstance();
            c.set(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
            applicant.setBirthday(c.getTime());
        }
        applicant.setTelephoneNumber(telephoneNumberField.getText());
        applicant.setCountry(countryField.getText());
        applicant.setCity(cityField.getText());
        applicant.setCitizenship(citizenshipField.getText());
        applicant.setNativeLanguage(nativeLanguageField.getText());
        applicant.setOtherLanguages(otherLanguageField.getText());

        ApplicantService.update(applicant);
        ApplicantService.closeDatabase();
        UserService.openUserDatabase();
        try {
            Stage stage = (Stage) saveButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClassLoader.getSystemResource("fxml/employee_profile.fxml"));
            Parent employeeProfileTab = loader.load();
            EmployeeProfileController employeeProfileController = loader.getController();
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
        Applicant applicant = ApplicantService.getCurrentApplicant();
        expInITField.setText(applicant.getExperienceInItDomain());
        if (applicant.getBirthday() != null) {
            birthdayField.setValue(LocalDate.parse(DateFormatUtils.format(applicant.getBirthday(), "dd-MM-yyyy"), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        }
        telephoneNumberField.setText(applicant.getTelephoneNumber());
        countryField.setText(applicant.getCountry());
        cityField.setText(applicant.getCity());
        citizenshipField.setText(applicant.getCitizenship());
        nativeLanguageField.setText(applicant.getNativeLanguage());
        otherLanguageField.setText(applicant.getOtherLanguages());
    }
}
