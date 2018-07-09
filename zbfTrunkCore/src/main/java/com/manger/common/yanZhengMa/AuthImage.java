package com.manger.common.yanZhengMa;
import java.io.IOException; 
 
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
import javax.servlet.http.HttpSession;
 
/**
 * <p><b>AuthImage Description:</b> (验证码)</p>
 * <b>DATE:</b> 2016年6月2日 下午3:53:12
 */
@WebServlet(urlPatterns = "/main")  // url路径
public class AuthImage extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet { 
    static final long serialVersionUID = 1L; 
   
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
        response.setHeader("Pragma", "No-cache"); 
        response.setHeader("Cache-Control", "no-cache"); 
        response.setDateHeader("Expires", 0); 
        response.setContentType("image/jpeg"); 
           
        //生成随机字串 
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);//验证码显示多少个字符
        //存入会话session 
        HttpSession session = request.getSession(); 
        //HttpSession session = request.getSession(true);//参数是true的话就会每次都新创建一个session这样的话session数据不会放到memcached中去 
        //删除以前的
        session.removeAttribute("verCode");
        session.setAttribute("verCode", verifyCode.toLowerCase());
        //生成图片 
        int w =110, h = 37; 
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode); 
   
    } 
}