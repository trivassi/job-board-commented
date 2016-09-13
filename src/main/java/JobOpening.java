import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class JobOpening {
  String mDescription;
  Integer mSalary;
  String mContactInfo;
  LocalDate mPostingExpires;

  public JobOpening(String description, Integer salary, String contactInfo, LocalDate postingExpires) {
    mDescription = description;
    mSalary = salary;
    mContactInfo = contactInfo;
    mPostingExpires = postingExpires;
  }

  public String getDescription() {
    return mDescription;
  }

  public Integer getSalary() {
    return mSalary;
  }

  public String getContactInfo() {
    return mContactInfo;
  }

  public LocalDate getPostingExpires() {
    return mPostingExpires;
  }

  public Boolean isPostExpired() {
    LocalDate today = LocalDate.now();
    return (ChronoUnit.DAYS.between(mPostingExpires, today) > 0);
  }
}
