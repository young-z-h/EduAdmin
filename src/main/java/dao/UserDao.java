package dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import domain.*;
import service.ActorService;
import service.MenuService;
import service.RoleService;
import service.UserService;
import util.JdbcHelper;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class UserDao {
    private static UserDao
            userDao = new UserDao();
    private static Set<User>
            users;

    public UserDao() {
    }

    public static UserDao getInstance() {
        return userDao;
    }

    public Collection<User> findAll()
            throws SQLException {
        users = new HashSet<User>();
        Connection connection = JdbcHelper.getConn();
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(
                "select * from user"
        );

        while (resultSet.next()) {
            users.add(new User(resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    ActorService.getInstance()
                            .find(resultSet.getInt("actor_id"))));
        }

        JdbcHelper.close(stmt, connection);
        return UserDao.users;
    }
    public User login(String un,String pw) throws SQLException {
        Connection connection = JdbcHelper.getConn();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from user where username=? and password=?");
        preparedStatement.setString(1,un);
        preparedStatement.setString(2,pw);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        while (resultSet.next()){
            user = new User(resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    ActorService.getInstance().find(resultSet.getInt("actor_id")));
        }
        JdbcHelper.close(resultSet,preparedStatement,connection);
        return user;
    }
    public User find(Integer id) throws SQLException {
        User user = null;
        Connection connection = JdbcHelper.getConn();
        String findUser_sql =
                "SELECT * FROM user WHERE id=?";
        PreparedStatement preparedStatement =
                connection.prepareStatement(findUser_sql);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            user = new User(resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    ActorService.getInstance()
                            .find(resultSet.getInt("actor_id")));
        }
        JdbcHelper.close(resultSet, preparedStatement, connection);
        return user;
    }

    public Collection<RoleMenu> findRoleMenus(User user) throws SQLException {
        Collection<RoleMenu> roleMenus = new TreeSet<>();
        Role role = new Role();
        Collection<Menu> menus = null;
        Connection connection = JdbcHelper.getConn();
        String findRoleMenu_sql =
                "select userroleass.role_id,\n" +
                        "       rolemenuass.menu_id\n" +
                        "from userroleass,rolemenuass\n" +
                        "where user_id=?\n" +
                        "  and userroleass.role_id = rolemenuass.role_id\n" +
                        "order by userroleass.role_id;";
        PreparedStatement preparedStatement =
                connection.prepareStatement(findRoleMenu_sql);
        preparedStatement.setInt(1, user.getId());

        ResultSet resultSet = preparedStatement.executeQuery();

        boolean check = resultSet.next();
        while (check) {
            menus = new TreeSet<>();
            int role_id = resultSet.getInt("role_id");
            role = RoleService.getInstance().find(role_id);
            while (check && role_id == resultSet.getInt("role_id")) {
                menus.add(MenuService.getInstance().find(resultSet.getInt("menu_id")));
                check = resultSet.next();
            }
            roleMenus.add(new RoleMenu(role, menus));
        }
        return roleMenus;
    }

    public static void main(String[] args) throws SQLException {
        String json_2 = JSON.toJSONString(UserService.getInstance().find(1), SerializerFeature.IgnoreNonFieldGetter);
        String json = JSON.toJSONString(userDao.findRoleMenus(UserService.getInstance().find(2)), SerializerFeature.IgnoreNonFieldGetter);
        System.out.println(JSON.toJSONString(UserService.getInstance().findRoleMenus(UserService.getInstance().find(1))));
    }
}
