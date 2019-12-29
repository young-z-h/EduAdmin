package authority.dao;

import authority.domain.Menu;
import authority.service.MenuService;
import authority.util.JdbcHelper;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MenuDao {
    private static MenuDao
            menuDao = new MenuDao();
    private static Set<Menu>
            menus;

    public MenuDao(){}

    public static MenuDao getInstance(){
        return menuDao;
    }

    /**
     * 返回表中所有的Menu对象
     * @return
     * @throws SQLException
     */
    public Collection<Menu> findAll()
            throws SQLException {
        menus = new HashSet<Menu>();
        Connection connection = JdbcHelper.getConn();
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(
                "select * from menu"
        );

        while(resultSet.next()){
            menus.add(new Menu(resultSet.getInt("id"),
                    resultSet.getString("description"),
                    resultSet.getString("no"),
                    resultSet.getString("url")));
        }

        JdbcHelper.close(stmt,connection);
        return MenuDao.menus;
    }

    /**
     * 根据id查询表中对应的Menu对象
     * @param userId
     * @return
     * @throws SQLException
     */
    public Menu find(Integer userId) throws SQLException {
        Menu menu = null;
        Connection connection = JdbcHelper.getConn();
        String findMenu_sql =
                "select * from menu where id=?;";
        PreparedStatement preparedStatement =
                connection.prepareStatement(findMenu_sql);
        preparedStatement.setInt(1,userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            menu = new Menu(resultSet.getInt("id"),
                    resultSet.getString("description"),
                    resultSet.getString("no"),
                    resultSet.getString("url"));
        }
        JdbcHelper.close(resultSet,preparedStatement,connection);
        return menu;
    }

    /**
     * 对表进行连接查询
     * 将user,role,userroleass,menu,rolemenuass连接起来
     * 查出user拥有的menu
     * 返回Menu集合
     * @param userId
     * @return
     * @throws SQLException
     */
    public Collection<Menu> findByUser(Integer userId) throws SQLException {
        Collection<Menu> menus = new HashSet<>();
        Connection connection = JdbcHelper.getConn();
        String findMenu_sql =
                "select distinct menu.id,menu.no,menu.description,url\n" +
                        "from user,role,userroleass,menu,rolemenuass\n" +
                        "where user.id = userroleass.user_id\n" +
                        "  and role.id = userroleass.role_id\n" +
                        "  and role.id = rolemenuass.role_id\n" +
                        "  and menu.id = rolemenuass.menu_id\n" +
                        "  and user_id = ?\n" +
                        "order by role.description;";
        PreparedStatement preparedStatement =
                connection.prepareStatement(findMenu_sql);
        preparedStatement.setInt(1,userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            menus.add(new Menu(resultSet.getInt("id"),
                    resultSet.getString("description"),
                    resultSet.getString("no"),
                    resultSet.getString("url")));
        }
        JdbcHelper.close(resultSet,preparedStatement,connection);
        return menus;
    }


    public static void main(String[] args) throws SQLException {
        String json = JSONObject.toJSONString(MenuService.getInstance().find(1));
        System.out.println(json);
    }
}
