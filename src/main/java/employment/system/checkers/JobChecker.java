package employment.system.checkers;

import employment.system.exceptions.JobWithThisIdAlreadyExistsException;
import employment.system.job.Job;
import employment.system.services.JobService;

import java.util.Objects;


public abstract class JobChecker {
    public static void checkIfValidId(String ID) throws JobWithThisIdAlreadyExistsException {
        for (Job job : JobService.getJobRepository().find()) {
            if (Objects.equals(ID, job.getJobID())) {
                throw new JobWithThisIdAlreadyExistsException(ID);
            }
        }
    }
}
