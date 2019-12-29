package authority.controller;


import authority.domain.Menu;
import authority.service.MenuService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/menu.ctl")
public class MenuController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String userId_str = request.getParameter("userId");

        JSONObject message = new JSONObject();
        try {
            //后台数据传输写死，只能根据用户来传输菜单
//            if (userId_str != null){
                int id = Integer.parseInt(userId_str);
                Collection<Menu> menus = MenuService.getInstance().findByUser(id);
                String menu_json = JSON.toJSONString(menus);
                response.getWriter().println(menu_json);
//            }
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
