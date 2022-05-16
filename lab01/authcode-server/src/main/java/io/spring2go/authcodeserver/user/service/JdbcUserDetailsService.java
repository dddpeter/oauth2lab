package io.spring2go.authcodeserver.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.spring2go.authcodeserver.user.mapper.CredentialsAuthoritiesMapper;
import io.spring2go.authcodeserver.user.mapper.CredentialsMapper;
import io.spring2go.authcodeserver.user.entity.Credentials;
import io.spring2go.authcodeserver.user.entity.CredentialsAuthorities;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lijinde
 * @create 2022/5/14 14:55
 **/
@Service("jdbcUserDetailsService")
public class JdbcUserDetailsService implements UserDetailsService {

    @Resource
    CredentialsMapper credentialsMapper;
    @Resource
    CredentialsAuthoritiesMapper credentialsAuthoritiesMapper;
    @Resource
    Map<Long,String> authorityMap;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        QueryWrapper<Credentials> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("name",username);
        Credentials credentials = credentialsMapper.selectOne(queryWrapper);
        if (credentials == null) {
            throw new UsernameNotFoundException("User '" + username + "' can not be found");
        }

        QueryWrapper<CredentialsAuthorities> queryAuthWrapper= new QueryWrapper<>();
        queryAuthWrapper.eq("credentials_id",credentials.getId());
        List<CredentialsAuthorities> authoritiesList= credentialsAuthoritiesMapper.selectList(queryAuthWrapper);
        Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        authoritiesList.stream().forEach(auth->{
            grantedAuthoritySet.add(new SimpleGrantedAuthority(authorityMap.get(auth.getAuthoritiesId())));
        });
        return new User(credentials.getName(), credentials.getPassword(), credentials.getEnabled(),
                true, true, true, grantedAuthoritySet);
    }

}
