package com.julio.learnSB.repository.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;


@MappedSuperclass
@Data
@DynamicUpdate
public class BaseEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "active", columnDefinition = "TINYINT default 1")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @NotNull
    private Boolean active;

    @Column(name = "created_date")
    @NotNull
    private Date createdDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

    @Column(name = "deleted_date")
    private Date deletedDate;

    @Column(name = "created_by")
    @NotNull
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "deleted_by")
    private String deletedBy;

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID().toString();
        this.active = true;
        this.createdDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.modifiedDate = new Date();
    }

    @PreRemove
    public void preRemove() {
        this.deletedDate = new Date();
        this.active = false;
    }
}
