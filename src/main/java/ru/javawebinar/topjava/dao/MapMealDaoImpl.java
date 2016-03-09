package ru.javawebinar.topjava.dao;


import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapMealDaoImpl implements UserMealDao {

    static Map<Integer,UserMeal> map = new HashMap<>();

    static {
        map.put(1,new UserMeal(1,LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        map.put(2,new UserMeal(2,LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        map.put(3,new UserMeal(3,LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        map.put(4,new UserMeal(4,LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        map.put(5,new UserMeal(5,LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        map.put(6,new UserMeal(6,LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
    }

    static int idCount = 0;

    @Override
    public int create(UserMeal meal) {
        int id = idCount;
        map.put(id,meal);
        idCount++;
        return id;
    }

    @Override
    public UserMeal read(int id) {
        return map.get(id);
    }

    @Override
    public void update(UserMeal meal) {
        map.put(meal.getId(),meal);
    }

    @Override
    public void delete(int id) {
        map.remove(id);
    }

    @Override
    public List<UserMeal> list() {
        return new ArrayList<>(map.values());
    }
}
