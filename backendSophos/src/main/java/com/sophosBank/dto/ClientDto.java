package com.sophosBank.dto;

import com.sophosBank.model.Admin;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ClientDto  {
    private Long id;
    private String birthday;
    private Date creationDate;
    private Admin creationUser;
    private Date modificationDate;
    private Admin modificationUser;
    private String name;
    private String lastName;
    private Integer  identification;
    private  String typeIdentification;
    private String email;

    public ClientDto() {
    }

    public ClientDto(String birthday, Date creationDate, Admin creationUser, Date modificationDate, Admin modificationUser, String name, String lastName, Integer identification, String typeIdentification, String email) {
        this.birthday = birthday;
        this.creationDate = creationDate;
        this.creationUser = creationUser;
        this.modificationDate = modificationDate;
        this.modificationUser = modificationUser;
        this.name = name;
        this.lastName = lastName;
        this.identification = identification;
        this.typeIdentification = typeIdentification;
        this.email = email;
    }
}
