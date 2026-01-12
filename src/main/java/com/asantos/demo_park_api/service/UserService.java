package com.asantos.demo_park_api.service;

import com.asantos.demo_park_api.entity.User;
import com.asantos.demo_park_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User salvar(User user) {
        return userRepository.save(user);
    }
}
