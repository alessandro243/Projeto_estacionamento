package com.asantos.demo_park_api.web.dto.mapper;

import com.asantos.demo_park_api.entity.User;
import com.asantos.demo_park_api.web.dto.UsersCreateDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static User toUser(UsersCreateDto createDto){
        return new ModelMapper().map(createDto, User.class);
    }

    public static UserResponseDto toDto(User user) {
        String role = user.getRole().name().substring("ROLE_".length());
        ModelMapper mapperMain = new ModelMapper();
        TypeMap<User, UserResponseDto> propertyMapper =
                mapperMain.createTypeMap(User.class, UserResponseDto.class);
        propertyMapper.addMappings(
                mapper -> mapper.map(src -> role, UserResponseDto::setRole)
        );
        return mapperMain.map(user, UserResponseDto.class);
    }

    public static List<UserResponseDto> toList(List<User> users) {
        List<UserResponseDto> responses = new ArrayList<>();
        for (User user : users) {
            responses.add(toDto(user));
        }
        return responses;
    }
}

