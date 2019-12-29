package authority.controller;

import domain.Actor;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import service.ActorService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/actorInfo.ctl")
public class ActorInfoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        JSONObject message = new JSONObject();
        try {
            //actor不是authority包中的actor
            Collection<Actor> actors = ActorService.getInstance().findAll();
            String mail_json = JSON.toJSONString(actors);
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

}
