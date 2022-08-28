package com.chen.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据传输类
 * @author whc
 * @date 2022/8/24 21:45
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysUserDTO implements Serializable {

    private Integer id;

    private String username;

    private String password;

    private String realName;

    private Integer gender;

    private String email;

    private String mobile;

    private Date createTime;

    /**
     * 冗余字段
     */
    private Integer pageNum;

    private Integer pageSize;
}
