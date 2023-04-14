package com.example.lablink.user.service;

import com.example.lablink.user.dto.request.SignupRequestDto;
import com.example.lablink.user.entity.UserInfo;
import com.example.lablink.user.exception.UserErrorCode;
import com.example.lablink.user.exception.UserException;
import com.example.lablink.user.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    // 회원가입시 UserInfo DB에 저장
    public UserInfo saveUserInfo(SignupRequestDto signupRequestDto) {
        return userInfoRepository.save(new UserInfo(signupRequestDto));
    }

    // UserInfo 찾기
    public UserInfo findUserInfo(Long userInfoId) {
        return userInfoRepository.findById(userInfoId).orElseThrow(() -> new UserException(UserErrorCode.USERINFO_NOT_FOUND));
    }
}
