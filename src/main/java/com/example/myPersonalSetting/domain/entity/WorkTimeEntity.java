package com.example.myPersonalSetting.domain.entity;

import com.example.myPersonalSetting.common.entity.CommonEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
public class WorkTimeEntity extends CommonEntity {

    private final String workDay;   //근무일
    @ManyToOne(fetch = FetchType.LAZY)//매일의 근무일은 유저에게 종속됨.
    private UserEntity user;


}
