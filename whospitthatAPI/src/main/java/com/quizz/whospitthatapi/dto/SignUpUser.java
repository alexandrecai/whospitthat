package com.quizz.whospitthatapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpUser {

    private String name;
    private String email;
    private String password;
    private String pictureUrl;
}
