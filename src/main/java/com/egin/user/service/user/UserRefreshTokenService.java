package com.egin.user.service.user;

import com.egin.auth.model.Token;
import com.egin.auth.model.dto.request.TokenRefreshRequest;

public interface UserRefreshTokenService {
    Token refreshToken(final TokenRefreshRequest request);
}
