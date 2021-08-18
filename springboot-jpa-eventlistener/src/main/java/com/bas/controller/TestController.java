package com.bas.controller;

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
public class TestController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @RequestMapping("/queryAll")
    @ResponseBody
    public List<UserInfo> queryAll11() {
        List<UserInfo> list = new ArrayList<UserInfo>();
        list = userInfoRepository.findAll();
        return list;
    }
}
