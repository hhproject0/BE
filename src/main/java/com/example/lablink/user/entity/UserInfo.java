package com.example.lablink.user.entity;

import com.example.lablink.application.dto.Request.ApplicationRequestDto;
import com.example.lablink.user.dto.request.SignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String userAddress;

    @Column(nullable = false)
    private String userPhone;

    public UserInfo(SignupRequestDto signupRequestDto) {
        this.userPhone = signupRequestDto.getUserPhone();
    }

    public void updateUserInfo(ApplicationRequestDto applicationRequestDto) {
        this.userAddress = applicationRequestDto.getUserAddress();
    }
}
