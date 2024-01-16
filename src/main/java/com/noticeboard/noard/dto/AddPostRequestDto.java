package com.noticeboard.noard.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class AddPostRequestDto {
    private String title;
    private String content;
    private List<String> imageUrl;
}
