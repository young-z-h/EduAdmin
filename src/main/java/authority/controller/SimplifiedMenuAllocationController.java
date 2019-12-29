package authority.controller;

import authority.domain.SimplifiedMenuAllocation;
import authority.service.SimplifiedMenuAllocationService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/simplifiedMenuAllocation.ctl")
public class SimplifiedMenuAllocationController extends HttpServlet {
    /**
     *虽说是put修改，但是操作数据库的本质是增删
     * @param request
     * @param response
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String simplifiedMenu_json = JSONUtil.getJSON(request);
        SimplifiedMenuAllocation sma
                = JSON.parseObject(simplifiedMenu_json, SimplifiedMenuAllocation.class);
        JSONObject message = new JSONObject();

        SimplifiedMenuAllocationService.getInstance().alterRole(sma);
        message.put("message", "成功");
        //响应message到前端
        response.getWriter().println(message);
    }
}
