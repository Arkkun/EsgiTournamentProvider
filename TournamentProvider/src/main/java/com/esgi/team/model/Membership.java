package com.esgi.team.model;

import com.esgi.account.model.Account;
import lombok.*;

import javax.persistence.*;

/**
 * Created by 212416808 on 4/13/2016.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "MEMBERSHIP")
public class Membership  {

    @Id
    @GeneratedValue
    //@ManyToMany(targetEntity = Account.class)
    //@JoinColumn(name = "id_account", table = "ACCOUNT", referencedColumnName = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "team")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "account")
    private Account account;

    @Column(name = "is_owner")
    private boolean isOwner;

    @Column(name = "status")
    private String status;
}
