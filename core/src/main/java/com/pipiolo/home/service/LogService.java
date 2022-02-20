package com.pipiolo.home.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogService {

    public void error(String className, String message) {
        log.error("[" + className + "] " + message);
    }

    public void warn(String className, String message) {
        log.warn("[" + className + "] " + message);
    }

    public void info(String className, String message) {
        log.info("[" + className + "] " + message);
    }

    public void debug(String className, String message) {
        log.debug("[" + className + "] " + message);
    }

}
