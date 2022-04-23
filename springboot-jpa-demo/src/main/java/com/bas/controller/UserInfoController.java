package com.bas.controller;

import com.bas.entity.UserInfo;
import com.bas.model.UserInfoDTO;
import com.bas.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserInfoRepository userInfoRepository;

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

    @PostMapping("/add")
    @ResponseBody
    public boolean add(@RequestBody @Validated UserInfoDTO user) {
        System.out.println("=========1==========");
        List<UserInfo> userInfoOptionalList = userInfoRepository.findByName(user.getName());
        userInfoOptionalList.forEach(userInfoOptional -> {
            UserInfo userInfo = new UserInfo();
            userInfo.setName(userInfoOptional.getName());
            userInfo.setId(userInfoOptional.getId());
            userInfo.setGender(user.getGender());
            userInfo.setAge(user.getAge());
            userInfo.setVersion(userInfoOptional.getVersion());
            System.out.println("userInfo1: "+userInfo);
            userInfoRepository.save(userInfo);
            System.out.println("=========4==========");
        });

        if(userInfoOptionalList.isEmpty()) {
            UserInfo userInfo = new UserInfo();
            userInfo.setName(user.getName());
            userInfo.setGender(user.getGender());
            userInfo.setAge(user.getAge());
            System.out.println("userInfo2: "+userInfo);
            userInfoRepository.save(userInfo);
        }

        return true;
    }
}
