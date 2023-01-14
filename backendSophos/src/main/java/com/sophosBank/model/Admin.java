package com.sophosBank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Administrators")
public class Admin {
    @Id
    @SequenceGenerator(name = "administrator_sequence", sequenceName = "administrator_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "administrator_sequence")
    private Long id;
    private String password;

    @OneToMany(mappedBy = "creationUser", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Client> createdClients;

    @OneToMany(mappedBy = "modificationUser", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Client> modClients;

    private String name;
    private String phone;
    private String address;
    private String email;

    public Admin() {
    }

    public Admin(String password, String email, String name, String phone, String address) {
        this.password = password;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
