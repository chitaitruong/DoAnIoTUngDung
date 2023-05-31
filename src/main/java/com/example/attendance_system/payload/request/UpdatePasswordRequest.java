package com.example.attendance_system.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordRequest {
    private String email;
    private String password;
}
