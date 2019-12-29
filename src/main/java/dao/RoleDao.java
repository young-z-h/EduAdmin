package dao;

import domain.Role;
import util.JdbcHelper;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class RoleDao {
    private static RoleDao
            roleDao = new RoleDao();
    private static Set<Role>
            roles;

    public RoleDao(){}

    public static RoleDao getInstance(){
        return roleDao;
    }

    /**
     * 返回Role表中的所有对象
     * @return 集合类对象,元素类型为Role
     * @throws SQLException
     */
    public Collection<Role> findAll()
            throws SQLException {
        //由于重写equals和hashcode方法的内容，使用HashSet则普遍添加不进去
        roles = new TreeSet<>();
        Connection connection = JdbcHelper.getConn();
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(
                "select * from role"
        );

        while(resultSet.next()){
            roles.add(new Role(resultSet.getInt("id"),
                    resultSet.getString("no"),
                    resultSet.getString("description")));
        }

        JdbcHelper.close(stmt,connection);
        return RoleDao.roles;
    }

    public Role find(Integer id) throws SQLException {
        Role role = null;
        Connection connection = JdbcHelper.getConn();
        String findRole_sql =
                "SELECT * FROM role WHERE id=?";
        PreparedStatement preparedStatement =
                connection.prepareStatement(findRole_sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            role = new Role(resultSet.getInt("id"),
                    resultSet.getString("no"),
                    resultSet.getString("description")
                    );
        }
        JdbcHelper.close(resultSet,preparedStatement,connection);
        return role;
    }


//    /**
//     * 教师这个用户只能够修改自己权限之内的毕设属性
//     * @param role
//     * @return
//     * @throws SQLException
//     */
//    public boolean update(Role role)
//            throws SQLException {
//        //获得连接对象
//        Connection connection = JdbcHelper.getConn();
//        //创建sql查询语句
//        String updateRole_sql = "update role " +
//                "set no=?, " +
//                "description=?, " +
//                "where id=?";
//        PreparedStatement pstmt =
//                connection.prepareStatement(updateRole_sql);
//        pstmt.setString(1, role.getNo());
//        pstmt.setString(2,role.getDescription());
//        pstmt.setInt(3,role.getId());
//
//        int affectedRowNum = pstmt.executeUpdate();
//        JdbcHelper.close(pstmt,connection);
//        return affectedRowNum>0;
//    }

    public static void main(String[] args) throws SQLException {
        String json_str = JSONObject.toJSONString(RoleDao.getInstance().findAll());
        System.out.println(json_str);
    }
}
