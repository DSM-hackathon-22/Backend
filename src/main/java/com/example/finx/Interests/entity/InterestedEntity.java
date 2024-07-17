package com.example.finx.Interests.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class InterestedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String interest;

    private String keyword;
}
