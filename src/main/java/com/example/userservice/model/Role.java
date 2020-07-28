package com.example.userservice.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @ManyToMany
    @JoinTable(name = "role_permission", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "pre_id", referencedColumnName = "id"))
    private List<Permission>permissions;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
    private List<User> users;


}
