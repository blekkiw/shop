package ee.blakcat.Repositories;

import ee.blakcat.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
public User findByLogin (String login);
public User findBySession (String session);
User findByLoginAndPassword (String login, String password);
}
