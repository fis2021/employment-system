package employment.system.jobs;/*
 * Created by Adrian Dragoș on 5/11/2021 May 2021.
 */


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

public class Job {
    private StringProperty jobName;
    private StringProperty companyName;
    private StringProperty jobLocation;
    private StringProperty jobSchedule;
    private StringProperty jobSalary;
    private StringProperty jobDescription;
    private StringProperty jobRequirements;
    private StringProperty jobCategory;
    private Image image;

    public Job() {
        this(null, null, null, null);
    }

    public Job(String jobName, String jobCategory, String companyName, Image image) {
        this.jobName = new SimpleStringProperty(jobName);
        this.jobCategory = new SimpleStringProperty(jobCategory);
        this.companyName = new SimpleStringProperty(companyName);

        this.jobLocation = new SimpleStringProperty("Not specified");
        this.jobSchedule = new SimpleStringProperty("Not specified");
        this.jobDescription = new SimpleStringProperty("Not specified");
        this.jobSalary = new SimpleStringProperty("Not specified.");
        this.jobRequirements = new SimpleStringProperty("Not Specified");
        this.image = image;
    }

    public String getJobName() {
        return jobName.get();
    }

    public StringProperty jobNameProperty() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName.set(jobName);
    }

    public String getCompanyName() {
        return companyName.get();
    }

    public StringProperty companyNameProperty() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName.set(companyName);
    }

    public String getJobLocation() {
        return jobLocation.get();
    }

    public StringProperty jobLocationProperty() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation.set(jobLocation);
    }

    public String getJobSchedule() {
        return jobSchedule.get();
    }

    public StringProperty jobScheduleProperty() {
        return jobSchedule;
    }

    public void setJobSchedule(String jobSchedule) {
        this.jobSchedule.set(jobSchedule);
    }

    public String getJobSalary() {
        return jobSalary.get();
    }

    public StringProperty jobSalaryProperty() {
        return jobSalary;
    }

    public void setJobSalary(String jobSalary) {
        this.jobSalary.set(jobSalary);
    }

    public String getJobDescription() {
        return jobDescription.get();
    }

    public StringProperty jobDescriptionProperty() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription.set(jobDescription);
    }

    public String getJobRequirements() {
        return jobRequirements.get();
    }

    public StringProperty jobRequirementsProperty() {
        return jobRequirements;
    }

    public void setJobRequirements(String jobRequirements) {
        this.jobRequirements.set(jobRequirements);
    }

    public String getJobCategory() {
        return jobCategory.get();
    }

    public StringProperty jobCategoryProperty() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory.set(jobCategory);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
