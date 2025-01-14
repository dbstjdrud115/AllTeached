package com.example.myPersonalSetting.domain.repository;

import com.example.myPersonalSetting.domain.entity.WorkTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkTimeRepository extends JpaRepository<WorkTimeEntity, Long> {
}
