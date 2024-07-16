package com.example.finx.Interests.entity;

import com.example.finx.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Builder;
import org.springframework.web.bind.annotation.RequestBody;
@Builder
@Entity
public class UserInterestedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private InterestedEntity interestedEntity;
}
