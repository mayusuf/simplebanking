package com.secured.banking.feature.user.service.implementation;

import com.secured.banking.feature.user.dao.Role;
import com.secured.banking.feature.user.repository.RoleRepository;
import com.secured.banking.feature.user.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImplementation implements RoleService {

    private final RoleRepository repository;

    @Value("${application.user.default.raw.role}")
    private String defaultRole;

    @Value("${application.user.default.raw.role.priority}")
    private Long defaultPriority;

    public Role setDefaultRole() {
        Optional<Role> role = repository.findByName(defaultRole);
        return role.orElseGet(() -> save(defaultRole, defaultPriority));
    }

    public Role save(String newRole, Long newPriority) {
        Role role = Role.builder()
                .name(newRole)
                .description(newRole)
                .priority(newPriority)
                .build();
        return repository.save(role);
    }
}
