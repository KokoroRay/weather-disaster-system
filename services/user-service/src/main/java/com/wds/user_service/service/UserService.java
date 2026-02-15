package com.wds.user_service.service;


import com.wds.user_service.dto.request.ChangePasswordRequest;
import com.wds.user_service.dto.request.UpdateProfileRequest;
import com.wds.user_service.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse getMyProfile();
    UserResponse updateProfile (UpdateProfileRequest request);
    void changePassword(ChangePasswordRequest request);
    List<UserResponse> getAllUsers();
    void lockUser(Long userId, boolean isLocked);

}