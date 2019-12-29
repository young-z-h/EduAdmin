package authority.dao;

import authority.domain.Menu;
import authority.domain.Role;
import authority.domain.RoleMenu;
import authority.domain.User;
import authority.service.ActorService;
import authority.service.MenuService;
import authority.service.RoleService;
import authority.service.UserService;
import authority.util.JdbcHelper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

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

    public UserDao(){}

    public static UserDao getInstance(){
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

        while(resultSet.next()){
            users.add(new User(resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    ActorService.getInstance()
                            .find(resultSet.getInt("actor_id"))));
        }

        JdbcHelper.close(stmt,connection);
        return UserDao.users;
    }

    public User find(Integer id) throws SQLException {
        User user = null;
        Connection connection = JdbcHelper.getConn();
        String findUser_sql =
                "SELECT * FROM user WHERE id=?";
        PreparedStatement preparedStatement =
                connection.prepareStatement(findUser_sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            user = new User(resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    ActorService.getInstance()
                            .find(resultSet.getInt("actor_id")));
        }
        JdbcHelper.close(resultSet,preparedStatement,connection);
        return user;
    }

    /**
     * 创建一个空Role对象
     * 创建空的Menu集合对象
     * 创建数据库连接对象
     * 连接查询查出user对应的role以及role对应的menu
     * 操作result结果集对象
     * 使用循环逐行操作结果集
     * 获得最终对象
     * @param user
     * @return
     * @throws SQLException
     */
    public Collection<RoleMenu> findRoleMenus(User user) throws SQLException {
        Collection<RoleMenu> roleMenus = new HashSet<>();
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
        while (check){
            menus = new TreeSet<>();
            int role_id = resultSet.getInt("role_id");
            role = RoleService.getInstance().find(role_id);
            while (check && role_id == resultSet.getInt("role_id")){
                menus.add(MenuService.getInstance().find(resultSet.getInt("menu_id")));
                check = resultSet.next();
            }
            roleMenus.add(new RoleMenu(role, menus));
        }
        return roleMenus;
    }


    public static void main(String[] args) throws SQLException {
        User user = UserService.getInstance().find(1);
        String json_str =
                JSON.toJSONString(UserDao.getInstance().findRoleMenus(user),
                        SerializerFeature.DisableCircularReferenceDetect);
        System.out.println(json_str);
        System.out.println("------------");
//        System.out.println(UserDao.);
    }
}
