package com.hiumx.identity.service;

import java.util.HashSet;
import java.util.List;

import com.hiumx.event.dto.NotificationEvent;
import com.hiumx.identity.dto.request.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hiumx.identity.constant.PredefinedRole;
import com.hiumx.identity.dto.response.UserProfileResponse;
import com.hiumx.identity.dto.response.UserResponse;
import com.hiumx.identity.entity.Role;
import com.hiumx.identity.entity.User;
import com.hiumx.identity.exception.AppException;
import com.hiumx.identity.exception.ErrorCode;
import com.hiumx.identity.mapper.UserMapper;
import com.hiumx.identity.mapper.UserProfileMapper;
import com.hiumx.identity.repository.RoleRepository;
import com.hiumx.identity.repository.UserRepository;
import com.hiumx.identity.repository.httpclient.ProfileClient;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    ProfileClient profileClient;
    UserProfileMapper userProfileMapper;
    KafkaTemplate<String, Object> kafkaTemplate;

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<Role> roles = new HashSet<>();
        roleRepository.findById(PredefinedRole.USER_ROLE).ifPresent(roles::add);

        user.setRoles(roles);
        user = userRepository.save(user);

        UserProfileCreationRequest profileRequest = userProfileMapper.toProfileCreationRequest(request);
        profileRequest.setUserId(user.getId());

        UserProfileResponse profileResponse = profileClient.createUserProfile(profileRequest);

        NotificationEvent notificationEvent = NotificationEvent.builder()
                .chanel("EMAIL")
                .receiver(request.getEmail())
                .subject("Welcome to my book website")
                .content("<p>Welcome <b style='color: blue;'>"+ request.getUsername() +"</b>. Thanks for registration to my website.</p>")
                .build();

        kafkaTemplate.send("notification-topic", notificationEvent);

        UserResponse userResponse = userMapper.toUserResponse(user);
        userResponse.setFirstName(profileResponse.getFirstName());
        userResponse.setLastName(profileResponse.getLastName());
        userResponse.setDob(profileResponse.getDob());

        return userResponse;
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers() {
        log.info("In method get Users");
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse getUser(String id) {
        return userMapper.toUserResponse(
                userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }
}
