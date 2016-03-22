package ru.javawebinar.topjava.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;


import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserMealServiceTest {


    @Autowired
    protected UserMealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testGet() throws Exception {
        UserMeal meal = service.get(USER_MEAL_ID, USER_ID);
        MATCHER.assertEquals(USER_MEAL, meal);
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(USER_MEAL_ID, USER_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(ADMIN_MEAL), service.getAll(ADMIN_ID));
    }

    @Test
    public void testGetBetweenDates() throws Exception {
        MATCHER.assertCollectionEquals(Collections.singletonList(ADMIN_MEAL),
                service.getBetweenDates(
                        LocalDate.of(2016, Month.MARCH, 20),
                        LocalDate.of(2016, Month.MARCH, 22), ADMIN_ID));
    }

    @Test
    public void testGetBetweenDateTimes() throws Exception {
        MATCHER.assertCollectionEquals(Collections.singletonList(USER_MEAL),
                service.getBetweenDateTimes(
                        LocalDateTime.of(2016, Month.MARCH, 22, 8, 0),
                        LocalDateTime.of(2016, Month.MARCH, 22, 12, 0), USER_ID));
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(Collections.singletonList(ADMIN_MEAL), service.getAll(ADMIN_ID));
        MATCHER.assertCollectionEquals(Collections.singletonList(USER_MEAL), service.getAll(USER_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        UserMeal updated = new UserMeal(USER_MEAL);
        updated.setDescription("UpdatedDescription");
        updated.setCalories(330);
        service.update(updated, USER_ID);
        MATCHER.assertEquals(updated, service.get(USER_MEAL_ID, USER_ID));
    }

    @Test
    public void testSave() throws Exception {
        UserMeal tm = new UserMeal(null, LocalDateTime.of(2016, Month.MARCH, 22, 20, 0), "newPass", 1555);
        UserMeal created = service.save(tm, USER_ID);
        tm.setId(created.getId());
        MATCHER.assertCollectionEquals(Arrays.asList(USER_MEAL, tm), service.getAll(USER_ID));
    }


    //  - Сделаеть тесты на чужих юзеров (delete, get, update) с тем чтобы получить `NotFoundException`

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(USER_MEAL_ID,ADMIN_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(ADMIN_MEAL_ID,USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNotFound() throws Exception {
        UserMeal updated = new UserMeal(USER_MEAL);
        updated.setDescription("UpdatedDescription");
        updated.setCalories(330);
        service.update(updated, ADMIN_ID);
    }


}