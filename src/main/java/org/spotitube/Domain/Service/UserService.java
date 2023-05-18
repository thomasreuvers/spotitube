package org.spotitube.Domain.Service;

import org.spotitube.Domain.Model.LoginRequest;
import org.spotitube.Domain.Model.LoginResponse;
import org.spotitube.Domain.Model.RegisterModel;

public interface UserService {
    LoginResponse loginUser(LoginRequest model);
    void registerUser(RegisterModel model);
}
