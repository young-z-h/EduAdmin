package authority.controller;


import authority.domain.MenuAllocation;
import authority.service.MenuAllocationService;
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

@WebServlet("/roleMenuAllocation.ctl")
public class RoleMenuAllocationController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String id_str = request.getParameter("id");

        JSONObject message = new JSONObject();
        try {
            if (id_str == null) {
                responseMenuAllocations(response);
            } else {
                int id = Integer.parseInt(id_str);
                responseMenuAllocation(id, response);
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

    private void responseMenuAllocation(int id, HttpServletResponse response)
            throws IOException, SQLException {
        MenuAllocation menuAllocation = MenuAllocationService.getInstance().find(id);
        String menuAllocation_json = JSON.toJSONString(menuAllocation);

        response.getWriter().println(menuAllocation_json);
    }

    private void responseMenuAllocations(HttpServletResponse response)
            throws IOException, SQLException {
        Collection<MenuAllocation> menuAllocations
                = MenuAllocationService.getInstance().findAll();
        String menuAllocations_json
                = JSON.toJSONString(menuAllocations
                , SerializerFeature.DisableCircularReferenceDetect);

        response.getWriter().println(menuAllocations_json);
    }

}
