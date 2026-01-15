package com.asantos.demo_park_api.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.transform.sax.SAXResult;

@Getter @Setter @AllArgsConstructor @ToString
public class UsersCreateDto {
    private String username;
    private String password;


}
