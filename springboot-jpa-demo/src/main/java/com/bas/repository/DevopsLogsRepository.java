package com.bas.repository;

import com.bas.entity.DevopsLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DevopsLogsRepository extends JpaRepository<DevopsLogs, UUID> {
}
