package com.example.auth.config;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A utility class for managing custom session mechanisms using cookies.
 * This class provides methods to create, retrieve, and expire sessions,
 * enabling basic session management independent of the servlet container.
 */
@Component
public class SessionManager {

    public static final String SESSION_COOKIE_NAME = "spring-react-auth";
    private final Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    /**
     * Creates a new session and associates it with a unique session ID.
     * The session ID is stored as a cookie in the HTTP response.
     *
     * @param value    the object to store in the session (e.g., user information)
     * @param response the HTTP response to which the session cookie will be added
     */
    public void createSession(Object value, HttpServletResponse response) {
        // Generate a unique session ID and store the session data.
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

        // Create a session cookie with the generated session ID.
        Cookie sessionCookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
        sessionCookie.setHttpOnly(true); // Enhance security by restricting client-side access
//        sessionCookie.setPath("/");      // Make the cookie accessible across the application
        response.addCookie(sessionCookie);
    }

    /**
     * Retrieves the session object associated with the session ID
     * stored in the request's cookies.
     *
     * @param request the HTTP request containing the session cookie
     * @return the session object, or {@code null} if no session exists
     */
    public Object getSession(HttpServletRequest request) {
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        if (sessionCookie == null) {
            return null; // No session cookie found
        }
        return sessionStore.get(sessionCookie.getValue());
    }

    /**
     * Invalidates the session associated with the session ID stored
     * in the request's cookies, effectively logging out the user.
     *
     * @param request the HTTP request containing the session cookie
     * @return if cookie is valid, return true else return false
     */
    public boolean expire(HttpServletRequest request) {
        Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
        System.out.println("sessionCookie = " + sessionCookie.getName());

        if (sessionCookie != null) {
            sessionStore.remove(sessionCookie.getValue());
            return true;
        }
        return false;
    }

    /**
     * Finds a specific cookie in the HTTP request by its name.
     *
     * @param request    the HTTP request containing the cookies
     * @param cookieName the name of the cookie to search for
     * @return the matching cookie, or {@code null} if not found
     */
    public Cookie findCookie(HttpServletRequest request, String cookieName) {
        if (request.getCookies() == null) {
            return null; // No cookies present in the request
        }
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findFirst()
                .orElse(null);
    }
}
