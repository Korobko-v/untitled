package org.levelup.trello.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user_credentials")
@ToString(exclude = "user")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentials {
    @Id
    @Column(name = "user_id")
    @PrimaryKeyJoinColumn
    private int userId;
    private String password;

    @OneToOne
    @MapsId
    private User user;
}
