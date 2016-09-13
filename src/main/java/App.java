import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    post("/jobOpenings", (request, response) ->{
      Map<String, Object> model = new HashMap<String, Object>();

      ArrayList<JobOpening> jobOpenings = request.session().attribute("jobOpenings");
    if (jobOpenings == null) {
      jobOpenings = new ArrayList<JobOpening>();
      request.session().attribute("jobOpenings", jobOpenings);
    }
    String title = request.queryParams("title");
    String description = request.queryParams("description");
    Integer salary = Integer.parseInt(request.queryParams("salary"));
    LocalDate postingExpires = stringToLocalDate(request.queryParams("postingExpires"));
    String contactInfo = request.queryParams("contactInfo");
    JobOpening newJobOpening = new JobOpening (title, description, salary, contactInfo, postingExpires);
    jobOpenings.add(newJobOpening);

    // model.put("template", "templates/success.vtl");
    response.redirect("/");
    return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("jobOpenings", request.session().attribute("jobOpenings"));
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());
  }
  public static LocalDate stringToLocalDate(String dateIn) {
    String[] dateToConvert = dateIn.split("-");
    Integer[] newDate = new Integer[3];
    for (int i = 0; i < 3; i++) {
      newDate[i] = Integer.parseInt(dateToConvert[i]);
    }
    LocalDate output = LocalDate.of(newDate[0], newDate[1], newDate[2]);
    return output;
  }
}
