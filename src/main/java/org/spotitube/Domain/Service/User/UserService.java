package org.spotitube.Domain.Service.User;

import org.apache.commons.codec.digest.DigestUtils;
import org.spotitube.Data.Entity.User;
import org.spotitube.Data.Mapper.User.IUserMapper;
import org.spotitube.Domain.Exception.AuthenticationException;
import org.spotitube.Domain.Model.LoginRequest;
import org.spotitube.Domain.Model.LoginResponse;
import org.spotitube.Domain.Model.RegisterModel;
import org.spotitube.Domain.Service.Token.ITokenService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class UserService implements IUserService {

    @Inject
    private IUserMapper userMapper;

    @Inject
    private ITokenService ITokenService;


    @Override
    public LoginResponse loginUser(LoginRequest model) {
        User user = userMapper.findByUsername(model.getUser());

        if (user == null) {
            throw new AuthenticationException("User does not exist!");
        }

        if(user.getUsername().equals(model.getUser()) && DigestUtils.sha256Hex(model.getPassword()).equals(user.getPassword()))
        {
            // Generate token
            String token = ITokenService.GenerateToken();

            // Update user in db with new token
            userMapper.updateToken(user.getId(), token);

            return new LoginResponse(token, user.getUsername());
        }else{
            throw new AuthenticationException("Invalid Username/Password!");
        }
    }

    @Override
    public void registerUser(RegisterModel model) {
        userMapper.newUser(model.asUserEntity(model));
    }
}
