package com.etoak.zhxy.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
public class ETlistener implements HttpSessionListener {
    private static final Map<String,HttpSession> sessionMap = new ConcurrentHashMap<>();
    @Override
    public void sessionCreated(HttpSessionEvent hse) {
        //session 创建，执行该方法，保存session
        HttpSession session = hse.getSession();
        sessionMap.put(session.getId(),session);

    }
public static HttpSession getSessionById(String sessionid){
        return sessionMap.get(sessionid);
}


    @Override
    public void sessionDestroyed(HttpSessionEvent hse) {
        String sessionid = hse.getSession().getId();
        sessionMap.remove(sessionid);
    }
}
