package com.nipun.Password.Manager.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "systemuser")
public class SystemUser {

    @Id
    @Column(name = "username")
    private String userName;

    @Column(name = "userlevel")
    private Integer userLevel;

    @Column(name = "password")
    private String password;

    @Column(name = "createdtime")
    private String createdTime;

    @Column(name = "lastupdatedtime")
    private String lastUpdatedTime;
}
