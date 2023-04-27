package com.example.lablink.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyPageCheckRequestDto {
    @NotNull(message = "정보를 입력해 주세요")
    @NotBlank(message = "정보를 입력해 주세요")
    private String password;

    @Getter
    @Setter
    public static class UserModifyRequestDto {
        private String userName;

        @Past(message = "생년월일은 유효한 날짜여야 합니다.")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        private LocalDate dateOfBirth;

        private String userGender;

        private String userPhone;

        private String userAddress;
    }

}
