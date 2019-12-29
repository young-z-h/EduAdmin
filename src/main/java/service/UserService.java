package service;

import dao.UserDao;
import domain.RoleMenu;
import domain.User;

import java.sql.SQLException;
import java.util.Collection;

public class UserService {
    private static UserDao userDao= UserDao.getInstance();
    private static UserService userService=new UserService();
    private UserService(){}

    public static UserService getInstance(){
        return userService;
    }
    public User login(String un,String pw) throws SQLException {
        return userDao.login(un,pw);
    }
    public Collection<User> findAll() throws SQLException {
        return userDao.findAll();
    }
    public Collection<RoleMenu> findRoleMenus(User user) throws SQLException {
        return userDao.findRoleMenus(user);
    }
    public User find(Integer id) throws SQLException {
        return userDao.find(id);
    }
}
