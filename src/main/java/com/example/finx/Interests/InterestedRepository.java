package com.example.finx.Interests;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestedRepository extends JpaRepository<InterestedEntity,Long> {

}
