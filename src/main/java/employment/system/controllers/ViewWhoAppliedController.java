package employment.system.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;


public class ViewWhoAppliedController {

    @FXML
    private TableColumn applicantNameColumn;
    @FXML
    private TableColumn PositionColumn;
    @FXML
    private TableColumn statusColumn;
    @FXML
    private Button jobOfferButton;
    @FXML
    private TableView appliedTable;

    public void jobOfferButtonOnAction(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) jobOfferButton.getScene().getWindow();
            Parent openViewJobTab = FXMLLoader.load(getClass().getClassLoader().getResource("view_job_offers.fxml"));
            Scene scene = new Scene(openViewJobTab, 912, 624);
            stage.setScene(scene);
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

