package com.asantos.demo_park_api.web.controller;

import com.asantos.demo_park_api.entity.User;
import com.asantos.demo_park_api.service.UserService;
import com.asantos.demo_park_api.web.dto.UsersCreateDto;
import com.asantos.demo_park_api.web.dto.mapper.UserMapper;
import com.asantos.demo_park_api.web.dto.mapper.UserResponseDto;
import com.asantos.demo_park_api.web.dto.mapper.UserSenhaDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UsersCreateDto createDto){
        User user = userService.salvar(UserMapper.toUser(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id){
        User user = userService.idFound(id);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updatePassword(@PathVariable Long id, @Valid @RequestBody UserSenhaDto dto){
        User user = userService.changePassword(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<User> users = userService.foundAll();
        List<UserResponseDto> dtoList = UserMapper.toList(users);
        return ResponseEntity.ok(dtoList);
    }
}

