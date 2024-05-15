package com.techcareer.business.dto;

import com.techcareer.annotation.AnnotationUniqueRoleName;
import com.techcareer.audit.AuditingAwareBaseDto;
import com.techcareer.role.ERole;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@Builder
public class RoleDto extends AuditingAwareBaseDto implements Serializable {

    public static final Long serialVersionUID = 1L;


    private Long roleId;

    @NotEmpty(message = "{role.name.validation.constraints.NotNull.message}")
    @Builder.Default
    @AnnotationUniqueRoleName
    private String roleName = ERole.USER.toString();


    private Date systemCreatedDate;

}
