package com.egin.user.service.user;

import com.egin.auth.model.Token;
import com.egin.auth.model.dto.request.LoginRequest;

public interface UserLoginService {
    Token login(final LoginRequest request);
}
