package com.secured.banking.feature.user.dao;

import com.secured.banking.utility.AbstractDAO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_roles")
public class Role extends AbstractDAO implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Long priority;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, targetEntity = User.class)
    private List<User> user;

    @Override
    public String getAuthority() {
        return this.name;
    }
}