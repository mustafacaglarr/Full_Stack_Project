package com.techcareer.data.entity;

import com.techcareer.audit.AuditingAwareBaseEntity;
import com.techcareer.role.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@Builder
@Entity(name = "Roles")
@Table(name = "roles")
public class RoleEntity extends AuditingAwareBaseEntity implements Serializable {

    public static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name",length = 20,nullable = true,columnDefinition = "varchar(255) default USER")
    private String roleName = ERole.USER.toString();

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date systemCreatedDate;

}