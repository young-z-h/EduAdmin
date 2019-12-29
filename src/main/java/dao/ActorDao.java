package dao;

import com.alibaba.fastjson.JSONObject;
import domain.Actor;
import util.JdbcHelper;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ActorDao {
        private static ActorDao
                actorDao = new ActorDao();
        private static Set<Actor>
                actors;

    public ActorDao(){}

        public static ActorDao getInstance(){
        return actorDao;
    }

        public Collection<Actor> findAll()
            throws SQLException {
        actors = new HashSet<Actor>();
        Connection connection = JdbcHelper.getConn();
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(
                "select * from actor"
        );

        while(resultSet.next()){
            actors.add(new Actor(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("IDCard"),
                    resultSet.getString("phoneNumber")));
        }

        JdbcHelper.close(stmt,connection);
        return ActorDao.actors;
    }

        public Actor find(Integer id) throws SQLException {
        Actor actor = null;
        Connection connection = JdbcHelper.getConn();
        String findActor_sql =
                "SELECT * FROM actor WHERE id=?";
        PreparedStatement preparedStatement =
                connection.prepareStatement(findActor_sql);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            actor = new Actor(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("IDCard"),
                    resultSet.getString("phoneNumber"));
        }
        JdbcHelper.close(resultSet,preparedStatement,connection);
        return actor;
    }

        public static void main(String[] args) throws SQLException {
        String json_str = JSONObject.toJSONString(ActorDao.getInstance().findAll());
        System.out.println(json_str);
    }

}
