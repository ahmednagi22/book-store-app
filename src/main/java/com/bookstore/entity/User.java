package com.bookstore.entity;

import com.bookstore.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String fullName;

    @Column
    private String phoneNumber;

    @Column
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role = Role.CUSTOMER;//default is customer
}
