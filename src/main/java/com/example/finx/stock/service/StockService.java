package com.example.finx.stock;

import com.example.finx.user.entity.UserEntity;
import com.example.finx.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StockService {
    private final StockClient stockClient;
    private final StockRepository stockRepository;
    private final UserRepository userRepository;
    

    public Object getStock() {
        // 유저의 관심 분야 가져오기
        String id= SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userRepository.findById(Long.valueOf(id)).get();



        // 관심분야에서 랜덤으로 종목 뽑기
        return stockClient.getStock("TIME_SERIES_DAILY", )
    }
}
