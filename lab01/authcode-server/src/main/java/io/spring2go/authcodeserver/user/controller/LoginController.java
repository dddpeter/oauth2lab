package io.spring2go.authcodeserver.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

/**
 * @author lijinde
 * @create 2022/5/14 16:55
 **/
@Controller
public class LoginController {
    @Resource
    JdbcClientDetailsService jdbcClientDetailsService;

    @Resource
    private TokenStore tokenStore;

    @Resource
    private ApprovalStore approvalStore;

    @RequestMapping("/")
    public ModelAndView root(Map<String,Object> model, Principal principal){
        List<Approval> approvals=jdbcClientDetailsService.listClientDetails().stream()
                .map(clientDetails -> approvalStore.getApprovals(principal.getName(),clientDetails.getClientId()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());


        model.put("approvals",approvals);
        model.put("clientDetails",jdbcClientDetailsService.listClientDetails());
        return new ModelAndView ("index",model);

    }



    @RequestMapping(value="/approval/revoke",method= RequestMethod.POST)
    public String revokApproval(@ModelAttribute Approval approval){

        approvalStore.revokeApprovals(asList(approval));
        tokenStore.findTokensByClientIdAndUserName(approval.getClientId(),approval.getUserId())
                .forEach(tokenStore::removeAccessToken) ;
        return "redirect:/";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }



    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value="/user/logout", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity logout(String accessToken,String clientId){
        tokenStore.findTokensByClientId(clientId).forEach(token -> {
            if(token.getValue().equals(accessToken)){
                try {
                    removeApprovals(accessToken,clientId);
                } catch (NoSuchFieldException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                tokenStore.removeAccessToken(token);
            }
        });


        return ResponseEntity.ok("退出成功");
    }

    private void removeApprovals(String accessToken,String clientId) throws NoSuchFieldException, IllegalAccessException {
        OAuth2Authentication oAuth2Authentication = tokenStore.readAuthentication(accessToken);
        User user = (User) oAuth2Authentication.getPrincipal();
        approvalStore.revokeApprovals(approvalStore.getApprovals(user.getUsername(),clientId));

    }

}

