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
@TableName("oauth_code")
public class OauthCode implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;

    private Blob authentication;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public Blob getAuthentication() {
        return authentication;
    }

    public void setAuthentication(Blob authentication) {
        this.authentication = authentication;
    }

    @Override
    public String toString() {
        return "OauthCode{" +
            "code=" + code +
            ", authentication=" + authentication +
        "}";
    }
}
