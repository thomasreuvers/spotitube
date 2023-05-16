package org.spotitube.Domain.Service;

import org.spotitube.Data.Entity.User;
import org.spotitube.Domain.Model.LoginModel;
import org.spotitube.Domain.Model.RegisterModel;

public interface UserService {
    User loginUser(LoginModel model);
    void registerUser(RegisterModel model);
}
