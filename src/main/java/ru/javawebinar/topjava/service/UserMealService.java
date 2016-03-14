package ru.javawebinar.topjava.service;


import ru.javawebinar.topjava.service.to.UserMealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface UserMealService {

    UserMealWithExceed save(UserMealWithExceed userMeal, int userId );

    void delete(int id, int userId ) throws NotFoundException;

    UserMealWithExceed get(int id, int userId ) throws NotFoundException;

    List<UserMealWithExceed> getFiltered(LocalDateTime start, LocalDateTime end,
                                         int exceed, int userId );

    void update(UserMealWithExceed userMeal, int userId );

}
