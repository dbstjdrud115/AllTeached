package com.example.myPersonalSetting.domain.controller;


import com.example.myPersonalSetting.common.utilities.ResultData;
import com.example.myPersonalSetting.domain.dto.UserDto;
import com.example.myPersonalSetting.domain.entity.UserEntity;
import com.example.myPersonalSetting.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //유저 리스트 조회
    @GetMapping("")
    public List<UserEntity> getUserList(){
        return userService.findAll();
    }

    //유저 한 명 조회
    @GetMapping("{id}")
    public Optional<UserEntity> getUserOne(
              @PathVariable long id
            , @RequestParam(value = "flag", required = false) Integer flag)
    {
        return userService.findById(id, flag);
    }

    @PostMapping
    public ResultData<UserDto> basicCreate(@RequestBody UserDto userDto){

        return new ResultData("200", "생성성공", userDto);
    }

    //id 대상 수정
    @PatchMapping("{id}")
    public ResultData<UserDto> basicUpdate(@PathVariable long id, @RequestBody UserDto userDto){

        return new ResultData("200", "수정성공", userDto);
    }

    @DeleteMapping("{id}")
    public String basicDelete(@PathVariable long id){

        return "basicDelete";
    }

}
