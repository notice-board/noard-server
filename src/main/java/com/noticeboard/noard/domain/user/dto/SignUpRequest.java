package com.noticeboard.noard.domain.user.dto;

import lombok.Getter;

@Getter
public class SignUpRequest {

    private String nickname;

    private String email;

    private String password;
}
