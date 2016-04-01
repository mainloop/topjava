package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by artem on 3/31/16.
 */
@Transactional(readOnly = true)
public interface ProxyUserMealRepository extends JpaRepository<UserMeal, Integer> {

    @Transactional
    @Modifying
    @Query(name = UserMeal.DELETE)
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Transactional
    UserMeal save(UserMeal userMeal);

    UserMeal findByIdAndUserId( int id, int userId);

    List<UserMeal> findByUserId(int userId, Sort sort);

    @Query("SELECT m FROM UserMeal m WHERE m.user.id=:userId AND m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC")
    List<UserMeal>  getBetween( @Param("userId") int userId,
                                @Param("startDate") LocalDateTime startDate,
                                @Param("endDate") LocalDateTime endDate);
}
