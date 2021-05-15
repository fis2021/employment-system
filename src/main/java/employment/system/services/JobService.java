package employment.system.services;


import employment.system.checkers.JobChecker;
import employment.system.exceptions.JobWithThisIdAlreadyExistsException;
import employment.system.job.Job;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import java.io.File;
import java.util.ArrayList;


import static employment.system.services.FileSystemService.getPathToFile;

public abstract class JobService {

    private static ObjectRepository<Job> jobRepository;
    private static Nitrite jobDatabase;

    public static void addJob(String recruiterEmail, String jobName, String category, String company) throws JobWithThisIdAlreadyExistsException {
        String ID = Job.createID(jobName, company);
        JobChecker.checkIfValidId(ID);
        jobRepository.insert(new Job(recruiterEmail, jobName,category, company));
    }

    public static void initJobDatabase() {
        if (!existJobDatabase()) {
            jobDatabase = Nitrite.builder()
                    .filePath(getPathToFile("jobs.db").toFile())
                    .openOrCreate("admin", "admin");

            jobRepository = jobDatabase.getRepository(Job.class);
            jobDatabase.close();
        }
    }

    public static void openJobDataBase() {
        jobDatabase = Nitrite.builder()
                .filePath(getPathToFile("jobs.db").toFile())
                .openOrCreate("admin", "admin");
        jobRepository = jobDatabase.getRepository(Job.class);
    }

    public static ArrayList<Job> extractJobsFromDataBase() {
        ArrayList<Job> jobList = new ArrayList<>();
        for (Job job : JobService.getJobRepository().find()) {
            jobList.add(job);
        }
        return jobList;
    }

    public static ObjectRepository<Job> getJobRepository() {
        return jobRepository;
    }

    public static void closeJobDataBase() {
        if (!jobDatabase.isClosed()) {
            jobDatabase.close();
        }
    }

    public static boolean existJobDatabase() {
        return getPathToFile("jobs.db").toFile().exists();
    }
}
