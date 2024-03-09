package com.noteCalendar.biz.filter;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
@Component
public class CORSFilter {
    //추후 Spring Security 사용을 위해 이렇게 구성

    @Bean
    public CorsFilter corsFilter() {
        System.out.println("corsFilter입성!");
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        /* setAllowCredentials
         * 내 서버가 응답할 때 json을 자바스크립에서 처리할 수 있게 할지를 설정 설정,
         *  TRUE로 할 경우 서버는 응답에 자격 증명을 포함시켜 사용자 인증 정보를 제공한다.
         *  주로 로그인된 상태를 유지해야 하는 상황에서 사용된다.
         */
        config.setAllowCredentials(true); 
        config.addAllowedOriginPattern("*"); // 모든 출처에서의 요청을 허용합니다. 실제 운영 환경에서는 특정 출처로 제한하는 것이 좋다.
        config.addAllowedHeader("*"); // 모든 header에 응답을 허용
        config.addAllowedMethod("OPTIONS"); // 00 메소드 요청을 허용
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config); // 해당 url들은 이 config를 수행한다.
        return new CorsFilter(source);
    }

}