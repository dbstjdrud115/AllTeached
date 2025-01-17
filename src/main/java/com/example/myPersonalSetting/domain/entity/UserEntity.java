package com.example.myPersonalSetting.domain.entity;

import com.example.myPersonalSetting.common.entity.CommonEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@SuperBuilder
@ToString(callSuper = true)
public class UserEntity extends CommonEntity {
    private final String userName;
    
    @JsonIgnore//비번은 비표시
    private final String password;

    //ManyToOne의 user와 매핑. CascadeType.ALL = 부모(UserEntity)의 변화에 연관테이블들 자동적용.
    //예를들어 List의 일부 삭제할 경우, WorkTimeEntity의 관련정보도 전체 삭제.
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WorkTimeEntity> workTimesEntity;
}
