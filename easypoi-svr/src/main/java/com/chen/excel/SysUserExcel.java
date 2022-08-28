package com.chen.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * Excel 类，主要用于映射excel表
 * @author whc
 * @date 2022/8/25 8:52
 */
@Data
public class SysUserExcel {

    @Excel(name = "序号", orderNum = "0", format = "isAddIndex")
    private Integer index = 1;

    @Excel(name = "用户账号")
    private String username;

    @Excel(name = "真实姓名")
    private String realName;

    @Excel(name = "性别", replace = {"男_1", "女_2"})
    private Integer gender;

    @Excel(name = "电子邮箱", width = 25)
    private String email;

    @Excel(name = "手机号码")
    private String mobile;

    @Excel(name = "创建时间", format = "yyyy-MM-dd", width = 15)
    private Date createTime;
}
