package com.example.lablink.user.entity;

import com.example.lablink.user.dto.request.SignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userAdrress;

    @Column(nullable = false)
    private String userPhone;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public UserInfo(SignupRequestDto signupRequestDto, User user) {
        this.userAdrress = signupRequestDto.getUserAdrress();
        this.userPhone = signupRequestDto.getUserPhone();
        this.user = user;
    }
}