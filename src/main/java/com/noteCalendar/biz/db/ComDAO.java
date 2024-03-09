package com.noteCalendar.biz.db;

import java.sql.*;
import java.util.ArrayList;
//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;




@Component
public class ComDAO {
      @Value("${spring.datasource.url}")
      private String url;
      @Value("${spring.datasource.username}")
      private String user;
      @Value("${spring.datasource.password}")
      private String password;
	

	
//	public String testLogin(Map<String, String> reqData) {
//		  Connection conn = null;
//	      CallableStatement callableStatement = null;
//	      String response = "";
//	      
//	      //select 쿼리를 위한 선언 
////	      Statement statement = null;
////	      ResultSet resultSet = null;
//	        
//	      
//	  	String reqId = reqData.get("user_id");
//		String reqPw = reqData.get("user_pw");
//		
//	      try {
//	            conn = DriverManager.getConnection(url, user, password);
//	            
//	            // 저장 프로시저 호출
//	            String sql = "{CALL test_schema.test_schema_check_user_id(?,?,?)}"; // 여러분의 저장 프로시저 이름으로 대체하세요
//	            
//	            callableStatement = conn.prepareCall(sql);
////	            
//	            callableStatement.setString(1, reqId);
//	            callableStatement.setString(2, reqPw);
//	            callableStatement.registerOutParameter(3, Types.INTEGER);
////	            // 저장 프로시저 실행
//	             callableStatement.execute();
//	             int userCount = callableStatement.getInt(3);
//	             response=  String.valueOf(userCount);
//	            
////	            String sql = "SELECT name FROM test_table"; // 여러분의 테이블 이름으로 대체하세요
////	            statement = conn.createStatement();
////	            resultSet = statement.executeQuery(sql);
////	            
////	            while (resultSet.next()) {
////	            	 System.out.println("resultSet" + ""+ resultSet.getString(1));
////	            }
//	          
//	          
//	            
//	        } catch (SQLException e) {
//	            e.printStackTrace();
//	        } finally {
//	            // 리소스 닫기
//	            try {
//	                if (callableStatement != null) {
//	                    callableStatement.close();
//	                }
//	                if (conn != null) {
//	                    conn.close();
//	                }
//	            } catch (SQLException e) {
//	                e.printStackTrace();
//	            }
//	        }
//		return response;	
//		
//	}
	
	public String runComnProcedureString(Map<String, String> reqMap) {
		  Connection conn = null;
	      CallableStatement callableStatement = null;

	      String response = "";
	      String jsonData = "";
	      String procedureName ="";
	    
	      if(reqMap.get("procedure_name") != null && reqMap.get("procedure_name") !="") {
	 	      procedureName = reqMap.get("procedure_name");
	 	      reqMap.remove("procedure_name");	      
	      }
	   
	      jsonData = convertToJson(reqMap);
	     

	      try {
	            conn = DriverManager.getConnection(url, user, password);
	            
	            // 수정1. 저장 프로시저 호출 이부분 sql문을 전달 받도록 수정 
//	            String sql = "{CALL test_schema.test_schema_check_user_email(?,?)}"; 
	            String sql = "{CALL "+procedureName+"(?,?)}";
	            System.out.println("sql" + " "+sql );
	            
	            callableStatement = conn.prepareCall(sql);
            
	            callableStatement.setString(1, jsonData);
	            // 수정2. 리턴받는 값 타입설정 가변적으로 
	            callableStatement.registerOutParameter(2, Types.VARCHAR);
//	            // 저장 프로시저 실행
	            callableStatement.execute();

                String tempVal = callableStatement.getString(2);
                response=  String.valueOf(tempVal);
	        
	           
	            
	          
	          
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            // 리소스 닫기
	            try {
	                if (callableStatement != null) {
	                    callableStatement.close();
	                }
	                if (conn != null) {
	                    conn.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
		return response;	
		
	}
	
	public Object runComnProcedureObj(Map<String, Object> reqMap) {
		  System.out.println("runComnProcedureObj"+reqMap );
		  Connection conn = null;
	      CallableStatement callableStatement = null;
	      ResultSet resultSet =null;
	      Object response = "";
	      String jsonData = "";
	      String procedureName ="";
	      Object jsonValue = "";
	      List<Object> jsonValues = new ArrayList<>();
	      JSONParser jsonParser = new JSONParser();
	     
	     
	      boolean hasResults = false;

	      System.out.println("procedure_name"+reqMap.get("procedure_name") );
	      if(reqMap.get("procedure_name") != null && reqMap.get("procedure_name") !="") {
	 	      procedureName = (String) reqMap.get("procedure_name");
	 	      reqMap.remove("procedure_name");	      
	      }
	   
	      jsonData = convertToJsonObj(reqMap);
	    		  
//	    		  convertToJsonObj(reqMap);
	     
	      System.out.println("jsonData" + " "+jsonData );
	      try {
	            conn = DriverManager.getConnection(url, user, password);
	            
	            // 수정1. 저장 프로시저 호출 이부분 sql문을 전달 받도록 수정 
//	            String sql = "{CALL test_schema.test_schema_check_user_email(?,?)}"; 
	            String sql = "{CALL "+procedureName+"(?)}";
	            System.out.println("sql" + " "+sql );
	            conn.setAutoCommit(false);
	            callableStatement = conn.prepareCall(sql);
          
	            callableStatement.setString(1, jsonData);
	            // 수정2. 리턴받는 값 타입설정 가변적으로 
//	            callableStatement.registerOutParameter(2, Types.ARRAY);
//	            // 저장 프로시저 실행
	            hasResults= callableStatement.execute();
	            conn.commit();
	       
	            if(hasResults) {
	            	resultSet = callableStatement.getResultSet();
	            	
	            while(resultSet.next()) {
	            	jsonValue = jsonParser.parse(resultSet.getString("json_object"));
//	            	jsonValues.add(jsonValue);
	            	
	            	jsonValues.add(jsonValue);
	            	 System.out.println("jsonValues"+jsonValues);
	            }
	            }
	        
	            System.out.println("jsonValues@@@"+jsonValues);
	            response=  jsonValues;
	            
	          
	          
	            
	        } catch (SQLException  | ParseException e) {
	        	try {
	        		 if (conn != null) {
	        	            conn.rollback(); // 롤백 수행
	        	        }
	        	} catch (SQLException rollbackException){
	        		   e.printStackTrace();
	        	}
	         
	        } finally {
	            // 리소스 닫기
	            try {
	                if (callableStatement != null) {
	                    callableStatement.close();
	                }
	                if (conn != null) {
	                    conn.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
		return response;	
		
	}
	
	public String convertToJson (Map<String, String> reqMap)  {
		
//		Map <String,String> reqData = new HashMap<>();
		String jsonData ="";
		JSONObject jsonObject = new JSONObject(reqMap);
//		ObjectMapper objectMapper = new ObjectMapper();
		 System.out.println("jsonObject"+jsonObject);
		 jsonData=jsonObject.toJSONString();
		
  	

        
		
		return jsonData;
	}
	public String convertToJsonObj (Map<String, Object> reqMap)  {
		
//		Map <String,String> reqData = new HashMap<>();
		String jsonData ="";
		JSONObject jsonObject = new JSONObject((Map) reqMap.get("reqData"));
//		ObjectMapper objectMapper = new ObjectMapper();
		 System.out.println("jsonObject"+jsonObject);
		 jsonData=jsonObject.toJSONString();
		
  	

        
		
		return jsonData;
	}

}
