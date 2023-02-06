package site.lemongproject.common.interceptor;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import site.lemongproject.common.response.ResponseBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private Gson gson;
    @Autowired
    public void setGson(Gson gson) {
        this.gson = gson;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if(session.getAttribute("loginUser")==null){
            gson.toJson(ResponseBuilder.unLogin(null),response.getWriter());
            return false;
        }
        return true;
    }
}
