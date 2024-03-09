package com.noteCalendar.biz.security;
import java.security.*;
public class PasswordEncoder {
	
	private final String en_pwd;
	
	public static void main(String[] arg) {
		
		PasswordEncoder pwe = new PasswordEncoder("Asdf99915!");;
		System.out.print("결과"+pwe.getEnPwd());
	}
	
	public PasswordEncoder (String pwd){
		System.out.println("PasswordEncoder입성"+pwd);
		String salt = getSalt();
		System.out.println("salt생성"+salt);
		
		 en_pwd = getEncrypt(pwd, salt);		
		
	}

	public String getSalt() {
		
		//1.Random, byte 객체 생성
		SecureRandom r = new SecureRandom ();
		byte[] salt = new byte[20];
		
		//2. 난수 생성
		r.nextBytes(salt);

		//3. byte to String(10진수의 문자열로 변경)
		StringBuffer sb = new StringBuffer();
		for(byte b: salt) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}
	
	public String getEncrypt(String pwd, String salt) {
		String result ="";
		
		MessageDigest md;
		try {
			//1 SHA256 알고리즘 객체 생성 
			md = MessageDigest.getInstance("SHA-256");
			
			//2.pwd와 salt 합친 문자열에 SHA 256 적용
			System.out.println("PWD + SALT 적용 전 "+ pwd+salt);
			md.update((pwd+salt).getBytes());
			byte[] pwdsalt = md.digest();
			
			//3. byte to String(10진수의 문자열로 전환)
			StringBuffer sb = new StringBuffer();
			
			for (byte b : pwdsalt) {
				sb.append(String.format("%02x", b));
			}
			
			result = sb.toString();
			System.out.println("PWD + SALT 적용 "+ result);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return result;
	}
	
	
	public String getEnPwd() {
		return en_pwd;
	}
}
