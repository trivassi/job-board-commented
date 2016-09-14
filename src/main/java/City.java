import java.util.List;
import java.util.ArrayList;

public class City {
  private String mName;
  private static ArrayList<City> instances = new ArrayList<City>();
  private ArrayList<JobOpening> mJobOpenings;
  private int mId;

  public City(String name) {
    mName = name;
    instances.add(this);
    mId = instances.size();
    mJobOpenings = new ArrayList<JobOpening>();
  }

  public String getName() {
    return mName;
  }

  public static ArrayList<City> all() {
    return instances;
  }

  public int getId(){
    return mId;
  }

  public static City find(int id) {
    return instances.get(id - 1);
  }

  public void addJobOpening(JobOpening jobOpening) {
    mJobOpenings.add(jobOpening);
  }

  public ArrayList<JobOpening> getJobOpenings() {
    return mJobOpenings;
  }
}
