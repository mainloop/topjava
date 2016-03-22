package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.ModelMatcher;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/**
 * GKislin
 * 13.03.2015.
 */
public class MealTestData {
    public static final int USER_MEAL_ID = 1;
    public static final int ADMIN_MEAL_ID = USER_MEAL_ID+1;

    public static final UserMeal USER_MEAL = new UserMeal(USER_MEAL_ID,LocalDateTime.of(2016, Month.MARCH, 22, 10, 0), "user breakfast", 250);
    public static final UserMeal ADMIN_MEAL = new UserMeal(ADMIN_MEAL_ID,LocalDateTime.of(2016, Month.MARCH, 22, 11, 0), "admin breakfast", 350);

    public static final ModelMatcher<UserMeal, String> MATCHER = new ModelMatcher<>(UserMeal::toString);

}
