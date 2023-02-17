package com.go.blog.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
    private int id;
    private String username;
    private String password;
    private String email;

}
