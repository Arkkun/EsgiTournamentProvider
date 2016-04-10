package com.esgi.account.authentication;

import lombok.*;

/**
 * Created by Andreï on 11/04/2016.
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticatedRequestBody<T> {
    private T body;
    private String token;
}
