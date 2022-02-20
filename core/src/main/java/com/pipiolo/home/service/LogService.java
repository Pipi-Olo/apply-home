package com.pipiolo.home.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogService {

    public void error(String message) {
        log.error(message);
    }

    public void warn(String message) {
        log.warn(message);
    }

    public void info(String message) {
        log.info(message);
    }

    public void debug(String message) {
        log.debug(message);
    }

}
