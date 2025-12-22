package com.michellecabrerac.mysecretsanta.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter @Setter
@Entity
public class GroupMember {
    @Id
    private Long id;
    @ManyToOne
    private Group group;
    @ManyToOne
    private User user;
    @CreationTimestamp
    private LocalDateTime joinedAt;
}
