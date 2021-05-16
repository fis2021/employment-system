package employment.system.services;

import employment.system.user.Applicant;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.util.Objects;

import static employment.system.services.FileSystemService.getPathToFile;

public abstract class ApplicantService {

    private static Nitrite applicantDatabase;
    private static ObjectRepository<Applicant> applicantRepository;
    private static Applicant currentApplicant;

    public static void initApplicantDatabase() {
        if (!existApplicantDatabase()) {
            applicantDatabase = Nitrite.builder()
                    .filePath(getPathToFile("applicants.db").toFile())
                    .openOrCreate("admin", "admin");

            applicantRepository = applicantDatabase.getRepository(Applicant.class);
            applicantDatabase.close();
        }
    }


    public static void openDatabase() {
        applicantDatabase = Nitrite.builder()
                .filePath(getPathToFile("applicants.db").toFile())
                .openOrCreate("admin", "admin");

        applicantRepository = applicantDatabase.getRepository(Applicant.class);
    }

    public static Applicant getCurrentApplicant() {
        return currentApplicant;
    }


    public static ObjectRepository<Applicant> getApplicantRepository() {
        return applicantRepository;
    }


    public static void setCurrentApplicant(String email) {
        for (Applicant applicant : ApplicantService.getApplicantRepository().find()) {
            if (Objects.equals(email, applicant.getEmail())) {
                currentApplicant = applicant;
            }
        }
    }

    public static void closeDatabase() {
        if (!applicantDatabase.isClosed()) {
            applicantDatabase.close();
        }
    }

    public static void update(Applicant applicant) {
        applicantRepository.update(applicant);
    }

    public static void addApplicant(String email, String experience) {
        applicantRepository.insert(new Applicant(email, experience));
    }

    public static boolean existApplicantDatabase() {
        return getPathToFile("applicants.db").toFile().exists();
    }
}
