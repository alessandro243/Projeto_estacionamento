package com.asantos.demo_park_api.service;

import com.asantos.demo_park_api.entity.User;
import com.asantos.demo_park_api.exception.EntityNotFoundException;
import com.asantos.demo_park_api.exception.PasswordInvalidException;
import com.asantos.demo_park_api.exception.UsernameUniqueViolationException;
import com.asantos.demo_park_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User salvar(User user) {

        try {
            return userRepository.save(user);
        }catch (DataIntegrityViolationException e){
            throw new UsernameUniqueViolationException(String.format("Username %s já cadastrado", user.getname()));
        }
    }

    @Transactional(readOnly = true)
    public User idFound(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Usuário do id %s não encontrado.", id))
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
            throw new PasswordInvalidException("Sua senha não confere.");
        }

        user.setPassword(novaSenha);
        return user;
    }
}
