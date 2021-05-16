package employment.system.services;

import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class FileSystemService {

    public static String APPLICATION_FOLDER = ".esfi-registration";
    private static final String USER_FOLDER = System.getProperty("user.home");
    public static final Path APPLICATION_HOME_PATH = Paths.get(USER_FOLDER, APPLICATION_FOLDER);
    private static final String CV_FOlDER = ".esfi-registration/CV";
    public static final Path CV_PATH = Paths.get(USER_FOLDER, CV_FOlDER);

    public static Path getPathToFile(String... path) {
        return APPLICATION_HOME_PATH.resolve(Paths.get(".", path));
    }
}
