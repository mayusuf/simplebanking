package com.secured.banking.feature.user.dao;

import com.secured.banking.utility.AbstractDAO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLOrder;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_users")
public class User extends AbstractDAO implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String firstName;
    private String lastName;

    private String email;
    private String mobile;
    private String occupation;
    private Boolean isLocked;
    private Boolean isTokenExpired;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, targetEntity = Role.class)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, targetEntity = Password.class)
    @SQLRestriction("is_active = true")
    @SQLOrder("created_date desc")
    private List<Password> password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(this.getRole());
    }

    @Override
    public String getPassword() {
        return this.password.getFirst().getHashed();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.getIsTokenExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.getIsLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.password.getFirst().getIsActive();
    }

    @Override
    public boolean isEnabled() {
        return this.getIsActive();
    }
}
