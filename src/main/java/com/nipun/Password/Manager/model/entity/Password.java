package com.nipun.Password.Manager.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "password")
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "password_seq")
    @SequenceGenerator(name = "password_seq", sequenceName = "password_seq", allocationSize = 1, initialValue = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "encodedpassword")
    private String encodedPassword;

    @Column(name = "description")
    private String description;

    @Column(name = "secretkey")
    private String secretKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(columnDefinition = "username", referencedColumnName = "username")
    private SystemUser systemUser;

    @Column(name = "lastupdatedtime")
    private Date lastUpdatedTime;
}
