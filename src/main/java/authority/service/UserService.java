package authority.service;

import authority.dao.UserDao;
import authority.domain.User;

import java.sql.SQLException;
import java.util.Collection;

public class UserService {
    private static UserDao userDao= UserDao.getInstance();
    private static UserService userService=new UserService();
    private UserService(){}

    public static UserService getInstance(){
        return userService;
    }

    public Collection<User> findAll() throws SQLException {
        return userDao.findAll();
    }

    public User find(Integer id) throws SQLException {
        return userDao.find(id);
    }
}
