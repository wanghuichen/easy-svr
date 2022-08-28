package com.chen.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.chen.common.ResultBean;
import com.chen.dto.SysUserDTO;
import com.chen.entity.SysUserEntity;
import com.chen.excel.SysUserExcel;
import com.chen.mapper.SysUserMapper;
import com.chen.service.SysUserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
*@author whc
*@date 2022/8/26 8:52
 */
@Slf4j
@Service
public class SysUserServiceImpl  implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public ResultBean<PageInfo<SysUserDTO>> page(SysUserDTO param) {
        try {
            PageHelper.startPage(param.getPageNum(), param.getPageSize());
            List<SysUserDTO> sysUserDTOList = sysUserMapper.list(param);

            PageInfo<SysUserDTO> pageInfo = new PageInfo<>(sysUserDTOList);

            return ResultBean.create(0, "success", pageInfo);
        } catch (Exception e) {
            log.error("查询用户集合出错！ e ==> {}", e);
        }
        return null;
    }

    @Override
    public ResultBean<Integer> insert(SysUserDTO param) {
        try {
            sysUserMapper.insert(param);
            return ResultBean.create(0, "success");
        } catch (Exception e) {
            log.error("新增用户失败！ e ==> {}", e);
            return ResultBean.create(10, "新增用户失败！");
        }
    }

    @Override
    public ResultBean<Integer> importExcel(MultipartFile file) {
        ImportParams importParams = new ImportParams();
        //标题行设置为1行，默认是0，可以不设置；依实际情况设置。
        importParams.setTitleRows(1);
        // 表头设置为1行
        importParams.setHeadRows(1);
        try {
            //读取excel
            List<SysUserExcel> sysUserExcelList = ExcelImportUtil.importExcel(file.getInputStream(), SysUserExcel.class, importParams);

            batchInsert(sysUserExcelList);
            return ResultBean.create(0, "success");
        } catch (Exception e) {
            log.error("导入 Excel 异常！ e ==> {}", e);
            return ResultBean.create(10, "导入excel 异常！"+e.getMessage());
        }
    }

    @Override
    public ResultBean<Integer> delUserIds(Map<String, Object> param) {
        try {
            List<String> userIds = (List<String>) param.get("delUserIds");
            sysUserMapper.delUserIds(userIds);
            return ResultBean.create(0, "success");
        } catch (Exception e) {
            log.error("删除用户失败！ e ==> {}", e);
            return ResultBean.create(10, "删除用户失败！ e ==> {}"+e.getMessage());
        }
    }

    @Override
    public void exportExcel(Map<String, Object> param, HttpServletResponse response) {
        try {
            List<SysUserDTO> res = sysUserMapper.list(null);
            List<SysUserExcel> sysUserExcelList = BeanUtil.copyToList(res, SysUserExcel.class);
            //设置信息头，告诉浏览器内容为excel类型
            response.setHeader("content-Type", "application/vnd.ms-excel");
            //设置下载名称
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("用户列表.xls", StandardCharsets.UTF_8.name()));

            //字节流输出
            ServletOutputStream out = response.getOutputStream();
            //设置excel参数
            ExportParams params = new ExportParams();
            //设置sheet名名称
            params.setSheetName("用户列表");
            //设置标题
            params.setTitle("用户信息表");

            Workbook workbook = ExcelExportUtil.exportExcel(params, SysUserExcel.class, sysUserExcelList);
            workbook.write(out);
        } catch (Exception e) {
            log.error("导出失败！ e ==> {}", e);
        }
    }

    public void batchInsert(List<SysUserExcel> param) throws Exception{
        //转换为dto集合
        List<SysUserDTO> sysUserDTOList = BeanUtil.copyToList(param, SysUserDTO.class);

        //转换集合
        List<SysUserDTO> userDTOList = new ArrayList<>();
        for (SysUserDTO sysUserDTO : sysUserDTOList) {
            if (!StringUtils.isEmpty(sysUserDTO.getUsername())) {
                sysUserDTO.setPassword("123");
                userDTOList.add(sysUserDTO);
            }
        }
        //批量插入数据库
        sysUserMapper.batchInsert(userDTOList);

    }

}
