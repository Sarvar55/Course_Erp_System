package com.erp.erpbackend.services.security;

import com.erp.erpbackend.models.dto.RefreshTokenDto;
import com.erp.erpbackend.models.mybatis.user.User;
import com.erp.erpbackend.models.payload.auth.LoginPayload;
import com.erp.erpbackend.models.payload.auth.RefreshTokenPayload;
import com.erp.erpbackend.models.response.LoginResponse;
import com.erp.erpbackend.services.user.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthBusinessServiceImpl implements AuthBusinessService {

    private final AuthenticationManager authenticationManager;
    private final AccessTokenManager accessTokenManager;
    private final RefreshTokenManager refreshTokenManager;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    @Override
    public LoginResponse login(LoginPayload loginPayload) {
        authenticate(loginPayload);

        return prepareLoginResponse(loginPayload.getEmail(), loginPayload.isRememberMe());
    }

    @Override
    public LoginResponse refresh(RefreshTokenPayload refreshTokenPayload) {

        final String email = refreshTokenManager.getEmail(refreshTokenPayload.getRefreshToken());

        return prepareLoginResponse(email, refreshTokenPayload.isRememberMe());

    }

    @Override
    public void logout() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("{} user logout succeed", userDetails.getUsername());
    }

    @Override
    public void setAuthentication(String email) {
        UserDetails user = userDetailsService.loadUserByUsername(email);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities())
        );
    }

    //private util methods


    private void authenticate(LoginPayload loginPayload) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginPayload.getEmail(), loginPayload.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private LoginResponse prepareLoginResponse(String email, boolean rememberMe) {
        User user = userService.getByEmail(email);

        return LoginResponse.builder()
                .refreshToken(refreshTokenManager.generate(
                        RefreshTokenDto.builder().rememberMe(rememberMe)
                                .user(user).build()
                )).accessToken(accessTokenManager.generate(user)).build();
    }
}
