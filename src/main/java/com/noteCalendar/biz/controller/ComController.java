package com.noteCalendar.biz.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.devtools.restart.RestartScope;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

//import java.util.HashMap;
import java.util.Map;

import com.noteCalendar.biz.service.ComService;
//@Controller
//@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ComController {
	@Autowired
	private ComService comService;

    @GetMapping("/test")
    public String test () {
        System.out.println("/test입성@@@@@@@@@@@@@@@@@@@@@@@");
        return "test.html";
    }

//    @PostMapping ("/login")
//    public String login(HttpServletRequest request) {
//        System.out.println("/login 입성@@@@"+request.toString());
////        System.out.println("/login 입성@@@@"+ request.getParameter("user_id").toString());
//        System.out.println("/login 입성@@@@"+ request.getParameterNames());
//        System.out.println("/login 입성@@@@"+request.getParameter("user_id") + " "+ request.getParameter("user_pw"));
//        return "login 컨트롤러";
//    }

    @PostMapping ("/login")
    public String login(HttpServletRequest request , @RequestBody Map<String, String> reqMap )  {
      
//        System.out.println("/login 입성@@@@"+request.getParameter("user_id") + " "+ request.getParameter("user_pw"));
        return comService.login(request,reqMap);
    }
    
    @PostMapping ("/com")
    public Object comProcess(HttpServletRequest request , @RequestBody Map<String, Object> reqMap )  {
    	for(Map.Entry<String, Object> entry : reqMap.entrySet()){
            System.out.println("key : " + entry.getKey());
            System.out.println("value : " + entry.getValue());
        }

        return comService.comService(request,reqMap);
    }


    @PostMapping ("/objectTest")
    public String objectTest(HttpServletRequest request , @RequestBody Map<String, Object> reqMap )  {
        System.out.println("/objectTest 입성@@@@"+reqMap);
//        System.out.println("/login 입성@@@@"+ request.getParameter("user_id").toString());
//        System.out.println("/login 입성@@@@"+ request.getParameterNames());
        for(Map.Entry<String, Object> entry : reqMap.entrySet()){
            System.out.println("key : " + entry.getKey());
            System.out.println("value : " + entry.getValue());
        }
//        System.out.println("/login 입성@@@@"+request.getParameter("user_id") + " "+ request.getParameter("user_pw"));
        return "objectTest 컨트롤러";
    }
}
