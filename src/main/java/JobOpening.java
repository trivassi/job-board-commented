import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ArrayList;

public class JobOpening {
  private String mTitle;
  private String mDescription;
  private Integer mSalary;
  private String mContactInfo;
  private LocalDate mPostingExpires;
  private static List<JobOpening> instances = new ArrayList<JobOpening>();
  private int mId;

  public JobOpening(String title, String description, Integer salary, String contactInfo, LocalDate postingExpires) {
    mTitle = title;
    mDescription = description;
    mSalary = salary;
    mContactInfo = contactInfo;
    mPostingExpires = postingExpires;
    instances.add(this);
    mId = instances.size();
  }

  public String getTitle() {
    return mTitle;
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

  public long daysUntilExpired() {
    LocalDate today = LocalDate.now();
    return ChronoUnit.DAYS.between(today, mPostingExpires);
  }

  public Boolean isPostExpired() {
    LocalDate today = LocalDate.now();
    return (ChronoUnit.DAYS.between(mPostingExpires, today) > 0);
  }

  public static List<JobOpening> all() {
    return instances;
  }

  public static void clear() {
    instances.clear();
  }

  public int getId() {
    return mId;
  }

  public static JobOpening find(int id) {
    return instances.get(id -1);
  }
}
