package com.nisum.evaluation.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Setter
@Getter
@Builder
@Entity
@AllArgsConstructor
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID userId;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private List<Phone> phones = new ArrayList<>();

    @Column(updatable = false)
    @CreatedDate
    private Instant created;

    @LastModifiedDate
    private Instant modified;

    private Instant lastLogin;

    @Builder.Default
    private boolean active = true;

    private String token;

    @PrePersist
    private void updateLastLogin(){
        this.lastLogin = getCreated();
    }

}
