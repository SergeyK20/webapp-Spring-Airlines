package com.example.airlines.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Account_user")
public class AccountUser implements UserDetails {

    private int id;
    private String login;
    private String passwordAccount;
    private String email;
    private boolean active;
    private Set<Role> roles;
    private List<UserFlight> flights;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    @Column(name = "Login")
    @NotNull
    public String getLogin() {
        return login;
    }

    @Column(name = "Password_account")
    @NotNull
    public String getPasswordAccount() {
        return passwordAccount;
    }

    @Column(name = "Email")
    @NotNull
    @Email
    public String getEmail() {
        return email;
    }

    @NotNull
    public boolean isActive() {
        return active;
    }

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "Id_account_user"))
    @Enumerated(EnumType.STRING)
    public Set<Role> getRoles() {
        return roles;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    public List<UserFlight> getFlights() {
        return flights;
    }

    public void setFlights(List<UserFlight> flights) {
        this.flights = flights;
    }

    public void setId(int idAccount) {
        this.id = idAccount;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPasswordAccount(String passwordAccount) {
        this.passwordAccount = passwordAccount;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role){
        roles.add(role);
    }

    public void deleteRole(Role role){
        roles.remove(role);
    }

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    @Transient
    public String getPassword() {
        return getPasswordAccount();
    }

    @Override
    @Transient
    public String getUsername() {
        return getLogin();
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return isActive();
    }
}
