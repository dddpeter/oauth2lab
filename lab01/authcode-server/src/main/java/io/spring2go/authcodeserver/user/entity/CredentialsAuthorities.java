package io.spring2go.authcodeserver.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lijinde
 * @since 2022-05-14
 */
@TableName("credentials_authorities")
public class CredentialsAuthorities implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 凭证id
     */
    private Long credentialsId;

    /**
     * 权限id
     */
    private Long authoritiesId;

    public Long getCredentialsId() {
        return credentialsId;
    }

    public void setCredentialsId(Long credentialsId) {
        this.credentialsId = credentialsId;
    }
    public Long getAuthoritiesId() {
        return authoritiesId;
    }

    public void setAuthoritiesId(Long authoritiesId) {
        this.authoritiesId = authoritiesId;
    }

    @Override
    public String toString() {
        return "CredentialsAuthorities{" +
            "credentialsId=" + credentialsId +
            ", authoritiesId=" + authoritiesId +
        "}";
    }
}
