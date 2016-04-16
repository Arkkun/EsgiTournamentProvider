package com.esgi.account.model;

import lombok.*;

/**
 * Created by Andreï on 15/04/2016.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AccountPublic {

    private int id;
    private String login;
    private boolean isAdmin;

    public AccountPublic( Account account )
    {
        this.id = account.getId();
        this.login = account.getLogin();
        this.isAdmin = account.isAdmin();
    }
}
