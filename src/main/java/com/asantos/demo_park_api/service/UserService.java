package com.asantos.demo_park_api.service;

import com.asantos.demo_park_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private UserRepository userRepository;
}
