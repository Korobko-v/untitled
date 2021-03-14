package org.levelup.trello.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "team_member")
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TeamMember {
    @Id
    @Column(name = "team_id")
    private Integer teamId;
    @Id
    @Column(name = "user_id")
    private Integer userId;

}
