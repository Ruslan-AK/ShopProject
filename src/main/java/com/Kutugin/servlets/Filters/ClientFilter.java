package com.Kutugin.servlets.Filters;

import com.Kutugin.exceptions.BusinessException;
import com.Kutugin.validators.ValidationService;
import com.Kutugin.validators.impl.ValidationServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter(urlPatterns = "/clients")
public class ClientFilter implements Filter {

    private ValidationService validationService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        validationService = new ValidationServiceImpl();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String age = servletRequest.getParameter("age");
        try{
            validationService.validateAge(age);
        } catch (NumberFormatException | BusinessException e){
            PrintWriter writer  = servletResponse.getWriter();
            writer.println("<h2> WRONG AGE </h2>");
            return;
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
