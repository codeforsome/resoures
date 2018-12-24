package com.example.health.serviceimpl;

import com.example.health.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceMpl implements UserService {
    @Override
    public boolean isLogin() {
        return false;
    }
}
