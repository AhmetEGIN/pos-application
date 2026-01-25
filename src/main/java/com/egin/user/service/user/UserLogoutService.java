package com.egin.user.service.user;

import com.egin.auth.model.dto.request.TokenInvalidateRequest;

public interface UserLogoutService {
    void logout(final TokenInvalidateRequest request);
}
