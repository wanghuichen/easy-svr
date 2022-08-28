package com.chen.controller;

import com.chen.common.ResultBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author whc
 * @date 2022/8/11 22:39
 */
@RestController
@CrossOrigin
@Api(tags = "apifox信息接口", description = "apiFox", value = "访问")
@RequestMapping("/apifox")
public class ApiFoxController {

    @ApiOperation("测试接口")
    @GetMapping("/test")
    public ResultBean<Map<String, Object>> test() {
        Map<String, Object> map = new HashMap<>();
        map.put("ApiFox", "YYDS!!!");
        return ResultBean.create(0, "success", map);
    }

}
