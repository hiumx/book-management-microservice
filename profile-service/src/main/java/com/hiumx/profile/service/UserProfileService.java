package com.hiumx.profile.service;

import com.hiumx.profile.dto.request.UserProfileCreationRequest;
import com.hiumx.profile.dto.response.UserProfileResponse;

import java.util.List;

public interface UserProfileService {
    UserProfileResponse createProfile(UserProfileCreationRequest request);
    UserProfileResponse getProfile(String profileId);
    List<UserProfileResponse> getAllUsers();
}
