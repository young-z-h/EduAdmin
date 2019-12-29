package controller.CourseType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import domain.CourseType;
import service.CourseTypeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/courseType.ctl")
public class CourseTypeController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String id_str = request.getParameter("id");
        JSONObject message = new JSONObject();
        if (id_str!=null){
            int id = Integer.parseInt(id_str);
            try {
                CourseType courseType = CourseTypeService.getInstance().find(id);
                String course = JSON.toJSONString(courseType);
                response.getWriter().println(course);
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
                Collection<CourseType> courseTypes = CourseTypeService.getInstance().findAll();
                String course_str = JSON.toJSONString(courseTypes,SerializerFeature.DisableCircularReferenceDetect);
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
