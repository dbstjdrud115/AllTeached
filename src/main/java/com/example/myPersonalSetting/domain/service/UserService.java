package com.example.myPersonalSetting.domain.service;

import com.example.myPersonalSetting.domain.entity.QUserEntity;
import com.example.myPersonalSetting.domain.entity.UserEntity;
import com.example.myPersonalSetting.domain.entity.WorkTimeEntity;
import com.example.myPersonalSetting.domain.repository.UserRepository;
import com.example.myPersonalSetting.domain.repository.WorkTimeRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final WorkTimeRepository workTimeRepository;
    private final JPAQueryFactory queryFactory;

    /*
    base가 readOnly면, 수정이 불가하고, 수정하는 경우는 Transactional을 걸어서,
    하나의 처리 단위로 수행되게 만들도록 세팅하였다.
    이 경우 조회는 readOnly면서 @Transactional이 안걸려, 부하가 조금 줄어들것으로 예상된다.

     */
    @Transactional
    public void createInitUser(String userName, String password) {
        UserEntity userEntity = UserEntity.builder()
                                          .userName(userName)
                                          .password(password)
                                          .build();
        userRepository.save(userEntity);
    }

    @Transactional
    public void createInitWorkTime(Long id, String workDay) {

        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("유저정보가 없습니다. 유저id는 : " + id + "입니다."));

        WorkTimeEntity workTimeEntity = WorkTimeEntity.builder()
                .userEntity(userEntity)
                .workDay(workDay)
                .build();

        workTimeRepository.save(workTimeEntity);
    }


    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(long id, Integer flag) {
        Optional<UserEntity> userEntity = Optional.empty();
        if(flag == 1) {
            userEntity = userRepository.findById(id);//JPA기본 조회
        } else {
            if(flag == 2){  //Spring Data Jpa를 사용하여 조회

                //JPQL 사용
                //userEntity = userRepository.findByIdWithWorkTimes(id);
                //userRepository.findByIdCustom(id);

                //SpringJPA 명명법 사용
                userRepository.findByUserNameAndId("1번 유저", 1);//유저명 말고 추가조건 필요

            } else if(flag == 3){  //QueryDsl 사용하여 조회
                //QueryDsl에선 Q클래스라는, 원본의 메타데이터를 만들고 실행한다.
                //자동 생성임으로 서버 한 번 켜주면 자동 빌드된다.
                QUserEntity qUserEntity = QUserEntity.userEntity;
                UserEntity result = queryFactory
                        .selectFrom(qUserEntity)
                        .where(qUserEntity.id.eq(id))
                        .fetchOne(); //단건조회시 사용됨
                userEntity = Optional.ofNullable(result);
            }
        }
        return userEntity;
    }

}
