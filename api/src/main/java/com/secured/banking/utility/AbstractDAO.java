package com.secured.banking.utility;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
@MappedSuperclass
public abstract class AbstractDAO {

    private Boolean isActive;


    private Long createdBy;

    private LocalDateTime createdDate;


    private Long updatedBy;

    private LocalDateTime updatedDate;


    private Boolean isDeleted;

    private Long deletedBy;

    private LocalDateTime deletedDate;

    public AbstractDAO() {
        LocalDateTime now = LocalDateTime.now();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserDTO user = new UserDTO();
//        if (Objects.nonNull(authentication)) {
//            user = (UserDTO) authentication.getPrincipal();
//        }

        this.setIsActive(true);
        this.setIsDeleted(false);
        this.setCreatedDate(now);
//        this.setCreatedBy(user.getId());
    }

}