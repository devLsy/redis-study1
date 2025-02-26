package com.test.lsy.redistest.user.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "test_user")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @CreationTimestamp
    private Timestamp create_at;

    @Builder
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
