package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by artem on 4/5/16.
 */
@ActiveProfiles( {Profiles.POSTGRES, Profiles.JDBC})
public class JdbcUserMealServiceTest extends UserMealServiceTest {
}
