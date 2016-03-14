package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.service.to.UserMealWithExceed;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.UserMealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class UserMealServiceImpl implements UserMealService {

    @Autowired
    private UserMealRepository repository;


    @Override
    public UserMealWithExceed save(UserMealWithExceed userMealWithExceed, int userId ) {

        UserMeal userMeal = repository.save( new UserMeal(userMealWithExceed.getId(),
                                                          userMealWithExceed.getDateTime(),
                                                          userMealWithExceed.getDescription(),
                                                          userMealWithExceed.getCalories(),
                                                          userId ));


        userMealWithExceed.setId( userMeal.getId() );
        return userMealWithExceed;
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        UserMeal meal = repository.get(id);
        if( meal == null ) {
            throw new  NotFoundException(String.format("UserMeal with id:%d not found",id));
        }
        else if( meal.getUserId() == userId) {
            repository.delete(id);
        }
        else {
            throw new  NotFoundException(String.format("Wrong userId:%d", userId));
        }
    }

    @Override
    public UserMealWithExceed get(int id, int userId) throws NotFoundException {

        UserMeal meal = repository.get(id);
        if( meal == null ) {
            throw new  NotFoundException(String.format("UserMeal with id:%d not found",id));
        }
        else if( meal.getUserId() == userId) {
            return new UserMealWithExceed(meal.getId(),
                    meal.getDateTime(),
                    meal.getDescription(),
                    meal.getCalories(),
                    false);
        }
        else {
            throw new  NotFoundException(String.format("Wrong userId:%d", userId));
        }
    }

    @Override
    public List<UserMealWithExceed> getFiltered(LocalDateTime start, LocalDateTime end, int exceed, int userId ) {
        List<UserMeal> list = repository.getAll(userId).stream()
                .filter(um->um.getUserId() == userId)
                .sorted(Comparator.comparing(UserMeal::getDateTime))
                .collect(Collectors.toList());

        return UserMealsUtil.getFilteredWithExceeded(list,start.toLocalTime(),end.toLocalTime(),exceed);
    }

    @Override
    public void update(UserMealWithExceed userMealWithExceed, int userId ) {
        UserMeal userMeal = new UserMeal(userMealWithExceed.getId(),
                                         userMealWithExceed.getDateTime(),
                                         userMealWithExceed.getDescription(),
                                         userMealWithExceed.getCalories(),
                                         userId);
        repository.save(userMeal);
    }


}
