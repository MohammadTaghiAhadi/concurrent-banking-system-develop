package com.ahadi.concurrent_banking_system.config.core;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Getter@Setter@SuperBuilder
@NoArgsConstructor
public class BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID", nullable=false)
    private Long id;

    @Column(name="INSERT_DATE",nullable = false)
    @CreationTimestamp
    private Date insertDate;

    @Column(name="USER_CREATOR")
    @CreatedBy
    private String userCreator;

    @Column(name="UPDATE_DATE")
    @LastModifiedDate
    private Date updateDate;

    @Column(name="UPDATING_USER")
    @LastModifiedBy
    private String updatingUser;

}
