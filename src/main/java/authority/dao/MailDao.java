package authority.dao;

import authority.domain.Mail;
import authority.service.MailService;
import authority.util.JdbcHelper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.sql.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MailDao {
    private static MailDao
            mailDao = new MailDao();
    private static Set<Mail>
            mails;

    public MailDao(){}

    public static MailDao getInstance(){
        return mailDao;
    }
    /**
     * 获得数据库中所有的Mail对象的信息
     * @return Collection<Mail>集合
     * @throws SQLException 操作数据库，可能出现SQLException
     */
    public Collection<Mail> findAll() throws SQLException {
        mails = new HashSet<Mail>();
        Connection connection = JdbcHelper.getConn();
        Statement stmt = connection.createStatement();
        ResultSet resultSet = stmt.executeQuery(
                "select * from mail"
        );

        while(resultSet.next()){
            mails.add(new Mail(resultSet.getInt("id"),
                    resultSet.getString("sender"),
                    resultSet.getString("mailcontent")));
        }

        JdbcHelper.close(stmt,connection);
        return MailDao.mails;
    }

    /**
     * 根据参数查找Mail中id为id的对象
     * @param userId
     * @return 返回在数据库中查找到的Mail
     * @throws SQLException
     */
    public Mail find(Integer userId) throws SQLException {
        Mail mail = null;
        Connection connection = JdbcHelper.getConn();
        String findMail_sql =
                "select * from mail where id=?;";
        PreparedStatement preparedStatement =
                connection.prepareStatement(findMail_sql);
        preparedStatement.setInt(1,userId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){
            mail = new Mail(resultSet.getInt("id"),
                    resultSet.getString("sender"),
                    resultSet.getString("mailcontent"));
        }
        JdbcHelper.close(resultSet,preparedStatement,connection);
        return mail;
    }

    public boolean add(Mail mail) throws SQLException {
        Connection connection = JdbcHelper.getConn();
        String add_sql = "INSERT INTO mail(sender, mailcontent) " +
                "VALUES (?,?)";
        PreparedStatement preparedStatement =
                connection.prepareStatement(add_sql);
        preparedStatement.setString(1,mail.getSender());
        preparedStatement.setString(2,mail.getMailContent());
//        ResultSet resultSet = preparedStatement.executeQuery();
        int affectedRowNum = preparedStatement.executeUpdate();
        return affectedRowNum>0;
    }


    public static void main(String[] args) throws SQLException {

        String json = "{\"id\":1,\"mailContent\":\"文苑三楼食堂饭难吃!\",\"sender\":\"雷锋\"}";
        Mail mail = JSONObject.parseObject(json, Mail.class);
        MailService.getInstance().add(mail);
        System.out.println(JSON.toJSONString(MailService.getInstance().findAll()));
//        System.out.println(JSON.toJSONString(mail));
    }

}
