package employment.system.controllers;

import employment.system.job.Job;
import employment.system.services.FileSystemService;
import employment.system.services.JobService;
import employment.system.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;


import java.io.*;
import java.net.URL;

import java.util.Scanner;


public class ApplyForJobController {
    @FXML
    private Label cvNameLabel;
    @FXML
    private Label uploadSuccessfulLabel;
    @FXML
    private Button cvButton;
    @FXML
    private Label applyForJobMessageLabel;
    @FXML
    private TextField nameField;
    @FXML
    private Label jobNameLabel;
    @FXML
    private TextField CVField;
    @FXML
    private Button cancelButton;
    @FXML
    private Button applyForJobButton;


    private static File uploadedFile;


    public void applyForJobButtonAction(ActionEvent actionEvent) {

        if (nameField.getText().isEmpty()) {
            applyForJobMessageLabel.setText("Please enter your name!");
            return;
        }

        if (uploadedFile == null) {
            applyForJobMessageLabel.setText("Please upload your cv!");
        }

        try {
            File source = new File(uploadedFile.getAbsolutePath());
            System.out.println(FileSystemService.CV_PATH + uploadedFile.getName());
            File destination = new File(FileSystemService.CV_PATH + "/" + uploadedFile.getName());
            FileInputStream fis = new FileInputStream(source);
            FileOutputStream fos = new FileOutputStream(destination);
            byte[] buffer = new byte[4096];
            int read;
            while ((read = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, read);
            }
            Stage stage = (Stage) applyForJobButton.getScene().getWindow();
            Parent openApplyingTab = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/successful_applying.fxml"));
            Scene scene = new Scene(openApplyingTab, 600, 400);
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
            stage.setScene(scene);
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void cancelButtonOnAction(ActionEvent actionEvent) {
        try {
            UserService.closeDatabase();
            JobService.openJobDataBase();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClassLoader.getSystemResource("fxml/view_job_offers.fxml"));
            Parent openViewJobsTab = loader.load();
            ViewJobsController viewJobsController = loader.getController();
            viewJobsController.createTable();
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            Scene scene = new Scene(openViewJobsTab, 912, 624);
            stage.setResizable(true);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initiate() {
        uploadedFile = null;
        applyForJobButton.setVisible(true);
        URL url = getClass().getResource("/images/pdf.png");
        Image pdfIcon = new Image(url.toString());
        ImageView imageView = new ImageView(pdfIcon);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        cvButton.setGraphic(imageView);
        cvButton.setText("");
        cvButton.setMaxSize(50, 50);
        cvButton.setVisible(true);
        cvNameLabel.setVisible(false);
        applyForJobMessageLabel.setText("");
        Job job = JobService.getSelectedJob();
        jobNameLabel.setText("Apply for " + job.getJobName() + " from " + job.getCompanyName());

    }

    public void cvButtonOnAction(ActionEvent actionEvent) {
        try {
            final FileChooser fileChooser = new FileChooser();
            Stage stage = new Stage();
            uploadedFile = fileChooser.showOpenDialog(stage);
            if (!isPDF(uploadedFile)) {
                applyForJobMessageLabel.setText("CV should be in PDF format.");
            }

            cvNameLabel.setVisible(true);
            cvNameLabel.setText(uploadedFile.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean isPDF(File file) throws Exception{
        Scanner input = new Scanner(new FileReader(file));
        while (input.hasNextLine()) {
            final String checkLine = input.nextLine();
            if(checkLine.contains("%PDF-")) {
                return true;
            }
        }
        return false;
    }

}
