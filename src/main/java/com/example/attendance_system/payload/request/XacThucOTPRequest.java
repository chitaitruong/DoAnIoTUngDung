package com.example.attendance_system.payload.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class XacThucOTPRequest {
    private String email;
    private String otp;
}
