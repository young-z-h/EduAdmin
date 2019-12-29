package authority.fiter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "Filter 0", urlPatterns = "/*")
public class Filter00_Encoding implements Filter {
    public void doFilter(ServletRequest req,
                         ServletResponse resp, FilterChain chain)
            throws ServletException, IOException {
        System.out.println("Filter 0 - encoding begin.");

        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response= (HttpServletResponse)resp;
        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=UTF-8");
//        if ("POST GET".contains(request.getMethod())){
            //编码优先级最高
            response.setCharacterEncoding("UTF-8");
            //编码优先级其次，这两个都可以起到作用，可自行选择
            response.setContentType("text/html;charset=UTF-8");
//        }
        //执行其他过滤器或者servlet
        chain.doFilter(req, resp);
        System.out.println("Filters 0 - encoding ends.");
    }
}
