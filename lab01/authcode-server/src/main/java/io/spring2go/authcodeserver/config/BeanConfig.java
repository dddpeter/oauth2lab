package io.spring2go.authcodeserver.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.spring2go.authcodeserver.user.mapper.AuthorityMapper;
import io.spring2go.authcodeserver.user.entity.Authority;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lijinde
 * @create 2022/5/14 15:23
 **/
@Configuration
public class BeanConfig {
    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Resource
    AuthorityMapper authorityMapper;
    @Bean
    public Map<Long,String> authorityMap(){
        Map<Long,String>  authorityMap = new HashMap<>();
        QueryWrapper<Authority>  authorityQueryWrapper= new QueryWrapper<>();
        authorityQueryWrapper.eq("1","1");
        authorityMapper.selectList(authorityQueryWrapper).stream().forEach(authority -> {
            authorityMap.put(authority.getId(), authority.getAuthority());
        });
        return  authorityMap;
    }

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
