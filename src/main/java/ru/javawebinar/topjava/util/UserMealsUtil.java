package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );


        List<UserMealWithExceed> list =
                getFilteredMealsWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        for (UserMealWithExceed m : list) {
            System.out.println(m);
        }
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed> getFilteredMealsWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        System.out.println("start:" + startTime);
        System.out.println("end:" + endTime);

        Map<LocalDate, Integer> map = new HashMap<>();
        for (UserMeal m : mealList) {
            LocalDate d = m.getDateTime().toLocalDate();
            int count = m.getCalories();
            if (map.containsKey(d)) {
                count += map.get(d);
            }
            map.put(d, count);
        }

        for (Map.Entry<LocalDate, Integer> e : map.entrySet()) {
            System.out.println("" + e.getKey() + " = " + e.getValue());
        }

        List<UserMealWithExceed> list = new ArrayList<>();
        for (UserMeal m : mealList) {
            LocalDate d = m.getDateTime().toLocalDate();
            LocalTime t = m.getDateTime().toLocalTime();
            if ((startTime == null || t.isAfter(startTime)) &&
                    (endTime == null || t.isBefore(endTime))) {
                list.add(new UserMealWithExceed(m.getDateTime(),
                        m.getDescription(),
                        m.getCalories(),
                        map.get(d) > caloriesPerDay));
            }
        }
        return list;
    }
}
