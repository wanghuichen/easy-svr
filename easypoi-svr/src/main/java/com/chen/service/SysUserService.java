package com.chen.service;

import com.chen.common.ResultBean;
import com.chen.dto.SysUserDTO;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author whc
 * @date 2022/8/24 21:58
 */
public interface SysUserService {

    ResultBean<PageInfo<SysUserDTO>> page(SysUserDTO param);

    ResultBean<Integer> insert(SysUserDTO param);

    ResultBean<Integer> importExcel(MultipartFile file);

    ResultBean<Integer> delUserIds(Map<String, Object> param);

    void exportExcel(Map<String, Object> param, HttpServletResponse response);
}
