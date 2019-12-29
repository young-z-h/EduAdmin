package authority.dao;

import authority.domain.UserRoleAss;
import authority.service.RoleService;
import authority.service.UserRoleAssService;
import authority.service.UserService;
import authority.util.JdbcHelper;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;

public class UserRoleAssDao {
    private static UserRoleAssDao
            userRoleAssDao = new UserRoleAssDao();
    private static Collection<UserRoleAss>
            userRoleAsss;

    public UserRoleAssDao(){}

    public static UserRoleAssDao getInstance(){
        return userRoleAssDao;
    }


    public Collection<UserRoleAss> findAll()
            throws SQLException {
        userRoleAsss = new HashSet<>();
        Connection connection = JdbcHelper.getConn();
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(
                "select * from userRoleAss"
        );

        while (resultSet.next()){
            userRoleAsss.add(new UserRoleAss(resultSet.getInt("id"),
                    UserService.getInstance().find(resultSet.getInt("user_id")),
                    RoleService.getInstance().find(resultSet.getInt("role_id"))));
        }

        JdbcHelper.close(stmt,connection);
        return userRoleAsss;
    }

    public UserRoleAss find(Integer id) throws SQLException {
        UserRoleAss userRoleAss = null;
        Connection connection = JdbcHelper.getConn();
        String findUserRoleAss_sql =
                "SELECT * FROM userRoleAss WHERE id=?";
        PreparedStatement preparedStatement =
                connection.prepareStatement(findUserRoleAss_sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            userRoleAss = new UserRoleAss(resultSet.getInt("id"),
                    UserService.getInstance().find(resultSet.getInt("user_id")),
                    RoleService.getInstance().find(resultSet.getInt("role_id")));
        }
        JdbcHelper.close(resultSet,preparedStatement,connection);
        return userRoleAss;
    }


//    public boolean update(UserRoleAss userRoleAss)
//            throws SQLException {
//        //获得连接对象
//        Connection connection = JdbcHelper.getConn();
//        //创建sql查询语句
//        String updateUserRoleAss_sql = "update userRoleAss " +
//                "set no=?, " +
//                "description=?, " +
//                "where id=?";
//        PreparedStatement pstmt =
//                connection.prepareStatement(updateUserRoleAss_sql);
//        pstmt.setString(1, userRoleAss.getNo());
//        pstmt.setString(2,userRoleAss.getDescription());
//        pstmt.setInt(3,userRoleAss.getId());
//
//        int affectedRowNum = pstmt.executeUpdate();
//        JdbcHelper.close(pstmt,connection);
//        return affectedRowNum>0;
//    }

    public static void main(String[] args) throws SQLException {
        String json_str = JSONObject.toJSONString(UserRoleAssService.getInstance().find(1));
        System.out.println(json_str);
    }

}
