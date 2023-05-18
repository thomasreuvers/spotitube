package org.spotitube.Domain.Service;

import org.spotitube.Data.Entity.User;
import org.spotitube.Data.Mapper.User.IUserDao;
import org.spotitube.Domain.Exception.AuthenticationException;
import org.spotitube.Domain.Model.LoginRequest;
import org.spotitube.Domain.Model.LoginResponse;
import org.spotitube.Domain.Model.RegisterModel;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class UserServiceImpl implements UserService {

    @Inject
    private IUserDao<User> userMapper;

    @Inject
    private TokenService tokenService;


    @Override
    public LoginResponse loginUser(LoginRequest model) {
        if (!userMapper.findByUsername(model.getUser()).isPresent()) {
            throw new AuthenticationException("User does not exist!");
        }

        User user = userMapper.findByUsername(model.getUser()).get();

        if(user.getUsername().equals(model.getUser()) && user.getPassword().equals(model.getPassword()))
        {
            // Generate token
            String token = tokenService.GenerateToken();

            // Update user in db with new token
            user.setToken(token);
            userMapper.update(user);

            return new LoginResponse(token, user.getUsername());
        }else{
            throw new AuthenticationException("Invalid Username/Password!");
        }
    }

    @Override
    public void registerUser(RegisterModel model) {
        userMapper.insert(model.asUserEntity(model));
    }
}
