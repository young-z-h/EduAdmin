package authority.controller;

import authority.domain.Mail;
import authority.service.MailService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/mail.ctl")
public class MailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        JSONObject message = new JSONObject();
        try {
            Collection<Mail> mails = MailService.getInstance().findAll();
            String mail_json = JSON.toJSONString(mails);
            response.getWriter().println(mail_json);
        }catch (SQLException e){
            message.put("message", "数据库操作异常");
            response.getWriter().println(message);
            e.printStackTrace();
        }catch(Exception e){
            message.put("message", "其他异常");
            response.getWriter().println(message);
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String mail_json = JSONUtil.getJSON(request);
        Mail mailToAdd = JSON.parseObject(mail_json, Mail.class);
        System.out.println(mailToAdd.toString());

        JSONObject message = new JSONObject();
        try {
            MailService.getInstance().add(mailToAdd);
            message.put("message", "增加成功");
        }catch (SQLException e){
            message.put("message", "数据库操作异常");
            e.printStackTrace();
        }catch(Exception e){
            message.put("message", "网络异常");
            e.printStackTrace();
        }
        response.getWriter().println(message);

    }
}

