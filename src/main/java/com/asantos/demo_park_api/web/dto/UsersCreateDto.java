package com.asantos.demo_park_api.web.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.transform.sax.SAXResult;

@Getter @Setter @AllArgsConstructor @ToString
public class UsersCreateDto {

    @NotBlank
    @Email(message = "Formato do e-mail inválido.")
    private String username;

    @NotBlank
    @Size(min = 6, max = 6)
    private String password;


}
