package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * GKislin
 * 27.03.2015.
 */
@Repository
public class DataJpaUserMealRepositoryImpl implements UserMealRepository {

    private static final Sort SORT_DATE_TIME = new Sort( Sort.Direction.DESC, "dateTime");

    @Autowired
    ProxyUserMealRepository proxy;

    @Autowired
    ProxyUserRepository userProxy;


    @Override
    public UserMeal save(UserMeal userMeal, int userId) {

        if (!userMeal.isNew()) {
            if (get(userMeal.getId(), userId) == null) {
                return null;
            }
        }
        User user = userProxy.getOne(userId);
        userMeal.setUser(user);
        return proxy.save(userMeal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return proxy.delete(id, userId) != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        return proxy.findByIdAndUserId(id, userId);
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return proxy.findByUserId(userId, SORT_DATE_TIME );
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return proxy.getBetween(userId, startDate, endDate);
    }
}
