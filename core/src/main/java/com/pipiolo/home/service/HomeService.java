package com.pipiolo.home.service;

import com.pipiolo.home.constant.HouseType;
import com.pipiolo.home.constant.SubscriptionType;
import com.pipiolo.home.domain.Home;
import com.pipiolo.home.dto.HomeRequest;
import com.pipiolo.home.dto.HomeResponse;
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

    private final LogService logService;
    private final HomeRepository homeRepository;

    @Transactional
    public HomeResponse upsert(HomeRequest request) {
        Home home = homeRepository.findByNoticeId(request.noticeId())
                .orElseGet(request::toEntity);

        home.update(request);
        return HomeResponse.from(homeRepository.save(home));
    }

    @Transactional
    public void insert(HomeRequest request) {
        if (homeRepository.findByNoticeId(request.noticeId()).isEmpty()) {
            HomeResponse.from(homeRepository.save(request.toEntity()));

            logService.info(this.getClass().getName(),
                    request.toEntity().toString() + "is saved.");
        }
    }

    @Transactional(readOnly = true)
    public List<HomeResponse> getHomes(Pageable pageable) {
        return homeRepository.findAll(pageable)
                .stream().map(HomeResponse::from).toList();
    }

    @Transactional(readOnly = true)
    public Page<HomeResponse> findHomeBySearchParams(
            String houseName,
            String region,
            SubscriptionType subscriptionType,
            HouseType houseType,
            Pageable pageable
    ) {
        return homeRepository.findHomeBySearchParams(
                houseName,
                region,
                subscriptionType,
                houseType,
                pageable
        );
    }

    @Transactional(readOnly = true)
    public List<HomeResponse> findHomesNoOffset(
            Long homeId,
            int pageSize
    ) {
        return homeRepository.findHomesNoOffset(
                homeId,
                pageSize
        );
    }
}

