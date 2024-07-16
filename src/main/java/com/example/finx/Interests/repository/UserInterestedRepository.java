package com.example.finx.Interests.repository;

import com.example.finx.Interests.entity.UserInterestedEntity;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInterestedRepository extends JpaRepository<UserInterestedEntity,Long> {



}
