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
public class Credentials implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 凭证id
     */
    private Long id;

    /**
     * 是否可用
     */
    private Boolean enabled;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 版本号
     */
    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Credentials{" +
            "id=" + id +
            ", enabled=" + enabled +
            ", name=" + name +
            ", password=" + password +
            ", version=" + version +
        "}";
    }
}
