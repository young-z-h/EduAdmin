package authority.fiter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(filterName = "Filter10_Login",  urlPatterns = "/*")
public class Filter10_Login implements Filter {

    public void doFilter(ServletRequest req,
                         ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {

        System.out.println("Filter 20 - log begins");
        //servletRequest强制类型转换为HttpServletRequest类型
        HttpServletRequest request = (HttpServletRequest)req;
        //servletResponse强制类型转换为HttpServletResponse类型
        HttpServletResponse response =(HttpServletResponse)resp;
        //如果当前请求对应着服务器内存中的一个session对象，则返回该对象
        //如果服务器内存没有session对象与当前请求对应，则返回null
        HttpSession session = request.getSession(false);
        //获得请求的路径
        String path= request.getRequestURL().toString();

        System.out.println(path);

        boolean testSession = (session != null && (session.getAttribute("老师") != null || session.getAttribute("学生") != null));

        System.out.println(testSession);

        if (testSession
                || (path.contains("log") || (path.contains("app") && !path.contains("index")))
        ) {
            chain.doFilter(req, resp);
            System.out.println("执行");
        }else {
            response.sendRedirect(
                    "http://49.234.112.12:8080/myapp/#/");
        }



//        if (session == null ||
//                (session!=null && session.getAttribute("currentUser") != null)){
//            response.sendRedirect(
//                    "http://49.234.112.12:8080/bysj1860/myapp/#/");
//        }


//        System.out.println("Filter 20 - login end");
    }
}
