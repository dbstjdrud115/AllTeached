package com.example.myPersonalSetting.domain.entity;

import com.example.myPersonalSetting.common.entity.CommonEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@SuperBuilder
@ToString(callSuper = true)
public class WorkTimeEntity extends CommonEntity {
    private final String workDay;   //근무일
    @ManyToOne(fetch = FetchType.LAZY)//매일의 근무일은 유저에게 종속됨.
    @JsonBackReference//Many쪽에 설정하면 순환참조 방지함
    private UserEntity userEntity;
}
