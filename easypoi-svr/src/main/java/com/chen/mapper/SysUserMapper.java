package com.chen.mapper;

import com.chen.dto.SysUserDTO;
import com.chen.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author whc
 * @date 2022/8/26 8:49
 */
@Mapper
public interface SysUserMapper {

    List<SysUserDTO> list(SysUserDTO param);

    Integer insert(SysUserDTO param);

    Integer batchInsert(@Param("list") List<SysUserDTO> param);

    Integer delUserIds(@Param("list") List<String> delUserIds);
}
