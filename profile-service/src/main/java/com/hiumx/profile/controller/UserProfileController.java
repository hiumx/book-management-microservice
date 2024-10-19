package com.hiumx.profile.controller;

import com.hiumx.profile.dto.request.UserProfileCreationRequest;
import com.hiumx.profile.dto.response.UserProfileResponse;
import com.hiumx.profile.service.UserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users")
@Slf4j
public class UserProfileController {
    UserProfileService userProfileService;

    @PostMapping
    UserProfileResponse createProfile(@RequestBody  UserProfileCreationRequest request) {
        log.info("User info: " + request.toString());
        return userProfileService.createProfile(request);
    }

    @GetMapping("{id}")
    UserProfileResponse getProfile(@PathVariable("id") String profileId) {
        return userProfileService.getProfile(profileId);
    }
}
