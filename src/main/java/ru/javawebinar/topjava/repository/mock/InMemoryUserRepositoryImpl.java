package ru.javawebinar.topjava.repository.mock;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by artem on 3/11/16.
 */
public class InMemoryUserRepositoryImpl implements UserRepository {

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public User save(User user) {
        if( user.isNew() ) {
            user.setId( counter.incrementAndGet() );
        }
        repository.put(user.getId(),user);
        return user;
    }

    @Override
    public boolean delete(int id) {

        if( repository.containsKey(id)) {
            repository.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public User getByEmail(String email) {
        for( User u: repository.values()) {
            if( email.equals(u.getEmail())){
                return u;
            }
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>(repository.values());
        Collections.sort(list, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        return list;
    }
}
