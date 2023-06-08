package org.spotitube.Domain.Service.User;

import org.spotitube.Domain.Model.LoginRequest;
import org.spotitube.Domain.Model.LoginResponse;
import org.spotitube.Domain.Model.RegisterModel;

public interface IUserService {
    LoginResponse loginUser(LoginRequest model);
    void registerUser(RegisterModel model);
}
