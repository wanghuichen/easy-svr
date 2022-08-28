package com.chen.entity;

import lombok.Data;

import java.util.Date;

/**
 * 用户实体类层
 * @author whc
 * @date 2022/8/24 21:43
 */
@Data
public class SysUserEntity {

    private Integer id;

    private String username;

    private String password;

    private String realName;

    private Integer gender;

    private String email;

    private String mobile;

    private Date createTime;

    private Date updateTime;
}
