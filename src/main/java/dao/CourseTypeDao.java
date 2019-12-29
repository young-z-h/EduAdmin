package dao;

import domain.CourseType;
import util.JdbcHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.TreeSet;

public class CourseTypeDao {
    private static CourseTypeDao courseTypeDao = new CourseTypeDao();
    private CourseTypeDao(){
    }
    //getInstance方法
    public static CourseTypeDao getInstance() {
        return courseTypeDao;
    }
    public CourseType find(Integer id) throws SQLException {
        //连接对象
        Connection connection = JdbcHelper.getConn();
        //预编译语句
        String courseType_sql = "SELECT * FROM coursetype where id = ?";
        //预编译对象
        PreparedStatement pstmt = connection.prepareStatement(courseType_sql);
        //赋值
        pstmt.setInt(1,id);
        //执行返回结果集
        ResultSet resultSet = pstmt.executeQuery();
        CourseType courseType = null;
        //指针下移
        while (resultSet.next()){
        //创建对象
        courseType = new CourseType(
                resultSet.getInt("id"),
                resultSet.getString("no"),
                resultSet.getString("description"),
                resultSet.getString("remarks")
        );}
        //关闭资源
        JdbcHelper.close(pstmt,connection);
        //返回对象
        return courseType;
    }
    public Collection<CourseType> findAll() throws SQLException {
        Collection<CourseType> courseTypes = new TreeSet<CourseType>();
        Connection connection = JdbcHelper.getConn();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM coursetype");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            courseTypes.add(new CourseType(
                    resultSet.getInt("id"),
                    resultSet.getString("no"),
                    resultSet.getString("description"),
                    resultSet.getString("remarks")));
        }
        JdbcHelper.close(preparedStatement,connection);
        return  courseTypes;
    }
}
