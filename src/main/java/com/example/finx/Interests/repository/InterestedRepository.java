package com.example.finx.Interests.repository;

import com.example.finx.Interests.entity.InterestedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InterestedRepository extends JpaRepository<InterestedEntity,Long> {

    Optional<InterestedEntity> findByInterest(String interest);

    @Query("select i from InterestedEntity i, UserInterestedEntity ui where i = ui.interestedEntity and ui.user.id = :userId")
    InterestedEntity findByUserId(Long userId);
}
