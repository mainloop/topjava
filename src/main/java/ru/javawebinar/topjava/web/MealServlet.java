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
                    break;
                }
                default: {
                    String description = request.getParameter("description");
                    String calories = request.getParameter("calories");
                }
            }
        }


        List<UserMealWithExceed> filteredMealsWithExceeded = UserMealsUtil.getFilteredMealsWithExceeded(dao.list(), LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        request.setAttribute("mealList",filteredMealsWithExceeded);



        request.getRequestDispatcher("/mealList.jsp").
                forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("redirect to mealList");
        doPost(request,response);
    }
}
