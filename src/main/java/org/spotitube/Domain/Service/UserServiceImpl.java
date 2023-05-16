package org.spotitube.Domain.Service;

import org.spotitube.Data.Entity.User;
import org.spotitube.Data.Mapper.User.UserMapper;
import org.spotitube.Domain.Exception.AuthenticationException;
import org.spotitube.Domain.Model.LoginModel;
import org.spotitube.Domain.Model.RegisterModel;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class UserServiceImpl implements UserService {

    @Inject
    private UserMapper userMapper;

    @Inject
    private TokenService tokenService;


    @Override
    public User loginUser(LoginModel model) {
        if (!userMapper.findByUsername(model.getUsername()).isPresent()) {
            throw new AuthenticationException("User does not exist!");
        }

        User user = userMapper.findByUsername(model.getUsername()).get();

        if(user.getUsername().equals(model.getUsername()) && user.getPassword().equals(model.getPassword()))
        {
            return user;
        }else{
            throw new AuthenticationException("Invalid Username/Password!");
        }
    }

    @Override
    public void registerUser(RegisterModel model) {
        userMapper.insert(model.asUserEntity(model));
    }
}
