package com.aykj.loglink.web;

import com.aykj.loglink.Constants;
import com.aykj.loglink.LogLinker;
import com.aykj.loglink.RequestIdGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * the upstream write log request id to header {RequestId}
 */
@Configuration
@WebFilter(urlPatterns = "/", filterName = "RequestIdFilter")
public class WebLogRequestIdFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        if (request instanceof HttpServletRequest) {
            String requestId = ((HttpServletRequest) request).getHeader(Constants.getRequestIdKey());
            if (StringUtils.isEmpty(requestId)) {
                // no requestId , generate one
                requestId = RequestIdGenerator.nextId();
            }
            LogLinker.setRequestId(requestId);
        }
        try {
            chain.doFilter(request, response);
        } catch (Exception ignore) {

        }
        LogLinker.clearRequestId();
    }


    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }
}
