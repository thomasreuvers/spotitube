package org.spotitube.Domain.Service;


import org.spotitube.Data.Mapper.UserMapperImpl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class UserServiceImpl implements UserService {

    @Inject
    private UserMapperImpl userMapper;

    public int calculate(int number, int number2) {
        return number + number2;
    }

    public void registerUser() {
        userMapper.insert(null);
    }
}
