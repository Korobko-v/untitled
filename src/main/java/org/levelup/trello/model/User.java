package org.levelup.trello.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "users")
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class User {
    public User(String name, String login, String email) {
        this.name = name;
        this.login = login;
        this.email = email;
    }

    @Id // primary key из таблицы users
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    private String login;
    private String email;

    @OneToMany(mappedBy = "owner")
    private Collection<Board> boards;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private UserCredentials credentials;

    @ManyToMany()
    @JoinTable(
            name = "team_member",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private Collection<Team> teams;

    public User(Integer id, String name, String login, String email) {
        this.id = id;
        this.name = name;
        this.login = login;
        this.email = email;
        this.boards = new ArrayList<>();
        this.teams = new ArrayList<>();
    }
}
