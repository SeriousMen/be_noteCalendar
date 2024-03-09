package com.noteCalendar.biz.service;


import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

public interface ComService {

    public String login(HttpServletRequest request,Map<String, String> reqMap);
    public Object comService(HttpServletRequest request,Map<String, Object> reqMap);
    public Object enPwd(Map<String,Object> reqMap);
}
