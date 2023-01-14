package com.sophosBank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminDto {
 private Long id;
    private String password;
    private String email;

    private String name;
    private String phone;
    private String address;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AdminDto(String password, String email, String name, String phone, String address) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public AdminDto() {
    }
}
