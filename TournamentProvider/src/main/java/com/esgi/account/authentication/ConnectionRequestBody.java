package com.esgi.account.authentication;

import lombok.*;

/**
 * Created by Andreï on 10/04/2016.
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConnectionRequestBody {
    private String login;
    private String password;
}
