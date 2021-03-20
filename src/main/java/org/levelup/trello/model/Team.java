package org.levelup.trello.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "team")
@ToString(exclude = "users")
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToMany(mappedBy = "teams", cascade = CascadeType.ALL)
    private Collection<User> users;
}
