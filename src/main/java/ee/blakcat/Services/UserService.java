package ee.blakcat.Services;

import ee.blakcat.Models.User;

import javax.jws.soap.SOAPBinding;

public interface UserService extends BaseService <User, String> {

    User findByLogin (String login);
User findBySession (String session);
User findByLoginAdnPassword (String login, String password);
}
