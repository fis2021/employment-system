package employment.system;

import employment.system.services.FileSystemService;
import employment.system.services.JobService;
import employment.system.services.UserService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        initDirectory();
        UserService.initUserDatabase();
        JobService.initJobDatabase();
        UserService.openUserDatabase();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ClassLoader.getSystemResource("login.fxml"));
        Parent root  = loader.load();


        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);

        primaryStage.setTitle("Worker");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    private void initDirectory() {
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
