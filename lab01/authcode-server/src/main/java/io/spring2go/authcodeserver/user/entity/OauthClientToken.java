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
@TableName("oauth_client_token")
public class OauthClientToken implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tokenId;

    private Blob token;

    private String authenticationId;

    private String userName;

    private String clientId;

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
    public String getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    @Override
    public String toString() {
        return "OauthClientToken{" +
            "tokenId=" + tokenId +
            ", token=" + token +
            ", authenticationId=" + authenticationId +
            ", userName=" + userName +
            ", clientId=" + clientId +
        "}";
    }
}
