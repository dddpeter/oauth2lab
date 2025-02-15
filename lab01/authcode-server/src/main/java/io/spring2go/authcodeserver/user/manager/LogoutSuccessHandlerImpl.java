package io.spring2go.authcodeserver.user.manager;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lijinde
 * @create 2022/5/14 15:59
 * 登出清空accessToken
 **/

@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    private static String BEARER_AUTHENTICATION = "Bearer";
    private static String HEADER_AUTHENTICATION = "authorization";
    @Resource
    private TokenStore tokenStore;
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String auth = httpServletRequest.getHeader(HEADER_AUTHENTICATION);
        String token = httpServletRequest.getParameter("access_token");
        if (auth != null && auth.startsWith(BEARER_AUTHENTICATION)) {
            token = token.split(" ")[0];
        }
        if (token != null) {
            OAuth2AccessToken accessToken = tokenStore.readAccessToken(token);
            if (accessToken != null) {
                tokenStore.removeAccessToken(accessToken);
            }
        }
    }
}
