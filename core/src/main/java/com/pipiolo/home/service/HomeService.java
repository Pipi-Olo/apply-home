package com.pipiolo.home.service;

import com.pipiolo.home.constant.HouseType;
import com.pipiolo.home.constant.SubscriptionType;
import com.pipiolo.home.domain.Home;
import com.pipiolo.home.dto.HomeRequest;
import com.pipiolo.home.repository.HomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HomeService {

    private final HomeRepository homeRepository;

    @Transactional
    public void upsert(HomeRequest request) {
        Home home = homeRepository.findByNoticeId(request.getNoticeId())
                .orElseGet(() -> Home.from(request));

        home.update(request);
        homeRepository.save(home);
    }

    @Transactional(readOnly = true)
    public List<Home> getHomes() {
        return homeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Home> findHomeBySearchParams(
            String region,
            SubscriptionType subscriptionType,
            HouseType houseType,
            Pageable pageable
    ) {
        return homeRepository.findHomeBySearchParams(
                region,
                subscriptionType,
                houseType,
                pageable
        );
    }
}

