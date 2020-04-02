package com.jerusalem.system.admin.entity;

        import lombok.Data;
        import lombok.ToString;
        import javax.persistence.Entity;
        import javax.persistence.GeneratedValue;
        import javax.persistence.Id;
        import javax.persistence.Table;

/**
 * <p>
 *
 * </p>
 *
 * @author jerusalem
 * @since 2019-12-24
 */

@Entity
@Data
@ToString
@Table(name = "admin")
public class Admin{

    /**
     * 管理员ID
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 管理员用户名
     */
    private String username;

    /**
     * 管理员登录密码
     */
    private String password;

    /**
     * 管理员头像
     */
    private String avatar;

    /**
     * 管理员简介
     */
    private String statement;

    public Admin() {
    }
}
