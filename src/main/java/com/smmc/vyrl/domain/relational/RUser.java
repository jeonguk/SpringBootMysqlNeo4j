package com.smmc.vyrl.domain.relational;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;


@Entity
@Data
@EqualsAndHashCode
@ToString
@Table(name = "user")
public class RUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;

    @NotEmpty
    @Column(name = "social_id")
    private String socialId;

    @NotEmpty
    @Column(name = "nickname")
    private String nickname;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "selfintro")
    private String selfintro;

    @Column(name = "homepage")
    private String homepage;

    @Column(name = "birthday")
    private DateTime birthday;

    @Column(name = "age")
    private int age;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "contact")
    private int contact;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }
}
