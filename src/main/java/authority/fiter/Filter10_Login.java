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
        //servletRequestǿ������ת��ΪHttpServletRequest����
        HttpServletRequest request = (HttpServletRequest)req;
        //servletResponseǿ������ת��ΪHttpServletResponse����
        HttpServletResponse response =(HttpServletResponse)resp;
        //�����ǰ�����Ӧ�ŷ������ڴ��е�һ��session�����򷵻ظö���
        //����������ڴ�û��session�����뵱ǰ�����Ӧ���򷵻�null
        HttpSession session = request.getSession(false);
        //��������·��
        String path= request.getRequestURL().toString();

        System.out.println(path);

        boolean testSession = (session != null && (session.getAttribute("��ʦ") != null || session.getAttribute("ѧ��") != null));

        System.out.println(testSession);

        if (testSession
                || (path.contains("log") || (path.contains("app") && !path.contains("index")))
        ) {
            chain.doFilter(req, resp);
            System.out.println("ִ��");
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
