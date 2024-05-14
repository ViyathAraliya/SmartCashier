package araliya.pointOfSales.security.jwt;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

    public void commence(HttpServletRequest request, HttpServletResponse response,AuthenticationException authenticationException) throws IOException{

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Error : Unauthorized");
    }
    
}
