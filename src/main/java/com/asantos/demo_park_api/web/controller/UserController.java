package com.asantos.demo_park_api.web.controller;

import com.asantos.demo_park_api.entity.User;
import com.asantos.demo_park_api.service.UserService;
import com.asantos.demo_park_api.web.dto.UsersCreateDto;
import com.asantos.demo_park_api.web.dto.mapper.UserMapper;
import com.asantos.demo_park_api.web.dto.mapper.UserResponseDto;
import com.asantos.demo_park_api.web.dto.mapper.UserSenhaDto;
import com.asantos.demo_park_api.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Users", description = "Contém todas as operações elativas aos recursos.")
@RestController
@RequestMapping(path = "api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Criar um novo usuário", description = "Recurso para criar novo usuário",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "409", description = "Já cadastrado.", content = @Content(mediaType = "application.json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "422", description = "Entrada inválida.", content = @Content(mediaType = "application.json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PostMapping
    public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UsersCreateDto createDto){
        User user = userService.salvar(UserMapper.toUser(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(user));
    }

    @Operation(summary = "Busca um usuário", description = "Recurso para buscar usuário por id",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Id não encontrado.", content = @Content(mediaType = "application.json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id){
        User user = userService.idFound(id);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    @Operation(summary = "Trocar senha", description = "Recurso para trocar senha",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Recurso criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Senha não confere.", content = @Content(mediaType = "application.json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = @Content(mediaType = "application.json", schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updatePassword(@PathVariable Long id, @Valid @RequestBody UserSenhaDto dto){
        User user = userService.changePassword(id, dto.getSenhaAtual(), dto.getNovaSenha(), dto.getConfirmaSenha());
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    @Operation(summary = "Busca usuários.", description = "Buscar todos os usuários.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso criado com sucesso", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado.", content = @Content(mediaType = "application.json", schema = @Schema(implementation = ErrorMessage.class))),
                    })
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<User> users = userService.foundAll();
        List<UserResponseDto> dtoList = UserMapper.toList(users);
        return ResponseEntity.ok(dtoList);
    }
}

