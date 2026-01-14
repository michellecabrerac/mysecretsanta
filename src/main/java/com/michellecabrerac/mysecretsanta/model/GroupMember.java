package com.michellecabrerac.mysecretsanta.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter @Setter
@Entity
@Table(name = "T_GROUP_MEMBER")
public class GroupMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "group_fk", nullable = false)
    private Group group;
    @ManyToOne
    @JoinColumn(name = "user_fk", nullable = false)
    private User user;
    @CreationTimestamp
    private LocalDateTime joinedAt;
    @Column(nullable = false, length = 20)
    private GroupRole role = GroupRole.MEMBER;
    @Column(nullable = false)
    private Boolean active;
}
enum GroupRole{
    ADMIN,
    MEMBER
}
