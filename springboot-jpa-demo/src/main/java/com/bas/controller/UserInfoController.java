package com.bas.controller;

import com.bas.config.CustomProperties;
import com.bas.entity.UserInfo;
import com.bas.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private CustomProperties customProperties;

    @RequestMapping("/test")
    @ResponseBody
    public List<UserInfo> queryAll() {
        List<UserInfo> list = new ArrayList<UserInfo>();

        System.out.println("customProperties: " + customProperties);

        return list;
    }

}
