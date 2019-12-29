package controller.Login;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import domain.*;
import service.*;
import util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/login.ctl")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String user_str = JSONUtil.getJSON(request);
        User user = JSONObject.parseObject(user_str,User.class);
        String username = user.getUsername();
        String password = user.getPassword();
        User user1 = null;
        JSONObject message = new JSONObject();
        try {
            user1 = UserService.getInstance().login(username,password);
            if (user1!=null){
                int actor_id = user1.getActor().getId();
                if (TeacherService.getInstance().find(actor_id)!=null){
                    HttpSession session = request.getSession();
                    session.setMaxInactiveInterval(60*30);
                    Collection<RoleMenu> roleMenus = UserService.getInstance().findRoleMenus(user1);
                    session.setAttribute("老师",user1);
                    String user1_str = JSONObject.toJSONString(user1, SerializerFeature.IgnoreNonFieldGetter) ;
                    String actor_json = JSONObject.toJSONString(roleMenus, SerializerFeature.IgnoreNonFieldGetter);
                    response.getWriter().println(user1_str);
                }
                else if (StudentService.getInstance().find(actor_id)!=null){
                    HttpSession session = request.getSession();
                    session.setMaxInactiveInterval(60*30);
                    Collection<RoleMenu> roleMenus = UserService.getInstance().findRoleMenus(user1);
                    session.setAttribute("学生",user1);
                    String user1_str = JSONObject.toJSONString(user1, SerializerFeature.IgnoreNonFieldGetter) ;
                    String actor_json = JSONObject.toJSONString(roleMenus, SerializerFeature.IgnoreNonFieldGetter);
                    response.getWriter().println(user1_str);
                }
            }else{
                //否则就是登录失败，用户名或密码错误
                message.put("message","User name or password error");
                response.getWriter().println(message);
            }
        } catch (SQLException e) {
            //抓取错误
            message.put("message", "数据库操作异常");
            e.printStackTrace();
            response.getWriter().println(message);
        } catch (Exception e){
            message.put("message", "网络异常");
            e.printStackTrace();
            response.getWriter().println(message);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
