package authority.controller;

import authority.domain.Role;
import authority.service.RoleService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/role.ctl")
public class RoleController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String id_str = request.getParameter("id");

        JSONObject message = new JSONObject();
        try {
            if (id_str == null) {
                responseRoles(response);
            } else {
                int id = Integer.parseInt(id_str);
                responseRole(id, response);
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

    private void responseRole(int id, HttpServletResponse response)
            throws IOException, SQLException {
        Role role = RoleService.getInstance().find(id);
        String role_json = JSON.toJSONString(role);

        response.getWriter().println(role_json);
    }

    private void responseRoles(HttpServletResponse response)
            throws IOException, SQLException {
        Collection<Role> roles = RoleService.getInstance().findAll();
        String roles_json = JSON.toJSONString(roles, SerializerFeature.DisableCircularReferenceDetect);

        response.getWriter().println(roles_json);
    }
}
