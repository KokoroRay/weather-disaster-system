package com.wds.user_service.entity;

import com.wds.user_service.entity.constants.AuthProvider;
import com.wds.user_service.entity.constants.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String avatarUrl;

    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean isLocked = false;
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;
}
