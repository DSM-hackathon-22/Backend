package com.example.finx.user.service;

import com.example.finx.user.dto.UserDto;
import com.example.finx.user.entity.UserEntity;
import com.example.finx.user.jwt.JwtProvider;
import com.example.finx.user.jwt.TokenResponse;
import com.example.finx.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Getter
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public TokenResponse signUp(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Long id = userRepository.save(userDto.toEntity()).getId();
        return jwtProvider.generateTokens(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findById(Long.valueOf(username)).get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    public TokenResponse login(UserDto userDto){
        UserEntity userEntity = userRepository.findByusername(userDto.getUsername()).get();
        if(!passwordEncoder.matches(userDto.getPassword(), userEntity.getPassword())) {
            throw new RuntimeException();
        }
        return jwtProvider.generateTokens(userEntity.getId());
    }
}
