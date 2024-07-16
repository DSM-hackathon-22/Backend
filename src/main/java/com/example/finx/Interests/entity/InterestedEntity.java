package com.example.finx.Interests.entity;

import com.example.finx.user.entity.UserEntity;
import jakarta.persistence.*;
import org.apache.catalina.User;

@Entity
public class InterestedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String interest;


}
