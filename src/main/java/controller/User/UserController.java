package controller.User;
import com.alibaba.fastjson.JSONObject;
import domain.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/user.ctl")
public class UserController {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_str = request.getParameter("id");
        response.setCharacterEncoding("UTF-8");
        JSONObject message = new JSONObject();
        try {
            if (id_str == null) {
                message.put("message","找不到对应信息");
            } else {
                int id = Integer.parseInt(id_str);
                responseRoleAllocation(id, response);
            }
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
    private void responseRoleAllocation(int id, HttpServletResponse response)
            throws IOException, SQLException {
        User user = UserService.getInstance().find(id);
        String name = user.getActor().getName();
        response.getWriter().println(name);
    }
}
