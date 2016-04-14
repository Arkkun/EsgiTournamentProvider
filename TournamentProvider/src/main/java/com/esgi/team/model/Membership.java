package com.esgi.team.model;

import com.esgi.account.model.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by 212416808 on 4/13/2016.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MEMBERSHIP")
public class Membership  {

    @Id
    @ManyToMany(targetEntity = Account.class)
    @JoinColumn(name = "id_account", table = "ACCOUNT", referencedColumnName = "id")
    private int id_account;

    @Id
    @ManyToMany(targetEntity = Team.class)
    @JoinColumn(name = "id_team", table = "TEAM", referencedColumnName = "id")
    private int id_team;

    private boolean isOwner;

    @ManyToOne(targetEntity = MembershipStatus.class)
    private int id_status;
}
