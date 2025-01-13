package com.example.myPersonalSetting.common.utilities;

import com.example.myPersonalSetting.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.stream.IntStream;

@Configuration
@Profile("!prod")
@RequiredArgsConstructor
public class InitData {

    @Bean//이거 하나 안해서, 왜 실행 안되는지 헤맸다.
         //Bean등록안하면 Spring에서 관리, 실행할 대상으로 취급 안해준다.
    public ApplicationRunner applicationRunner(
            UserService userService
            //ChatRoomService chatRoomService,
    ) {
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {
                System.out.println("동작하였음!");
                userService.createUser( "1번 유저", "1234");
                IntStream.rangeClosed(1, 4).forEach(num -> {
                    userService.createUser(num + "번 유저", "1234");
                });
            }
        };
    }
}
