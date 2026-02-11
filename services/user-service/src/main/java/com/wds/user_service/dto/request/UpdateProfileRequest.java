package com.wds.user_service.dto.request;

import lombok.Data;

@Data
public class UpdateProfileRequest {

    private String firstName;
    private String lastName;
    private String avatarUrl;
}
