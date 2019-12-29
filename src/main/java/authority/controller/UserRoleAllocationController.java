package authority.controller;


import authority.domain.RoleAllocation;
import authority.service.RoleAllocationService;
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

@WebServlet("/userRoleAllocation.ctl")
public class UserRoleAllocationController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String id_str = request.getParameter("id");

        JSONObject message = new JSONObject();
        try {
            if (id_str == null) {
                responseRoleAllocations(response);
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
        RoleAllocation roleAllocation = RoleAllocationService.getInstance().find(id);
        String roleAllocation_json = JSON.toJSONString(roleAllocation);

        response.getWriter().println(roleAllocation_json);
    }

    private void responseRoleAllocations(HttpServletResponse response)
            throws IOException, SQLException {
        Collection<RoleAllocation> roleAllocations
                = RoleAllocationService.getInstance().findAll();
        String roleAllocations_json
                = JSON.toJSONString(roleAllocations
                , SerializerFeature.DisableCircularReferenceDetect);

        response.getWriter().println(roleAllocations_json);
    }

}
