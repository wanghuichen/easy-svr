package com.chen.controller;

import com.chen.common.ResultBean;
import com.chen.dto.SysUserDTO;
import com.chen.entity.SysUserEntity;
import com.chen.service.SysUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.bytedeco.javacpp.opencv_core;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author whc
 * @date 2022/8/24 22:19
 */
@RestController
@CrossOrigin
@RequestMapping("/sys/user/")
@Api(tags = "用户管理")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("page")
    public ResultBean<PageInfo<SysUserDTO>> list(@RequestBody SysUserDTO param) {
        ResultBean<PageInfo<SysUserDTO>> res = sysUserService.page(param);
        return res;
    }

    @PostMapping("insert")
    public ResultBean<Integer> insert(@RequestBody SysUserDTO param) {
        ResultBean<Integer> res = sysUserService.insert(param);
        return res;
    }

    @PostMapping("import_excel")
    public ResultBean<Integer> import_excel(MultipartFile file) {
        ResultBean<Integer> res = sysUserService.importExcel(file);
        return res;
    }

    @PostMapping("batch_delete_user")
    public ResultBean<Integer> batch_delete_user(@RequestBody Map<String, Object> param) {
        ResultBean<Integer> res = sysUserService.delUserIds(param);
        return res;
    }

    @GetMapping("export_excel")
    public void export_excel(Map<String, Object> param, HttpServletResponse response) {
        sysUserService.exportExcel(param, response);
    }

}
