package ee.blakcat.Repositories;

import ee.blakcat.Models.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryToMap implements UserRepository {
    HashMap <String , User> users;

    public UserRepositoryToMap() {
        users = new HashMap<String, User>();
    }

    public User getByID(String id) {
        if (Objects.isNull(id)) throw new RuntimeException("null on id");
        return users.get(id);

    }

    public User save(User user) {
        if (Objects.isNull(user.getId())) {
            user.setId(UUID.randomUUID().toString());
        }
        users.put(user.getId(), user);
        return getByID(user.getId());
    }

    public Set<User> getAll() {
       return new HashSet<>(this.users.values());
    }
}
