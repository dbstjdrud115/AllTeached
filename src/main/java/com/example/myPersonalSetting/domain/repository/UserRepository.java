package com.example.myPersonalSetting.domain.repository;

import com.example.myPersonalSetting.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    //JPQL 사용
    @Query("SELECT u FROM UserEntity u WHERE u.id = :id")
    Optional<UserEntity> findByIdCustom(@Param("id") Long id);
    @Query("SELECT u FROM UserEntity u LEFT JOIN FETCH u.workTimesEntity WHERE u.id = :id")
    Optional<UserEntity> findByIdWithWorkTimes(@Param("id") Long id);

    //Spring Data JPA의 명명규칙을 사용하여 자동 작업
    Optional<UserEntity> findByUserNameAndId(String userName, int id);
}
