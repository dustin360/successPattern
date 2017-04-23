package us.freeenergy.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.WebUtils;

public class CookieUtil {
	public static void create(HttpServletResponse httpServletResponse, String cookieName, String token, Boolean secure, Integer maxAge, String domain)
	{
		Cookie cookie = new Cookie(cookieName, token);
		cookie.setSecure(secure);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(maxAge);
		cookie.setDomain(domain);
		cookie.setPath("/");
		httpServletResponse.addCookie(cookie);
	}
	
	public static void clear(HttpServletResponse httpServletResponse, String cookieNmae)
	{
		Cookie cookie = new Cookie(cookieNmae, null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
	}
	
	public static String getValue(HttpServletRequest httpServletRequest, String cookieName) 
	{
        Cookie cookie = WebUtils.getCookie(httpServletRequest, cookieName);
        return cookie != null ? cookie.getValue() : null;
    }
	
//	cookie.setSecure(secure): secure=true => work on HTTPS only.
//	cookie.setHttpOnly(true): invisible to JavaScript.
//	cookie.setMaxAge(maxAge): maxAge=0: expire cookie now, maxAge<0: expire cookiie on browser exit.
//	cookie.setDomain(domain): visible to domain only.
//	cookie.setPath("/"): visible to all paths.
}
