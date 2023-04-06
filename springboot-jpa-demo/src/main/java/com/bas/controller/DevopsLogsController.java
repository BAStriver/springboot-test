package com.bas.controller;

import com.bas.entity.UserInfo;
import com.bas.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/devops")
public class DevopsLogsController {
    @Autowired
    private GeneratorService generatorService;

    @RequestMapping("/generate")
    @ResponseBody
    public List<UserInfo> generate() {
        generatorService.generateData();
        return null;
    }

}
