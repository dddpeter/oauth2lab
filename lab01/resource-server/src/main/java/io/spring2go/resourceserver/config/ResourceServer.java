package io.spring2go.resourceserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

/** 资源服务配置
 * @author hqins**/
@Configuration
@EnableResourceServer
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServer extends ResourceServerConfigurerAdapter {
    @Resource
    private TokenStore tokenStore;
    @Value("${security.oauth2.client.client-id}")
    String clientId;
    @Value("${security.oauth2.client.client-secret}")
    String clientSecret;
    @Value("${security.oauth2.resource.token-info-uri}")
    String tokenInfoUri;

    @Bean
    RemoteTokenServices remoteTokenServices() {
        RemoteTokenServices services = new RemoteTokenServices();
        services.setCheckTokenEndpointUrl(tokenInfoUri);
        services.setClientId(clientId);
        services.setClientSecret(clientId);
        return services;
    }
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("project_api").stateless(false);
        resources.tokenStore(tokenStore).tokenServices(remoteTokenServices());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/api/**")
                .authenticated();

    }

}
