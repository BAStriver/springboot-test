package com.bas.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name="devops_logs")
@Builder
public class DevopsLogs {

    @Id
    @GeneratedValue
    @Column(name="id")
    private UUID id;

    @Column(name="deploy_id")
    private String deployId;
    @Column(name="start_date")
    private Date startDate;
    @Column(name="end_date")
    private Date endDate;
    @Column(name="status")
    private String status;
    @Column(name="service")
    private String service;
    @Column(name="team")
    private String team;
    @Column(name="rollback")
    private String rollback;

}
