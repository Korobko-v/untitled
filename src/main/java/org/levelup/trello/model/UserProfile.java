package org.levelup.trello.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@Table(name = "user_profile")
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    private Date birthDate;
}
