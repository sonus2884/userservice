package com.example.userservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "permissions")
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles;
}
