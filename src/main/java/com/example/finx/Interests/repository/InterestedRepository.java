package com.example.finx.Interests.repository;

import com.example.finx.Interests.entity.InterestedEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InterestedRepository extends JpaRepository<InterestedEntity,Long> {

    Optional<InterestedEntity> findByInterest(String interest);
}
