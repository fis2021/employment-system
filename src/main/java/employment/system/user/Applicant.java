package employment.system.user;

import org.dizitart.no2.objects.InheritIndices;

import java.util.Date;

@InheritIndices
public class Applicant extends User {
    private String experienceInItDomain;
    private Date birthday;
    private String telephoneNumber;
    private String country;
    private String city;
    private String citizenship;
    private String nativeLanguage;
    private String otherLanguages;

    public Applicant() {

    }

    public Applicant(String email, String experienceInItDomain) {
        super.email = email;
        this.experienceInItDomain = experienceInItDomain;
    }

    public Applicant(String email, String experienceInItDomain, Date birthday, String telephoneNumber, String country,
                     String city, String citizenship, String nativeLanguage, String otherLanguages) {
        super.email = email;
        this.experienceInItDomain = experienceInItDomain;
        this.birthday = birthday;
        this.telephoneNumber = telephoneNumber;
        this.country = country;
        this.city = city;
        this.citizenship = citizenship;
        this.nativeLanguage = nativeLanguage;
        this.otherLanguages = otherLanguages;
    }

    public String getExperienceInItDomain() {
        return experienceInItDomain;
    }

    public void setExperienceInItDomain(String experienceInItDomain) {
        this.experienceInItDomain = experienceInItDomain;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getNativeLanguage() {
        return nativeLanguage;
    }

    public void setNativeLanguage(String nativeLanguage) {
        this.nativeLanguage = nativeLanguage;
    }

    public String getOtherLanguages() {
        return otherLanguages;
    }

    public void setOtherLanguages(String otherLanguages) {
        this.otherLanguages = otherLanguages;
    }
}
