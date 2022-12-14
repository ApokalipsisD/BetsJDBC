package com.bets.controller.command;

import com.bets.controller.command.api.RequestContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.Optional;

public class RequestContextImpl implements RequestContext {
    private static final String REFERER = "referer";
    private final HttpServletRequest request;

    public RequestContextImpl(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void addAttributeToJsp(String name, Object attribute) {
        request.setAttribute(name, attribute);
    }

    @Override
    public Optional<HttpSession> getCurrentSession() {
        return Optional.ofNullable(request.getSession());
    }

    @Override
    public String getParameterByName(String paramName) {
        return request.getParameter(paramName);
    }

    @Override
    public void invalidateCurrentSession() {
        final HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    @Override
    public HttpSession createSession() {
        return request.getSession(true);
    }

    @Override
    public String getContextPath() {
        return request.getContextPath();
    }

    @Override
    public String getHeader() {
        return request.getHeader(REFERER);
    }
}
