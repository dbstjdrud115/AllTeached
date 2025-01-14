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
                userService.createInitUser( "1번 유저", "1234");
                IntStream.rangeClosed(1, 4).forEach(num -> {
                    userService.createInitUser(num + "번 유저", "1234");
                });

                // 유저에게 종속된 근무일 정보를 5건 정도 삽입해주고 싶다.
                // 그럼 종속Entity정보와, 근무일을 입력해주면되겠네.
                //그럼 종속 ENTITY의 어느 ROW값에 종속시킬지를 알아야 하니까,
                //종속 ENTITY의 ID값을 필요로 할것같다.
                IntStream.rangeClosed(1, 4).forEach(num -> {
                    userService.createInitWorkTime(1L, "99991231");
                });
            }
        };
    }
}
