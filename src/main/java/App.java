import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import java.time.LocalDate;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("cities", City.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cities/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/city-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cities/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      City city = City.find(Integer.parseInt(request.params(":id")));
      model.put("city", city);
      model.put("template", "templates/city.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cities/:id/jobopenings/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      City city = City.find(Integer.parseInt(request.params(":id")));
      model.put("city", city);
      model.put("template", "templates/city-jobopenings-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/jobopenings", (request, response) ->{
      Map<String, Object> model = new HashMap<String, Object>();
      Integer cityId = Integer.parseInt(request.queryParams("cityId"));
      City city = City.find(cityId);

      String title = request.queryParams("title");
      String description = request.queryParams("description");
      Integer salary = Integer.parseInt(request.queryParams("salary"));
      LocalDate postingExpires = stringToLocalDate(request.queryParams("postingExpires"));
      String contactInfo = request.queryParams("contactInfo");
      JobOpening newJobOpening = new JobOpening (title, description, salary, contactInfo, postingExpires);
      city.addJobOpening(newJobOpening);
      model.put("city", city);
      response.redirect("/cities/" + cityId);
      //same as doing:
      //      Integer cityId = Integer.parseInt(request.queryParams("cityId")) and then "/:id"
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/cities", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      City newCity = new City(name);
      response.redirect("/cities/" + newCity.getId());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cities", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("cities", City.all());
      model.put("template", "templates/cities.vtl");
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
