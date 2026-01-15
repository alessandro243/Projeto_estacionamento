package com.asantos.demo_park_api.service;

import com.asantos.demo_park_api.entity.User;
import com.asantos.demo_park_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User salvar(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User idFound(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado")
        );
    }

    @Transactional(readOnly = true)
    public List<User> foundAll() {
        return userRepository.findAll();
    }

    @Transactional
    public User changePassword(Long id, String senhaAtual, String novaSenha, String confirmaSenha) {

        if (!novaSenha.equals(confirmaSenha)){
            throw new RuntimeException("Nova senha não confere com confirmação de senha.");
        }

        User user = idFound(id);

        if (!user.getPassword().equals(senhaAtual)){
            throw new RuntimeException("Sua senha não confere.");
        }

        user.setPassword(novaSenha);
        return user;
    }
}
