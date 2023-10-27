package com.vti.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;


import javax.persistence.*;

@Getter
@Setter
@Table(name = "account")
@Entity
public class Account {
    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", length = 50, unique = true,nullable = false,updatable = false)
    private String username;

    @Column(name = "password", length = 72, nullable = false)
    private String password;
    @Column(name = "first_Name", length = 50, nullable = false)
    private String firstName;

    @Column(name = "last_Name", length = 50, nullable = false)
    private String lastName;

    @Formula(" concat(first_Name, ' ', last_Name) ")
    private String fullName;

    @Column(name = "role", length = 8, nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;


    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id",nullable = false)
    private Department department;

    public enum Role{
        ADMIN, MANAGER, EMPLOYEE
    }
}
