package com.secondary.repository;

import com.secondary.entity.UserInfo2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserInfoRepository2 extends JpaRepository<UserInfo2, UUID> {

}