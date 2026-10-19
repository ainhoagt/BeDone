package com.example.be_done_api.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;
    @Column(name= "password_hash", nullable = false)
    private String passwordHash;
    @Column(name= "email", nullable = false, unique = true)
    private String email;
    @Column(name= "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;
    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL,orphanRemoval = true)
    private List<Board> boards;

}
