package com.esgi.match;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Andreï on 16/04/2016.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "TEAM")
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private int id;
}