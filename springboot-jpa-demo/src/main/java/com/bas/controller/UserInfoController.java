package com.bas.controller;

import com.bas.entity.UserInfo;
import com.bas.repository.UserInfoRepository;
import com.secondary.entity.UserInfo2;
import com.secondary.repository.UserInfoRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserInfoRepository2 userInfoRepository2;

    @RequestMapping("/queryAll")
    @ResponseBody
    public List<UserInfo> queryAll() {
        List<UserInfo> list = new ArrayList<UserInfo>();
        list = userInfoRepository.findAll();

        System.out.println("=========1==========");
        UserInfo userInfo = new UserInfo();
        userInfo.setName("123");
//        userInfoRepository.save(userInfo);
        System.out.println("=========2==========");
//        userInfo = new UserInfo();
//        userInfo.setName(System.currentTimeMillis()+"");
//        userInfo.setId(UUID.fromString("ffb41e89-4a9c-4989-9dce-26622e733717"));
//        userInfoRepository.save(userInfo);
        System.out.println("=========3==========");
        userInfo = new UserInfo();
        userInfo.setName(System.currentTimeMillis()+"");
        userInfo.setId(UUID.fromString("ffb41e89-4a9c-4989-9dce-26622e733717"));
        Optional<UserInfo> userInfoOptional=userInfoRepository.findById(UUID.fromString("ffb41e89-4a9c-4989-9dce-26622e733717"));
        userInfo.setVersion(userInfoOptional.get().getVersion());
        userInfoRepository.save(userInfo);
        System.out.println("=========4==========");

        return list;
    }

    @RequestMapping("/queryAll2")
    @ResponseBody
    public List<UserInfo2> queryAll2() {
        List<UserInfo2> list = new ArrayList<>();
        list = userInfoRepository2.findAll();

        System.out.println("=========4==========" + list);

        return list;
    }
}
