package employment.system.user;


import org.dizitart.no2.objects.InheritIndices;

@InheritIndices
public class Recruiter extends User {
    private String companyName;
    private String companyAddress;
    private String telephone;

    public Recruiter() {

    }

    public Recruiter(String email, String companyName, String companyAddress, String telephone) {
        super.setEmail(email);
        this.companyName = companyName;
        this.companyAddress = companyAddress;
        this.telephone = telephone;
    }

    public Recruiter(String email, String companyName) {
        this.companyName = companyName;
        super.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean hasNullFields() {
        return telephone == null || companyAddress == null || companyName == null;
    }

    public boolean hasEmptyFields() {
        return telephone.isEmpty() || companyName.isEmpty() || companyAddress.isEmpty();
    }
}
