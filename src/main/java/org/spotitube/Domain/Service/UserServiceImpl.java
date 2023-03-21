package org.spotitube.Domain.Service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserServiceImpl implements IUserService{

    public int calculate(int number, int number2) {
        return number + number2;
    }
}
