package com.mzhj19.eborrow.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mzhj19.eborrow.dto.ResponseErrorData;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    /*@Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }*/


    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // Set the content type of the response to application/json
        response.setContentType("application/json");

        // Set the HTTP status code to 401 (Unauthorized)
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Create an ErrorObject with the necessary information

        ResponseErrorData<String> responseErrorData = new ResponseErrorData<String>();
        responseErrorData.setStatus(false);
        responseErrorData.setStatusCode(HttpServletResponse.SC_UNAUTHORIZED);
        responseErrorData.setMessage("UNAUTHORIZED");

        // Convert the ErrorObject to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(responseErrorData);

        // Write the JSON response to the response body
        PrintWriter writer = response.getWriter();
        writer.println(jsonResponse);
        writer.flush();
    }
}
