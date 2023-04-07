package com.example.lablink.company.service;

import com.example.lablink.company.dto.request.CompanyLoginRequestDto;
import com.example.lablink.company.dto.request.CompanySignupRequestDto;
import com.example.lablink.company.entity.Company;
import com.example.lablink.company.exception.CompanyErrorCode;
import com.example.lablink.company.exception.CompanyException;
import com.example.lablink.company.repository.CompanyRepository;
import com.example.lablink.jwt.JwtUtil;
import com.example.lablink.user.entity.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    // 인증 인가를 담당하는 Service의 보안? 을 위함이기에 단익책임 위반 X
//    private final CsrfTokenRepository csrfTokenRepository;

    // 기업 회원가입
    public String companySignup(CompanySignupRequestDto companySignupRequestDto) {
//        UserRoleEnum role = UserRoleEnum.BUSINESS;
        String email = companySignupRequestDto.getEmail();
        String password = passwordEncoder.encode(companySignupRequestDto.getPassword());

        //이메일 중복 확인
        if (companyRepository.existsByEmail(email)) {
            throw new CompanyException(CompanyErrorCode.DUPLICATE_EMAIL);
        }

        companyRepository.save(new Company(password, companySignupRequestDto, UserRoleEnum.BUSINESS));
        return "회원가입 완료";
    }

    // 기업 로그인
    public void companyLogin(CompanyLoginRequestDto companyLoginRequestDto, HttpServletResponse response, HttpServletRequest request) {
        String email = companyLoginRequestDto.getEmail();
        String password = companyLoginRequestDto.getPassword();

        // 로그인시 작성 email과 db의 email의 일치, 존재 확인
        Company company = companyRepository.findByEmail(email).orElseThrow(() -> new CompanyException(CompanyErrorCode.EMAIL_NOT_FOUND));

        // 비밀번호 일치 여부
        if (!passwordEncoder.matches(password, company.getPassword())) {
            throw new CompanyException(CompanyErrorCode.PASSWORD_MISMATCH);
        }

        // CSRF, JWT토큰 생성
//        CsrfToken companyCsrfToken = csrfTokenRepository.generateToken(request);
        String companyToken = jwtUtil.createCompanyToken(company);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, companyToken);

        // 쿠키 생성 및 JWT토큰 추가
//        Cookie cookie = new Cookie("Authorization", companyToken);
//        cookie.setMaxAge(60 * 60 * 24); // 쿠키 유효 기간 (1일)
//        cookie.setPath("/"); // 전제api가 쿠키에 액세스 가능
//        cookie.setHttpOnly(true); // XSS공격 방지 (악성코드?)
////        cookie.setSecure(true); // HTTPS 사용 시 설정 (쿠키가 보안되지 않은 연결을 통해 전송되는 경우 탈취 방지)
//        response.addCookie(cookie);

        // 쿠키 생성 및 CSRF토큰 추가
//        Cookie csrfCookie = new Cookie("XSRF-TOKEN", companyCsrfToken.getToken());
//        csrfCookie.setMaxAge(60 * 60 * 24); // 쿠키 유효 기간 (1일)
//        csrfCookie.setPath("/"); // 전제api가 쿠키에 액세스 가능
//        csrfCookie.setHttpOnly(true); // XSS공격 방지 (악성코드?)
////        csrfCookie.setSecure(true); // HTTPS 사용 시 설정 (쿠키가 보안되지 않은 연결을 통해 전송되는 경우 탈취 방지)
//        response.addCookie(csrfCookie);

//        // 세션 쿠키 생성 및 추가 > websecurity 수정 필요
//        HttpSession session = request.getSession(true);
//        session.setAttribute("Authorization", token);
    }

    // 기업 이메일 찾기
    public Company findByCompanyEmail(String email) {
        Company company = companyRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
        return company;
    }


}