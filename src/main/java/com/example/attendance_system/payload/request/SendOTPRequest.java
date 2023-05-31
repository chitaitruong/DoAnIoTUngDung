package com.example.attendance_system.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendOTPRequest {
    private String email;
    private String info;
}
