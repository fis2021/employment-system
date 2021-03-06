package employment.system.job;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import org.dizitart.no2.objects.Id;

public class Job {
//    private StringProperty jobName;
//    private StringProperty companyName;
//    private StringProperty jobLocation;
//    private StringProperty jobSchedule;
//    private StringProperty jobSalary;
//    private StringProperty jobDescription;
//    private StringProperty jobRequirements;
//    private StringProperty jobCategory;

    private String jobName;
    private String companyName;
    private String jobLocation;
    private String jobSchedule;
    private String jobSalary;
    private String jobDescription;
    private String jobRequirements;
    private String jobCategory;
    private  String recruiterEmail;
    @Id
    private String ID;

    public Job() {
        this(null, null, null, null);
    }

    public Job(String recruiterEmail, String jobName, String jobCategory, String companyName) {
        this.recruiterEmail = recruiterEmail;
        this.jobName = jobName;
        this.jobCategory = jobCategory;
        this.companyName = companyName;
        this.ID = createID(jobName, companyName);
    }


    public static String createID(String jobName, String companyName) {
        return  jobName + companyName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getJobSchedule() {
        return jobSchedule;
    }

    public void setJobSchedule(String jobSchedule) {
        this.jobSchedule = jobSchedule;
    }

    public String getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(String jobSalary) {
        this.jobSalary = jobSalary;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobRequirements() {
        return jobRequirements;
    }

    public void setJobRequirements(String jobRequirements) {
        this.jobRequirements = jobRequirements;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public ObservableValue<String> jobNameProperty() {
        return new SimpleStringProperty(jobName);
    }

    public ObservableValue<String> jobCategoryProperty() {
        return new SimpleStringProperty(jobCategory);
    }

    public ObservableValue<String> companyNameProperty() {
        return  new SimpleStringProperty(companyName);
    }

    public String getJobID() {
        return this.ID;
    }

    public void setJobID(String ID) {
        this.ID = ID;
    }

}
