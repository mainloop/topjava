package ru.javawebinar.topjava.dao;


import ru.javawebinar.topjava.model.UserMeal;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapMealDaoImpl implements UserMealDao {

    static Map<Integer,UserMeal> map = new ConcurrentHashMap<>();

    static volatile Integer idCount = 0;

    @Override
    public int create(UserMeal meal) {
        int id;
        synchronized (idCount) {
            id = idCount;
            map.put(id, new UserMeal(id, meal.getDateTime(), meal.getDescription(), meal.getCalories()));
            idCount++;
        }
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
