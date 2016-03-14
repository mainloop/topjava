package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.LoggedUser;
import ru.javawebinar.topjava.service.to.UserMealWithExceed;
import ru.javawebinar.topjava.service.UserMealService;

import java.time.LocalDateTime;

import java.util.List;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class UserMealRestController {

    @Autowired
    private UserMealService service;

    public void save(UserMealWithExceed userMealWithExceed) {
        service.save(userMealWithExceed, LoggedUser.id() );
    }

    public List<UserMealWithExceed> getFiltered(LocalDateTime start, LocalDateTime end, int exceed) {
        return service.getFiltered(start, end, exceed, LoggedUser.id());
    }

    public void delete(int id) {
        service.delete(id, LoggedUser.id());
    }

    public UserMealWithExceed get(int id) {
        return service.get(id, LoggedUser.id());
    }


}
