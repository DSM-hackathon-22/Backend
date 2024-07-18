package com.example.finx.Interests.repository;

import com.example.finx.Interests.entity.UserInterestedEntity;
import com.example.finx.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInterestedRepository extends JpaRepository<UserInterestedEntity,Long> {

    boolean existsByUserId(Long userId);

    void deleteByUserId(Long userId);

}
