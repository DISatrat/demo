
package com.example.demo.Config;

import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionIdResolver;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

public class CustomHttpSessionIdResolver implements HttpSessionIdResolver {

    private final DefaultCookieSerializer cookieSerializer;

    public CustomHttpSessionIdResolver() {
        this.cookieSerializer = new DefaultCookieSerializer();
        this.cookieSerializer.setUseSecureCookie(true); // Опционально, для использования безопасного cookie
        this.cookieSerializer.setSameSite("None"); // Опционально, для использования SameSite=None cookie
    }

    @Override
    public List<String> resolveSessionIds(HttpServletRequest request) {
        String jSessionId = extractJSessionId(request);
        return (jSessionId != null) ? Collections.singletonList(jSessionId) : Collections.emptyList();
    }

    @Override
    public void setSessionId(HttpServletRequest request, HttpServletResponse response, String sessionId) {
        this.cookieSerializer.writeCookieValue(new CookieSerializer.CookieValue(request, response, sessionId));
    }

    @Override
    public void expireSession(HttpServletRequest request, HttpServletResponse response) {
        this.cookieSerializer.writeCookieValue(new CookieSerializer.CookieValue(request, response, ""));
    }

    private String extractJSessionId(HttpServletRequest request) {
        return request.getRequestedSessionId(); // Извлекает текущий JSESSIONID
    }
}