package controller.College;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import domain.College;
import service.CollegeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/college.ctl")
public class CollegeController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String id_str = request.getParameter("id");
        JSONObject message = new JSONObject();
        if (id_str!=null){
            int id = Integer.parseInt(id_str);
            try {
                College college = CollegeService.getInstance().find(id);
                String college_str = JSON.toJSONString(college);
                response.getWriter().println(college_str);
            } catch (SQLException e) {
                e.printStackTrace();
                message.put("message","数据库异常");
                response.getWriter().println(message);
            }catch (Exception e){
                e.printStackTrace();
                message.put("message","网络异常");
                response.getWriter().println(message);
            }
        }else {
            try {
                Collection<College> colleges = CollegeService.getInstance().findAll();
                String course_str = JSON.toJSONString(colleges, SerializerFeature.DisableCircularReferenceDetect);
                response.getWriter().println(course_str);
            } catch (SQLException e) {
                e.printStackTrace();
                message.put("message","数据库异常");
                response.getWriter().println(message);
            }catch (Exception e){
                e.printStackTrace();
                message.put("message","网络异常");
                response.getWriter().println(message);
            }
        }
    }
}

