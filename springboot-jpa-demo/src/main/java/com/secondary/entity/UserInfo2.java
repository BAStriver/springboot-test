package com.secondary.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name="userinfo", schema = "public2")
public class UserInfo2 {
    @Id
    @GeneratedValue
    @Column(name="id")
    private UUID id;

    @Column(name="name")
    private String name;

    @Column(name="gender")
    private String gender;

    @Column(name="age")
    private int age;

    @Column
    @Version
    private int version;
}
