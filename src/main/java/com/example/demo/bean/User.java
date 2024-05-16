package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(value = {"password", "ssn"})
public class User {
    private Integer id;

    @Size(min=2, message = "Name은 2글자 이상 입력해 주세요")//유효성검사
    private String name;

    @Past(message = "등록일은 미래 날짜를 입력하실 수 없습니다")//유효성검사
    private Date joinDate;

    //@JsonIgnore
    private String password;

   // @JsonIgnore //직렬화 또는 역질렬화 할때 해당 필드는 무시된다
    private String ssn;
}
