package com.example.myPersonalSetting.domain.service;

import com.example.myPersonalSetting.domain.entity.UserEntity;
import com.example.myPersonalSetting.domain.repository.UserRepository;
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

    /*
    base가 readOnly면, 수정이 불가하고, 수정하는 경우는 Transactional을 걸어서,
    하나의 처리 단위로 수행되게 만들도록 세팅하였다.
    이 경우 조회는 readOnly면서 @Transactional이 안걸려, 부하가 조금 줄어들것으로 예상된다.

     */
    @Transactional
    public void createUser(String userName, String password) {
        UserEntity userEntity = UserEntity.builder()
                                          .userName(userName)
                                          .password(password)
                                          .build();
        userRepository.save(userEntity);
    }

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(long id) {
        return userRepository.findById(id);
    }
}
