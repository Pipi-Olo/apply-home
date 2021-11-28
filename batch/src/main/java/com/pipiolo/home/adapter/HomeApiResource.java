package com.pipiolo.home.adapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;

@Slf4j
@Component
public class HomeApiResource {

    @Value("${external.home-api.path}")
    private String path;

    @Value("${external.home-api.service-key}")
    private String serviceKey;

    public Resource getResource(String startMonth, String endMonth) {
        String url = String.format("%s?serviceKey=%s&startmonth=%s&endmonth=%s",
                path, serviceKey, startMonth, endMonth);

        log.info("Resource URL = " + url);

        try {
            return new UrlResource(url);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Failed to created UrlResource.");
        }
    }
}
