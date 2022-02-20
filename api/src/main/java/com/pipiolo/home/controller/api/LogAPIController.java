package com.pipiolo.home.controller.api;

import com.pipiolo.home.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class LogAPIController {

    private final LogService logService;

    @GetMapping("/log/error")
    public void logError(String message) {
        logService.error(message);
    }

    @GetMapping("/log/warn")
    public void logWarn(String message) {
        logService.warn(message);
    }

    @GetMapping("/log/info")
    public void logInfo(String message) {
        logService.info(message);
    }

    @GetMapping("/log/debug")
    public void logDebug(String message) {
        logService.debug(message);
    }

}
