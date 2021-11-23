package com.pipiolo.home.service;

import com.pipiolo.home.dto.HomeDto;
import com.pipiolo.home.dto.HomeRequest;
import org.springframework.stereotype.Service;

@Service
public class HomeRequestService {

    public HomeRequest from(HomeDto dto) {
        return dto.toRequest();
    }
}
