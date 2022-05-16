package io.spring2go.authcodeserver.user.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lijinde
 * @since 2022-05-14
 */
public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限id
     */
    private Long id;

    /**
     * 权限
     */
    private String authority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Authority{" +
            "id=" + id +
            ", authority=" + authority +
        "}";
    }
}
