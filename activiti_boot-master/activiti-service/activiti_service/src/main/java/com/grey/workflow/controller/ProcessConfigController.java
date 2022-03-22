package com.grey.workflow.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.grey.workflow.entities.ProcessConfig;
import com.grey.workflow.service.IProcessConfigService;
import com.grey.workflow.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("流程配置控制层")
@RestController
@RequestMapping("/processConfig")
public class ProcessConfigController {

    @Autowired
    IProcessConfigService processConfigService;

    @ApiOperation("根据流程定义key查询流程配置")
    @GetMapping("{processKey}")
    public Result view(@PathVariable String processKey) {
        ProcessConfig processConfig = processConfigService.getByProcessKey(processKey);
        return Result.ok(processConfig);
    }

    @ApiOperation("新增或更新流程配置")
    @PutMapping
    public Result saveOrUpdate(@RequestBody ProcessConfig processConfig) {
        boolean b = processConfigService.saveOrUpdate(processConfig);
        if(b) {
            return Result.ok();
        }else {
            return Result.error("操作失败");
        }
    }

}
