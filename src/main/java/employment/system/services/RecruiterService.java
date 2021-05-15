package employment.system.services;



import employment.system.user.Recruiter;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.Objects;

import static employment.system.services.FileSystemService.getPathToFile;

public abstract class RecruiterService {


    private static ObjectRepository<Recruiter> recruiterRepository;
    private static Nitrite recruiterDatabase;
    private static Recruiter currentChosenRecruiter;


    public static void initApplicantDatabase() {
        if (!existRecruiterDatabase()) {
            recruiterDatabase = Nitrite.builder()
                    .filePath(getPathToFile("applicants.db").toFile())
                    .openOrCreate("admin", "admin");

            recruiterRepository = recruiterDatabase.getRepository(Recruiter.class);
            recruiterDatabase.close();
        }
    }

    private static boolean existRecruiterDatabase() {
        return recruiterDatabase.isClosed();
    }


    public static void openDatabase() {
        recruiterDatabase = Nitrite.builder()
                .filePath(getPathToFile("applicants.db").toFile())
                .openOrCreate("admin", "admin");

        recruiterRepository = recruiterDatabase.getRepository(Recruiter.class);
    }


    public static void setCurrentRecruiter(String email) {
        for (Recruiter recruiter : RecruiterService.getRecruiterRepository().find()) {
            if (Objects.equals(email, recruiter.getEmail())) {
                currentChosenRecruiter = recruiter;
            }
        }
    }

    private static ObjectRepository<Recruiter> getRecruiterRepository() {
        return recruiterRepository;
    }

    public static Recruiter getCurrentRecruiter() {
        return currentChosenRecruiter;
    }
}
