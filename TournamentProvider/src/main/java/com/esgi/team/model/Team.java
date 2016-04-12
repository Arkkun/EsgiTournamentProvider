package com.esgi.team.model;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Kevin DHORNE on 4/5/2016.
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name= "TEAM")
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id_team;

    @Column(name = "id_account_owner")
    @ManyToOne
    //@JoinColum(name = "id_account")
    private int id_account_owner;

    @Column(name = "name")
    private int name;

    @Column(name = "tag")
    private String tag;
}
