package io.spring2go.authcodeserver.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author lijinde
 * @since 2022-05-14
 */
@TableName("oauth_approvals")
public class OauthApprovals implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userId;

    private String clientId;

    private String scope;

    private String status;

    private LocalDateTime expiresAt;

    private LocalDateTime lastModifiedAt;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(LocalDateTime lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    @Override
    public String toString() {
        return "OauthApprovals{" +
            "userId=" + userId +
            ", clientId=" + clientId +
            ", scope=" + scope +
            ", status=" + status +
            ", expiresAt=" + expiresAt +
            ", lastModifiedAt=" + lastModifiedAt +
        "}";
    }
}
