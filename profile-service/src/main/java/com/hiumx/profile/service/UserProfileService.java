package com.hiumx.profile.service;

import java.util.List;

import com.hiumx.profile.dto.request.UserProfileCreationRequest;
import com.hiumx.profile.dto.response.UserProfileResponse;

public interface UserProfileService {
    UserProfileResponse createProfile(UserProfileCreationRequest request);
    UserProfileResponse getProfile(String profileId);
    List<UserProfileResponse> getAllUsers();
    UserProfileResponse getProfileByUserId(String userId);
}
