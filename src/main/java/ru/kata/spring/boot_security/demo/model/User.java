package ru.kata.spring.boot_security.demo.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "user")
public class User implements UserDetails {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "Age")
    private int age;

    @Column(name = "Email")
    private String username;

    @Column(name = "password")
    private String password;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @Transient
    private List<String> singleRole;

    public User() {
    }

    public User(int id, String firstName, String lastName, int age, String username) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.username = username;
    }

    public List<String> getSingleRole() {
        return singleRole;
    }

    public void setSingleRole(List<String> singleRole) {
        this.singleRole = singleRole;
        addRoles(singleRole);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getRoles() {
        String rol = "";

        for (Role r : roles) {
            rol += " " + r.getName();
        }

        return rol.replaceAll("ROLE_", "");
    }


    public List<String> getRolesName() {
        List<String> rolesName = new ArrayList<>();
        for (Role i : roles) {
            rolesName.add(i.getName().replaceAll("ROLE_", "\n"));
        }
        return rolesName;
    }


    public void addRoles(List<String> rolesName) {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        for (String name : rolesName) {
            if (name.contains("ROLE_ADMIN")) {
                roles.add(new Role(1, "ROLE_ADMIN"));
            }
            if (name.contains("ROLE_USER")) {
                roles.add(new Role(2, "ROLE_USER"));
            }
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
