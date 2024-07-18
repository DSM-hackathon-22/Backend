package com.example.finx.Interests.entity;

import com.example.finx.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@NoArgsConstructor
@Builder
@Entity
@AllArgsConstructor
public class UserInterestedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private UserEntity user;

    @ManyToOne
    private InterestedEntity interestedEntity;
}
