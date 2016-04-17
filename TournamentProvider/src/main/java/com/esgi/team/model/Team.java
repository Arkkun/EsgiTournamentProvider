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
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "tag")
    private String tag;

    @Column(name = "is_deleted")
    private boolean isDeleted;

}
