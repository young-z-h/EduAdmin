package authority.dao;

import authority.domain.RoleMenuAss;
import authority.service.MenuService;
import authority.service.RoleMenuAssService;
import authority.service.RoleService;
import authority.util.JdbcHelper;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;

public class RoleMenuAssDao {
    private static RoleMenuAssDao
            userRoleAssDao = new RoleMenuAssDao();
    private static Collection<RoleMenuAss>
            userRoleAsss;

    public RoleMenuAssDao(){}

    public static RoleMenuAssDao getInstance(){
        return userRoleAssDao;
    }


    public Collection<RoleMenuAss> findAll()
            throws SQLException {
        userRoleAsss = new HashSet<>();
        Connection connection = JdbcHelper.getConn();
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(
                "select * from rolemenuass"
        );

        while (resultSet.next()){
            userRoleAsss.add(new RoleMenuAss(resultSet.getInt("id"),
                    MenuService.getInstance().find(resultSet.getInt("menu_id")),
                    RoleService.getInstance().find(resultSet.getInt("role_id"))));
        }

        JdbcHelper.close(stmt,connection);
        return userRoleAsss;
    }

    public RoleMenuAss find(Integer id) throws SQLException {
        RoleMenuAss userRoleAss = null;
        Connection connection = JdbcHelper.getConn();
        String findRoleMenuAss_sql =
                "SELECT * FROM rolemenuass WHERE id=?";
        PreparedStatement preparedStatement =
                connection.prepareStatement(findRoleMenuAss_sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            userRoleAss = new RoleMenuAss(resultSet.getInt("id"),
                    MenuService.getInstance().find(resultSet.getInt("menu_id")),
                    RoleService.getInstance().find(resultSet.getInt("role_id")));
        }
        JdbcHelper.close(resultSet,preparedStatement,connection);
        return userRoleAss;
    }


    public static void main(String[] args) throws SQLException {
        String json_str = JSONObject.toJSONString(RoleMenuAssService.getInstance().find(1));
        System.out.println(json_str);
    }

}
