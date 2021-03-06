package com.vinicius.santos.marvelapi.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vinicius.santos.marvelapi.model.response.DataResponse;
import com.vinicius.santos.marvelapi.model.response.ResponseError;
import com.vinicius.santos.marvelapi.model.response.ResponseSuccess;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Component
public class MyFilter extends OncePerRequestFilter {

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        ArrayList<String> routesToNotFilter = new ArrayList<>(Arrays.asList(
                "/swagger-ui.html",
                "/swagger-ui/index.html",
                "/api-docs",
                "/v2/api-docs"));
        return routesToNotFilter.contains(path);
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HtmlResponseWrapper responseWrapper = new HtmlResponseWrapper(response);
        chain.doFilter(request, responseWrapper);
        try {
            response.setContentType("application/json");
            int statusCode = response.getStatus();
            if (statusCode >= 200 && statusCode < 400) {
                ResponseSuccess responseSuccess = new ResponseSuccess();
                DataResponse dataResponse = new DataResponse();
                responseSuccess.setCode(statusCode);
                responseSuccess.setStatus("OK");
                responseSuccess.setCopyright("© 2021 MARVEL");
                responseSuccess.setAttributionText("Data provided by Marvel. © 2021 MARVEL");
                responseSuccess.setAttributionHTML("<a href=\\\"http://marvel.com\\\">Data provided by Marvel. © 2021 MARVEL</a>");
                responseSuccess.setEtag("55342c8b21941bfea4b795ff85633d9063e1da0e");
                responseSuccess.setData(dataResponse);
                if (!response.getContentType().isEmpty())
                    this.successResponse(response, responseWrapper, responseSuccess);
            } else {
                ResponseError responseError = new ResponseError();
                responseError.setCode(statusCode);
                this.errorResponse(response, responseWrapper, responseError);
            }

        } catch (Exception ex) {
            throw new IOException("FAIL_TO_INTERCEPT_RESPONSE", ex);
        }
    }

    @Override
    public void destroy() {
    }

    private void successResponse(ServletResponse response, HtmlResponseWrapper responseWrapper, ResponseSuccess responseSuccess) throws Exception {
        try {
            if (response.getContentType() != null) {
                Object responseContent = responseWrapper.getCaptureAsObject();
                responseSuccess.getData().getResult().add(responseContent);
            }
            byte[] bytesResponse = new ObjectMapper().writeValueAsBytes(responseSuccess);
            response.setContentLength(bytesResponse.length);
            response.getOutputStream().write(bytesResponse);
        } catch (Exception ex) {
            throw new Exception("FAIL_TO_INTERCEPT_RESPONSE", ex);
        }
    }

    private void errorResponse(ServletResponse response, HtmlResponseWrapper responseWrapper, ResponseError responseError) throws Exception {
        try {
            if (response.getContentType() != null) {
                Object responseContent = responseWrapper.getCaptureAsObject();
                responseError.setStatus(responseContent);
            }
            byte[] bytesResponse = new ObjectMapper().writeValueAsBytes(responseError);
            response.setContentLength(bytesResponse.length);
            response.getOutputStream().write(bytesResponse);
        } catch (Exception ex) {
            throw new Exception("FAIL_TO_INTERCEPT_RESPONSE", ex);
        }
    }
}
