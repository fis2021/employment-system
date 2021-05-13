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

    public static void addJob(String jobName, String company, String department) throws JobWithThisIdAlreadyExistsException {
        String ID = Job.createID(jobName, company);
        JobChecker.checkIfValidId(ID);
        jobRepository.insert(new Job(jobName,company,department));
    }

    public static void initJobDatabase() {
        File file = getPathToFile("jobs.db").toFile();
        if (!file.exists()) {
            jobDatabase = Nitrite.builder()
                    .filePath(getPathToFile("jobs.db").toFile())
                    .openOrCreate("admin", "admin");

            jobRepository = jobDatabase.getRepository(Job.class);
            // This initiate the initial database with predefined jobs
            addGhostJobsOfGhostRecruiters();
            jobDatabase.close();
        }
    }

    private static void addGhostJobsOfGhostRecruiters() {
        try {
            JobService.addJob("Full Stack Engineer", "Full Stack", "Google");
            JobService.addJob("Full Stack Engineer", "Full Stack", "Amazon");
            JobService.addJob("Full Stack Engineer", "Full Stack", "Facebook");
            JobService.addJob("Web Designer", "Web Designer", "Advanced Supreme");
            JobService.addJob("ASAP.Net Developer", "Back End", "ASsOQ");
            JobService.addJob("PHP programmer", "Front End", "BPay");
            JobService.addJob("Java Developer", "Back End", "Google");
            JobService.addJob("C/C++", "Embedded", "Mantis");
            JobService.addJob("Android Developer", "Mobile", "Amazon");
        } catch (JobWithThisIdAlreadyExistsException e) {
            e.printStackTrace();
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
        jobDatabase.close();
    }
}
