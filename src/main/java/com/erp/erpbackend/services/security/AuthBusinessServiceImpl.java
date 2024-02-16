package com.erp.erpbackend.services.security;

import com.erp.erpbackend.exception.BaseException;
import com.erp.erpbackend.models.dto.RefreshTokenDto;
import com.erp.erpbackend.models.dto.SendOTPDto;
import com.erp.erpbackend.models.enums.branch.BranchStatus;
import com.erp.erpbackend.models.mappers.CourseEntityMapper;
import com.erp.erpbackend.models.mappers.UserEntityMapper;
import com.erp.erpbackend.models.mybatis.branch.Branch;
import com.erp.erpbackend.models.mybatis.course.Course;
import com.erp.erpbackend.models.mybatis.employee.Employee;
import com.erp.erpbackend.models.mybatis.role.Role;
import com.erp.erpbackend.models.mybatis.user.User;
import com.erp.erpbackend.models.payload.auth.LoginPayload;
import com.erp.erpbackend.models.payload.auth.RefreshTokenPayload;
import com.erp.erpbackend.models.payload.auth.SignUpPayload;
import com.erp.erpbackend.models.payload.otp.OtpPayload;
import com.erp.erpbackend.models.payload.otp.SignUpOTPRequest;
import com.erp.erpbackend.models.response.LoginResponse;
import com.erp.erpbackend.services.branch.BranchService;
import com.erp.erpbackend.services.course.CourseService;
import com.erp.erpbackend.services.employee.EmployeeService;
import com.erp.erpbackend.services.otp.OptFactory;
import com.erp.erpbackend.services.role.RoleService;
import com.erp.erpbackend.services.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.erp.erpbackend.models.enums.response.ErrorResponseMessages.EMAIL_ALREADY_REGISTERED;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthBusinessServiceImpl implements AuthBusinessService {

    private final static String BRANCH_NAME_DEFAULT_PATTERN = "%s Default Branch";

    private final AuthenticationManager authenticationManager;
    private final RefreshTokenManager refreshTokenManager;
    private final AccessTokenManager accessTokenManager;
    private final UserDetailsService userDetailsService;
    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;
    private final BranchService branchService;
    private final CourseService courseService;
    private final UserService userService;
    private final RoleService roleService;

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
    public void signUp(SignUpPayload payload) {

        if (userService.checkByEmail(payload.getEmail())) {
            throw BaseException.of(EMAIL_ALREADY_REGISTERED);
        }
        Role defaultRole = roleService.getDefaultRole();

        User user = UserEntityMapper.INSTANCE.fromSignUpPayloadToUser(
                payload,
                passwordEncoder.encode(payload.getPassword()),
                defaultRole.getId()
        );

        userService.insert(user);

        Course course = CourseEntityMapper.INSTANCE.fromSignUpPayload(payload);

        courseService.insert(course);

        Branch branch = populateDefaultBranchData(payload, course);

        branchService.insert(branch);


        employeeService.insert(Employee.builder().userId(user.getId()).build());


    }

    @Override
    public void signUpOTP(OtpPayload payload) {
        OptFactory.handle(payload.getChannel()).send(SendOTPDto.of("user","key"));
    }

    @Override
    public void setAuthentication(String email) {
        UserDetails user = userDetailsService.loadUserByUsername(email);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(user, "", user.getAuthorities())
        );
    }

    @Override
    public void signUpOTPConfirmation(SignUpOTPRequest payload) {
        log.info(payload.getOtp() + "confirmed");

    }

    //private util methods


    private void authenticate(LoginPayload loginPayload) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginPayload.getEmail(), loginPayload.getPassword())
            );
        } catch (AuthenticationException e) {
            throw (e.getCause() instanceof BaseException)
                    ? (BaseException) e.getCause()
                    : BaseException.unexpected();
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

    private Branch populateDefaultBranchData(SignUpPayload payload, Course course) {
        // TODO: customize field setters
        return Branch.builder()
                .name(BRANCH_NAME_DEFAULT_PATTERN.formatted(payload.getCourseName()))
                .status(BranchStatus.ACTIVE)
                .address(payload.getAddress())
                .courseId(course.getId())
                .build();
    }
}
