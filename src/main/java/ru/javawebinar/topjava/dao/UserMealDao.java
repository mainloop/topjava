package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.List;


public interface UserMealDao {

    int create(UserMeal meal);

    UserMeal read(int id);

    void update(UserMeal meal);

    void delete(int id);

    List<UserMeal> list();

}
