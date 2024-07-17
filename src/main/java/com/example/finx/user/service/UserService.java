package com.example.finx.user.service;

import com.example.finx.user.dto.UserDto;
import com.example.finx.user.entity.UserEntity;
import com.example.finx.user.jwt.JwtProvider;
import com.example.finx.user.jwt.TokenResponse;
import com.example.finx.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public TokenResponse signUp(UserDto userDto) {
        UserEntity userEntity = userDto.toEntity();
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Long id = userRepository.save(userEntity).getId();
        return jwtProvider.generateTokens(id);
    }

    public TokenResponse login(UserDto userDto) {
        UserEntity userEntity = userRepository.findByusername(userDto.getUsername())
            .orElseThrow();

        if (!passwordEncoder.matches(userDto.getPassword(), userEntity.getPassword())) {
            throw new RuntimeException();
        }
        return jwtProvider.generateTokens(userEntity.getId());
    }
}
