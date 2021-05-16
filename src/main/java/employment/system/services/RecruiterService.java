package employment.system.services;



import employment.system.user.Applicant;
import employment.system.user.Recruiter;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.Objects;

import static employment.system.services.FileSystemService.getPathToFile;

public abstract class RecruiterService {


    private static ObjectRepository<Recruiter> recruiterRepository;
    private static Nitrite recruiterDatabase;
    private static Recruiter currentChosenRecruiter;


    public static void initRecruiterDatabase() {
        if (!existRecruiterDatabase()) {
            recruiterDatabase = Nitrite.builder()
                    .filePath(getPathToFile("recruiter.db").toFile())
                    .openOrCreate("admin", "admin");

            recruiterRepository = recruiterDatabase.getRepository(Recruiter.class);
            recruiterDatabase.close();
        }
    }

    private static boolean existRecruiterDatabase() {
        return getPathToFile("recruiter.db").toFile().exists();
    }


    public static void openDatabase() {
        recruiterDatabase = Nitrite.builder()
                .filePath(getPathToFile("recruiter.db").toFile())
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

    public static void closeDatabase() {
        if (!recruiterDatabase.isClosed()) {
            recruiterDatabase.close();
        }
    }

    public static void update(Recruiter recruiter) {
        recruiterRepository.update(recruiter);
    }

    public static void addRecruiter(String email, String companyName) {
        recruiterRepository.insert(new Recruiter(email, companyName));
    }
}
