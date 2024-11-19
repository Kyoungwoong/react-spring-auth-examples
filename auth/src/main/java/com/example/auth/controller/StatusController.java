package com.example.auth.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/status")
@Tags(value = @Tag(name = "StatusController", description = "Retrieve any status"))
public class StatusController {

    @GetMapping
    public ResponseEntity<?> serverStatusCheck() {
        log.info("this server is health");
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/all")
    public ResponseEntity<?> anyOneCanAccess() {
        log.info("all people can access");
        return ResponseEntity.ok("all people can access");
    }
}