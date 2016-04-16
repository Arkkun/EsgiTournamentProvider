package com.esgi.team.model;

import javax.persistence.*;

/**
 * Created by 212416808 on 4/13/2016.
 */
@Entity
@Table(name = "MEMBERSHIPSTATUS")
public class MembershipStatus {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "status")
    private String status;
}
