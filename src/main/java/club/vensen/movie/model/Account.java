package club.vensen.movie.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author VENSEN
 * @Classname Account
 * @Description 管理员账户表
 * @Date 2020/1/19 14:44
 */
@Data
public class Account implements Serializable {

    /**
     * 主键id，无意义
     */
    private String id;

    /**
     * 账户名
     */
    private String account;

    /**
     * 账户密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 电话
     */
    private String tel;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 头像地址
     */
    private String headPic;

    /**
     * 是否锁定，1锁定
     */
    private Boolean looked;

    /**
     * 上一次登录时间
     */
    private Date lastLoginAt;

    /**
     * 逻辑删除，1删除
     */
    private Boolean del;

    /**
     * 状态，1正常
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 跟新时间
     */
    private Date updateAt;

    /**
     * 重复密码，注册时验证
     */
    private String rePassword;

    private static final long serialVersionUID = 1L;

}