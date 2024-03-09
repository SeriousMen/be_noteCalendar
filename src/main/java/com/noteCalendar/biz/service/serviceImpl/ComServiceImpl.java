package com.noteCalendar.biz.service.serviceImpl;

import com.noteCalendar.biz.db.ComDAO;
import com.noteCalendar.biz.service.ComService;

import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ComServiceImpl implements ComService {
	
	@Autowired
	private ComDAO comDao;
    @Override
    public String login(HttpServletRequest request,Map<String, String> reqMap){
    
    	  System.out.println("/login 입성@@@@"+reqMap);
          System.out.println("/login 입성@@@@"+reqMap.get("user_id"));

          
     
         
        return comDao.runComnProcedureString(reqMap);
    }
	@Override
	public Object comService(HttpServletRequest request, Map<String, Object> reqMap) {
		// TODO Auto-generated method stub
		
		   System.out.println("/comService 입성@@@@"+reqMap);
		   
	
		return comDao.runComnProcedureObj(reqMap);
	};

	@Override
	public Object enPwd(Map<String,Object> reqMap) {
	String reqPw ="";
		if(reqMap != null) {
			  for(Map.Entry<String, Object> entry : reqMap.entrySet()){
		            System.out.println("key : " + entry.getKey());
		            System.out.println("value : " + entry.getValue());
		        }
		}
		System.out.print("reqData!!!"+reqPw);
		return reqMap;
	}
}
