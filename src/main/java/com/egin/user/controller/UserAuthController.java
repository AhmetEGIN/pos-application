package com.egin.user.controller;

import com.egin.auth.model.Token;
import com.egin.auth.model.dto.request.LoginRequest;
import com.egin.auth.model.dto.request.TokenInvalidateRequest;
import com.egin.auth.model.dto.request.TokenRefreshRequest;
import com.egin.auth.model.dto.response.TokenResponse;
import com.egin.auth.model.mapper.TokenToTokenResponseMapper;
import com.egin.common.model.dto.response.CustomResponse;
import com.egin.user.model.User;
import com.egin.user.model.dto.request.UserRegisterRequest;
import com.egin.user.service.user.*;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authentication/user")
public class UserAuthController {

    private final UserRegisterService userRegisterService;
    private final UserLoginService userLoginService;
    private final UserRefreshTokenService userRefreshTokenService;
    private final UserLogoutService userLogoutService;
    private final UserReadService userReadService;

    public UserAuthController(
            UserRegisterService userRegisterService,
            UserLoginService userLoginService,
            UserRefreshTokenService userRefreshTokenService,
            UserLogoutService userLogoutService,
            UserReadService userReadService
    ) {
        this.userRegisterService = userRegisterService;
        this.userLoginService = userLoginService;
        this.userRefreshTokenService = userRefreshTokenService;
        this.userLogoutService = userLogoutService;
        this.userReadService = userReadService;
    }

    @PostMapping("/register")
    public CustomResponse<Void> registerUser(
            @RequestBody @Valid final UserRegisterRequest request
    ) {
        userRegisterService.registerUser(request);
        return CustomResponse.SUCCESS;
    }


    @PostMapping("/login")
    public CustomResponse<TokenResponse> loginUser(
            @RequestBody @Valid final LoginRequest request
    ) {
        final Token token = userLoginService.login(request);
        final TokenResponse tokenResponse = TokenToTokenResponseMapper.toTokenResponse(token);
        return CustomResponse.successOf(tokenResponse);
    }

    @PostMapping("/refresh-token")
    public CustomResponse<TokenResponse> refreshToken(
            @RequestBody @Valid final TokenRefreshRequest request
    ) {
        final Token token = userRefreshTokenService.refreshToken(request);
        final TokenResponse tokenResponse = TokenToTokenResponseMapper.toTokenResponse(token);
        return CustomResponse.successOf(tokenResponse);
    }

    @PostMapping("/logout")
    @PreAuthorize("hasAnyAuthority('USER')")
    public CustomResponse<Void> logout(
            @RequestBody @Valid final TokenInvalidateRequest request
    ) {
        userLogoutService.logout(request);
        return CustomResponse.SUCCESS;
    }

    @GetMapping("/{user-id}")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public CustomResponse<User> getUserById(
            @PathVariable("user-id") final String userId
    ) {
        return CustomResponse.successOf(userReadService.getUserById(userId));
    }




}
