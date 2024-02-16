package com.erp.erpbackend.filters;

import com.erp.erpbackend.constants.TokenConstants;
import com.erp.erpbackend.exception.BaseException;
import com.erp.erpbackend.services.security.AccessTokenManager;
import com.erp.erpbackend.services.security.AuthBusinessService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorizationFilter extends OncePerRequestFilter {

    private final AccessTokenManager accessTokenManager;
    private final AuthBusinessService authBusinessService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        try {

            if (Objects.nonNull(token) && token.startsWith(TokenConstants.TOKEN_PREFIX)) {

                authBusinessService.setAuthentication(
                        accessTokenManager.getEmail(
                                token.substring(7)
                        )
                );
            }

        } catch (BaseException | JwtException e) {
            log.warn(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        filterChain.doFilter(request, response);
    }
}
