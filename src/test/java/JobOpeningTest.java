import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class JobOpeningTest {

  @Test
  public void JobOpening_instantiatesCorrectly_true() {
    String description = "Student";
    Integer salary = 0;
    String contactInfo = "NoOne";
    LocalDate postingExpires = LocalDate.of(2016, Month.SEPTEMBER, 13);
    JobOpening myJobOpening = new JobOpening(description, salary, contactInfo, postingExpires);
    assertEquals(true, myJobOpening instanceof JobOpening);
  }

  @Test
  public void getDescription_returnsDescription_String() {
    String description = "Student";
    Integer salary = 0;
    String contactInfo = "NoOne";
    LocalDate postingExpires = LocalDate.of(2016, Month.SEPTEMBER, 13);
    JobOpening myJobOpening = new JobOpening(description, salary, contactInfo, postingExpires);
    assertEquals("Student", myJobOpening.getDescription());
  }

  @Test
  public void getSalary_returnsSalary_Integer() {
    String description = "Student";
    Integer salary = 0;
    String contactInfo = "NoOne";
    LocalDate postingExpires = LocalDate.of(2016, Month.SEPTEMBER, 13);
    JobOpening myJobOpening = new JobOpening(description, salary, contactInfo, postingExpires);
    Integer expected = 0;
    assertEquals(expected, myJobOpening.getSalary());
  }

  @Test
  public void getContactInfo_returnsContactInfo_String() {
    String description = "Student";
    Integer salary = 0;
    String contactInfo = "NoOne";
    LocalDate postingExpires = LocalDate.of(2016, Month.SEPTEMBER, 13);
    JobOpening myJobOpening = new JobOpening(description, salary, contactInfo, postingExpires);
    assertEquals("NoOne", myJobOpening.getContactInfo());
  }

  @Test
  public void getPostingExpires_returnsPostingExpires_LocalDate() {
    String description = "Student";
    Integer salary = 0;
    String contactInfo = "NoOne";
    LocalDate postingExpires = LocalDate.of(2016, Month.SEPTEMBER, 13);
    JobOpening myJobOpening = new JobOpening(description, salary, contactInfo, postingExpires);
    LocalDate expected = LocalDate.of(2016, Month.SEPTEMBER, 13);
    assertEquals(expected, myJobOpening.getPostingExpires());
  }

  @Test
  public void isPostExpired_returnsPostingExpired_true() {
    String description = "Student";
    Integer salary = 0;
    String contactInfo = "NoOne";
    LocalDate postingExpires = LocalDate.of(2016, Month.SEPTEMBER, 10);
    JobOpening myJobOpening = new JobOpening(description, salary, contactInfo, postingExpires);
    assertEquals(true, myJobOpening.isPostExpired());
  }

}
