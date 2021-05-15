package employment.system.services;

import employment.system.exceptions.JobWithThisIdAlreadyExistsException;
import employment.system.exceptions.UserWithThisEmailAlreadyExistsException;
import employment.system.user.AccountType;

public abstract class GhostsAccounts {
    private static final String PASSWORD = "a";
    private static final String EMAIL1 = "jeff.bezos@amazon.com";
    private static final String EMAIL2 = "bill.gates@microsoft.com";
    private static final String EMAIL3 = "mark.zuckerberg@facebook.com";
    private static final String EMAIL4 = "jhon.smith@gmail.com";
    private static final String EMAIL5 = "employer@gmail.com";
    private static final String EMAIL6 = "a@gmail.com";

    public static void createGhostsAccounts() {

        UserService.openUserDatabase();
        try {
            UserService.addUser(EMAIL1, "Jef", "Bezos", PASSWORD, AccountType.RECRUITER);
            UserService.addUser(EMAIL2, "Bill", "Gates", PASSWORD , AccountType.RECRUITER);
            UserService.addUser(EMAIL3, "Mark", "Zuckerberg", PASSWORD, AccountType.RECRUITER);
        } catch (UserWithThisEmailAlreadyExistsException ignore) {}

        try {
            UserService.addUser(EMAIL4, "jhon", "smith", PASSWORD, AccountType.EMPLOYEE);
            UserService.addUser(EMAIL5, "employer", "employer", PASSWORD, AccountType.EMPLOYEE);
            UserService.addUser(EMAIL6, "Sam", "Sung", PASSWORD, AccountType.EMPLOYEE);
        } catch (UserWithThisEmailAlreadyExistsException ignore) {}
        UserService.closeDatabase();

        JobService.openJobDataBase();
        try {
            JobService.addJob("hana.rutemol@gmail.com" ,"Full Stack Engineer", "Full Stack", "Google");
            JobService.addJob(EMAIL1,"Full Stack Engineer", "Full Stack", "Amazon");
            JobService.addJob(EMAIL3,"Full Stack Engineer", "Full Stack", "Facebook");
            JobService.addJob("chun.hung@asw.com", "Web Designer", "Web Designer", "Advanced Supreme");
            JobService.addJob("fracno.debano@assoq.com","ASAP.Net Developer", "Back End", "ASsOQ");
            JobService.addJob("dick.smith@gmail.com", "PHP programmer", "Front End", "BPay");
            JobService.addJob("diana.cretu@gmail.com", "Java Developer", "Back End", "Google");
            JobService.addJob(EMAIL2,"C/C++", "Embedded", "Microsoft");
            JobService.addJob(EMAIL1,"Android Developer", "Mobile", "Amazon");
        } catch (JobWithThisIdAlreadyExistsException ignore) {}
        JobService.closeJobDataBase();

        ApplicantService.openDatabase();
        try {
            ApplicantService.addApplicant(EMAIL4, "10");
            ApplicantService.addApplicant(EMAIL5, "2");
            ApplicantService.addApplicant(EMAIL6, "4");
        } catch (Exception ignore) {}
        ApplicantService.closeDatabase();

    }
}
