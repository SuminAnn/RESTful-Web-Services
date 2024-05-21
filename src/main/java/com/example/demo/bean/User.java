package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"password", "ssn"})
@Schema(description = "사용자 상세 정보를 위한 도메인 객체")
@Entity //클래스 이름에 해당하는 데이터 베이스 테이블이 생성되는 어노테이션
@Table(name = "users") //테이블 명을 직접 명시
public class User {
    @Schema(title = "사용자 고유 ID", description = "사용자 ID는 자동 생성됩니다")
    @Id
    @GeneratedValue
    private Integer id;

    @Schema(title = "사용자 이름", description = "사용자 이름을 입력합니다")
    @Size(min=2, message = "Name은 2글자 이상 입력해 주세요")//유효성검사
    private String name;

    @Schema(title = "사용자 등록일", description = "사용자 등록일을 입력합니다. 입력하지 않으면 현재 날짜가 지정됩니다")
    @Past(message = "등록일은 미래 날짜를 입력하실 수 없습니다")//유효성검사
    private Date joinDate;

    //@JsonIgnore
    @Schema(title = "사용자 비밀번호", description = "사용자 비밀번호를 입력합니다")
    private String password;

   // @JsonIgnore //직렬화 또는 역질렬화 할때 해당 필드는 무시된다
    @Schema(title = "사용자 주민번호", description = "사용자 주민번호를 입력합니다")
    private String ssn;
}

/*
 * ORM
 *  - 객체를 관계형 데이터베이스 데이터와 자동으로 맵핑
 * 
 * JPA
 *  - 자바 ORM 기술에 대한 API 표준 명세
 *  - 자바 어플리케이션에서 관계형 데이터베이스를 사용하는 방식을 정의한 인터페이스
 *  - EntityManager를 통해 CRUD 처리
 * 
 * Hibernate
 *  - 객체 관계 맵핑 프레임워크
 *  - JPA의 구현체, 인터페이스를 직접 구현한 라이브러리
 *  - 생산성, 유지보수, 비종속성
 * 
 * Spring Data JPA
 *  - Spring Module
 *  - JPA를 추상화한 Repository 인터페이스 제공
 */