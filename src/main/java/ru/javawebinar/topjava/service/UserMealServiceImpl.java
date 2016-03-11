package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
public class UserMealServiceImpl implements UserMealService {

    private UserMealRepository repository;

    @Override
    public UserMeal save(UserMeal userMeal) {
        return repository.save(userMeal);
    }

    @Override
    public void delete(int id) throws NotFoundException {

    }

    @Override
    public UserMeal get(int id) throws NotFoundException {
        return null;
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return repository.getAll(userId);
    }

    @Override
    public void update(UserMeal userMeal) {
        repository.save(userMeal);
    }
}
