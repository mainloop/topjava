package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MapMealDaoImpl;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import ru.javawebinar.topjava.util.UserMealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger LOG = getLogger(MealServlet.class);

    private static final MapMealDaoImpl dao = new MapMealDaoImpl();

    static {
        dao.create(new UserMeal(0,LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        dao.create(new UserMeal(0,LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        dao.create(new UserMeal(0,LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        dao.create(new UserMeal(0,LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        dao.create(new UserMeal(0,LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        dao.create(new UserMeal(0,LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        dao.create(new UserMeal(0,LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String identificator = request.getParameter("identificator");
        String operation = request.getParameter("operation");

        if( operation != null ) {
            switch (operation) {
                case "delete": {
                    int id = Integer.valueOf(identificator);
                    dao.delete(id);
                    break;
                }
                case "edit": {
                    int id = Integer.valueOf(identificator);
                    UserMeal userMeal = dao.read(id);
                    request.setAttribute("id",userMeal.getId());
                    request.setAttribute("calories",userMeal.getCalories());
                    request.setAttribute("description",userMeal.getDescription());
                    request.setAttribute("date",userMeal.getDateTime());
                    break;
                }
                default: {
                    String description = request.getParameter("description");
                    int calories =  Integer.valueOf(request.getParameter("calories"));
                    int id =  Integer.valueOf(request.getParameter("id"));
                    String date = request.getParameter("date");
                    dao.update( new UserMeal(id,LocalDateTime.of(2015, Month.MAY, 30, 20, 0), description, calories ));
                }
            }
        }


        List<UserMealWithExceed> filteredMealsWithExceeded = UserMealsUtil.getFilteredMealsWithExceeded(dao.list(), LocalTime.of(0, 0), LocalTime.of(23, 59), 2000);
        request.setAttribute("mealList",filteredMealsWithExceeded);



        request.getRequestDispatcher("/mealList.jsp").
                forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to mealList");
        doPost(request,response);
    }
}
