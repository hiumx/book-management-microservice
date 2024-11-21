package com.hiumx.profile.controller;

import org.springframework.web.bind.annotation.*;

import com.hiumx.profile.dto.request.UserProfileCreationRequest;
import com.hiumx.profile.dto.response.UserProfileResponse;
import com.hiumx.profile.service.UserProfileService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/internal/users")
@Slf4j
public class InternalUserProfileController {
    UserProfileService userProfileService;

    @PostMapping
    UserProfileResponse createProfile(@RequestBody UserProfileCreationRequest request) {
        log.info("User info: " + request.toString());
        return userProfileService.createProfile(request);
    }

    @GetMapping("/{userId}")
    UserProfileResponse getProfileByUserId(@PathVariable String userId) {
        return userProfileService.getProfileByUserId(userId);
    }
}
