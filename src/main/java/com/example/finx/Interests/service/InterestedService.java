package com.example.finx.Interests.service;

import com.example.finx.Interests.dto.InterestsDto;
import com.example.finx.Interests.entity.InterestedEntity;
import com.example.finx.Interests.entity.UserInterestedEntity;
import com.example.finx.Interests.repository.InterestedRepository;
import com.example.finx.Interests.repository.UserInterestedRepository;
import com.example.finx.user.entity.UserEntity;
import com.example.finx.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InterestedService {

    private final UserRepository userRepository;
    private final InterestedRepository interestedRepository;
    private final UserInterestedRepository userInterestedRepository;

    public void save(InterestsDto interestsDto){
        // 현재 인증 된 사용자 정보 가져오기
        String id= SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity=userRepository.findById(Long.valueOf(id)).get();


        // for 문 돌면서 USERInterested를 저장
        interestsDto.getInterests().forEach(interest -> {
                InterestedEntity interestedEntity=interestedRepository.findByInterest(interest).get()


                UserInterestedEntity userInterestedEntity = UserInterestedEntity.builder()
                        .user(userEntity)
                        .interestedEntity(interestedEntity)
                        .build();
                userInterestedRepository.save(userInterestedEntity);
        });
    }
}
