package io.spring2go.authcodeserver.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.sql.Blob;

/**
 * <p>
 * 
 * </p>
 *
 * @author lijinde
 * @since 2022-05-14
 */
@TableName("oauth_refresh_token")
public class OauthRefreshToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tokenId;

    private Blob token;

    private Blob authentication;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
    public Blob getToken() {
        return token;
    }

    public void setToken(Blob token) {
        this.token = token;
    }
    public Blob getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Blob authentication) {
        this.authentication = authentication;
    }

    @Override
    public String toString() {
        return "OauthRefreshToken{" +
            "tokenId=" + tokenId +
            ", token=" + token +
            ", authentication=" + authentication +
        "}";
    }
}
