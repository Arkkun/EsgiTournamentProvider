package com.esgi.team.model;

import com.esgi.account.model.AccountPublic;
import lombok.*;


/**
 * Created by Kevin DHORNE on 17/04/2016.
 */
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PublicMembership {

    private int id;

    private Team team;

    private AccountPublic account;

    private boolean isOwner;

    private String status;

    public PublicMembership(Membership membership){
        this.id = membership.getId();
        this.team = membership.getTeam();
        this.isOwner = membership.isOwner();
        this.status = membership.getStatus();

        this.account = new AccountPublic(membership.getAccount());
    }
}
