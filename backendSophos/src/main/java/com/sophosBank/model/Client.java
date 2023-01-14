package com.sophosBank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Clients")

@EntityListeners(AuditingEntityListener.class)
public class Client {
    @Id
    @SequenceGenerator(name = "client_sequence", sequenceName = "client_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_sequence")
    private Long id;

    private String birthday;
    @CreatedDate
    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "createAdmin_id", nullable = false)
    private Admin creationUser;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "modAdmin_id", nullable = false)
    private Admin modificationUser;

    @LastModifiedDate
    private Date modificationDate;

    private String name;
    private String lastName;
    private Integer  identification;
    private  String typeIdentification;
    private String email;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Account> accounts = new HashSet<>();


    public Client() {
    }

    public Client(String birthday, Admin creationUser, Admin modificationUser,String name, String lastName, Integer identification, String typeIdentification, String email) {
        this.birthday = birthday;
        this.creationUser = creationUser;
        this.modificationUser = modificationUser;
        this.name = name;
        this.lastName = lastName;
        this.identification = identification;
        this.typeIdentification = typeIdentification;
        this.email = email;
    }
}
