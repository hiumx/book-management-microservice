package com.hiumx.profile.controller;

import com.hiumx.profile.dto.response.ApiResponse;
import com.hiumx.profile.dto.response.UserProfileResponse;
import com.hiumx.profile.service.UserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users")
@Slf4j
public class UserProfileController {
    UserProfileService userProfileService;

    @GetMapping("{id}")
    UserProfileResponse getProfile(@PathVariable("id") String profileId) {
        return userProfileService.getProfile(profileId);
    }

    @GetMapping
    ApiResponse<List<UserProfileResponse>> getAllUsers() {
        return ApiResponse.<List<UserProfileResponse>>builder()
                .code(1000)
                .message("Get all user profile successfully")
                .result(userProfileService.getAllUsers())
                .build();
    }
}
